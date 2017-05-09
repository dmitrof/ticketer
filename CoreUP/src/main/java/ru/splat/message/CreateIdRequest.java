package ru.splat.message;

import ru.splat.messages.proxyup.ticket.TicketInfo;

/**
 * Message from receiver to id_generator.
 */
public class CreateIdRequest implements InnerMessage {
    private final TicketInfo ticketInfo;

    public CreateIdRequest(TicketInfo ticketInfo) {
        this.ticketInfo = ticketInfo;
    }

    public TicketInfo getTicketInfo() {
        return ticketInfo;
    }

    @Override
    public String toString() {
        return "CreateIdRequest{" +
                "ticketInfo=" + ticketInfo +
                '}';
    }
}
