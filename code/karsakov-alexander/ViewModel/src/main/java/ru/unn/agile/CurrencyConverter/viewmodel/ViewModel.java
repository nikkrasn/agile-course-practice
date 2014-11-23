package ru.unn.agile.CurrencyConverter.viewmodel;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.unn.agile.CurrencyConverter.viewmodel.ViewModelStatus;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty status = new SimpleStringProperty();

    // FXML needs default c-tor for binding
    public ViewModel() {

    }

    public void convert() {

    }

    private ViewModelStatus getInputStatus() {
        ViewModelStatus inputStatus = ViewModelStatus.READY;
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
