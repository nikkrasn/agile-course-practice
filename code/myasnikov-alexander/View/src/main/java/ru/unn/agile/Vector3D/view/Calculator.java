package ru.unn.agile.Vector3D.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import ru.unn.agile.Vector3D.viewmodel.*;

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
        // Two-way binding hasn't supported by FXML yet, so place it in code-behind
        vec1X.textProperty().bindBidirectional(viewModel.getVector1CoordinateX());
        vec1Y.textProperty().bindBidirectional(viewModel.getVector1CoordinateY());
        vec1Z.textProperty().bindBidirectional(viewModel.getVector1CoordinateZ());

        vec2X.textProperty().bindBidirectional(viewModel.getVector2CoordinateX());
        vec2Y.textProperty().bindBidirectional(viewModel.getVector2CoordinateY());
        vec2Z.textProperty().bindBidirectional(viewModel.getVector2CoordinateZ());

        status.textProperty().bindBidirectional(viewModel.statusProperty());

        operation.valueProperty().bindBidirectional(viewModel.operationProperty());

        solve.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });
    }
}
