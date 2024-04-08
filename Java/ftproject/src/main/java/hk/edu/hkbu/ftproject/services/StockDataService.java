package hk.edu.hkbu.ftproject.services;

import hk.edu.hkbu.ftproject.beans.*;
import hk.edu.hkbu.ftproject.entity.*;

import hk.edu.hkbu.ftproject.entity.key.EvalTblCompKey;
import hk.edu.hkbu.ftproject.entity.key.PriceTblCompKey;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weka.classifiers.Classifier;
import weka.classifiers.timeseries.WekaForecaster;

import weka.core.Instance;
import weka.core.Instances;

import java.io.*;


import java.util.Date;
import java.util.List;

@Service("stockDataService")
public class StockDataService {



    @Autowired
    private DataManager dataManager;


    @Transactional
    private void updateStatus(String symbol, ProcessStatus status) {

        CompanyFile entity = dataManager.load(CompanyFile.class).id(symbol).one();
        if (entity != null) {
            entity.setStatus(status);
        }
        dataManager.save(entity);
    }

    @Transactional
    private void deleteRecords(String symbol) {

        dataManager.load(PriceTbl.class)
                .query("select c from ftp_PriceTbl c " +
                        " where c.id.symbol = :symbol")
                .parameter("symbol", symbol)
                .list().forEach(e -> dataManager.remove(e));

        dataManager.load(EvalTbl.class)
                .query("select a from ftp_EvalTbl a " +
                        " where a.id.symbol = :symbol")
                .parameter("symbol", symbol)
                .list().forEach(e -> dataManager.remove(e));
    }

    @Transactional
    private void insertRawRecords(String symbol, Instances instances) {

        ClassifierTbl classifierEntity = dataManager.load(ClassifierTbl.class).id("RAW").one();

        for (int i=0; i<instances.size(); i++) {

            Instance instance = instances.get(i);
            // tradeDate
            Date valueDate = new Date((long)instance.value(0));

            PriceTblCompKey priceTblCompKey = new PriceTblCompKey();
            priceTblCompKey.setClassifier("RAW");
            priceTblCompKey.setSymbol(symbol);
            priceTblCompKey.setValueDate(valueDate);

            PriceTbl priceTbl = dataManager.load(PriceTbl.class)
                            .id(priceTblCompKey).optional().orElse(dataManager.create(PriceTbl.class));

            priceTbl.setId(priceTblCompKey);

            priceTbl.setClassifier(classifierEntity);

            priceTbl.setOpen(instance.value(1));
            priceTbl.setHigh(instance.value(2));
            priceTbl.setLow(instance.value(3));
            priceTbl.setClose(instance.value(4));
            priceTbl.setAdjclose(instance.value(5));
            priceTbl.setVolume((long) instance.value(6));
            priceTbl.setCreateTime(new Date());
            priceTbl.setUpdateTime(new Date());
            dataManager.save(priceTbl);
        }
    }


