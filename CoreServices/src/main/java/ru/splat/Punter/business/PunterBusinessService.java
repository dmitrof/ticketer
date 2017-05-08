package ru.splat.Punter.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import ru.splat.Event.feautures.EventInfo;
import ru.splat.facade.feautures.Proxy;
import ru.splat.facade.feautures.Limit;
import ru.splat.facade.service.LimitService;
import ru.splat.facade.util.JmxUtil;
import ru.splat.kafka.feautures.TransactionResult;
import ru.splat.Punter.repository.PunterRepository;
import ru.splat.Punter.feautures.PunterInfo;
import ru.splat.facade.business.BusinessService;
import ru.splat.messages.Response;
import ru.splat.messages.conventions.ServiceResult;
import ru.splat.messages.conventions.TaskTypesEnum;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@ManagedResource(objectName = "Punter Limit Window:name=Resource")
public class PunterBusinessService implements BusinessService<PunterInfo>, LimitService<PunterInfo>
{

    @Autowired
    private PunterRepository punterRepository;

    private long lastDeleteTime;

    private Map<Integer,Long> commitAddDequeMap;

    private List<Integer> commitCancelDequeList;

    private ConcurrentMap<Integer,Proxy> dequeMap;

    public PunterBusinessService()
    {
        dequeMap = new ConcurrentHashMap<>();
        lastDeleteTime = System.currentTimeMillis();
    }

    @ManagedOperation
    public String getPunterFreeLimitFrom(int id)
    {
        return JmxUtil.getLimit(id, punterRepository, dequeMap);
    }

    @ManagedOperation
    public synchronized void setPunterLimit(int id, int lim, int limitTime, String timeType)
    {
        if (timeType.equals("sec")) { limitTime *= 1000;}
        else if (timeType.equals("min")) {limitTime *= 60*1000;}
        else return;
        JmxUtil.set(new Limit(id, lim,limitTime),punterRepository,dequeMap);
    }

    @Override
    public ConcurrentMap<Integer, Proxy> getDequeMap() {
        return dequeMap;
    }

