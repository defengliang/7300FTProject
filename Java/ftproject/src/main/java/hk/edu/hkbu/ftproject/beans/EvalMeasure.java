package hk.edu.hkbu.ftproject.beans;

public class EvalMeasure {

    String symbol;

    String classifier;
    String target;
    Double N;
    Double MAE;
    Double MSE;
    Double RMSE;
    Double MAPE;
    Double DAC;
    Double RAE;
    Double RRSE;

    public String toString() {
        return "classifier=" + classifier
                + ", target=" + target
                + ", N=" + N
                + ", MAE=" + MAE
                + ", MSE=" + MSE
                + ", RMSE=" + RMSE
                + ", MAPE=" + MAPE
                + ", DAC=" + DAC
                + ", RAE=" + RAE
                + ", RRSE=" + RRSE;
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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Double getN() {
        return N;
    }

    public void setN(Double n) {
        N = n;
    }

    public Double getMAE() {
        return MAE;
    }

    public void setMAE(Double MAE) {
        this.MAE = MAE;
    }

    public Double getMSE() {
        return MSE;
    }

    public void setMSE(Double MSE) {
        this.MSE = MSE;
    }

    public Double getRMSE() {
        return RMSE;
    }

    public void setRMSE(Double RMSE) {
        this.RMSE = RMSE;
    }

    public Double getMAPE() {
        return MAPE;
    }

    public void setMAPE(Double MAPE) {
        this.MAPE = MAPE;
    }

    public Double getDAC() {
        return DAC;
    }

    public void setDAC(Double DAC) {
        this.DAC = DAC;
    }

    public Double getRAE() {
        return RAE;
    }

    public void setRAE(Double RAE) {
        this.RAE = RAE;
    }

    public Double getRRSE() {
        return RRSE;
    }

    public void setRRSE(Double RRSE) {
        this.RRSE = RRSE;
    }
}

