package ru.unn.agile.Queue.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.Queue.viewmodel.ViewModel;

public class Queue {
    @FXML
    private ViewModel viewModel;
    @FXML
    private Button btnRemove;
    @FXML
    private Button btnAdd;
    @FXML
    private TextField txtToAdd;

    @FXML
    public void initialize() {
        txtToAdd.textProperty().bindBidirectional(viewModel.txtToAddProperty());

        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.add();
            }
        });

        btnRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.remove();
            }
        });
    }
}
