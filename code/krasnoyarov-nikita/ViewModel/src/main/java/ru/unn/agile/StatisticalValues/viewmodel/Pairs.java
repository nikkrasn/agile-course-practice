package ru.unn.agile.StatisticalValues.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pairs {
    private final StringProperty probability = new SimpleStringProperty();
    private final StringProperty value = new SimpleStringProperty();

    public Pairs(final String a, final String b) {
        probability.set(a);
        value.set(b);
    }
    public String getProbability() {
        return probability.get();
    }
    public String getValue() {
        return value.get();
    }
    public Pairs setFirstComponent(final String string) {
        probability.set(string);
        return this;
    }
    public Pairs setSecondComponent(final String string) {
        value.set(string);
        return this;
    }
    public Boolean equals(final Pairs components) {
        return components.getProbability().equals(probability.get())
                && components.getValue().equals(value.get());
    }
}
