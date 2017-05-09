package ru.splat.messages.uptm.trmetadata.punter;

import ru.splat.messages.conventions.ServicesEnum;
import ru.splat.messages.conventions.TaskTypesEnum;
import ru.splat.messages.proxyup.ticket.TicketInfo;
import ru.splat.messages.uptm.trmetadata.LocalTask;

/**
 * Created by Дмитрий on 17.12.2016.
 */
public class AddPunterLimitsTask extends LocalTask {
    private final Integer punterId;
    private final ServicesEnum service = ServicesEnum.PunterService;

    public AddPunterLimitsTask(Integer _punterId, Long time) {
        super(time);
        this.punterId = _punterId;
    }

    @Override
    public TaskTypesEnum getType() {
        return TaskTypesEnum.ADD_PUNTER_LIMITS;
    }

    @Override
    public ServicesEnum getService() {
        return service;
    }

    public Integer getPunterId() {
        return punterId;
    }

    public static AddPunterLimitsTask create(TicketInfo ticketInfo) {
        return new AddPunterLimitsTask(ticketInfo.getUserId(), System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "AddPunterLimitsTask{" +
                "punterId=" + punterId +
                ", service=" + service +
                '}';
    }
}
