package hk.edu.hkbu.ftproject.entity;

import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

import java.util.Date;

@JmixEntity
@Table(name = "FTP_GLOBAL_SETTINGS")
@Entity(name = "ftp_GlobalSettings")
public class GlobalSettings {
    @Column(name = "ID", nullable = false, length = 30)
    @Id
    private String id;

    @Column(name = "FLOAT_VALUE")
    private Float floatValue;

    @Column(name = "STRING_VALUE")
    private String stringValue;

    @Column(name = "DATA_VALUE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataValue;

    @Column(name = "INT_VALUE")
    private Integer intValue;

    @InstanceName
    @Column(name = "DESCRIPTION", length = 1000)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDataValue() {
        return dataValue;
    }

    public void setDataValue(Date dataValue) {
        this.dataValue = dataValue;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public Float getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(Float floatValue) {
        this.floatValue = floatValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}