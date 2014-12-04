package ru.unn.agile.ColorConverter.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

        // Two-way binding hasn't supported by FXML yet, so place it in code-behind
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
        //txtFirstChannelSrcColor.setTooltip(new Tooltip("0 to 255"));
    }
}
