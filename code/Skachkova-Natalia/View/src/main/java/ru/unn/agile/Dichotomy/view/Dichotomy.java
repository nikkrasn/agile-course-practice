package ru.unn.agile.Dichotomy.view;

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
        txtInputArray.textProperty().bindBidirectional(viewModel.stringArrayProperty());
        txtInputElement.textProperty().bindBidirectional(viewModel.stringElementProperty());
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

