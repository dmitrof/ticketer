package ru.splat.messages.uptm.trmetadata.event;

import ru.splat.messages.conventions.ServicesEnum;
import ru.splat.messages.conventions.TaskTypesEnum;
import ru.splat.messages.proxyup.ticket.TicketInfo;
import ru.splat.messages.uptm.trmetadata.LocalTask;

import java.util.Set;

/**
 * Created by Дмитрий on 04.02.2017.
 */
public class CancelReserveSeatTask extends LocalTask {
    private final Set<Integer> selections;
    private final ServicesEnum service = ServicesEnum.EventService;

    public CancelReserveSeatTask(
            Set<Integer> selections, Long time) {
        super(time);
        this.selections = selections;
    }


    @Override
    public TaskTypesEnum getType() {
        return TaskTypesEnum.CANCEL_SELECTION_LIMIT;
    }

    public ServicesEnum getService() {
        return service;
    }

    public Set<Integer> getSelections() {
        return selections;
    }

    public static CancelReserveSeatTask create(TicketInfo ticketInfo) {
        return new CancelReserveSeatTask(ticketInfo.getSelectionsId(), System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "CancelReserveSeatTask{" +
                "selections=" + selections +
                ", service=" + service +
                "} ";
    }
}
