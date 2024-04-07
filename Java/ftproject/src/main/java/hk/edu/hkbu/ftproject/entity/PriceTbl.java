package hk.edu.hkbu.ftproject.entity;

import hk.edu.hkbu.ftproject.entity.key.PriceTblCompKey;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.data.DdlGeneration;
import jakarta.persistence.*;

import java.util.Date;

@DdlGeneration(value = DdlGeneration.DbScriptGenerationMode.DISABLED)
@JmixEntity
@Table(name = "PRICE_TBL")
@Entity(name = "ftp_PriceTbl")
public class PriceTbl {
    @EmbeddedId
    private PriceTblCompKey id;

    @Column(name = "ADJCLOSE")
    private Double adjclose;

    @JoinColumn(name = "CLASSIFIER")
    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    private ClassifierTbl classifier;

    @Column(name = "\"CLOSE\"")
    private Double close;

    @Column(name = "\"CREATE_TIME\"", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "HIGH")
    private Double high;

    @Column(name = "LOW")
    private Double low;

    @Column(name = "\"OPEN\"")
    private Double open;

    @Column(name = "\"UPDATE_TIME\"", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Column(name = "VOLUME")
    private Long volume;

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public ClassifierTbl getClassifier() {
        return classifier;
    }

    public void setClassifier(ClassifierTbl classifier) {
        this.classifier = classifier;
    }

    public Double getAdjclose() {
        return adjclose;
    }

    public void setAdjclose(Double adjclose) {
        this.adjclose = adjclose;
    }

    public PriceTblCompKey getId() {
        return id;
    }

    public void setId(PriceTblCompKey id) {
        this.id = id;
    }
}