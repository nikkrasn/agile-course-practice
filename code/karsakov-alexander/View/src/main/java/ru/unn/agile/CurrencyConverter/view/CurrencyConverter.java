package ru.unn.agile.CurrencyConverter.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.CurrencyConverter.Model.CurrencyIndexes;
import ru.unn.agile.CurrencyConverter.viewmodel.ViewModel;

public class CurrencyConverter {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtZ1Re;
    @FXML
    private TextField txtZ1Im;
    @FXML
    private TextField txtZ2Re;
    @FXML
    private TextField txtZ2Im;
    @FXML
    private ComboBox<CurrencyIndexes> fromCurrency;
    @FXML
    private ComboBox<CurrencyIndexes> toCurrency;
    @FXML
    private Button btnCalc;

    @FXML
    void initialize() {

        // Two-way binding hasn't supported by FXML yet, so place it in code-behind

        btnCalc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.convert();
            }
        });
    }
}
