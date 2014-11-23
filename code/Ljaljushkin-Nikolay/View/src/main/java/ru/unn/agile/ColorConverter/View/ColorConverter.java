package ru.unn.agile.ColorConverter.View;

//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.scene.control.*;

// import ru.unn.agile.ComplexNumber.model.ComplexNumber.Operation;
// import ru.unn.agile.ComplexNumber.viewmodel.ViewModel;

public class ColorConverter {

    @FXML
    private TextField txtFirstChannel;
    @FXML
    private TextField txtSecondChannel;
    @FXML
    private TextField txtThirdChannel;

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
        //txtFirstChannel.setTooltip(new Tooltip("0 to 255"));
    }
}
