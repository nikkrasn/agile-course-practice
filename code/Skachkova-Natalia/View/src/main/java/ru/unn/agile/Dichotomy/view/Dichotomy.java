package ru.unn.agile.Dichotomy.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.Dichotomy.viewmodel.ViewModel;
import ru.unn.agile.Dichotomy.infrastructure.TxtLogger;

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
        viewModel.setLogger(new TxtLogger("./TxtLogger-lab3-dichotomy.log"));

        final ChangeListener<Boolean> focusChangedListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(final ObservableValue<? extends Boolean> observable,
                                final Boolean oldValues, final Boolean newValues) {
                viewModel.onFocusChanged(oldValues, newValues);
            }
        };

        txtInputArray.textProperty().bindBidirectional(viewModel.stringArrayProperty());
        txtInputArray.focusedProperty().addListener(focusChangedListener);
        txtInputElement.textProperty().bindBidirectional(viewModel.stringElementProperty());
        txtInputElement.focusedProperty().addListener(focusChangedListener);
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

