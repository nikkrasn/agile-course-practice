package ru.unn.agile.ComplexNumber.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ru.unn.agile.ComplexNumber.viewmodel.ViewModel;

public class Calculator {
    @FXML
    private TextField txtZ1Re;
    @FXML
    private TextField txtZ1Im;
    @FXML
    private TextField txtZ2Re;
    @FXML
    private TextField txtZ2Im;
    @FXML
    private ComboBox cbOperation;
    @FXML
    private Button btnCalc;
    @FXML
    private Label lbResult;
    @FXML
    private Label lbStatus;

    @FXML
    @SuppressWarnings("unchecked") // FIXME
    void initialize() {
        ViewModel viewModel = new ViewModel();
        txtZ1Re.textProperty().bindBidirectional(viewModel.re1Property());
        txtZ1Im.textProperty().bindBidirectional(viewModel.im1Property());
        txtZ2Re.textProperty().bindBidirectional(viewModel.re2Property());
        txtZ2Im.textProperty().bindBidirectional(viewModel.im2Property());
        cbOperation.itemsProperty().bind(viewModel.operationsProperty());
        btnCalc.disableProperty().bind(viewModel.isCalculationPossibleProperty().not());
    }
}
