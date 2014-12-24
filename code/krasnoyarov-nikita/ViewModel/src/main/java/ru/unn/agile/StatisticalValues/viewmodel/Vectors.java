package ru.unn.agile.StatisticalValues.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Vectors {
    private final StringProperty firstComponent = new SimpleStringProperty();
    private final StringProperty secondComponent = new SimpleStringProperty();

    public Vectors(final String a, final String b) {
        firstComponent.set(a);
        secondComponent.set(b);
    }
    public String getFirstComponent() {
        return firstComponent.get();
    }
    public String getSecondComponent() {
        return secondComponent.get();
    }
    public Vectors setFirstComponent(final String string) {
        firstComponent.set(string);
        return this;
    }
    public Vectors setSecondComponent(final String string) {
        secondComponent.set(string);
        return this;
    }
    public Boolean equals(final Vectors components) {
        return components.getFirstComponent().equals(firstComponent.get())
                && components.getSecondComponent().equals(secondComponent.get());
    }
}
