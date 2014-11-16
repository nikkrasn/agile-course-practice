package ru.unn.agile.ComplexNumber.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.ComplexNumber.model.ComplexNumber.Operation;
import ru.unn.agile.ComplexNumber.viewmodel.ViewModel;

public class Calculator {
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
    private ComboBox<Operation> cbOperation;
    @FXML
    private Button btnCalc;

    @FXML
    void initialize() {

        // Two-way binding hasn't supported by FXML yet, so place it in code-behind
        txtZ1Re.textProperty().bindBidirectional(viewModel.re1Property());
        txtZ1Im.textProperty().bindBidirectional(viewModel.im1Property());
        txtZ2Re.textProperty().bindBidirectional(viewModel.re2Property());
        txtZ2Im.textProperty().bindBidirectional(viewModel.im2Property());

        cbOperation.valueProperty().bindBidirectional(viewModel.operationProperty());

        btnCalc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });
    }
}
