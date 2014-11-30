package ru.unn.agile.Deque.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ViewModel {
    private final StringProperty txtValue = new SimpleStringProperty();
    private final StringProperty status   = new SimpleStringProperty();

    private final ValueChangeListener valueChangedListener = new ValueChangeListener();

    public ViewModel() {
        txtValue.set("");
        status.set(Status.WAITING.toString());

        txtValue.addListener(valueChangedListener);
    }

    public void addFirst() {
        return;
    }

    public StringProperty txtValueProperty() {
        return txtValue;
    }

    public final String getTxtValue() {
        return txtValue.get();
    }

    public StringProperty statusProperty() {
        return status;
    }
    public final String getStatus() {
        return status.get();
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        return inputStatus;
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getInputStatus().toString());
        }
    }
}

enum Status {
    WAITING("Please provide input data"),
    READY("Press any button"),
    BAD_FORMAT("Bad format"),
    SUCCESS("Success");

    private final String name;
    private Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}