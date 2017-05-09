package ru.splat.mvc.features;


public class OutcomeInfo
{

    private int outcomeId;
    private String name;
    private double price;
    private int marketId;

    public int getId() {
        return outcomeId;
    }

    public void setId(int id) {
        this.outcomeId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {this.price = price;}

    public int getMarketId() {
        return marketId;
    }

    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }
}
