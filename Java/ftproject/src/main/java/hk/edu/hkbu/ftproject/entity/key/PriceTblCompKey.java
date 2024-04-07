package hk.edu.hkbu.ftproject.entity.key;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@JmixEntity(name = "ftp_PriceTblCompKey")
@Embeddable
public class PriceTblCompKey {
    @Column(name = "CLASSIFIER", nullable = false, unique = true, length = 20)
    private String classifier;

    @Column(name = "SYMBOL", nullable = false, unique = true, length = 20)
    private String symbol;

    @Column(name = "\"VALUE_DATE\"", nullable = false, unique = true)
    @Temporal(TemporalType.DATE)
    private Date valueDate;

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getClassifier() {
        return classifier;
    }

    public void setClassifier(String classifier) {
        this.classifier = classifier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, classifier, valueDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceTblCompKey entity = (PriceTblCompKey) o;
        return Objects.equals(this.symbol, entity.symbol) &&
                Objects.equals(this.classifier, entity.classifier) &&
                Objects.equals(this.valueDate, entity.valueDate);
    }
}