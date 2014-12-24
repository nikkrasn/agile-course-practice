package ru.unn.agile.StatisticalValues.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pairs {
    private final StringProperty firstComponent = new SimpleStringProperty();
    private final StringProperty secondComponent = new SimpleStringProperty();

    public Pairs(final String a, final String b) {
        firstComponent.set(a);
        secondComponent.set(b);
    }
    public String getFirstComponent() {
        return firstComponent.get();
    }
    public String getSecondComponent() {
        return secondComponent.get();
    }
    public Pairs setFirstComponent(final String string) {
        firstComponent.set(string);
        return this;
    }
    public Pairs setSecondComponent(final String string) {
        secondComponent.set(string);
        return this;
    }
    public Boolean equals(final Pairs components) {
        return components.getFirstComponent().equals(firstComponent.get())
                && components.getSecondComponent().equals(secondComponent.get());
    }
}
