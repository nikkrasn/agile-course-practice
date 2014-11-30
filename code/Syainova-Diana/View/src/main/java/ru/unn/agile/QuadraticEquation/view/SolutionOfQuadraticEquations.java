package ru.unn.agile.QuadraticEquation.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.QuadraticEquation.viewmodel.ViewModel;

public class SolutionOfQuadraticEquations {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtCoefA;
    @FXML
    private TextField txtCoefB;
    @FXML
    private TextField txtCoefC;
    @FXML
    private Button btnSolve;

    @FXML
    void initialize() {

        txtCoefA.textProperty().bindBidirectional(viewModel.firstCoefficientProperty());
        txtCoefB.textProperty().bindBidirectional(viewModel.secondCoefficientProperty());
        txtCoefC.textProperty().bindBidirectional(viewModel.freeTermProperty());

        btnSolve.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.solve();
            }
        });
    }
}
