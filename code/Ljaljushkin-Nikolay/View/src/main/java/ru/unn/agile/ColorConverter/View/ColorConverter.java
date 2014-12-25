package ru.unn.agile.ColorConverter.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import ru.unn.agile.ColorConverter.infrastructure.DatedTextLogger;
import ru.unn.agile.ColorConverter.viewmodel.ViewModel;
import ru.unn.agile.ColorConverter.viewmodel.Color;

public class ColorConverter {
    @FXML
    private ViewModel viewModel;
    @FXML
    private ComboBox<Color> cbSrcColor;
    @FXML
    private TextField txtFirstChannelSrcColor;
    @FXML
    private TextField txtSecondChannelSrcColor;
    @FXML
    private TextField txtThirdChannelSrcColor;

    @FXML
    private ComboBox<Color> cbDstColor;
    @FXML
    private TextField txtFirstChannelDstColor;
    @FXML
    private TextField txtSecondChannelDstColor;
    @FXML
    private TextField txtThirdChannelDstColor;

    @FXML
    private Button btConvert;
    @FXML
    private Label lbStatus;

    @FXML
    void initialize() {
        viewModel.setLogger(new DatedTextLogger("./ViewDatedTextLogger.log"));

        txtFirstChannelSrcColor.textProperty().bindBidirectional(
                viewModel.firstChannelSrcColorStringProperty());
        txtSecondChannelSrcColor.textProperty().bindBidirectional(
                viewModel.secondChannelSrcColorStringProperty());
        txtThirdChannelSrcColor.textProperty().bindBidirectional(
                viewModel.thirdChannelSrcColorStringProperty());

        txtFirstChannelDstColor.textProperty().bindBidirectional(
                viewModel.firstChannelDstColorStringProperty());
        txtSecondChannelDstColor.textProperty().bindBidirectional(
                viewModel.secondChannelDstColorStringProperty());
        txtThirdChannelDstColor.textProperty().bindBidirectional(
                viewModel.thirdChannelDstColorStringProperty());

        cbSrcColor.valueProperty().bindBidirectional(viewModel.srcColorProperty());
        cbDstColor.valueProperty().bindBidirectional(viewModel.dstColorProperty());

        btConvert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.convert();
            }
        });

        lbStatus.textProperty().bindBidirectional(viewModel.statusProperty());

        final ChangeListener<Boolean> onFocusChangeListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(final ObservableValue<? extends Boolean> observable,
                                final Boolean oldValue, final Boolean newValue) {
                viewModel.onFocusChanged(oldValue, newValue);
            }
        };

        txtFirstChannelSrcColor.focusedProperty().addListener(onFocusChangeListener);
        txtSecondChannelSrcColor.focusedProperty().addListener(onFocusChangeListener);
        txtThirdChannelSrcColor.focusedProperty().addListener(onFocusChangeListener);
    }
}
