package ru.unn.agile.CalculateSquare.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.CalculateSquare.infrastructure.TextLog;
import ru.unn.agile.CalculateSquare.viewmodel.ViewModel;

public class Calculator {
    @FXML
    private ViewModel viewModel;

    @FXML
    private TextField txtParam1;

    @FXML
    private TextField txtParam2;

    @FXML
    private Button btnCalc;

    @FXML
    private ComboBox<String> cbOperation;

    @FXML
    void initialize() {
        viewModel.setLog(new TextLog("./txtLog.log"));

        final ChangeListener<Boolean> focusChangingListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(final ObservableValue<? extends Boolean> observable,
                                final Boolean oldValue, final Boolean newValue) {
                viewModel.onFocusChanged(oldValue, newValue);
            }
        };

        txtParam1.textProperty().bindBidirectional(viewModel.txtParam1Property());
        txtParam1.focusedProperty().addListener(focusChangingListener);
        txtParam2.textProperty().bindBidirectional(viewModel.txtParam2Property());
        txtParam2.focusedProperty().addListener(focusChangingListener);


        cbOperation.valueProperty().bindBidirectional(viewModel.operationProperty());
        cbOperation.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable,
                                final String oldValue,
                                final String newValue) {
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
