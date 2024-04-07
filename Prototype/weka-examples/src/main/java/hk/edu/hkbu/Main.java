package hk.edu.hkbu;

import weka.classifiers.Classifier;
import weka.classifiers.lazy.IBk;
import weka.classifiers.timeseries.WekaForecaster;
import weka.core.EuclideanDistance;
import weka.core.Instances;
import weka.core.neighboursearch.LinearNNSearch;

import java.io.*;
import java.util.List;

import weka.core.converters.ConverterUtils.DataSource;

public class Main {

    private NamedClassifier getNamedClassifier() {
        IBk iBkClassifier = new IBk();

        iBkClassifier.setKNN(1);
        iBkClassifier.setBatchSize("100");
        iBkClassifier.setCrossValidate(false);
        iBkClassifier.setMeanSquared(false);

        LinearNNSearch linearNNSearch = new LinearNNSearch();

        // set linearNNSearch's distanceFunction to euclideanDistance
        EuclideanDistance euclideanDistance = new EuclideanDistance();
        euclideanDistance.setAttributeIndices("first-last");
        euclideanDistance.setDontNormalize(false);
        euclideanDistance.setInvertSelection(false);

        try {
            linearNNSearch.setDistanceFunction(euclideanDistance);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        linearNNSearch.setMeasurePerformance(false);
        linearNNSearch.setSkipIdentical(false);
        iBkClassifier.setNearestNeighbourSearchAlgorithm(linearNNSearch);

        iBkClassifier.setNumDecimalPlaces(8);
        iBkClassifier.setWindowSize(0);

        return new NamedClassifier("IBK", iBkClassifier);
    }


    public String csvToArff(String symbol, File file) {
        String header =
                "@relation " + symbol + "\n" + """
@attribute 'Date' DATE "yyyy-MM-dd"
@attribute 'OPEN' numeric
@attribute 'HIGH' numeric
@attribute 'LOW' numeric
@attribute 'CLOSE' numeric
@attribute 'ADJCLOSE' numeric
@attribute 'VOLUME' numeric

@data\n""";
        File outputFile = new File(symbol + ".arff");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder content = new StringBuilder();
            content.append(header);
            int lineCount = 0;
            while ((line = reader.readLine()) != null) {

                // The first line doesn't need
                if (lineCount != 0)
                    content.append(line).append("\n");
                lineCount++;
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write(content.toString());
            writer.close();

            return content.toString();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void proecess(String symbol) {

        try {

            String arffString = csvToArff(symbol, new File("AAPL-2.csv"));

            Instances instances = new Instances(new BufferedReader(new StringReader(arffString)));


            WekaForecaster forecaster = new WekaForecaster();

            forecaster.setFieldsToForecast("OPEN,CLOSE,HIGH,LOW,ADJCLOSE,VOLUME");

            // set Base Learner to IBk, it can use others
            // IBK START
            NamedClassifier namedClassifier = getNamedClassifier();
            Classifier classifier = namedClassifier.getClassifier();

            System.out.println(classifier.toString());

            forecaster.setBaseForecaster(classifier);
            // IBK END

            forecaster.getTSLagMaker().setTimeStampField("Date");

            // forecaster.buildForecaster(aapl, System.out);

            ExtendTSEvaluation eval = new ExtendTSEvaluation(instances, 0.3);
            eval.setPrimeWindowSize(10);

            eval.setForecastFuture(true);
            eval.setEvaluateOnTestData(true);
            eval.setEvaluateOnTrainingData(false);
            eval.setRebuildModelAfterEachTestForecastStep(true);
            eval.setEvaluationModules("MAE,MSE,RMSE,MAPE,DAC,RAE,RRSE");

            File progressFile = new File("progress.log");
            PrintStream printStream = new PrintStream(progressFile);
            eval.evaluateForecaster(forecaster, printStream);

            List<EvalMeasure> list = eval.obtainEvalMeasure(namedClassifier.getName());

            for (EvalMeasure evalMeasure: list) {
                System.out.println(evalMeasure.toString());
            }

            List<PriceBean> list2 = eval.getPredictions(symbol, namedClassifier.getName());

            for (PriceBean bean: list2) {
                System.out.println(bean.toString());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.proecess("AAPL");

    }

    public static void main2(String[] args) {

        try {

            DataSource ds = new DataSource("AAPL.arff");
            Instances aapl = ds.getDataSet();

            // new Instances(new BufferedReader(new FileReader("AAPL.arff")));

            WekaForecaster forecaster = new WekaForecaster();

            forecaster.setFieldsToForecast("OPEN,CLOSE,HIGH,LOW,ADJCLOSE,VOLUME");


            // set Base Learner to IBk, it can use others
            // IBK START
            IBk iBkClassifier = new IBk();

            iBkClassifier.setKNN(1);
            iBkClassifier.setBatchSize("100");
            iBkClassifier.setCrossValidate(false);
            iBkClassifier.setMeanSquared(false);

            LinearNNSearch linearNNSearch = new LinearNNSearch();

            // set linearNNSearch's distanceFunction to euclideanDistance
            EuclideanDistance euclideanDistance = new EuclideanDistance();
            euclideanDistance.setAttributeIndices("first-last");
            euclideanDistance.setDontNormalize(false);
            euclideanDistance.setInvertSelection(false);
            linearNNSearch.setDistanceFunction(euclideanDistance);

            linearNNSearch.setMeasurePerformance(false);
            linearNNSearch.setSkipIdentical(false);
            iBkClassifier.setNearestNeighbourSearchAlgorithm(linearNNSearch);

            iBkClassifier.setNumDecimalPlaces(8);
            iBkClassifier.setWindowSize(0);

            forecaster.setBaseForecaster(iBkClassifier);
            // IBK END

            forecaster.getTSLagMaker().setTimeStampField("Date");

            // forecaster.buildForecaster(aapl, System.out);

//            forecaster.primeForecaster(aapl);
            //List<List<NumericPrediction>> forecast = forecaster.forecast(1, System.out);

            ExtendTSEvaluation eval = new ExtendTSEvaluation(aapl, 0.3);
            eval.setPrimeWindowSize(10);

            eval.setForecastFuture(true);
            eval.setEvaluateOnTestData(true);
            eval.setEvaluateOnTrainingData(false);
            eval.setRebuildModelAfterEachTestForecastStep(true);
            eval.setEvaluationModules("MAE,MSE,RMSE,MAPE,DAC,RAE,RRSE");

            eval.evaluateForecaster(forecaster, System.out);

            //System.out.println(eval.toSummaryString());

            List<EvalMeasure> list = eval.obtainEvalMeasure("IBK");

            for (EvalMeasure evalMeasure: list) {
                System.out.println(evalMeasure.toString());
            }

            List<PriceBean> list2 = eval.getPredictions("AAPL", "IBK");

            for (PriceBean bean: list2) {
                System.out.println(bean.toString());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}