package hk.edu.hkbu.ftproject.beans;

import java.util.Date;

public class PriceBean {
    String symbol;
    Date tradeDate;
    String classifier;
    Double open;
    Double close;
    Double high;
    Double low;
    Double adjClose;
    Double volume;

    public String toString() {
        return "symbol=" + symbol
                + ", classifier=" + classifier
                + ", tradeDate=" + tradeDate
                + ", open=" + open
                + ", close=" + close
                + ", high=" + high
                + ", low=" + low
                + ", adjClose=" + adjClose
                + ", volume=" + volume;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getClassifier() {
        return classifier;
    }

    public void setClassifier(String classifier) {
        this.classifier = classifier;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(Double adjClose) {
        this.adjClose = adjClose;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }
}
