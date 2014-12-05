package ru.unn.agile.CalculateSquare.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.CalculateSquare.viewmodel.ViewModel;

import java.awt.*;

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

        txtParam1.textProperty().bindBidirectional(viewModel.txtParam1Property());
        txtParam2.textProperty().bindBidirectional(viewModel.txtParam2Property());

        cbOperation.valueProperty().bindBidirectional(viewModel.operationProperty());

        btnCalc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });



    }
}
