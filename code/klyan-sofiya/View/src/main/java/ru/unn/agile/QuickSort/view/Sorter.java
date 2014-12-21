package ru.unn.agile.QuickSort.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.QuickSort.viewmodel.ViewModel;
import ru.unn.agile.QuickSort.infrastructure.TxtLogger;


public class Sorter {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField inputField;
    @FXML
    private TextField outputField;
    @FXML
    private Button btnSort;

    private static final String LOGGER_FILENAME = "./TxtLogger-lab3.log";

    @FXML
    void initialize() {
        viewModel.setLogger(new TxtLogger(LOGGER_FILENAME));

        final ChangeListener<Boolean> focusChangeListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(final ObservableValue<? extends Boolean> observable,
                                final Boolean oldVal, final Boolean newVal) {
                viewModel.onFocusChanged(oldVal, newVal);
            }
        };
        inputField.textProperty().bindBidirectional(viewModel.unsortedArrayProperty());
        inputField.focusedProperty().addListener(focusChangeListener);
        outputField.textProperty().bindBidirectional(viewModel.sortedArrayProperty());

        btnSort.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.sort();
            }
        });
    }
}
