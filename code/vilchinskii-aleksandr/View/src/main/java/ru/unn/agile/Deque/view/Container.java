package ru.unn.agile.Deque.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.Deque.viewmodel.ViewModel;

public class Container {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtItem;
    @FXML
    private Button btnAddFirst;
    @FXML
    private Button btnAddLast;
    @FXML
    private Button btnGetFirst;
    @FXML
    private Button btnGetLast;
    @FXML
    private Button btnRemoveFirst;
    @FXML
    private Button btnRemoveLast;

    @FXML
    void initialize() {
        txtItem.textProperty().bindBidirectional(viewModel.txtItemProperty());

        btnAddFirst.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.addFirst();
            }
        });

        btnAddLast.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.addLast();
            }
        });

        btnGetFirst.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.getFirst();
            }
        });

        btnGetLast.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.getLast();
            }
        });

        btnRemoveFirst.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.removeFirst();
            }
        });

        btnRemoveLast.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.removeLast();
            }
        });
    }
}

