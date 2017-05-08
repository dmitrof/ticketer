package ru.splat.Ticket.feautures;


import ru.splat.facade.feautures.TransactionRequest;
import ru.splat.messages.BetRequest;

import java.util.List;

/**
 * Created by Daniil Kochubey 23.04.2017
 */
public class TicketInfo implements TransactionRequest
{

    private long transactionId;
    private int localTask;
    private BetRequest.Bet blob;
    private List<Integer> services;
    private long id;

    public TicketInfo(long transactionId, int localTask, BetRequest.Bet blob, List<Integer> services)
    {
        this.transactionId = transactionId;
        this.localTask = localTask;
        this.blob = blob;
        this.services = services;
    }

    public TicketInfo(long transactionId, int localTask, BetRequest.Bet blob, List<Integer> services, long id)
    {
        this.transactionId = transactionId;
        this.localTask = localTask;
        this.blob = blob;
        this.services = services;
        this.id = id;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketInfo ticketInfo = (TicketInfo) o;

        return transactionId == ticketInfo.transactionId;

    }

    @Override
    public int hashCode() {
        return (int) (transactionId ^ (transactionId >>> 32));
    }

    @Override
    public String toString() {
        return "TicketInfo{" +
                "transactionId=" + transactionId +
                ", localTask=" + localTask +
                ", blob=" + blob +
                ", services=" + services +
                ", id=" + id +
                '}';
    }

    public BetRequest.Bet getBlob() {
        return blob;
    }

    @Override
    public long getTransactionId() {
        return transactionId;
    }

    @Override
    public int getLocalTask() {
        return localTask;
    }

    @Override
    public List<Integer> getServices() {
        return services;
    }

    public long getId() {
        return id;
    }
}
