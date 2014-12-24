package ru.unn.agile.StatisticalValues.view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import ru.unn.agile.StatisticalValues.model.Operation;
import ru.unn.agile.StatisticalValues.viewmodel.Vectors;
import ru.unn.agile.StatisticalValues.viewmodel.ViewModel;

public class StatisticalCalculator {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField vectorsDimension;
    @FXML
    private TableView<Vectors> tableView;
    @FXML
    private ComboBox<Operation> operationBox;
    @FXML
    private Button calculateOperation;
    @FXML
    private TableColumn<Vectors, String> probabilities;
    @FXML
    private TableColumn<Vectors, String> values;

    @FXML
    void initialize() {
        probabilities.setCellFactory(TextFieldTableCell.forTableColumn());
        values.setCellFactory(TextFieldTableCell.forTableColumn());
        setColumns();

        tableView.itemsProperty().bindBidirectional(viewModel.vectProbValueProperty());

        operationBox.valueProperty().bindBidirectional(viewModel.operationProperty());

        vectorsDimension.textProperty().bindBidirectional(viewModel.vectDimensionProperty());

        calculateOperation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });
    }

    private void setColumns() {
        probabilities.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Vectors, String>>() {
                    @Override
                    public void handle(final TableColumn.
                            CellEditEvent<Vectors, String> editedCell) {
                        updateCells(getEditedRowIndex(editedCell),
                                getEditedVectors(editedCell).
                                        setFirstComponent(editedCell.getNewValue()));
                    }
                }
        );
        values.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Vectors, String>>() {
                    @Override
                    public void handle(final TableColumn.
                            CellEditEvent<Vectors, String> editedCell) {
                        updateCells(getEditedRowIndex(editedCell),
                                getEditedVectors(editedCell).
                                        setSecondComponent(editedCell.getNewValue()));
                    }
                }
        );
    }

    private void updateCells(final Integer index, final Vectors newValue) {
        ObservableList<Vectors> newTable = tableView.getItems();
        newTable.set(index, newValue);

        tableView.setItems(newTable);
    }

    private Integer getEditedRowIndex(final TableColumn.
            CellEditEvent<Vectors, String> editedCell) {
        return editedCell.getTablePosition().getRow();
    }

    private ObservableList<Vectors> getTableViewItems(final TableColumn.
            CellEditEvent<Vectors, String> editedCell) {
        return editedCell.getTableView().getItems();
    }

    private Vectors getEditedVectors(final TableColumn.
            CellEditEvent<Vectors, String> editedCell) {
        Integer editedRowIndex = getEditedRowIndex(editedCell);
        return getTableViewItems(editedCell).get(editedRowIndex);
    }
}
