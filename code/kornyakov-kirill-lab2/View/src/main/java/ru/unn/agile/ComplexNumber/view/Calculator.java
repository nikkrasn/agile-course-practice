package ru.unn.agile.ComplexNumber.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.unn.agile.ComplexNumber.viewmodel.MockLogger;
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
    private ComboBox<ViewModel.Operation> cbOperation;
    @FXML
    private Button btnCalc;

    @FXML
    void initialize() {
        viewModel.setLogger(new MockLogger());

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
        cbOperation.valueProperty().addListener(new ChangeListener<ViewModel.Operation>() {
            @Override
            public void changed(final ObservableValue<? extends ViewModel.Operation> observable,
                                final ViewModel.Operation oldValue,
                                final ViewModel.Operation newValue) {
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
