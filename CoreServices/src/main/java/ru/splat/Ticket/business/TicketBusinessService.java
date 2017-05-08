package ru.splat.Ticket.business;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ru.splat.Ticket.feautures.TicketInfo;
import ru.splat.Ticket.repository.BetRepository;
import ru.splat.facade.business.BusinessService;
import ru.splat.kafka.feautures.TransactionResult;
import ru.splat.messages.Response;
import ru.splat.messages.conventions.ServiceResult;
import ru.splat.messages.conventions.TaskTypesEnum;
import java.util.*;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Daniil Kochubey 19.04.2017
 */
public class TicketBusinessService implements BusinessService<TicketInfo>
{

    private Logger LOGGER = getLogger(TicketBusinessService.class);

    @Autowired
    BetRepository betRepository;

    @Override
    public List<TransactionResult> processTransactions(List<TicketInfo> transactionRequests)
    {

        LOGGER.info("Start processTransaction");

        Map<Integer, List<TicketInfo>> localTaskComplex = new TreeMap<>(Collections.reverseOrder());

        for (TicketInfo ticketInfo : transactionRequests)
        {
            if (!localTaskComplex.containsKey(ticketInfo.getLocalTask()))
            {
                localTaskComplex.put(ticketInfo.getLocalTask(), new ArrayList<>());
            }
            localTaskComplex.get(ticketInfo.getLocalTask()).add(ticketInfo);
        }

        List<TransactionResult> results = new ArrayList<>();
        for (Map.Entry<Integer, List<TicketInfo>> entry : localTaskComplex.entrySet())
        {
            if (entry.getKey() == TaskTypesEnum.ADD_BET.ordinal())
                    results.addAll(addBet(entry.getValue()));
            else
            if (entry.getKey() == TaskTypesEnum.CANCEL_BET.ordinal())
                    results.addAll(fixBet(entry.getValue(),"FAILED"));
            else
            if (entry.getKey() == TaskTypesEnum.FIX_BET.ordinal())
                results.addAll(fixBet(entry.getValue(),"SUCESSEFULL"));

        }
        LOGGER.info("Stop processTransaction");
        return results;
    }

    private Set<TransactionResult> addBet(List<TicketInfo> ticketInfoList)
    {
        LOGGER.info("Start Add Bet");
        int sequence = betRepository.getCurrentSequenceVal();
        betRepository.addBet(ticketInfoList);
        LOGGER.info("Add bet array: ");
        LOGGER.info(Arrays.toString(ticketInfoList.toArray()));
        Set<TransactionResult> transactionalResult = new HashSet<>(ticketInfoList.size());
        for (TicketInfo ticketInfo : ticketInfoList)
        {
            sequence++;

            transactionalResult.add(new TransactionResult(
                    ticketInfo.getTransactionId(),
                    Response.ServiceResponse.newBuilder().addAllServices(ticketInfo.getServices())
                            .setResult(ServiceResult.CONFIRMED.ordinal()).setLongAttachment(sequence).build()

            ));
        }
        LOGGER.info("Stop Add Bet");
        return transactionalResult;
    }

    private Set<TransactionResult> fixBet(List<TicketInfo> ticketInfoList, String state)
    {
        LOGGER.info("Start fix state = " + state);
        LOGGER.info("Array for fix state = " + state + " : ");
        LOGGER.info(Arrays.toString(ticketInfoList.toArray()));
        betRepository.fixBetState(ticketInfoList,state);
        LOGGER.info("Stop fix state = " + state);
        return ticketInfoList.stream().map(map -> new TransactionResult(
                map.getTransactionId(),
                Response.ServiceResponse.newBuilder()
                        .setResult(ServiceResult.CONFIRMED.ordinal()).addAllServices(map.getServices()).build()
        )).collect(Collectors.toSet());
    }

    @Override
    public void commitBusinessService() {

    }

    @Override
    public void rollbackBusinessSerivce() {

    }
}
