//import org.junit.Test;
//import ru.splat.messages.Transaction;
//import ru.splat.messages.conventions.ServiceResult;
//import ru.splat.messages.proxyup.bet.TicketInfo;
//import ru.splat.messages.uptm.trmetadata.LocalTask;
//import ru.splat.messages.uptm.trmetadata.MetadataPatterns;
//import ru.splat.messages.uptm.trmetadata.TransactionMetadata;
//import ru.splat.messages.uptm.trmetadata.bet.CancelTicketOrderTask;
//import ru.splat.messages.uptm.trmetadata.bet.FixTicketOrderTask;
//import ru.splat.messages.uptm.trmetadata.billing.CancelWithdrawTask;
//import ru.splat.messages.uptm.trstate.ServiceResponse;
//import ru.splat.messages.uptm.trstate.TransactionState;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//
//import static java.util.Collections.singletonList;
//import static org.junit.Assert.assertEquals;
//import static ru.splat.messages.Transaction.State.CREATED;
//import static ru.splat.messages.conventions.ServicesEnum.*;
//
///**
// * Created by Иван on 15.02.2017.
// */
//public class MetadataPatternsTest {
//    @Test
//    public void testPhase2() {
//        TransactionMetadata expected = new TransactionMetadata(1L, singletonList(FixTicketOrderTask.create(testBetInfo())));
//        TransactionMetadata real = MetadataPatterns.createPhase2(testTransaction(testBetInfo()));
//
//        compareMetadata(expected, real);
//    }
//
//    @Test
//    public void testCancel() {
//        List<LocalTask> tasks = new ArrayList<>();
//
//        tasks.add(CancelWithdrawTask.create(testBetInfo()));
//        tasks.add(CancelTicketOrderTask.create(testBetInfo()));
//
//        TransactionMetadata expected = new TransactionMetadata(1L, tasks);
//        TransactionMetadata real = MetadataPatterns.createCancel(testTransaction(testBetInfo()), testState());
//
//        compareMetadata(expected, real);
//    }
//
//    private void compareMetadata(TransactionMetadata e, TransactionMetadata r) {
//        System.err.println(e.toString());
//        System.err.println(r.toString());
//
//        assertEquals(e.toString(), r.toString());
//    }
//
//    private static TransactionState testState() {
//        TransactionState trState = new TransactionState(0L, new HashMap<>());
//        trState.setLocalState(BetService, positive());
//        trState.setLocalState(BillingService, positive());
//        trState.setLocalState(EventService, negative());
//        trState.setLocalState(PunterService, negative());
//
//        return trState;
//    }
//
//    private static ServiceResponse<String> positive() {
//        return new ServiceResponse<>("test", ServiceResult.CONFIRMED);
//    }
//
//    private static ServiceResponse<String> negative() {
//        return new ServiceResponse<>("test", ServiceResult.DENIED);
//    }
//
//    private static Transaction testTransaction(TicketInfo betInfo) {
//        Transaction transaction = new Transaction();
//        transaction.setState(CREATED);
//        transaction.setTicketInfo(betInfo);
//        transaction.setLowerBound(0L);
//        transaction.setUpperBound(50L);
//        transaction.setCurrent(1L);
//
//        return transaction;
//    }
//
//    private static TicketInfo testBetInfo() {
//        TicketInfo betInfo = new TicketInfo();
//        betInfo.setBetId(1L);
//        betInfo.setBet(20);
//        betInfo.setSelectionsId(new HashSet<>());
//        betInfo.setTicketDetails(new HashSet<>());
//
//        return betInfo;
//    }
//}
