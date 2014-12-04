package ru.unn.agile.Stack.View;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import ru.unn.agile.Stack.ViewModel.ViewModel;

public class Stack {
    @FXML
    private ViewModel viewModel;
    @FXML
    private Button popButton;
    @FXML
    private Button pushButton;
    @FXML
    private TableView<String> stackTable;
    @FXML
    private TableColumn<String, String> valueColumn;
    @FXML
    private TextField pushText;

    @FXML
    void initialize() {
        pushText.textProperty().bindBidirectional(viewModel.pushTextProperty());

        valueColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String, String>,
                ObservableValue<String>>()
        {
            @Override
            public ObservableValue<String> call(
                    final TableColumn.CellDataFeatures<String, String> param) {
                return new SimpleStringProperty(param.getValue());
            }
        });

        stackTable.itemsProperty().bindBidirectional(viewModel.stackTableProperty());

        pushButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.push();
            }
        });

        popButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.pop();
            }
        });
    }
}
