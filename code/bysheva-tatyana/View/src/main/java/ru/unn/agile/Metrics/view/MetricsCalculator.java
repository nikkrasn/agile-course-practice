package ru.unn.agile.Metrics.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.unn.agile.Metrics.Model.Metrics.Operation;
import ru.unn.agile.Metrics.viewmodel.ViewModel;

public class MetricsCalculator {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField vectorsDimension;
    @FXML
    private TableView tableView;
    @FXML
    private ComboBox<Operation> cbOperation;
    @FXML
    private Button btnCalc;
    @FXML
    private TableColumn vector1;
    @FXML
    private TableColumn vector2;

    @FXML
    void initialize() {

        // Two-way binding hasn't supported by FXML yet, so place it in code-behind
        tableView.setEditable(true);
        tableView.setVisible(true);

        tableView.setItems(viewModel.getVectorsValuesProperty());

        vectorsDimension.textProperty().bindBidirectional(viewModel.vectorsDimensionProperty());

        cbOperation.valueProperty().bindBidirectional(viewModel.operationProperty());

        btnCalc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });
    }
}
