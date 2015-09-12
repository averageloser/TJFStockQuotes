package com.example.tj.tjfstockquotes.Model;

/**
 * Created by tj on 8/29/2015.
 */
public class StockQuote {
    private String name;
    private String symbol;
    private String exchange;

    public String toString() {
        return "Name: " +name
                + "\n"
                + "Symbol: " + symbol + "\n"
                + "Exchange: " + exchange + "\n";
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


    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
}
