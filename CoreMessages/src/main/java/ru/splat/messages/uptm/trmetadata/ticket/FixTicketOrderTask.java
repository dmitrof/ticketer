package ru.splat.messages.uptm.trmetadata.ticket;

import ru.splat.messages.conventions.ServicesEnum;
import ru.splat.messages.conventions.TaskTypesEnum;
import ru.splat.messages.proxyup.ticket.TicketInfo;
import ru.splat.messages.uptm.trmetadata.LocalTask;

/**
 * Created by Дмитрий on 04.02.2017.
 */
public class FixTicketOrderTask extends LocalTask {
    private final ServicesEnum service = ServicesEnum.BetService;
    private final Long betId;
    //конструктор второй фазы
    public FixTicketOrderTask(Long betId, Long time) {
        super(time);
        this.betId = betId;

    }

    public Long getBetId() {
        return betId;
    }

    @Override
    public TaskTypesEnum getType() {
        return TaskTypesEnum.FIX_BET;
    }

    @Override
    public ServicesEnum getService() {
        return service;
    }

    public static FixTicketOrderTask create(TicketInfo ticketInfo) {
        return new FixTicketOrderTask(ticketInfo.getBetId(), System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "FixTicketOrderTask{" +
                "service=" + service +
                ", betId=" + betId +
                "} ";
    }
}
