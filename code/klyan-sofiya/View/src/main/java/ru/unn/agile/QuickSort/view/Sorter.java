package ru.unn.agile.QuickSort.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.QuickSort.viewmodel.ViewModel;

public class Sorter {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField inputField;
    @FXML
    private TextField outputField;
    @FXML
    private Button btnSort;

    @FXML
    void initialize() {
        inputField.textProperty().bindBidirectional(viewModel.unsortedArrayProperty());
        outputField.textProperty().bindBidirectional(viewModel.sortedArrayProperty());

        btnSort.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.sort();
            }
        });
    }
}
