package ru.unn.agile.ColorConverter.view;

//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.scene.control.*;

// import ru.unn.agile.ComplexNumber.model.ComplexNumber.Operation;

//import ru.unn.agile.ColorConverter.viewmodel.ViewModel;

public class ColorConverter {

    @FXML
    private ComboBox cbSrcColor;
    @FXML
    private TextField txtFirstChannelSrcColor;
    @FXML
    private TextField txtSecondChannelSrcColor;
    @FXML
    private TextField txtThirdChannelSrcColor;

    @FXML
    private ComboBox cbDstColor;
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
        //txtFirstChannelSrcColor.setTooltip(new Tooltip("0 to 255"));
    }
}
