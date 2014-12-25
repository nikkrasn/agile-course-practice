package ru.unn.agile.Metrics.view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;
import ru.unn.agile.Metrics.Infrastructure.TextLogger;
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
    private TableColumn<Components, String> vector1;
    @FXML
    private TableColumn<Components, String> vector2;

    @FXML
    void initialize() {
        viewModel.setLogger(new TextLogger("TextLogger-View.log"));

        vector1.setCellFactory(TextFieldTableCell.forTableColumn());
        vector2.setCellFactory(TextFieldTableCell.forTableColumn());
        setColumnsOnEditCommit();

        tableView.itemsProperty().bindBidirectional(viewModel.vectorsValuesProperty());

        vectorsDimension.textProperty().bindBidirectional(viewModel.vectorsDimensionProperty());

        cbOperation.valueProperty().bindBidirectional(viewModel.currentOperationProperty());

        btnCalc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });
    }

    private void setColumnsOnEditCommit() {
        vector1.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Components, String>>() {
                    @Override
                    public void handle(final TableColumn.
                            CellEditEvent<Components, String> editedCell) {
                        updateComponentsCell(getEditedRowIndex(editedCell),
                                getEditedComponents(editedCell).
                                        setComponent1(editedCell.getNewValue()));
                    }
                }
        );
        vector2.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Components, String>>() {
                    @Override
                    public void handle(final TableColumn.
                            CellEditEvent<Components, String> editedCell) {
                        updateComponentsCell(getEditedRowIndex(editedCell),
                                getEditedComponents(editedCell).
                                        setComponent2(editedCell.getNewValue()));
                    }
                }
        );
    }

    private void updateComponentsCell(final Integer index, final Components newValue) {
        ObservableList<Components> newTable = tableView.getItems();
        newTable.set(index, newValue);

        tableView.setItems(newTable);
    }

    private Integer getEditedRowIndex(final TableColumn.
            CellEditEvent<Components, String> editedCell) {
        return editedCell.getTablePosition().getRow();
    }

    private ObservableList<Components> getTableViewItems(final TableColumn.
            CellEditEvent<Components, String> editedCell) {
        return editedCell.getTableView().getItems();
    }

    private Components getEditedComponents(final TableColumn.
            CellEditEvent<Components, String> editedCell) {
        Integer editedRowIndex = getEditedRowIndex(editedCell);
        return getTableViewItems(editedCell).get(editedRowIndex);
    }
}
