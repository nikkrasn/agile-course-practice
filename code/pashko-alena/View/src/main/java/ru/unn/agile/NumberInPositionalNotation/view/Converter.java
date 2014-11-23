package ru.unn.agile.NumberInPositionalNotation.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.NumberInPositionalNotation.Model.Notation;
import ru.unn.agile.NumberInPositionalNotation.viewmodel.ViewModel;


public class Converter {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtInNum;
    @FXML
    private ComboBox<Notation> cbInNotation;
    @FXML
    private ComboBox<Notation> cbOutNotation;
    @FXML
    private Button btnConvert;

    @FXML
    void initialize() {
        txtInNum.textProperty().bindBidirectional(viewModel.inputNumberProperty());

        cbInNotation.valueProperty().bindBidirectional(viewModel.inputNotationProperty());
        cbOutNotation.valueProperty().bindBidirectional(viewModel.outputNotationProperty());

        btnConvert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.convert();
            }
        });

    }
}
