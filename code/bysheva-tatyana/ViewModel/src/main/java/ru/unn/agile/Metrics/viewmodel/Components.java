package ru.unn.agile.Metrics.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Components {
    public final StringProperty component1 = new SimpleStringProperty();
    public final StringProperty component2 = new SimpleStringProperty();

    public Components(String a, String b) {
        component1.set(a);
        component2.set(b);
    }
    public String getComponent1() {
        return component1.get();
    }
    public String getComponent2() {
        return component2.get();
    }
    public void setComponent1(String string) {
        component1.set(string);
    }
    public void  setComponent2(String string) {
        component2.set(string);
    }
}
