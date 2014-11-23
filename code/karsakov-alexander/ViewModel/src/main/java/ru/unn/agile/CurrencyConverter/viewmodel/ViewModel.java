package ru.unn.agile.CurrencyConverter.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.CurrencyConverter.Model.CurrencyIndexes;
import ru.unn.agile.CurrencyConverter.viewmodel.ViewModelStatus;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty inputValue = new SimpleStringProperty();

    private final ObjectProperty<ObservableList<CurrencyIndexes>> fromCurrencyList =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(CurrencyIndexes.values()));
    private final ObjectProperty<CurrencyIndexes> fromCurrency = new SimpleObjectProperty<>();

    private final ObjectProperty<ObservableList<CurrencyIndexes>> toCurrencyList =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(CurrencyIndexes.values()));
    private final ObjectProperty<CurrencyIndexes> toCurrency = new SimpleObjectProperty<>();

    private final BooleanProperty convertButtonDisabled = new SimpleBooleanProperty();

    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty resultCurrency = new SimpleStringProperty();

    private final StringProperty status = new SimpleStringProperty();

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    // FXML needs default c-tor for binding
    public ViewModel() {
        inputValue.set("");
        result.set("");
        resultCurrency.setValue("");

        fromCurrency.setValue(CurrencyIndexes.RUB);
        toCurrency.setValue(CurrencyIndexes.USD);

        status.set(ViewModelStatus.WAITING.toString());

        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(inputValue);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == ViewModelStatus.READY;
            }
        };
        convertButtonDisabled.bind(couldCalculate.not());

        // Add listeners to the input text fields
        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(inputValue);
        } };

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }

    }

    public StringProperty inputValueProperty() {
        return inputValue;
    }

    public ObjectProperty<ObservableList<CurrencyIndexes>> fromCurrencyListProperty() {
        return fromCurrencyList;
    }

    public ObservableList<CurrencyIndexes> getFromCurrencyList() {
        return fromCurrencyList.get();
    }

    public ObjectProperty<CurrencyIndexes> fromCurrencyProperty() {
        return fromCurrency;
    }

    public ObjectProperty<ObservableList<CurrencyIndexes>> toCurrencyListProperty() {
        return toCurrencyList;
    }

    public ObservableList<CurrencyIndexes> getToCurrencyList() {
        return toCurrencyList.get();
    }

    public ObjectProperty<CurrencyIndexes> toCurrencyProperty() {
        return toCurrency;
    }

    public BooleanProperty convertDisabledProperty() {
        return convertButtonDisabled;
    }
    public final boolean getCalculationDisabled() {
        return convertButtonDisabled.get();
    }

    public StringProperty resultProperty() {
        return result;
    }

    public StringProperty resultCurrencyProperty() {
        return resultCurrency;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public final String getStatus() {
        return status.get();
    }

    public void convert() {

    }

    private ViewModelStatus getInputStatus() {
        ViewModelStatus inputStatus = ViewModelStatus.READY;

        if (inputValue.get().isEmpty()) {
            inputStatus = ViewModelStatus.WAITING;
        }

        try {
            if (!inputValue.get().isEmpty()) {
                Double.parseDouble(inputValue.get());
            }
        } catch (NumberFormatException e) {
            inputStatus = ViewModelStatus.BAD_FORMAT;
        }
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
