package ru.splat.messages.uptm.trmetadata.ticket;

/**
 * Created by Дмитрий on 01.02.2017.
 */
public class TicketDetail {
    private Integer seatId;
    private Integer eventId;
    private Double price;
    private Integer marketId;

    public TicketDetail() {
    }

    public TicketDetail(Integer marketId, Integer eventId, Integer seatId, Double price) {
        this.seatId = seatId;
        this.eventId = eventId;
        this.price = price;
        this.marketId = marketId;
    }

    @Override
    public String toString() {
        return "TicketDetail{" +
                "seatId=" + seatId +
                ", eventId=" + eventId +
                ", price=" + price +
                ", marketId=" + marketId +
                '}';
    }

    public Integer getSeatId() {
        return seatId;
    }
    public Integer getEventId() {
        return eventId;
    }
    public Double getPrice() {
        return price;
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

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setMarketId(Integer marketId) {
        this.marketId = marketId;
    }

}