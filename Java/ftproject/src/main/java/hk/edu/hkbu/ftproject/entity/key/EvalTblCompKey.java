package hk.edu.hkbu.ftproject.entity.key;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;

import java.util.Objects;

@JmixEntity(name = "ftp_EvalTblCompKey")
@Embeddable
public class EvalTblCompKey {
    @Column(name = "CLASSIFIER", nullable = false, unique = true, length = 20)
    private String classifier;

    @Column(name = "SYMBOL", nullable = false, unique = true, length = 20)
    private String symbol;

    @Column(name = "TARGET", nullable = false, unique = true, length = 20)
    private String target;

    @Column(name = "TESTORTRAIN", nullable = false, unique = true)
    private Boolean testortrain = false;

    public Boolean getTestortrain() {
        return testortrain;
    }

    public void setTestortrain(Boolean testortrain) {
        this.testortrain = testortrain;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
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
        return Objects.hash(symbol, testortrain, classifier, target);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EvalTblCompKey entity = (EvalTblCompKey) o;
        return Objects.equals(this.symbol, entity.symbol) &&
                Objects.equals(this.testortrain, entity.testortrain) &&
                Objects.equals(this.classifier, entity.classifier) &&
                Objects.equals(this.target, entity.target);
    }
}