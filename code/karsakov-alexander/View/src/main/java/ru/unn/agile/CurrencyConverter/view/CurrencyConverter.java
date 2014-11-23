package ru.unn.agile.CurrencyConverter.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import ru.unn.agile.CurrencyConverter.Model.Currency;
import ru.unn.agile.CurrencyConverter.viewmodel.ViewModel;

public class CurrencyConverter {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField valueTextField;
    @FXML
    private TextField resultTextField;
    @FXML
    private Label resultCurrencyLabel;
    @FXML
    private Label statusLabelText;
    @FXML
    private ComboBox<Currency> fromCurrencyComboBox;
    @FXML
    private ComboBox<Currency> toCurrencyComboBox;
    @FXML
    private Button convertButton;


    @FXML
    void initialize() {
        // Two-way binding hasn't supported by FXML yet, so place it in code-behind
        valueTextField.textProperty().bindBidirectional(viewModel.inputValueProperty());
        fromCurrencyComboBox.valueProperty().bindBidirectional(viewModel.fromCurrencyProperty());
        toCurrencyComboBox.valueProperty().bindBidirectional(viewModel.toCurrencyProperty());

        convertButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.convert();
            }
        });
    }
}
