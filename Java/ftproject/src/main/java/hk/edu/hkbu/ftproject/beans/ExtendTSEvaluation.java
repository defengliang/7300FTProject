package hk.edu.hkbu.ftproject.beans;

import weka.classifiers.evaluation.NumericPrediction;
import weka.classifiers.timeseries.eval.ErrorModule;
import weka.classifiers.timeseries.eval.TSEvalModule;
import weka.classifiers.timeseries.eval.TSEvaluation;
import weka.core.Instances;
import weka.core.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExtendTSEvaluation extends TSEvaluation {
    
    

    protected List<String> targetFieldNames;


    public ExtendTSEvaluation(Instances trainingData, double testSplitSize) throws Exception {
        super(trainingData, testSplitSize);
        this.targetFieldNames = new ArrayList<>();
        this.targetFieldNames.add("OPEN");
        this.targetFieldNames.add("CLOSE");
        this.targetFieldNames.add("HIGH");
        this.targetFieldNames.add("LOW");
        this.targetFieldNames.add("ADJCLOSE");
        this.targetFieldNames.add("VOLUME");
    }

    public List<PriceBean> getPredictions(String symbol, String classifier) throws Exception {

        List<PriceBean> retList = new ArrayList<>();

        ErrorModule predsForStep = this.getPredictionsForTestData(1);

        List<List<NumericPrediction>> allPredictList = predsForStep.getPredictionsForAllTargets();

        for (int i=0; i< this.m_testData.size(); i++) {

            if (i < this.getPrimeWindowSize()) {
                continue;
            }

            PriceBean bean = new PriceBean();

            bean.symbol = symbol;
            bean.classifier = classifier;
            bean.tradeDate = toDate(this.m_testData.get(i).toString(0));

            for (int j=0; j< targetFieldNames.size(); j++) {

                List<NumericPrediction> predictions = allPredictList.get(j);
                String targetName = targetFieldNames.get(j);
                NumericPrediction prediction = predictions.get(i);
                if (targetName.equals("OPEN")) {
                    bean.open = prediction.predicted();
                }
                if (targetName.equals("CLOSE")) {
                    bean.close = prediction.predicted();
                }

                if (targetName.equals("HIGH")) {
                    bean.high = prediction.predicted();
                }
                if (targetName.equals("LOW")) {
                    bean.low = prediction.predicted();
                }

                if (targetName.equals("ADJCLOSE")) {
                    bean.adjClose = prediction.predicted();
                }
                if (targetName.equals("VOLUME")) {
                    bean.volume = prediction.predicted();
                }
            }
            retList.add(bean);
        }

        return retList;
    }

    public List<EvalMeasure> obtainEvalMeasure(String symbol, String classifier) throws Exception {

        List<EvalMeasure> retList = new ArrayList<>();

        Set keys;
        String key;
        List evalsForKey;
        Iterator i$;
        TSEvalModule e;

        if (this.m_evaluateTrainingData) {
            keys = this.m_metricsForTrainingData.keySet();
            i$ = keys.iterator();

            while(i$.hasNext()) {
                key = (String)i$.next();
                evalsForKey = (List)this.m_metricsForTrainingData.get(key);
                i$ = evalsForKey.iterator();

                while(i$.hasNext()) {
                    e = (TSEvalModule)i$.next();
                    if (targetFieldNames == null) {
                        targetFieldNames = e.getTargetFields();
                    }
                }
            }
        }

        Map<String, List<TSEvalModule>> metricsToUse = this.m_metricsForTestData;

        for(int i = 0; i < targetFieldNames.size(); i++) {
            EvalMeasure evalMeasure = new EvalMeasure();

            evalMeasure.symbol = symbol;
            evalMeasure.classifier = classifier;
            evalMeasure.target = targetFieldNames.get(i);

            if (this.m_evalModules.get(1) instanceof ErrorModule) {

                String tempKey = (this.m_evalModules.get(1)).getEvalName();

                // temp.append(pad("  N", " ", maxWidthForLeftCol, false));
                List<TSEvalModule> metricForSteps = metricsToUse.get(tempKey);
                Iterator j$ = metricForSteps.iterator();

                while(j$.hasNext()) {
                    TSEvalModule m1 = (TSEvalModule)j$.next();
                    double[] countsForTargets = ((ErrorModule)m1).countsForTargets();
                    double countForTarget = countsForTargets[i];
                    evalMeasure.N = countForTarget;
                }
            }

            Set<String> keys1 = metricsToUse.keySet();
            Iterator k$ = keys1.iterator();

            while(k$.hasNext()) {

                String key2 = (String)k$.next();
                String shortName = ((TSEvalModule)((List)metricsToUse.get(key2)).get(0)).getEvalName();

                List<TSEvalModule> metricForSteps = metricsToUse.get(key2);
                Iterator m$ = metricForSteps.iterator();

                while(m$.hasNext()) {
                    TSEvalModule m2 = (TSEvalModule)m$.next();
                    double[] metricsForTargets2 = m2.calculateMeasure();
                    double metricForTargetI = metricsForTargets2[i];
                    Double result2 = Utils.isMissingValue(metricForTargetI) ? null : metricForTargetI;

                    if (shortName.equals("MAE")) {
                        evalMeasure.MAE = result2;
                    }
                    if (shortName.equals("MSE")) {
                        evalMeasure.MSE = result2;
                    }

                    if (shortName.equals("RMSE")) {
                        evalMeasure.RMSE = result2;
                    }

                    if (shortName.equals("MAPE")) {
                        evalMeasure.MAPE = result2;
                    }

                    if (shortName.equals("DAC")) {
                        evalMeasure.DAC = result2;
                    }

                    if (shortName.equals("RAE")) {
                        evalMeasure.RAE = result2;
                    }
                    if (shortName.equals("RRSE")) {
                        evalMeasure.RRSE = result2;
                    }
                }

            }

            retList.add(evalMeasure);
        }

        return retList;
    }
    
    private Date toDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }
    
    

}
