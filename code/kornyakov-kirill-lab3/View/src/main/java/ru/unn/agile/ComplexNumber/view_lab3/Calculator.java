package ru.unn.agile.ComplexNumber.view_lab3;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.unn.agile.ComplexNumber.model_lab3.ComplexNumber.Operation;
import ru.unn.agile.ComplexNumber.viewmodel_lab3.ViewModel;
import ru.unn.agile.ComplexNumber.infrastructure_lab3.TxtLogger;

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
        viewModel.setLogger(new TxtLogger("./TxtLogger-lab3.log"));

        final ChangeListener<Boolean> focusChangeListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(final ObservableValue<? extends Boolean> observable,
                                final Boolean oldValue, final Boolean newValue) {
                viewModel.onFocusChanged(oldValue, newValue);
            }
        };
        // two-way binding hasn't supported by fxml yet, so place it in code-behind
        txtZ1Re.textProperty().bindBidirectional(viewModel.re1Property());
        txtZ1Re.focusedProperty().addListener(focusChangeListener);

        txtZ1Im.textProperty().bindBidirectional(viewModel.im1Property());
        txtZ1Im.focusedProperty().addListener(focusChangeListener);

        txtZ2Re.textProperty().bindBidirectional(viewModel.re2Property());
        txtZ2Re.focusedProperty().addListener(focusChangeListener);

        txtZ2Im.textProperty().bindBidirectional(viewModel.im2Property());
        txtZ2Im.focusedProperty().addListener(focusChangeListener);

        cbOperation.valueProperty().bindBidirectional(viewModel.operationProperty());
        cbOperation.valueProperty().addListener(new ChangeListener<Operation>() {
            @Override
            public void changed(final ObservableValue<? extends Operation> observable,
                                final Operation oldValue,
                                final Operation newValue) {
                viewModel.onOperationChanged(oldValue, newValue);
            }
        });

        btnCalc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });
    }
}
