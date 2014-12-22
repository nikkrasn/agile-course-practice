package ru.unn.agile.Dichotomy.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.Dichotomy.viewmodel.ViewModel;

public class Dichotomy {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtInputArray;
    @FXML
    private TextField txtInputElement;
    @FXML
    private Button btnInputArray;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnNewArray;

    @FXML
    void initialize() {
        final ChangeListener<Boolean> focusChangeListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(final ObservableValue<? extends Boolean> observable,
                                final Boolean oldValue, final Boolean newValue) {
                viewModel.onFocusChanged(oldValue, newValue);
            }
        };

        txtInputArray.textProperty().bindBidirectional(viewModel.stringArrayProperty());
        txtInputArray.focusedProperty().addListener(focusChangeListener);
        txtInputElement.textProperty().bindBidirectional(viewModel.stringElementProperty());
        txtInputElement.focusedProperty().addListener(focusChangeListener);
        btnInputArray.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.parseString();
            }
        });
        btnSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.findElement();
            }
        });
        btnNewArray.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.enterNewArray();
            }
        });
    }
}

