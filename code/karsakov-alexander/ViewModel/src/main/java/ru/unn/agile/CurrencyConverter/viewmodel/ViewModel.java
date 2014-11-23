package ru.unn.agile.CurrencyConverter.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.CurrencyConverter.Model.Currency;
import ru.unn.agile.CurrencyConverter.Model.CurrencyIndexes;
import ru.unn.agile.CurrencyConverter.Model.Money;
import ru.unn.agile.CurrencyConverter.Provider.FixedCurrencyProvider;
import ru.unn.agile.CurrencyConverter.Provider.ICurrencyProvider;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private ArrayList<Currency> actualRates;

    private final StringProperty inputValue = new SimpleStringProperty();

    private final ObjectProperty<ObservableList<Currency>> fromCurrencyList =
            new SimpleObjectProperty<>();
    private ObjectProperty<Currency> fromCurrency = new SimpleObjectProperty<>();

    private final ObjectProperty<ObservableList<Currency>> toCurrencyList =
            new SimpleObjectProperty<>();
    private ObjectProperty<Currency> toCurrency = new SimpleObjectProperty<>();

    private final BooleanProperty convertButtonDisabled = new SimpleBooleanProperty();

    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty resultCurrency = new SimpleStringProperty();

    private final StringProperty status = new SimpleStringProperty();

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    // FXML needs default c-tor for binding
    public ViewModel() {
        inputValue.set("");
        result.set("");
        resultCurrency.set("");

        ICurrencyProvider provider = new FixedCurrencyProvider();
        actualRates = provider.getActualCurrencyRates();
        fromCurrencyList.set(FXCollections.observableArrayList(actualRates));
        toCurrencyList.set(FXCollections.observableArrayList(actualRates));
        fromCurrency.set(actualRates.get(CurrencyIndexes.RUB.getIndex()));
        toCurrency.set(actualRates.get(CurrencyIndexes.USD.getIndex()));

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

    public ObjectProperty<ObservableList<Currency>> fromCurrencyListProperty() {
        return fromCurrencyList;
    }

    public ObservableList<Currency> getFromCurrencyList() {
        return fromCurrencyList.get();
    }

    public ObjectProperty<Currency> fromCurrencyProperty() {
        return fromCurrency;
    }

    public ObjectProperty<ObservableList<Currency>> toCurrencyListProperty() {
        return toCurrencyList;
    }

    public ObservableList<Currency> getToCurrencyList() {
        return toCurrencyList.get();
    }

    public ObjectProperty<Currency> toCurrencyProperty() {
        return toCurrency;
    }

    public BooleanProperty convertButtonDisabledProperty() {
        return convertButtonDisabled;
    }
    public final boolean getConvertButtonDisabled() {
        return convertButtonDisabled.get();
    }

    public StringProperty resultProperty() {
        return result;
    }

    public final String getResult() {
        return result.get();
    }

    public StringProperty resultCurrencyProperty() {
        return resultCurrency;
    }

    public final String getResultCurrency() {
        return resultCurrency.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public final String getStatus() {
        return status.get();
    }

    public void convert() {
        if (getConvertButtonDisabled()) {
            return;
        }

        double amount = Double.parseDouble(inputValue.get());

        Money inputMoney = new Money(fromCurrency.get(), amount);
        Money convertedMoney = inputMoney.convertToCurrency(toCurrency.get());

        result.set(String.format("%.5f", convertedMoney.getAmount()));
        resultCurrency.set(toCurrency.get().getCharCode());
        status.set(ViewModelStatus.SUCCESS.toString());
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
