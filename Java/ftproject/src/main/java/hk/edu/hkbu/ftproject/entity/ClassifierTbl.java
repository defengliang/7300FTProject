package hk.edu.hkbu.ftproject.entity;

import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.data.DdlGeneration;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@DdlGeneration(value = DdlGeneration.DbScriptGenerationMode.DISABLED)
@JmixEntity
@Table(name = "CLASSIFIER_TBL")
@Entity(name = "ftp_ClassifierTbl")
public class ClassifierTbl {
    @InstanceName
    @Column(name = "NAME", nullable = false, length = 20)
    @Id
    private String name;

    @Column(name = "DESCRIPTION", length = 2000)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}