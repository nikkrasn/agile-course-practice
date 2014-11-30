package ru.unn.agile.LeftistHeap.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.LeftistHeap.viewmodel.ViewModel;

public class LeftistHeap {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField key1;
    @FXML
    private TextField value1;
    @FXML
    private TextField newKey1;

    @FXML
    private Button add;
    @FXML
    private Button getMinimum;
    @FXML
    private Button delete;
    @FXML
    private Button decrease;
    @FXML
    private Button merge;

    @FXML
    private ComboBox<ru.unn.agile.LeftistHeap.Model.LeftistHeap<String>> cbHeap;

    @FXML
    void initialize() {
        key1.textProperty().bindBidirectional(viewModel.keyProperty());
        value1.textProperty().bindBidirectional(viewModel.valueProperty());
        newKey1.textProperty().bindBidirectional(viewModel.newKeyProperty());

        cbHeap.valueProperty().bindBidirectional(viewModel.heapProperty());

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.add();
            }
        });

        getMinimum.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.extractMinimum();
            }
        });

        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.extract();
            }
        });

        merge.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.merge();
            }
        });

        decrease.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.decreaseKey();
            }
        });
    }
}
