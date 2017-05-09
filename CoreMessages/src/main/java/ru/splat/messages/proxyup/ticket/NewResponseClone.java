package ru.splat.messages.proxyup.ticket;


public class NewResponseClone
{
    private boolean active;
    private int userId;
    private long transactionId;

    public NewResponseClone() {
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "NewResponseClone{" +
                "active=" + active +
                ", userId=" + userId +
                ", transactionId=" + transactionId +
                '}';
    }
}
