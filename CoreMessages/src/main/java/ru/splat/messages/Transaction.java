package ru.splat.messages;

import ru.splat.messages.proxyup.ticket.TicketInfo;

/**
 * Wrapper class for Transaction.
 */
public class Transaction {
    //TODO: change to range of identifiers
    private State state;
    private TicketInfo ticketInfo;
    private Long lowerBound;
    private Long upperBound;
    private Long current;


    public Long getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(Long lowerBound) {
        this.lowerBound = lowerBound;
    }

    public Long getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(Long upperBound) {
        this.upperBound = upperBound;
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public TicketInfo getTicketInfo() {
        return ticketInfo;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setTicketInfo(TicketInfo ticketInfo) {
        this.ticketInfo = ticketInfo;
    }

    public void nextState(State state) {
        nextId();
        setState(state);
    }

    private void nextId() {
        if(current + 1 <= upperBound) {
            current++;
        } else {
            throw new Error("tr_id out of bounds: " + toString());
        }
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "state=" + state +
                ", ticketInfo=" + ticketInfo +
                ", lowerBound=" + lowerBound +
                ", upperBound=" + upperBound +
                ", current=" + current +
                '}';
    }

    public enum State {
        CREATED, CANCEL, COMPLETED, DENIED, PHASE1_RESPONDED, PHASE2_SEND, PHASE2_RESPONDED, CANCEL_RESPONDED, CANCEL_COMPLETED
    }

    public static class Builder {
        Transaction transaction = new Transaction();

        public static Builder builder() {
            return new Builder();
        }

        public Builder of(Transaction tr) {
            state(tr.state);
            betInfo(tr.ticketInfo);
            lower(tr.lowerBound);
            upper(tr.upperBound);
            current(tr.current);
            return this;
        }

        public Builder state(State state) {
            transaction.state = state;
            return this;
        }

        public Builder betInfo(TicketInfo ticketInfo) {
            transaction.ticketInfo = ticketInfo;
            return this;
        }

        public Builder lower(Long lower) {
            transaction.lowerBound = lower;
            return this;
        }

        public Builder upper(Long upper) {
            transaction.upperBound = upper;
            return this;
        }

        public Builder current(Long current) {
            transaction.current = current;
            return this;
        }

        public Transaction freshBuild() {
            transaction.current = transaction.lowerBound;
            return transaction;
        }

        public Transaction build() {
            return transaction;
        }
    }
}
