package ru.unn.agile.DeterminatorIntersection.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.DeterminatorIntersection.viewmodel.ViewModel;

import java.awt.*;

public class Determinator {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtPlaneA;
    @FXML
    private TextField txtPlaneB;
    @FXML
    private TextField txtPlaneC;
    @FXML
    private TextField txtPlaneD;
    @FXML
    private TextField txtLinePointX;
    @FXML
    private TextField txtLinePointY;
    @FXML
    private TextField txtLinePointZ;
    @FXML
    private TextField txtLineVectorX;
    @FXML
    private TextField txtLineVectorY;
    @FXML
    private TextField txtLineVectorZ;
    @FXML
    private Button btnDet;

    @FXML
    void initialize() {
        txtPlaneA.textProperty().bindBidirectional(viewModel.planeAProperty());
        txtPlaneB.textProperty().bindBidirectional(viewModel.planeBProperty());
        txtPlaneC.textProperty().bindBidirectional(viewModel.planeCProperty());
        txtPlaneD.textProperty().bindBidirectional(viewModel.planeDProperty());

        txtLinePointX.textProperty().bindBidirectional(viewModel.getLinePXProperty());
        txtLinePointY.textProperty().bindBidirectional(viewModel.getLinePYProperty());
        txtLinePointZ.textProperty().bindBidirectional(viewModel.getLinePZProperty());

        txtLineVectorX.textProperty().bindBidirectional(viewModel.getLineVXProperty());
        txtLineVectorY.textProperty().bindBidirectional(viewModel.getLineVYProperty());
        txtLineVectorZ.textProperty().bindBidirectional(viewModel.getLineVZProperty());

        btnDet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.determinate();
            }
        });
    }
}
