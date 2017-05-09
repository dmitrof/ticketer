package ru.splat.tm.protobuf;

import com.google.protobuf.Message;
import ru.splat.messages.BetRequest;
import ru.splat.messages.BillingRequest;
import ru.splat.messages.EventRequest;
import ru.splat.messages.PunterRequest;
import ru.splat.messages.conventions.ServicesEnum;
import ru.splat.messages.uptm.trmetadata.LocalTask;
import ru.splat.messages.uptm.trmetadata.ticket.AddTicketOrderTask;
import ru.splat.messages.uptm.trmetadata.ticket.CancelTicketOrderTask;
import ru.splat.messages.uptm.trmetadata.ticket.FixTicketOrderTask;
import ru.splat.messages.uptm.trmetadata.billing.BillingWithdrawTask;
import ru.splat.messages.uptm.trmetadata.billing.CancelWithdrawTask;
import ru.splat.messages.uptm.trmetadata.event.ReserveSeatTask;
import ru.splat.messages.uptm.trmetadata.event.CancelReserveSeatTask;
import ru.splat.messages.uptm.trmetadata.punter.AddPunterLimitsTask;
import ru.splat.messages.uptm.trmetadata.punter.CancelPunterLimitsTask;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Дмитрий on 17.01.2017.
 */
//простая фабрика протобуфов
    //TO-DO: необходимо протестировать возможность добавления пустых полей (Optional<>)
    //Избавиться от привязки к классам
public  class ProtobufFactory{
    public ProtobufFactory() {
    }
    //подготовить мессаги для оптравки
    public static Message buildProtobuf(LocalTask localTask, Set<ServicesEnum> _services) throws IllegalArgumentException{
        Set<Integer> services = _services.stream().map(Enum::ordinal)
                .collect(Collectors.toSet());
        Message message;
        //для BetService
        if (localTask instanceof AddTicketOrderTask) {
            AddTicketOrderTask task = (AddTicketOrderTask)localTask;
            //BetRequest.Bet message;
            Set betOutcomes = (task.getTicketDetails().stream()
                    .map(betOutcome ->  BetRequest.Bet.BetOutcome.newBuilder()
                                .setCoefficient(betOutcome.getCoefficient())
                                .setEventId(betOutcome.getEventId())
                                .setId(betOutcome.getSeatId())
                                .build()
                    ).collect(Collectors.toSet()));
            BetRequest.Bet.Builder builder = BetRequest.Bet.newBuilder()
                    .setLocalTask(task.getType().ordinal())
                    .setPunterId(task.getPunterId())
                    .setTime(localTask.getTime())
                    //.setId(((AddTicketOrderTask)localTask).getBetId())
                    .addAllBetOutcome(betOutcomes)   //неверно
                    .addAllServices(services);
            message = builder.build();
            return message;
        }
        if (localTask instanceof FixTicketOrderTask) {
            FixTicketOrderTask task = (FixTicketOrderTask)localTask;
            //BetRequest.Bet message;
            BetRequest.Bet.Builder builder = BetRequest.Bet.newBuilder()
                    .setLocalTask(task.getType().ordinal())
                    .setId(task.getBetId())
                    .setTime(localTask.getTime())
                    .addAllServices(services);
            message = builder.build();
            return message;
        }
        //BillingService tasks
        if (localTask instanceof BillingWithdrawTask) {
            BillingWithdrawTask task = (BillingWithdrawTask)localTask;
            Message.Builder builder = BillingRequest.Billing.
                    newBuilder()
                    .setLocalTask(localTask.getType().ordinal())
                    .setPunterId(task.getPunterId())
                    .setTime(localTask.getTime())
                    .setSum(task.getSum())
                    .addAllServices(services);

            message = builder.build();
            return message;
        }
        //EventService tasks
        if (localTask instanceof ReserveSeatTask) {
            ReserveSeatTask task = (ReserveSeatTask)localTask;
            Message.Builder builder = EventRequest.Event.newBuilder()
                    .setLocalTask(task.getType().ordinal())
                    .setTime(localTask.getTime())
                    .addAllSelections(task.getSelections())
                    .addAllServices(services);
            message = builder.build();
            return message;
        }
        //PunterService tasks
        if (localTask instanceof AddPunterLimitsTask) {
            AddPunterLimitsTask task = (AddPunterLimitsTask) localTask;
            Message.Builder builder =  PunterRequest.Punter.newBuilder()
                    .setLocalTask(localTask.getType().ordinal())
                    .setPunterId(task.getPunterId())
                    .setTime(localTask.getTime())
                    .addAllServices(services);
            message = builder.build();
            return message;
        }
        if (localTask instanceof CancelWithdrawTask) {
            CancelWithdrawTask task = (CancelWithdrawTask)localTask;
            Message.Builder builder = BillingRequest.Billing.
                    newBuilder()
                    .setLocalTask(localTask.getType().ordinal())
                    .setPunterId(task.getPunterId())
                    .setTime(localTask.getTime())
                    .setSum(task.getSum())
                    .addAllServices(services);
            message = builder.build();
            return message;
        }
        if (localTask instanceof CancelTicketOrderTask) {
            CancelTicketOrderTask task = (CancelTicketOrderTask)localTask;
            //BetRequest.Bet message;
            BetRequest.Bet.Builder builder = BetRequest.Bet.newBuilder()
                    .setLocalTask(task.getType().ordinal())
                    .setId(task.getBetId())
                    .setTime(localTask.getTime())
                    .addAllServices(services);
            message = builder.build();
            return message;
        }
        if (localTask instanceof CancelReserveSeatTask) {
            CancelReserveSeatTask task = (CancelReserveSeatTask)localTask;
            Message.Builder builder = EventRequest.Event.newBuilder()
                    .setLocalTask(task.getType().ordinal())
                    .addAllSelections(task.getSelections())
                    .setTime(localTask.getTime())
                    .addAllServices(services);
            message = builder.build();
            return message;
        }
        if (localTask instanceof CancelPunterLimitsTask) {
            CancelPunterLimitsTask task = (CancelPunterLimitsTask) localTask;
            Message.Builder builder =  PunterRequest.Punter.newBuilder()
                    .setLocalTask(localTask.getType().ordinal())
                    .setPunterId(task.getPunterId())
                    .setTime(localTask.getTime())
                    .addAllServices(services);
            message = builder.build();
            return message;
        }



        else {
            throw new IllegalArgumentException("Unknown task type!");
        }
    }
}
