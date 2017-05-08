package ru.splat.messages.uptm.trmetadata.bet;

import ru.splat.messages.conventions.ServicesEnum;
import ru.splat.messages.conventions.TaskTypesEnum;
import ru.splat.messages.proxyup.bet.BetInfo;
import ru.splat.messages.uptm.trmetadata.LocalTask;

import java.util.Set;

/**
 * Created by Дмитрий on 22.12.2016.
 */
//первая фаза по ставкам
public class AddBetTask extends LocalTask {
    private final Integer punterId;
    private final Set<BetOutcome> betOutcomes; //список возможных исходов

    public Integer getPunterId() {
        return punterId;
    }

    public Set<BetOutcome> getBetOutcomes() {
        return betOutcomes;
    }

    //конструктор первой фазы
    public AddBetTask(Integer punterId, Set<BetOutcome> betOutcomes, Long time) {
        super(time);
        this.punterId = punterId;
        this.betOutcomes = betOutcomes;
    }


    @Override
    public TaskTypesEnum getType() {
        return TaskTypesEnum.ADD_BET;
    }

    @Override
    public ServicesEnum getService() {
        return ServicesEnum.BetService;
    }


    public static AddBetTask create(BetInfo betInfo) {
        return new AddBetTask(betInfo.getUserId(),
                betInfo.getBetOutcomes(),
                System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "AddBetTask{" +
                "punterId=" + punterId +
                ", betOutcomes=" + betOutcomes +
                "} ";
    }
}
