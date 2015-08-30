package com.example.tj.tjfstockquotes.Model;

/**
 * Created by tj on 8/29/2015.
 */
public class StockQuote {
    private String name;
    private String symbol;
    private String lastPrice;
    private String marketCap;
    private String high;
    private String low;
    private String open;
    private String change;
    private String changePercent;
    private String timestamp;
    private String volume;
    private String changeYTD;
    private String changePercentYTD;

    public String toString() {
        return "Name: " +name;
                /*+ "\n"
                + "Symbol: " + symbol + "\n"
                + "Last price: " + lastPrice + "\n"
                + "Market cap: " + marketCap + "\n"
                + "High: " + high + "\n "
                + "Low: " + low + "\n"
                + "Open: " + open + "\n "
                + "Change: " + change + "\n "
                + "Change percent: " + changePercent + "\n"
                + "Timestamp: " + timestamp + "\n"
                + "Volume: " + volume + "\n"
                + "Change YTD: " + changeYTD + "\n "
                + "Percent Change YTD: " + changePercentYTD + "\n"; */
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(String changePercent) {
        this.changePercent = changePercent;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getChangeYTD() {
        return changeYTD;
    }

    public void setChangeYTD(String changeYTD) {
        this.changeYTD = changeYTD;
    }

    public String getChangePercentYTD() {
        return changePercentYTD;
    }

    public void setChangePercentYTD(String changePercentYTD) {
        this.changePercentYTD = changePercentYTD;
    }
}
