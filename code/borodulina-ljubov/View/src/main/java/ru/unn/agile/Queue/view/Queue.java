package ru.unn.agile.Queue.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Queue {
    @FXML
    private Button btnRemove;
    @FXML
    private Button btnAdd;
    @FXML
    private TableView<String> queueTable;
    @FXML
    private TableColumn<String, String> valueColumn;
    @FXML
    private TextField txtToAdd;

    @FXML
    void initialize() {
    }
}
