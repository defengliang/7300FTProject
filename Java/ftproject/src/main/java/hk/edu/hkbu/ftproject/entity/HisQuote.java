package hk.edu.hkbu.ftproject.entity;

import hk.edu.hkbu.ftproject.entity.key.HisQuoteCompKey;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.data.DdlGeneration;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@DdlGeneration(value = DdlGeneration.DbScriptGenerationMode.DISABLED)
@JmixEntity
@Table(name = "HIS_QUOTE")
@Entity(name = "ft_HisQuote")
public class HisQuote {
    @EmbeddedId
    private HisQuoteCompKey id;

    @Column(name = "\"ADJ_CLOSE_PRICE\"")
    private Double adjClosePrice;

    @Column(name = "\"CLOSE_PRICE\"")
    private Double closePrice;

    @Column(name = "\"HIGH_PRICE\"")
    private Double highPrice;

    @Column(name = "\"LOW_PRICE\"")
    private Double lowPrice;

    @Column(name = "\"OPEN_PRICE\"")
    private Double openPrice;


    @Column(name = "VOLUME")
    private Long volume;

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(Double openPrice) {
        this.openPrice = openPrice;
    }

    public Double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Double highPrice) {
        this.highPrice = highPrice;
    }

    public Double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(Double closePrice) {
        this.closePrice = closePrice;
    }

    public Double getAdjClosePrice() {
        return adjClosePrice;
    }

    public void setAdjClosePrice(Double adjClosePrice) {
        this.adjClosePrice = adjClosePrice;
    }

    public HisQuoteCompKey getId() {
        return id;
    }

    public void setId(HisQuoteCompKey id) {
        this.id = id;
    }
}