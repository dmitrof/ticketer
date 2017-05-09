package ru.splat.task;

import org.apache.log4j.Logger;
import ru.splat.messages.proxyup.ticket.NewResponseClone;
import ru.splat.messages.proxyup.check.CheckResult;
import ru.splat.service.StateCheckService;
import java.util.Iterator;
import java.util.concurrent.*;

/**
 * Created by Дмитрий & Ильнар on 10.02.2017.
 */
public class StateRequestTask implements Runnable{
    private ConcurrentSkipListSet<NewResponseClone> trIdSet;
    private static Logger LOGGER = Logger.getLogger(StateRequestTask.class);

    public StateRequestTask(ConcurrentSkipListSet<NewResponseClone> trIdSet)
    {
        this.trIdSet = trIdSet;
    }

    //TODO добавить логирование

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {    //настроить частоту обращений

            Iterator<NewResponseClone> iterator = trIdSet.iterator();
            while (iterator.hasNext() && !Thread.currentThread().isInterrupted())
            {
                NewResponseClone response = iterator.next();

                LOGGER.info(response.toString());
                StateCheckService stateCheckService = new StateCheckService(response);

                int state = 0;

                try {
                    state = stateCheckService.doRequest();
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

                if (state == CheckResult.ACCEPTED.ordinal()) {LOGGER.info("TrState for " + response.getTransactionId() + ": ACCEPTED");
                    iterator.remove();
                } else if (state == CheckResult.REJECTED.ordinal()) {
                    LOGGER.info("TrState for " + response.getTransactionId() + ": REJECTED");
                    iterator.remove();
                } else if (state == CheckResult.PENDING.ordinal())
                    LOGGER.info("TrState for " + response.getTransactionId() + ": PENDING");
                else LOGGER.info("TrState for " + response.getTransactionId() + ": UNDEFINED");
            }

            try {
                Thread.currentThread().sleep(100l);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }
    }
}
