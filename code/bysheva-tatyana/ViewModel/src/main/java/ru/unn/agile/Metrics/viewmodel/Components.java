package ru.unn.agile.Metrics.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Components {
    private final StringProperty component1 = new SimpleStringProperty();
    private final StringProperty component2 = new SimpleStringProperty();

    public Components(final String a, final String b) {
        component1.set(a);
        component2.set(b);
    }
    public String getComponent1() {
        return component1.get();
    }
    public String getComponent2() {
        return component2.get();
    }
    public Components setComponent1(final String string) {
        component1.set(string);
        return this;
    }
    public Components setComponent2(final String string) {
        component2.set(string);
        return this;
    }
    public Boolean equals(final Components components) {
        return components.getComponent1().equals(component1.get())
                && components.getComponent2().equals(component2.get());
    }
}
