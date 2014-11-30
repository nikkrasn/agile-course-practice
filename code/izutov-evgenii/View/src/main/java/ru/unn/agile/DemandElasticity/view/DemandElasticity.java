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
    private TextField txtRange1Start;
    @FXML
    private TextField txtRange1Finish;
    @FXML
    private TextField txtRange2Start;
    @FXML
    private TextField txtRange2Finish;
    @FXML
    private ComboBox<DemandElasticityType> cbDemandElasticityType;
    @FXML
    private Button btnCalculate;

    @FXML
    void initialize() {
        txtRange1Start.textProperty().bindBidirectional(viewModel.start1Property());
        txtRange1Finish.textProperty().bindBidirectional(viewModel.finish1Property());
        txtRange2Start.textProperty().bindBidirectional(viewModel.start2Property());
        txtRange2Finish.textProperty().bindBidirectional(viewModel.finish2Property());

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
