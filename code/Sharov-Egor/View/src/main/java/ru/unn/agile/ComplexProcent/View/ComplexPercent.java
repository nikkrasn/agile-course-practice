package ru.unn.agile.ComplexProcent.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import ru.unn.agile.ComplexProcent.ViewModel.ViewModel;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ComplexPercent extends VBox {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtBase;
    @FXML
    private TextField txtInterestCount;
    @FXML
    private TextField txtPercent;
    @FXML
    private Button btnCount;
    @FXML
    private DatePicker dtPkrStart;
    @FXML
    private DatePicker dtPkrEnd;

    @FXML
    void initialize() {
        txtBase.textProperty().bindBidirectional(viewModel.getTxtBaseProperty());
        txtInterestCount.textProperty().bindBidirectional(viewModel.getTxtInterestCountProperty());
        txtPercent.textProperty().bindBidirectional(viewModel.getTxtPercentProperty());
        dtPkrStart.valueProperty().bindBidirectional(viewModel.dtPkrStartProperty());
        dtPkrEnd.valueProperty().bindBidirectional(viewModel.dtPkrEndProperty());
        btnCount.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });
    }
}
