package ru.unn.agile.Deque.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ViewModel {
    private final StringProperty txtValue = new SimpleStringProperty();

    public ViewModel() {
        txtValue.set("");
    }

    public StringProperty txtValueProperty() {
        return txtValue;
    }

    public final String getTxtValue() {
        return txtValue.get();
    }
}
