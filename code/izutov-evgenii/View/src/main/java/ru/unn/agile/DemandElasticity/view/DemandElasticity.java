package ru.unn.agile.DemandElasticity.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.DemandElasticity.viewmodel.DemandElasticityType;
import ru.unn.agile.DemandElasticity.viewmodel.ViewModel;

public class DemandElasticity {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtFirstRangeStart;
    @FXML
    private TextField txtFirstRangeFinish;
    @FXML
    private TextField txtSecondRangeStart;
    @FXML
    private TextField txtSecondRangeFinish;
    @FXML
    private ComboBox<DemandElasticityType> cbDemandElasticityType;
    @FXML
    private Button btnCalculate;

    @FXML
    void initialize() {
        txtFirstRangeStart.textProperty().bindBidirectional(viewModel.firstRangeStartProperty());
        txtFirstRangeFinish.textProperty().bindBidirectional(viewModel.firstRangeFinishProperty());
        txtSecondRangeStart.textProperty().bindBidirectional(viewModel.secondRangeStartProperty());
        txtSecondRangeFinish.textProperty().bindBidirectional(
                viewModel.secondRangeFinishProperty());

        cbDemandElasticityType.valueProperty().bindBidirectional(
                viewModel.demandElasticityTypeProperty());

        btnCalculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });
    }
}
