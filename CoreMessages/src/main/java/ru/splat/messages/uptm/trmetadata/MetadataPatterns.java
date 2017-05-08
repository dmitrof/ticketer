package ru.splat.messages.uptm.trmetadata;

import ru.splat.messages.Transaction;
import ru.splat.messages.conventions.ServicesEnum;
import ru.splat.messages.proxyup.bet.BetInfo;
import ru.splat.messages.uptm.trmetadata.bet.AddBetTask;
import ru.splat.messages.uptm.trmetadata.bet.CancelBetTask;
import ru.splat.messages.uptm.trmetadata.bet.FixBetTask;
import ru.splat.messages.uptm.trmetadata.billing.BillingWithdrawTask;
import ru.splat.messages.uptm.trmetadata.billing.CancelWithdrawTask;
import ru.splat.messages.uptm.trmetadata.event.AddSelectionLimitsTask;
import ru.splat.messages.uptm.trmetadata.event.CancelSelectionLimitsTask;
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

    private static final Map<ServicesEnum, Function<BetInfo, LocalTask>> cancelServices = new HashMap<>();

    static {
        cancelServices.put(BillingService, CancelWithdrawTask::create);
        cancelServices.put(EventService, CancelSelectionLimitsTask::create);
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
                    AddSelectionLimitsTask::create,
                    AddPunterLimitsTask::create,
                    AddBetTask::create));
    }

    //cancel commands
    public static TransactionMetadata createCancel(Transaction transaction, TransactionState trState) {
        List<Function<BetInfo, LocalTask>> tasks = cancelServices.entrySet()
                .stream()
                .filter(e -> trState.getLocalStates().get(e.getKey()).isPositive())
                .map(Map.Entry::getValue)
                .collect(toList());
        tasks.add(CancelBetTask::create);

        return createMetadataWithTasks(transaction, tasks);
    }

    //phase2 commands
    public static TransactionMetadata createPhase2(Transaction transaction) {
        return createMetadataWithTasks(transaction,
                singletonList(FixBetTask::create));
    }

    private static TransactionMetadata createMetadataWithTasks(Transaction transaction,
                                                               List<Function<BetInfo, LocalTask>> builders) {
        return new TransactionMetadata(transaction.getCurrent(),
                tasksFrom(builders, transaction.getBetInfo()));
    }

    private static List<LocalTask> tasksFrom(List<Function<BetInfo, LocalTask>> builders,
                                             BetInfo betInfo) {
        List<LocalTask> tasks = new ArrayList<>();
        builders.forEach(b -> tasks.add(b.apply(betInfo)));
        return tasks;
    }
}
