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

public class ViewModel {
    private final StringProperty inputValue = new SimpleStringProperty("");

    private final ObjectProperty<ObservableList<Currency>> fromCurrencyList =
            new SimpleObjectProperty<>();
    private final ObjectProperty<Currency> fromCurrency = new SimpleObjectProperty<>();

    private final ObjectProperty<ObservableList<Currency>> toCurrencyList =
            new SimpleObjectProperty<>();
    private final ObjectProperty<Currency> toCurrency = new SimpleObjectProperty<>();

    private final BooleanProperty convertButtonDisabled = new SimpleBooleanProperty();

    private final StringProperty result = new SimpleStringProperty("");
    private final StringProperty resultCurrency = new SimpleStringProperty("");

    private final StringProperty status =
            new SimpleStringProperty(ViewModelStatus.WAITING.toString());

    private final StringProperty log = new SimpleStringProperty("");

    private ILogger logger;

    private void init() {
        ICurrencyProvider provider = new FixedCurrencyProvider();
        ArrayList<Currency> actualRates = provider.getActualCurrencyRates();
        fromCurrencyList.set(FXCollections.observableArrayList(actualRates));
        toCurrencyList.set(FXCollections.observableArrayList(actualRates));
        fromCurrency.set(actualRates.get(CurrencyIndexes.RUB.getIndex()));
        toCurrency.set(actualRates.get(CurrencyIndexes.USD.getIndex()));

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

        // Add listener to the input text field
        final ChangeListener<String> inputValueListener = new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable,
                                final String oldValue, final String newValue) {
                status.set(getInputStatus().toString());
            }
        };
        inputValue.addListener(inputValueListener);
    }

    public ViewModel() {
        init();
    }

    public ViewModel(ILogger logger) {
        init();
        setLogger(logger);
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

    public StringProperty logProperty() {
        return log;
    }

    public final String getLog() {
        return log.get();
    }

    public void setLogger(ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger can't be null");
        }
        this.logger = logger;
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
}

enum ViewModelStatus {
    WAITING("Please enter amount of money"),
    READY("Please select convert mode and press 'Convert'"),
    BAD_FORMAT("Bad format"),
    SUCCESS("Success");

    private final String name;
    private ViewModelStatus(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
