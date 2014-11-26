package ru.unn.agile.ColorConverter.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.ColorConverter.model.ColorSpaces.ColorSpace;

public class ViewModel {

    private final StringProperty srcFirstChannel = new SimpleStringProperty();
    private final StringProperty srcSecondChannel = new SimpleStringProperty();
    private final StringProperty srcThirdChannel = new SimpleStringProperty();

    private final StringProperty dstRgbR = new SimpleStringProperty();
    private final StringProperty dstRgbG = new SimpleStringProperty();
    private final StringProperty dstRgbB = new SimpleStringProperty();

    private final StringProperty dstLabL = new SimpleStringProperty();
    private final StringProperty dstLabA = new SimpleStringProperty();
    private final StringProperty dstLabB = new SimpleStringProperty();

    private final StringProperty dstHsvH = new SimpleStringProperty();
    private final StringProperty dstHsvS = new SimpleStringProperty();
    private final StringProperty dstHsvV = new SimpleStringProperty();

    private final ObjectProperty<ObservableList<SourceColor>> sourceColors =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(SourceColor.values()));

    private final ObjectProperty<SourceColor> sourceColor = new SimpleObjectProperty<>();

    //    private final BooleanProperty convertionDisabled = new SimpleBooleanProperty();
    private final StringProperty status = new SimpleStringProperty();
    //    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    // FXML needs default c-tor for binding
    public ViewModel() {
        srcFirstChannel.set("0");
        srcSecondChannel.set("0");
        srcThirdChannel.set("0");

        dstLabL.set("0");
        dstLabA.set("0");
        dstLabB.set("0");

        dstRgbR.set("0");
        dstRgbG.set("0");
        dstRgbB.set("0");

        dstHsvH.set("0");
        dstHsvS.set("0");
        dstHsvV.set("0");

        status.set(Status.WAITING.toString());
        sourceColor.set(SourceColor.HSV);
    }

    public StringProperty srcFirstChannelProperty() {
        return srcFirstChannel;
    }

    public StringProperty srcSecondChannelProperty() {
        return srcSecondChannel;
    }

    public StringProperty srcThirdChannelProperty() {
        return srcThirdChannel;
    }

    public StringProperty dstRgbRProperty() {
        return dstRgbR;
    }

    public StringProperty dstRgbGProperty() {
        return dstRgbG;
    }

    public StringProperty dstRgbBProperty() {
        return dstRgbB;
    }

    public StringProperty dstLabLProperty() {
        return dstLabL;
    }

    public StringProperty dstLabAProperty() {
        return dstLabA;
    }

    public StringProperty dstLabBProperty() {
        return dstLabB;
    }

    public StringProperty dstHsvHProperty() {
        return dstHsvH;
    }

    public StringProperty dstHsvSProperty() {
        return dstHsvS;
    }

    public StringProperty dstHsvVProperty() {
        return dstHsvV;
    }

    public ObjectProperty<ObservableList<SourceColor>> sourceColorsProperty() {
        return sourceColors;
    }

    public ObjectProperty<SourceColor> sourceColorProperty() {
        return sourceColor;
    }

    public StringProperty statusProperty() {
        return status;
    }
}

enum SourceColor { //TODO replace to the ColorSpace and rename?
    HSV("HSV"),
    LAB("LAB"),
    RGB("RGB");

    private final String name;

    SourceColor(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public ColorSpace convertToColor(final ColorSpace srcColor, final Class<ColorSpace> clazz) {
        return srcColor.toColor(clazz);
    }
}

enum Status {
    WAITING("Please provide input data"),
    READY("Press 'Calculate' or Enter"),
    OUT_OF_RANGE("Values of one(several) color channel(s) out of range"),
    BAD_FORMAT("Bad format"),
    SUCCESS("Success");

    private final String name;

    private Status(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
