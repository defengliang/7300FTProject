package hk.edu.hkbu;

public class EvalMeasure {

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
}

