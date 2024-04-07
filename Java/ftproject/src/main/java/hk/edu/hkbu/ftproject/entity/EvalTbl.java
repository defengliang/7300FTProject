package hk.edu.hkbu.ftproject.entity;

import hk.edu.hkbu.ftproject.entity.key.EvalTblCompKey;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.data.DdlGeneration;
import jakarta.persistence.*;

import java.util.Date;

@DdlGeneration(value = DdlGeneration.DbScriptGenerationMode.DISABLED)
@JmixEntity
@Table(name = "EVAL_TBL")
@Entity(name = "ftp_EvalTbl")
public class EvalTbl {
    @EmbeddedId
    private EvalTblCompKey id;

    @JoinColumn(name = "CLASSIFIER")
    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    private ClassifierTbl classifier;

    @Column(name = "\"CREATE_TIME\"", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "DAC")
    private Double dac;

    @Column(name = "MAE")
    private Double mae;

    @Column(name = "MAPE")
    private Double mape;

    @Column(name = "MSE")
    private Double mse;

    @Column(name = "N")
    private Long n;

    @Column(name = "RAE")
    private Double rae;

    @Column(name = "RMSE")
    private Double rmse;

    @Column(name = "RRSE")
    private Double rrse;

    @JoinColumn(name = "TARGET")
    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    private TargetTbl target;

    @Column(name = "\"UPDATE_TIME\"", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public TargetTbl getTarget() {
        return target;
    }

    public void setTarget(TargetTbl target) {
        this.target = target;
    }

    public Double getRrse() {
        return rrse;
    }

    public void setRrse(Double rrse) {
        this.rrse = rrse;
    }

    public Double getRmse() {
        return rmse;
    }

    public void setRmse(Double rmse) {
        this.rmse = rmse;
    }

    public Double getRae() {
        return rae;
    }

    public void setRae(Double rae) {
        this.rae = rae;
    }

    public Long getN() {
        return n;
    }

    public void setN(Long n) {
        this.n = n;
    }

    public Double getMse() {
        return mse;
    }

    public void setMse(Double mse) {
        this.mse = mse;
    }

    public Double getMape() {
        return mape;
    }

    public void setMape(Double mape) {
        this.mape = mape;
    }

    public Double getMae() {
        return mae;
    }

    public void setMae(Double mae) {
        this.mae = mae;
    }

    public Double getDac() {
        return dac;
    }

    public void setDac(Double dac) {
        this.dac = dac;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public ClassifierTbl getClassifier() {
        return classifier;
    }

    public void setClassifier(ClassifierTbl classifier) {
        this.classifier = classifier;
    }

    public EvalTblCompKey getId() {
        return id;
    }

    public void setId(EvalTblCompKey id) {
        this.id = id;
    }
}