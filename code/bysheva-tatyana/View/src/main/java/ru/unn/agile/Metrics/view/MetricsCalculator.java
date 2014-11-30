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
import javafx.scene.control.cell.TextFieldTableCell;
import ru.unn.agile.Metrics.Model.Metrics.Operation;
import ru.unn.agile.Metrics.viewmodel.Components;
import ru.unn.agile.Metrics.viewmodel.ViewModel;

public class MetricsCalculator {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField vectorsDimension;
    @FXML
    private TableView<Components> tableView;
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
        tableView.setEditable(true);
        tableView.setVisible(true);

        vector1.setCellValueFactory(new PropertyValueFactory<Components, String>("component1"));
        vector1.setCellFactory(TextFieldTableCell.forTableColumn());
        vector1.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Components, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Components, String> t) {
                        ((Components) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).component1.set(t.getNewValue());
                    }
                }
        );
        vector2.setCellValueFactory(new PropertyValueFactory<Components, String>("component2"));
        vector2.setCellFactory(TextFieldTableCell.forTableColumn());
        vector2.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Components, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Components, String> t) {
                        ((Components) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).component2.set(t.getNewValue());
                    }
                }
        );
        tableView.itemsProperty().bindBidirectional(viewModel.vectorsValuesProperty);

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
