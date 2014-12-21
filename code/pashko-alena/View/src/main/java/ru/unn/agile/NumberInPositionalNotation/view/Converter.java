package ru.unn.agile.NumberInPositionalNotation.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.NumberInPositionalNotation.Model.Notation;
import ru.unn.agile.NumberInPositionalNotation.viewmodel.ViewModel;
import ru.unn.agile.NumberInPositionalNotation.infrastructure.TxtLogger;


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
        viewModel.setLogger(new TxtLogger("./Txt_logger.log"));

        final ChangeListener<Boolean> focusChangeListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(final ObservableValue<? extends Boolean> observable,
                                final Boolean oldValue, final Boolean newValue) {
                viewModel.changedArguments(oldValue, newValue);
            }
        };
        txtInNum.textProperty().bindBidirectional(viewModel.inputNumberProperty());
        txtInNum.focusedProperty().addListener(focusChangeListener);

        cbInNotation.valueProperty().bindBidirectional(viewModel.inputNotationProperty());
        cbInNotation.valueProperty().addListener(new ChangeListener<Notation>() {
            @Override
            public void changed(final ObservableValue<? extends Notation> observable,
                                final Notation oldValue,
                                final Notation newValue) {
                viewModel.onNotationChanged(oldValue, newValue, "Input");
            }
        });

        cbOutNotation.valueProperty().bindBidirectional(viewModel.outputNotationProperty());
        cbOutNotation.valueProperty().addListener(new ChangeListener<Notation>() {
            @Override
            public void changed(final ObservableValue<? extends Notation> observable,
                                final Notation oldValue,
                                final Notation newValue) {
                viewModel.onNotationChanged(oldValue, newValue, "Output");
            }
        });

        btnConvert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.convert();
            }
        });

    }
}
