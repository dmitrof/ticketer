package ru.splat.messages.uptm.trmetadata.ticket;

import ru.splat.messages.conventions.ServicesEnum;
import ru.splat.messages.conventions.TaskTypesEnum;
import ru.splat.messages.proxyup.ticket.TicketInfo;
import ru.splat.messages.uptm.trmetadata.LocalTask;

import java.util.Set;

/**
 * Created by Дмитрий on 22.12.2016.
 */
//первая фаза по ставкам
public class AddTicketOrderTask extends LocalTask {
    private final Integer punterId;
    private final Set<TicketDetail> ticketDetails; //список возможных исходов

    public Integer getPunterId() {
        return punterId;
    }

    public Set<TicketDetail> getTicketDetails() {
        return ticketDetails;
    }

    //конструктор первой фазы
    public AddTicketOrderTask(Integer punterId, Set<TicketDetail> ticketDetails, Long time) {
        super(time);
        this.punterId = punterId;
        this.ticketDetails = ticketDetails;
    }


    @Override
    public TaskTypesEnum getType() {
        return TaskTypesEnum.ADD_BET;
    }

    @Override
    public ServicesEnum getService() {
        return ServicesEnum.BetService;
    }


    public static AddTicketOrderTask create(TicketInfo ticketInfo) {
        return new AddTicketOrderTask(ticketInfo.getUserId(),
                ticketInfo.getTicketDetails(),
                System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "AddTicketOrderTask{" +
                "punterId=" + punterId +
                ", ticketDetails=" + ticketDetails +
                "} ";
    }
}
