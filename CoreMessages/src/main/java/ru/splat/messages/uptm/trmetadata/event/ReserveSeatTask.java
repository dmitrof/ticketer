package ru.splat.messages.uptm.trmetadata.event;

import ru.splat.messages.conventions.ServicesEnum;
import ru.splat.messages.conventions.TaskTypesEnum;
import ru.splat.messages.proxyup.ticket.TicketInfo;
import ru.splat.messages.uptm.trmetadata.LocalTask;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Дмитрий on 17.12.2016.
 */
public class ReserveSeatTask extends LocalTask {
    private final Set<Integer> selections;
    private final ServicesEnum service = ServicesEnum.EventService;

    public ReserveSeatTask(
            Set<Integer> selections, Long time) {
        super(time);
        this.selections = selections;
    }


    @Override
    public TaskTypesEnum getType() {
        return TaskTypesEnum.ADD_SELECTION_LIMIT;
    }

    public ServicesEnum getService() {
        return service;
    }

    public Set<Integer> getSelections() {
        return selections;
    }


    public static ReserveSeatTask create(TicketInfo ticketInfo) {
        return new ReserveSeatTask(new HashSet<>(ticketInfo.getSelectionsId()),
                System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "ReserveSeatTask{" +
                "selections=" + selections +
                ", service=" + service +
                "} ";
    }
}
