package ru.unn.agile.Queue.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ViewModel {
    private final StringProperty txtToAdd = new SimpleStringProperty();

    ViewModel() {
        txtToAdd.set("");
    }

    public StringProperty txtToAddProperty() {
        return txtToAdd;
    }

    public final String getTxtToAdd() {
        return txtToAdd.get();
    }
}
