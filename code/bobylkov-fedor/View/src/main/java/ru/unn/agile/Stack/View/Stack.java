package ru.unn.agile.Stack.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.unn.agile.Stack.ViewModel.ViewModel;

public class Stack {
    @FXML
    private ViewModel viewModel;
    @FXML
    private Button popButton;
    @FXML
    private Button pushButton;
    @FXML
    private Label topLabel;
    @FXML
    private TableView stackTable;
    @FXML
    private TableColumn valueColumn;
    @FXML
    private TextField pushText;

    @FXML
    void initialize() {
        pushText.textProperty().bindBidirectional(viewModel.pushTextProperty());
        //stackTable.itemsProperty().bindBidirectional(viewModel.stackTableProperty());
        //popButton.disableProperty().bindBidirectional(viewModel.isEmptyProperty());
        //topLabel.textProperty().bindBidirectional(viewModel.topProperty());

        pushButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.push();
            }
        });
    }
}
