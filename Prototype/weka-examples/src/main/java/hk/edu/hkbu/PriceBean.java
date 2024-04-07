package hk.edu.hkbu;

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
}
