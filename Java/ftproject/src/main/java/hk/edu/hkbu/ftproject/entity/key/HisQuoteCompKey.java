package hk.edu.hkbu.ftproject.entity.key;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;
import java.util.Objects;

@JmixEntity(name = "ft_HisQuoteCompKey")
@Embeddable
public class HisQuoteCompKey {
    @Column(name = "SYMBOL", nullable = false, unique = true, length = 20)
    private String symbol;

    @Column(name = "\"TRADE_DATE\"", nullable = false, unique = true)
    @Temporal(TemporalType.DATE)
    private Date tradeDate;

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, tradeDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HisQuoteCompKey entity = (HisQuoteCompKey) o;
        return Objects.equals(this.symbol, entity.symbol) &&
                Objects.equals(this.tradeDate, entity.tradeDate);
    }
}