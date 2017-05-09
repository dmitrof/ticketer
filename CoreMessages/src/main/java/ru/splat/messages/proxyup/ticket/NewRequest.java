package ru.splat.messages.proxyup.ticket;

import ru.splat.messages.proxyup.ProxyUPMessage;

/**
 * ProxyUPMessage for UP asking to place a new bet.
 */
public class NewRequest extends ProxyUPMessage {
    private final TicketInfo ticketInfo;

    public NewRequest(TicketInfo ticketInfo) {
        super(ticketInfo.getUserId());
        this.ticketInfo = ticketInfo;
    }

    public TicketInfo getTicketInfo() {
        return ticketInfo;
    }

    @Override
    public String toString() {
        return "NewRequest{" +
                "ticketInfo=" + ticketInfo +
                '}';
    }
}