    @Override
    public Set<Integer> convertToSet(List<PunterInfo> punterInfoList)
    {
        return new HashSet<Integer>(punterInfoList.stream().map(punterInfo -> punterInfo.getId()).collect(Collectors.toSet()));
    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    private List<TransactionResult> addPunterLimits(List<PunterInfo> punterInfoList)
    {
        long currentTime = System.currentTimeMillis();

        List<TransactionResult> result = new ArrayList<>(punterInfoList.size());
        LOGGER.info(getClassName() + " Start Add Punter limits: ");
        LOGGER.info(getClassName() + " " + Arrays.toString(punterInfoList.toArray()));

        addInDeque(punterInfoList, punterRepository);

        for (PunterInfo punterInfo: punterInfoList) {

            LOGGER.info(getClassName() + " Punter id = " + punterInfo.getId());

            Proxy proxy = dequeMap.get(punterInfo.getId());

            boolean answer = false;
            if (proxy != null)
            if (currentTime - punterInfo.getTime() <= proxy.getLimitTime())
            {
                Deque<Long> deque = proxy.getDeque();
                while (!deque.isEmpty() && currentTime - deque.getFirst() > proxy.getLimitTime())
                    deque.pollFirst();

                if (deque.isEmpty() || deque.size() < proxy.getLimit())
                {
                    answer = true;
                  //  deque.addLast(punterInfo.getTime());
                    commitAddDequeMap.put(punterInfo.getId(),punterInfo.getTime());
                }
            }
            if (answer)
            {
                LOGGER.info(getClassName() + " Reserve limit");
            }
            else
            {
                LOGGER.info(getClassName() + " Don't reserve limit");
            }
            result.add(new TransactionResult(punterInfo.getTransactionId(),
                    Response.ServiceResponse.newBuilder().addAllServices(punterInfo.getServices())
                    .setResult(answer?ServiceResult.CONFIRMED.ordinal():ServiceResult.DENIED.ordinal()).build()));
        }
        LOGGER.info(getClassName() + " Stop Add Punter limits: ");
        return result;
    }

    private List<TransactionResult> cancelPunterLimits(List<PunterInfo> punterInfoList)
    {

        LOGGER.info("Start cancel punter limits");

        List<TransactionResult> result = new ArrayList<>();

        long currentTime = System.currentTimeMillis();

        for (PunterInfo p : punterInfoList)
        {

            LOGGER.info("Punter id = " + p.getId());
            if (dequeMap.containsKey(p.getId()))
            {
                Proxy proxy = dequeMap.get(p.getId());
                Deque<Long> deque = proxy.getDeque();
                while (!deque.isEmpty() && currentTime - deque.getFirst() > proxy.getLimitTime())
                    deque.pollFirst();

                if (!deque.isEmpty())
                {
                   commitCancelDequeList.add(p.getId());
                    LOGGER.info(getClassName() + " Cancel Reserve limit");
                    // deque.pollFirst();
                }
                else
                {
                    LOGGER.info(getClassName() + " Don't Cancel Reserve limit");
                }
            }
            else
            {
                LOGGER.info(getClassName() + " Map hasnt same id");
            }

            result.add(new TransactionResult(
                    p.getTransactionId(),
                    Response.ServiceResponse.newBuilder().addAllServices(p.getServices())
                            .setResult(ServiceResult.CONFIRMED.ordinal()).build()
            ));
        }

        LOGGER.info("Stop cancel punter limits");
        return result;
    }

    @Override
    public List<TransactionResult> processTransactions(List<PunterInfo> transactionRequests)
    {
        LOGGER.info("Start processTransaction");


       lastDeleteTime = scanDeque(lastDeleteTime);

        Map<Integer, Set<PunterInfo>> localTaskComplex = new TreeMap<>(Collections.reverseOrder());

        for (PunterInfo punterInfo : transactionRequests)
        {
            if (!localTaskComplex.containsKey(punterInfo.getLocalTask()))
            {
                localTaskComplex.put(punterInfo.getLocalTask(), new TreeSet<PunterInfo>(new Comparator<PunterInfo>() {

                    @Override
                    public int compare(PunterInfo o1, PunterInfo o2) {
                        return (o1.getTime() >= o2.getTime() ? -1 : 1);
                    }
                }));
            }
            localTaskComplex.get(punterInfo.getLocalTask()).add(punterInfo);
        }

        commitCancelDequeList = new ArrayList<>();
        commitAddDequeMap = new HashMap<>();

        List<TransactionResult> results = new ArrayList<>();
        for (Map.Entry<Integer, Set<PunterInfo>> entry : localTaskComplex.entrySet())
        {
           if (entry.getKey() == TaskTypesEnum.ADD_PUNTER_LIMITS.ordinal())
           {
               LOGGER.info(getClassName() +  " Reserve limit array: ");
               LOGGER.info(Arrays.toString(entry.getValue().toArray()));
               results.addAll(addPunterLimits(entry.getValue().stream().
                       collect(Collectors.toList())));
           }
            else if (entry.getKey() == TaskTypesEnum.CANCEL_PUNTER_LIMITS.ordinal())
            {
                LOGGER.info(getClassName() + " Cancel limit array: ");
               LOGGER.info(Arrays.toString(entry.getValue().toArray()));
               results.addAll(cancelPunterLimits(new ArrayList<PunterInfo>(entry.getValue())).stream().
                       collect(Collectors.toList()));
           }
        }
        LOGGER.info(getClassName() + " Stop processTransaction");
        return results;
    }

    @Override
    public void commitBusinessService()
    {
        if (commitCancelDequeList != null)
        {
            commitCancelDequeList.forEach(p ->
                {
                    Proxy proxy = dequeMap.get(p);
                    if (proxy != null)
                    {
                        Deque<Long> deque = proxy.getDeque();
                        if (deque != null)
                        {
                            deque.pollFirst();
                            LOGGER.info(getClassName() + " Delete from Deque for punter id = " + p);
                        }
                    }
                }
        );
            commitCancelDequeList = null;
        }

        if (commitAddDequeMap != null)
        {

            for (Map.Entry<Integer, Long> entry : commitAddDequeMap.entrySet())
            {
                Proxy proxy = dequeMap.get(entry.getKey());

                if (proxy != null)
                {
                    Deque<Long> deque = proxy.getDeque();
                    if (deque != null) {
                        deque.addLast(entry.getValue());
                        LOGGER.info(getClassName() + " Add in Deque for punter id = " + entry.getKey());
                    }
                }
            }
            commitAddDequeMap = null;
        }
    }

    @Override
    public void rollbackBusinessSerivce()
    {
        LOGGER.info(getClassName() + " Rollback");
        commitAddDequeMap = null;
        commitCancelDequeList = null;
    }
}
