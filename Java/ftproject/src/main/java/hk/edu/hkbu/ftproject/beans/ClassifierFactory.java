package hk.edu.hkbu.ftproject.beans;

import weka.classifiers.Classifier;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.RandomForest;
import weka.classifiers.trees.RandomTree;
import weka.core.EuclideanDistance;
import weka.core.neighboursearch.LinearNNSearch;

public class ClassifierFactory {

    public static String[] classifier = new String[] {
            "IBK",
            "RandomTree",
            "RandomForest"
    };

    public static NamedClassifier generateClassifier(String name) {

        Classifier classifier;
        switch (name) {
            case "IBK":
                classifier = getIBKClassifier();
                break;
            case "RandomTree":
                classifier = getRandomTreeClassifier();
                break;
            case "RandomForest":
                classifier = getRandomForestClassifier();
                break;
            default:
                classifier = null;
        }

        NamedClassifier namedClassifier = new NamedClassifier(name, classifier);
        return namedClassifier;
    }

    /**
     * IBk
     * @return
     */
    private static IBk getIBKClassifier() {
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
        return iBkClassifier;
    }


    /**
     * RandomTree
     * @return
     */
    private static RandomTree getRandomTreeClassifier() {
        RandomTree classifier = new RandomTree();
        classifier.setKValue(0);
        classifier.setBatchSize("100");
        classifier.setBreakTiesRandomly(false);
        classifier.setMaxDepth(0);
        classifier.setMinNum(1.0);
        classifier.setMinVarianceProp(0.01);
        classifier.setNumDecimalPlaces(6);
        classifier.setNumFolds(0);
        classifier.setSeed(1);

        return classifier;
    }

    /**
     * RandomForest
     * @return
     */
    private static RandomForest getRandomForestClassifier() {
        RandomForest classifier = new RandomForest();
        classifier.setBagSizePercent(100);
        classifier.setBatchSize("100");
        classifier.setBreakTiesRandomly(true);
        classifier.setCalcOutOfBag(true);
        classifier.setComputeAttributeImportance(true);
        classifier.setMaxDepth(0);
        classifier.setNumDecimalPlaces(6);
        classifier.setNumExecutionSlots(1);
        classifier.setNumFeatures(0);
        classifier.setNumIterations(100);
        classifier.setOutputOutOfBagComplexityStatistics(false);
        classifier.setSeed(1);
        classifier.setStoreOutOfBagPredictions(false);
        return classifier;
    }

}
