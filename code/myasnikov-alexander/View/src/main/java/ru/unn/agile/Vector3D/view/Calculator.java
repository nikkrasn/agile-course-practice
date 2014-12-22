package ru.unn.agile.Vector3D.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import ru.unn.agile.Vector3D.viewmodel.*;
import ru.unn.agile.Vector3D.infrastructure.*;

public class Calculator {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField vec1X;
    @FXML
    private TextField vec1Y;
    @FXML
    private TextField vec1Z;
    @FXML
    private TextField vec2X;
    @FXML
    private TextField vec2Y;
    @FXML
    private TextField vec2Z;
    @FXML
    private Label status;
    @FXML
    private ComboBox<VectorOperation> operation;
    @FXML
    private Button solve;

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

        vec1X.textProperty().bindBidirectional(viewModel.getVector1CoordinateX());
        vec1X.focusedProperty().addListener(focusChangeListener);
        vec1Y.textProperty().bindBidirectional(viewModel.getVector1CoordinateY());
        vec1Y.focusedProperty().addListener(focusChangeListener);
        vec1Z.textProperty().bindBidirectional(viewModel.getVector1CoordinateZ());
        vec1Z.focusedProperty().addListener(focusChangeListener);

        vec2X.textProperty().bindBidirectional(viewModel.getVector2CoordinateX());
        vec2X.focusedProperty().addListener(focusChangeListener);
        vec2Y.textProperty().bindBidirectional(viewModel.getVector2CoordinateY());
        vec2Y.focusedProperty().addListener(focusChangeListener);
        vec2Z.textProperty().bindBidirectional(viewModel.getVector2CoordinateZ());
        vec2Z.focusedProperty().addListener(focusChangeListener);

        status.textProperty().bindBidirectional(viewModel.statusProperty());

        operation.valueProperty().bindBidirectional(viewModel.operationProperty());
        operation.valueProperty().addListener(new ChangeListener<VectorOperation>() {
            @Override
            public void changed(final ObservableValue<? extends VectorOperation> observable,
                                final VectorOperation oldValue,
                                final VectorOperation newValue) {
                viewModel.onOperationChanged(oldValue, newValue);
            }
        });

        solve.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });
    }
}
