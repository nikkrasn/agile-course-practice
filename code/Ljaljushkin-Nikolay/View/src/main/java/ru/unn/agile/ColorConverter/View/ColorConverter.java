package ru.unn.agile.ColorConverter.view;

//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.scene.control.*;

// import ru.unn.agile.ComplexNumber.model.ComplexNumber.Operation;

//import ru.unn.agile.ColorConverter.viewmodel.ViewModel;

public class ColorConverter {

    @FXML
    private TextField txtFirstChannelSrcColor;
    @FXML
    private TextField txtSecondChannelSrcColor;
    @FXML
    private TextField txtThirdChannelSrcColor;

    @FXML
    private TextField txtRgbR;
    @FXML
    private TextField txtRgbB;
    @FXML
    private TextField txtRgbG;

    @FXML
    private TextField txtLabL;
    @FXML
    private TextField txtLabA;
    @FXML
    private TextField txtLabB;

    @FXML
    private TextField txtHsvH;
    @FXML
    private TextField txtHsvS;
    @FXML
    private TextField txtHsvV;

    @FXML
    private Button btConvert;
    @FXML
    private Label lbStatus;
    @FXML
    private ComboBox cbSrcColor;

    @FXML
    void initialize() {
        //txtFirstChannelSrcColor.setTooltip(new Tooltip("0 to 255"));
    }
}
