package ru.splat.messages.uptm.trmetadata.ticket;

import ru.splat.messages.conventions.ServicesEnum;
import ru.splat.messages.conventions.TaskTypesEnum;
import ru.splat.messages.proxyup.ticket.TicketInfo;
import ru.splat.messages.uptm.trmetadata.LocalTask;

/**
 * Created by Дмитрий on 04.02.2017.
 */
public class CancelTicketOrderTask extends LocalTask {
    private final Long betId;

    public Long getBetId() {
        return betId;
    }

    public CancelTicketOrderTask(Long betId, Long time) {
        super(time);
        this.betId = betId;
    }

    @Override
    public TaskTypesEnum getType() {
        return TaskTypesEnum.CANCEL_BET;
    }

    @Override
    public ServicesEnum getService() {
        return ServicesEnum.BetService;
    }

    public static CancelTicketOrderTask create(TicketInfo ticketInfo) {
        return new CancelTicketOrderTask(ticketInfo.getBetId(), System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "CancelTicketOrderTask{" +
                "betId=" + betId +
                "} ";
    }
}
