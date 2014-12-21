package ru.unn.agile.CurrencyConverter.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.CurrencyConverter.Model.Currency;
import ru.unn.agile.CurrencyConverter.viewmodel.ViewModel;
import ru.unn.agile.CurrencyConverter.viewmodel.

public class CurrencyConverter {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField valueTextField;
    @FXML
    private ComboBox<Currency> fromCurrencyComboBox;
    @FXML
    private ComboBox<Currency> toCurrencyComboBox;
    @FXML
    private Button convertButton;

    @FXML
    void initialize() {
        //viewModel.setLogger(new MockLogger());

        ChangeListener<Currency> currencyModeListener = new ChangeListener<Currency>() {
            @Override
            public void changed(final ObservableValue<? extends Currency> observable,
                                final Currency oldValue,
                                final Currency newValue) {
                viewModel.onCurrencyConvertModeChanged(oldValue, newValue);
            }
        };

        valueTextField.textProperty().bindBidirectional(viewModel.inputValueProperty());
        fromCurrencyComboBox.valueProperty().bindBidirectional(viewModel.fromCurrencyProperty());
        fromCurrencyComboBox.valueProperty().addListener(currencyModeListener);
        toCurrencyComboBox.valueProperty().bindBidirectional(viewModel.toCurrencyProperty());
        toCurrencyComboBox.valueProperty().addListener(currencyModeListener);

        convertButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.convert();
            }
        });
    }
}
