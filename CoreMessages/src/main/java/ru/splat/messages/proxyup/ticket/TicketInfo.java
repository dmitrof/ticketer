package ru.splat.messages.proxyup.ticket;

import ru.splat.messages.uptm.trmetadata.ticket.TicketDetail;

import java.util.Set;

/**
 * Information about new bet.
 */
public class TicketInfo {
    private Long betId = -1L; //temporary decision
    private Integer userId;
    private Integer bet;
    private Set<TicketDetail> ticketDetails;
    private Set<Integer> selectionsId;

    public TicketInfo() {
    }

    public TicketInfo(Long betId, Integer userId, Integer bet, Set<TicketDetail> ticketDetails) {
        this.betId = betId;
        this.userId = userId;
        this.bet = bet;
        this.ticketDetails = ticketDetails;
    }

    public Integer getBet() {
        return bet;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setBet(Integer bet) {
        this.bet = bet;
    }

    public Set<TicketDetail> getTicketDetails() {
        return ticketDetails;
    }

    public void setTicketDetails(Set<TicketDetail> ticketDetails) {
        this.ticketDetails = ticketDetails;
    }

    public Set<Integer> getSelectionsId() {
        return selectionsId;
    }

    public void setSelectionsId(Set<Integer> selectionsId) {
        this.selectionsId = selectionsId;
    }

    public Long getBetId() {
        return betId;
    }

    public void setBetId(Long betId) {
        this.betId = betId;
    }

    @Override
    public String toString() {
        return "TicketInfo{" +
                "betId=" + betId +
                ", userId=" + userId +
                ", bet=" + bet +
                ", ticketDetails=" + ticketDetails +
                ", selectionsId=" + selectionsId +
                '}';
    }
}
