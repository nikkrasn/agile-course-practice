package ru.unn.agile.BinaryTree.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.BinaryTree.viewmodel.ViewModel;
import ru.unn.agile.BinaryTree.viewmodel.Operation;

public class BinaryTree {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtKey;
    @FXML
    private TextField txtValue;
    @FXML
    private ComboBox<Operation> cbOperation;
    @FXML
    private Button btnExec;

    @FXML
    void initialize() {
        txtKey.textProperty().bindBidirectional(viewModel.keyProperty());
        txtValue.textProperty().bindBidirectional(viewModel.valueProperty());

        cbOperation.valueProperty().bindBidirectional(viewModel.operationProperty());

        btnExec.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.execute();
            }
        });
    }
}
