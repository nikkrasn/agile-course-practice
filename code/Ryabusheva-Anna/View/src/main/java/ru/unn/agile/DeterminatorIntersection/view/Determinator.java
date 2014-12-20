package ru.unn.agile.DeterminatorIntersection.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.DeterminatorIntersection.viewmodel.ViewModel;
import ru.unn.agile.DeterminatorIntersection.infrastructure.TxtLogger;

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
        viewModel.setLogger(new TxtLogger("./TxtLogger-lab3.log"));

        final ChangeListener<Boolean> focusChangeListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(final ObservableValue<? extends Boolean> observable,
                                final Boolean oldValue, final Boolean newValue) {
                viewModel.onFocusChanged(oldValue, newValue);
            }
        };

        txtPlaneA.textProperty().bindBidirectional(viewModel.planeAProperty());
        txtPlaneA.focusedProperty().addListener(focusChangeListener);

        txtPlaneB.textProperty().bindBidirectional(viewModel.planeBProperty());
        txtPlaneB.focusedProperty().addListener(focusChangeListener);

        txtPlaneC.textProperty().bindBidirectional(viewModel.planeCProperty());
        txtPlaneC.focusedProperty().addListener(focusChangeListener);

        txtPlaneD.textProperty().bindBidirectional(viewModel.planeDProperty());
        txtPlaneD.focusedProperty().addListener(focusChangeListener);

        txtLinePointX.textProperty().bindBidirectional(viewModel.getLinePXProperty());
        txtLinePointX.focusedProperty().addListener(focusChangeListener);

        txtLinePointY.textProperty().bindBidirectional(viewModel.getLinePYProperty());
        txtLinePointY.focusedProperty().addListener(focusChangeListener);

        txtLinePointZ.textProperty().bindBidirectional(viewModel.getLinePZProperty());
        txtLinePointZ.focusedProperty().addListener(focusChangeListener);

        txtLineVectorX.textProperty().bindBidirectional(viewModel.getLineVXProperty());
        txtLineVectorX.focusedProperty().addListener(focusChangeListener);

        txtLineVectorY.textProperty().bindBidirectional(viewModel.getLineVYProperty());
        txtLineVectorY.focusedProperty().addListener(focusChangeListener);

        txtLineVectorZ.textProperty().bindBidirectional(viewModel.getLineVZProperty());
        txtLineVectorZ.focusedProperty().addListener(focusChangeListener);

        btnDet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.determinate();
            }
        });
    }
}
