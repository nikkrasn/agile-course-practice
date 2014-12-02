package ru.unn.agile.Triangle.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.Triangle.viewmodel.ViewModel;
import ru.unn.agile.Triangle.Model.Triangle.Operation;

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
        txtAX.textProperty().bindBidirectional(viewModel.aXProperty());
        txtAY.textProperty().bindBidirectional(viewModel.aYProperty());
        txtBX.textProperty().bindBidirectional(viewModel.bXProperty());
        txtBY.textProperty().bindBidirectional(viewModel.bYProperty());
        txtCX.textProperty().bindBidirectional(viewModel.cXProperty());
        txtCY.textProperty().bindBidirectional(viewModel.cYProperty());

        cbComputation.valueProperty().bindBidirectional(viewModel.operationProperty());

        btnCompute.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.compute();
            }
        });
    }
}
