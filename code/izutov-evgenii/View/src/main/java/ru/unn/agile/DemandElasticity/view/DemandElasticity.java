package ru.unn.agile.DemandElasticity.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import ru.unn.agile.DemandElasticity.viewmodel.DemandElasticityType;
import ru.unn.agile.DemandElasticity.viewmodel.ViewModel;
import ru.unn.agile.DemandElasticity.infrastructure.LoggerToTxt;

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
        viewModel.setLogger(new LoggerToTxt("./LoggerToTxt.log"));

        final ChangeListener<Boolean> inputFieldChangeListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(final ObservableValue<? extends Boolean> observable,
                                final Boolean oldValue, final Boolean newValue) {
                viewModel.onInputFieldChanged(oldValue, newValue);
            }
        };

        txtFirstRangeStart.textProperty().bindBidirectional(viewModel.firstRangeStartProperty());
        txtFirstRangeStart.focusedProperty().addListener(inputFieldChangeListener);

        txtFirstRangeFinish.textProperty().bindBidirectional(viewModel.firstRangeFinishProperty());
        txtFirstRangeFinish.focusedProperty().addListener(inputFieldChangeListener);

        txtSecondRangeStart.textProperty().bindBidirectional(viewModel.secondRangeStartProperty());
        txtSecondRangeStart.focusedProperty().addListener(inputFieldChangeListener);

        txtSecondRangeFinish.textProperty()
                .bindBidirectional(viewModel.secondRangeFinishProperty());
        txtSecondRangeFinish.focusedProperty().addListener(inputFieldChangeListener);

        cbDemandElasticityType.valueProperty()
                .bindBidirectional(viewModel.demandElasticityTypeProperty());
        cbDemandElasticityType.valueProperty()
                .addListener(new ChangeListener<DemandElasticityType>() {
            @Override
            public void changed(final ObservableValue<? extends DemandElasticityType> observable,
                                final DemandElasticityType oldValue,
                                final DemandElasticityType newValue) {
                viewModel.onDemandElasticityTypeChanged(oldValue, newValue);
            }
        });

        btnCalculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });
    }
}