    public void proecess(String symbol, File file) {

        System.out.println("process START");

        double testSplitSize = dataManager.load(GlobalSettings.class).id("test_split_size").one().getFloatValue();
        try {
            String arffString = csvToArff(symbol, file);
            updateStatus(symbol, ProcessStatus.PROCESSING);
            deleteRecords(symbol);

            Instances instances = new Instances(new BufferedReader(new StringReader(arffString)));

            System.out.println("instances created.");
            insertRawRecords(symbol, instances);

            WekaForecaster forecaster = new WekaForecaster();

            forecaster.setFieldsToForecast("OPEN,CLOSE,HIGH,LOW,ADJCLOSE,VOLUME");

            forecaster.getTSLagMaker().setTimeStampField("Date");

            forecaster.getTSLagMaker().setIncludePowersOfTime(false);
            forecaster.getTSLagMaker().setIncludeTimeLagProducts(false);

            // forecaster.buildForecaster(instances, System.out);


            File progressFile = new File("progress.log");
            PrintStream printStream = new PrintStream(progressFile);



            // set Base Learner to IBk, it can use others
            // IBK START
            for ( String classifierName: ClassifierFactory.classifier) {

                ExtendTSEvaluation eval = new ExtendTSEvaluation(instances, testSplitSize);
                eval.setPrimeWindowSize(10);

                eval.setForecastFuture(true);
                eval.setEvaluateOnTestData(true);
                eval.setEvaluateOnTrainingData(false);
                // eval.setRebuildModelAfterEachTestForecastStep(true);
                eval.setEvaluationModules("MAE,MSE,RMSE,MAPE,DAC,RAE,RRSE");

                NamedClassifier namedClassifier = ClassifierFactory.generateClassifier(classifierName);
                Classifier classifier = namedClassifier.getClassifier();

                System.out.println(classifier.toString());
                forecaster.setBaseForecaster(classifier);
                // IBK END

                System.out.println("start evaluate forecaster");
                eval.evaluateForecaster(forecaster, printStream);

                System.out.println("end evaluate forecaster");

                List<EvalMeasure> list = eval.obtainEvalMeasure(symbol, classifierName);

//            for (EvalMeasure evalMeasure: list) {
//                System.out.println(evalMeasure.toString());
//            }

                insertEvalMeasure(classifierName, list);

                System.out.println("evaluation measure output finished.");

//            for (EvalMeasure evalMeasure: list) {
//                System.out.println(evalMeasure.toString());
//            }

                List<PriceBean> list2 = eval.getPredictions(symbol, classifierName);

//            for (PriceBean bean: list2) {
//                System.out.println(bean.toString());
//            }

                insertPredictions(symbol, list2);

                System.out.println("predictions output finished.");

//            for (PriceBean bean: list2) {
//                System.out.println(bean.toString());
//            }
            }

            updateStatus(symbol, ProcessStatus.COMPLETE);
            System.out.println("process END");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    private void insertEvalMeasure(String classifier, List<EvalMeasure> list) {

        ClassifierTbl classifierEntity = dataManager.load(ClassifierTbl.class).id(classifier).one();
        for (EvalMeasure evalMeasure: list) {

            System.out.println(evalMeasure.toString());

            TargetTbl targetEntity = dataManager.load(TargetTbl.class).id(evalMeasure.getTarget()).one();

            EvalTblCompKey key =  new EvalTblCompKey();
            key.setClassifier(evalMeasure.getClassifier());
            key.setSymbol(evalMeasure.getSymbol());
            key.setTarget(evalMeasure.getTarget());
            key.setTestortrain(true);

            EvalTbl evalTbl = dataManager.load(EvalTbl.class)
                    .id(key)
                    .optional()
                    .orElse(dataManager.create(EvalTbl.class));

            evalTbl.setId(key);

            evalTbl.setN(evalMeasure.getN().longValue());

            evalTbl.setTarget(targetEntity);

            // classifier foreign key
            evalTbl.setClassifier(classifierEntity);

            // totally 7 measures
            evalTbl.setDac(evalMeasure.getDAC());
            evalTbl.setMae(evalMeasure.getMAE());
            evalTbl.setMape(evalMeasure.getMAPE());
            evalTbl.setMse(evalMeasure.getMSE());
            evalTbl.setRae(evalMeasure.getRAE());
            evalTbl.setRrse(evalMeasure.getRRSE());
            evalTbl.setRmse(evalMeasure.getRMSE());

            // create and update time
            evalTbl.setCreateTime(new Date());
            evalTbl.setUpdateTime(new Date());

            dataManager.save(evalTbl);
        }

    }

    @Transactional
    private void insertPredictions(String symbol, List<PriceBean> list) {


        for (PriceBean priceBean: list) {

            ClassifierTbl classifierEntity = dataManager.load(ClassifierTbl.class).id(priceBean.getClassifier()).one();

            PriceTblCompKey priceTblCompKey = new PriceTblCompKey();
            priceTblCompKey.setClassifier(priceBean.getClassifier());
            priceTblCompKey.setSymbol(symbol);
            priceTblCompKey.setValueDate(priceBean.getTradeDate());

            PriceTbl priceTbl = dataManager.load(PriceTbl.class)
                    .id(priceTblCompKey).optional().orElse(dataManager.create(PriceTbl.class));

            priceTbl.setId(priceTblCompKey);

            priceTbl.setClassifier(classifierEntity);

            priceTbl.setOpen(priceBean.getOpen());
            priceTbl.setHigh(priceBean.getHigh());
            priceTbl.setLow(priceBean.getLow());
            priceTbl.setClose(priceBean.getClose());
            priceTbl.setAdjclose(priceBean.getAdjClose());
            priceTbl.setVolume(priceBean.getVolume().longValue());
            priceTbl.setCreateTime(new Date());
            priceTbl.setUpdateTime(new Date());
            dataManager.save(priceTbl);
        }
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

    public boolean fetchQuote(String symbol, String startDate, String endDate) {
        return true;
    }

}