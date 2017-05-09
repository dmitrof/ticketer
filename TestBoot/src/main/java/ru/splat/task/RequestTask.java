package ru.splat.task;


import org.apache.log4j.Logger;
import ru.splat.messages.proxyup.ticket.NewResponseClone;
import ru.splat.service.BootService;

import java.util.concurrent.ConcurrentSkipListSet;


public class RequestTask implements Runnable {

    private long requestTimeout;
    private int punterId;
    private ConcurrentSkipListSet<NewResponseClone> trIdSet;
    private static Logger LOGGER = Logger.getLogger(RequestTask.class);

    public RequestTask(long requestTimeout, int punterId, ConcurrentSkipListSet<NewResponseClone> trIdSet) {
        this.requestTimeout = requestTimeout;
        this.trIdSet = trIdSet;
        this.punterId = punterId;
    }

    @Override
    public void run() {

        BootService bootService = new BootService(punterId);

        while (!Thread.currentThread().isInterrupted())
        {
            long timeStart = System.currentTimeMillis();
            long residual = 0;


            NewResponseClone newResponse = new NewResponseClone();
            try {
                newResponse = bootService.doRequest();
            }catch (InterruptedException ie){
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                LOGGER.error("High level",e);
            }
            LOGGER.info("Response from server: " + newResponse.toString());
            if (!newResponse.getActive()) trIdSet.add(newResponse);  //добавление нового id в сет
            else LOGGER.info("Another transaction is active for userId = " + newResponse.getUserId());

            residual = System.currentTimeMillis() - timeStart;


            if (residual < requestTimeout)
            {
                long freeTime = requestTimeout - residual;
                LOGGER.info("Sleep time: " + freeTime);
                try {
                    Thread.currentThread().sleep(freeTime);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
