package ru.unn.agile.Stack.ViewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ViewModel {
    private final StringProperty top = new SimpleStringProperty();
    private final StringProperty push = new SimpleStringProperty();
    public StringProperty top() {
        return top;
    }
    public StringProperty push() {
        return push;
    }

    public ViewModel() {
        top.set("");
        push.set("Push me!");
    }
}
