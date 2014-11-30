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
    private TextField txtBitArray1;
    @FXML
    private TextField txtBitArray2;
    @FXML
    private TextField txtBitArray3;
    @FXML
    private ComboBox<BitArray.Operation> cbOperation1;
    @FXML
    private ComboBox<BitArray.Operation> cbOperation2;
    @FXML
    private Button btnCalculate;
    @FXML
    private Button btnOperationNotForBitArray1;
    @FXML
    private Button btnOperationNotForBitArray2;
    @FXML
    private Button btnOperationNotForBitArray3;

    @FXML
    void initialize() {
        txtBitArray1.addEventFilter(KeyEvent.KEY_TYPED , numericValidation(ARRAY_SIZE));
        txtBitArray2.addEventFilter(KeyEvent.KEY_TYPED , numericValidation(ARRAY_SIZE));
        txtBitArray3.addEventFilter(KeyEvent.KEY_TYPED , numericValidation(ARRAY_SIZE));

        txtBitArray1.textProperty().bindBidirectional(viewModel.bitArray1StrValue());
        txtBitArray2.textProperty().bindBidirectional(viewModel.bitArray2StrValue());
        txtBitArray3.textProperty().bindBidirectional(viewModel.bitArray3StrValue());

        cbOperation1.valueProperty().bindBidirectional(viewModel.bitOperation1());
        cbOperation2.valueProperty().bindBidirectional(viewModel.bitOperation2());

        btnCalculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });

        btnOperationNotForBitArray1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.performNot(0);
            }
        });

        btnOperationNotForBitArray2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.performNot(1);
            }
        });

        btnOperationNotForBitArray3.setOnAction(new EventHandler<ActionEvent>() {
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
