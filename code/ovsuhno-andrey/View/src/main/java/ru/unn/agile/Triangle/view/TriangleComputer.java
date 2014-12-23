package ru.unn.agile.Triangle.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.Triangle.viewmodel.ViewModel;
import ru.unn.agile.Triangle.Model.Triangle.Operation;
import ru.unn.agile.Triangle.Infrastructure.RecordsLogger;

public class TriangleComputer {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtAX;
    @FXML
    private TextField txtAY;
    @FXML
    private TextField txtBX;
    @FXML
    private TextField txtBY;
    @FXML
    private TextField txtCX;
    @FXML
    private TextField txtCY;
    @FXML
    private ComboBox<Operation> cbComputation;
    @FXML
    private Button btnCompute;

    @FXML
    void initialize() {
        viewModel.setLog(new RecordsLogger("./TxtLogger-lab3.log"));

        final ChangeListener<Boolean> inputValuesListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(final ObservableValue<? extends Boolean> observable,
                                final Boolean oldValue, final Boolean newValue) {
                viewModel.onFocusChanged(oldValue, newValue);
            }
        };

        final ChangeListener<Operation> operationChooseListener = new ChangeListener<Operation>() {
            @Override
            public void changed(final ObservableValue<? extends Operation> observable,
                                final Operation oldValue, final Operation newValue) {
                viewModel.onOperationChanged(oldValue, newValue);
            }
        };

        txtAX.textProperty().bindBidirectional(viewModel.aXProperty());
        txtAX.focusedProperty().addListener(inputValuesListener);

        txtAY.textProperty().bindBidirectional(viewModel.aYProperty());
        txtAY.focusedProperty().addListener(inputValuesListener);

        txtBX.textProperty().bindBidirectional(viewModel.bXProperty());
        txtBX.focusedProperty().addListener(inputValuesListener);

        txtBY.textProperty().bindBidirectional(viewModel.bYProperty());
        txtBY.focusedProperty().addListener(inputValuesListener);

        txtCX.textProperty().bindBidirectional(viewModel.cXProperty());
        txtCX.focusedProperty().addListener(inputValuesListener);

        txtCY.textProperty().bindBidirectional(viewModel.cYProperty());
        txtCY.focusedProperty().addListener(inputValuesListener);

        cbComputation.valueProperty().bindBidirectional(viewModel.operationProperty());
        cbComputation.valueProperty().addListener(operationChooseListener);


        btnCompute.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.compute();
            }
        });
    }
}
