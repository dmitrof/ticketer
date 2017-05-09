package ru.splat.messages.uptm.trmetadata;

import ru.splat.messages.Transaction;
import ru.splat.messages.conventions.ServicesEnum;
import ru.splat.messages.proxyup.ticket.TicketInfo;
import ru.splat.messages.uptm.trmetadata.ticket.AddTicketOrderTask;
import ru.splat.messages.uptm.trmetadata.ticket.CancelTicketOrderTask;
import ru.splat.messages.uptm.trmetadata.ticket.FixTicketOrderTask;
import ru.splat.messages.uptm.trmetadata.billing.BillingWithdrawTask;
import ru.splat.messages.uptm.trmetadata.billing.CancelWithdrawTask;
import ru.splat.messages.uptm.trmetadata.event.CancelReserveSeatTask;
import ru.splat.messages.uptm.trmetadata.event.ReserveSeatTask;
import ru.splat.messages.uptm.trmetadata.punter.AddPunterLimitsTask;
import ru.splat.messages.uptm.trmetadata.punter.CancelPunterLimitsTask;
import ru.splat.messages.uptm.trstate.TransactionState;

import java.util.*;
import java.util.function.Function;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static ru.splat.messages.conventions.ServicesEnum.*;

/**
 * Factory class for phase metadata.
 * */
public class MetadataPatterns {
    private static final List<ServicesEnum> services = Arrays.asList(BetService, BillingService, EventService, PunterService);
    private static final List<ServicesEnum> phase2Services = singletonList(BetService);

    private static final Map<ServicesEnum, Function<TicketInfo, LocalTask>> cancelServices = new HashMap<>();

    static {
        cancelServices.put(BillingService, CancelWithdrawTask::create);
        cancelServices.put(EventService, CancelReserveSeatTask::create);
        cancelServices.put(PunterService, CancelPunterLimitsTask::create);
    }

    public static List<ServicesEnum> getPhase1Services() {
        return services;
    }

    public static List<ServicesEnum> getPhase2Services() {
        return phase2Services;
    }

    public static List<ServicesEnum> getCancelServices(TransactionState trState) {
        return services.stream()
                .filter(service -> trState.getLocalStates().get(service).isPositive())
                .collect(toList());
    }

    //phase1 commands
    public static TransactionMetadata createPhase1(Transaction transaction) {
        return createMetadataWithTasks(transaction,
                asList(
                    BillingWithdrawTask::create,
                    ReserveSeatTask::create,
                    AddPunterLimitsTask::create,
                    AddTicketOrderTask::create));
    }

    //cancel commands
    public static TransactionMetadata createCancel(Transaction transaction, TransactionState trState) {
        List<Function<TicketInfo, LocalTask>> tasks = cancelServices.entrySet()
                .stream()
                .filter(e -> trState.getLocalStates().get(e.getKey()).isPositive())
                .map(Map.Entry::getValue)
                .collect(toList());
        tasks.add(CancelTicketOrderTask::create);

        return createMetadataWithTasks(transaction, tasks);
    }

    //phase2 commands
    public static TransactionMetadata createPhase2(Transaction transaction) {
        return createMetadataWithTasks(transaction,
                singletonList(FixTicketOrderTask::create));
    }

    private static TransactionMetadata createMetadataWithTasks(Transaction transaction,
                                                               List<Function<TicketInfo, LocalTask>> builders) {
        return new TransactionMetadata(transaction.getCurrent(),
                tasksFrom(builders, transaction.getTicketInfo()));
    }

    private static List<LocalTask> tasksFrom(List<Function<TicketInfo, LocalTask>> builders,
                                             TicketInfo ticketInfo) {
        List<LocalTask> tasks = new ArrayList<>();
        builders.forEach(b -> tasks.add(b.apply(ticketInfo)));
        return tasks;
    }
}
