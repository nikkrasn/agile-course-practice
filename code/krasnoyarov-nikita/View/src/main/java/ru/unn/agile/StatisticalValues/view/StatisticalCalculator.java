package ru.unn.agile.StatisticalValues.view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import ru.unn.agile.StatisticalValues.model.Operation;
import ru.unn.agile.StatisticalValues.viewmodel.ProbabilityValuePair;
import ru.unn.agile.StatisticalValues.viewmodel.ViewModel;
import ru.unn.agile.StatisticalValues.infrastructure.SimpleTxtLogger;

public class StatisticalCalculator {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField vectorsDimension;
    @FXML
    private TableView<ProbabilityValuePair> tableView;
    @FXML
    private ComboBox<Operation> operationBox;
    @FXML
    private Button calculateOperation;
    @FXML
    private TableColumn<ProbabilityValuePair, String> probabilities;
    @FXML
    private TableColumn<ProbabilityValuePair, String> values;

    @FXML
    void initialize() {
        viewModel.setLogger(new SimpleTxtLogger("SimpleTxtLoggerView.log"));
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
                new EventHandler<TableColumn.CellEditEvent<ProbabilityValuePair, String>>() {
                    @Override
                    public void handle(final TableColumn.
                            CellEditEvent<ProbabilityValuePair, String> editedCell) {
                        updateCells(getEditedRowIndex(editedCell),
                                getEditedVectors(editedCell).
                                        setProbability(editedCell.getNewValue()));
                    }
                }
        );
        values.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<ProbabilityValuePair, String>>() {
                    @Override
                    public void handle(final TableColumn.
                            CellEditEvent<ProbabilityValuePair, String> editedCell) {
                        updateCells(getEditedRowIndex(editedCell),
                                getEditedVectors(editedCell).
                                        setValue(editedCell.getNewValue()));
                    }
                }
        );
    }

    private void updateCells(final Integer index, final ProbabilityValuePair newValue) {
        ObservableList<ProbabilityValuePair> newTable = tableView.getItems();
        newTable.set(index, newValue);

        tableView.setItems(newTable);
    }

    private Integer getEditedRowIndex(final TableColumn.
            CellEditEvent<ProbabilityValuePair, String> editedCell) {
        return editedCell.getTablePosition().getRow();
    }

    private ObservableList<ProbabilityValuePair> getTableViewItems(final TableColumn.
            CellEditEvent<ProbabilityValuePair, String> editedCell) {
        return editedCell.getTableView().getItems();
    }

    private ProbabilityValuePair getEditedVectors(final TableColumn.
            CellEditEvent<ProbabilityValuePair, String> editedCell) {
        Integer editedRowIndex = getEditedRowIndex(editedCell);
        return getTableViewItems(editedCell).get(editedRowIndex);
    }
}
