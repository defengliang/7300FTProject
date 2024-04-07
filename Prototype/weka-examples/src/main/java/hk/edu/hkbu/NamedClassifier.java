package hk.edu.hkbu;

import weka.classifiers.Classifier;

public class NamedClassifier {

    private Classifier classifier;
    private String name;

    public NamedClassifier(String name, Classifier classifier) {
        this.classifier = classifier;
        this.name = name;
    }

    public Classifier getClassifier() {
        return classifier;
    }

    public void setClassifier(Classifier classifier) {
        this.classifier = classifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
