package ru.unn.agile.Stack.ViewModel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ViewModel {
    private final StringProperty top = new SimpleStringProperty();
    private final StringProperty push = new SimpleStringProperty();
    private final BooleanProperty isEmpty = new SimpleBooleanProperty();
    public StringProperty topProperty() {
        return top;
    }
    public StringProperty pushProperty() {
        return push;
    }
    public BooleanProperty isEmptyProperty() {
        return isEmpty;
    }

    public ViewModel() {
        top.set("");
        push.set("Push me!");
        isEmpty.set(false);
    }
}
