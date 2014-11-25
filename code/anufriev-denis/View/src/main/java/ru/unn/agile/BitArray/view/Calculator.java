package ru.unn.agile.BitArray.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import ru.unn.agile.BitArray.model.BitArray;
import ru.unn.agile.BitArray.viewModel.ViewModel;

public class Calculator {

    private static final int ARRAY_SIZE = 10000;
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtArray1;
    @FXML
    private TextField txtArray2;
    @FXML
    private TextField txtArray3;
    @FXML
    private ComboBox<BitArray.Operation> cbOperation1;
    @FXML
    private ComboBox<BitArray.Operation> cbOperation2;
    @FXML
    private Button btnCalc;
    @FXML
    private Button btnNot1;
    @FXML
    private Button btnNot2;
    @FXML
    private Button btnNot3;

    @FXML
    void initialize() {

        txtArray1.addEventFilter(KeyEvent.KEY_TYPED , numericValidation(ARRAY_SIZE));
        txtArray2.addEventFilter(KeyEvent.KEY_TYPED , numericValidation(ARRAY_SIZE));
        txtArray3.addEventFilter(KeyEvent.KEY_TYPED , numericValidation(ARRAY_SIZE));


        txtArray1.textProperty().bindBidirectional(viewModel.array1Property());
        txtArray2.textProperty().bindBidirectional(viewModel.array2Property());
        txtArray3.textProperty().bindBidirectional(viewModel.array3Property());

        cbOperation1.valueProperty().bindBidirectional(viewModel.operation1Property());
        cbOperation2.valueProperty().bindBidirectional(viewModel.operation2Property());

        btnCalc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });

        btnNot1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.performNot(0);
            }
        });

        btnNot2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.performNot(1);
            }
        });

        btnNot3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.performNot(2);
            }
        });
    }

    public EventHandler<KeyEvent> numericValidation(final Integer maxLength) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent e) {
                TextField txtTextField = (TextField) e.getSource();
                if (txtTextField.getText().length() >= maxLength) {
                    e.consume();
                }
                if (!e.getCharacter().matches("[0-1]")) {
                    e.consume();
                }
            }
        };
    }
}
