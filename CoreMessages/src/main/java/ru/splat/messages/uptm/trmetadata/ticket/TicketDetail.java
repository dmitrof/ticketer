package ru.splat.messages.uptm.trmetadata.ticket;

/**
 * Created by Дмитрий on 01.02.2017.
 */
public class TicketDetail {
    private Integer seatId;
    private Integer eventId;
    private Double coefficient;
    private Integer marketId;

    public TicketDetail() {
    }

    public TicketDetail(Integer marketId, Integer eventId, Integer seatId, Double coefficient) {
        this.seatId = seatId;
        this.eventId = eventId;
        this.coefficient = coefficient;
        this.marketId = marketId;
    }

    @Override
    public String toString() {
        return "TicketDetail{" +
                "seatId=" + seatId +
                ", eventId=" + eventId +
                ", coefficient=" + coefficient +
                ", marketId=" + marketId +
                '}';
    }

    public Integer getSeatId() {
        return seatId;
    }
    public Integer getEventId() {
        return eventId;
    }
    public Double getCoefficient() {
        return coefficient;
    }

    public Integer getMarketId() {
        return marketId;
    }

    public void setSeatId(Integer outcomeId) {
        this.seatId = outcomeId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    public void setMarketId(Integer marketId) {
        this.marketId = marketId;
    }

}