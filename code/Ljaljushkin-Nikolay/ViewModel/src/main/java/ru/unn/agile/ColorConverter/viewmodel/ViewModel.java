package ru.unn.agile.ColorConverter.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.ColorConverter.model.ColorSpaces.ColorSpace;

public class ViewModel {

    private final StringProperty firstChannelSrcColor = new SimpleStringProperty();
    private final StringProperty secondChannelSrcColor = new SimpleStringProperty();
    private final StringProperty thirdChannelSrcColor = new SimpleStringProperty();

    private final StringProperty firstChannelDstColor = new SimpleStringProperty();
    private final StringProperty secondChannelDstColor = new SimpleStringProperty();
    private final StringProperty thirdChannelDstColor = new SimpleStringProperty();

    private final ObjectProperty<ObservableList<Color>> srcColors =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Color.values()));

    private final ObjectProperty<Color> srcColor = new SimpleObjectProperty<>();

    private final ObjectProperty<ObservableList<Color>> dstColors =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Color.values()));

    private final ObjectProperty<Color> dstColor = new SimpleObjectProperty<>();

    //    private final BooleanProperty convertionDisabled = new SimpleBooleanProperty();
    private final StringProperty status = new SimpleStringProperty();
    //    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    // FXML needs default c-tor for binding
    public ViewModel() {
        firstChannelSrcColor.set("");
        secondChannelSrcColor.set("");
        thirdChannelSrcColor.set("");

        firstChannelDstColor.set("");
        secondChannelDstColor.set("");
        thirdChannelDstColor.set("");

        status.set(Status.WAITING.toString());
        srcColor.set(Color.RGB);
        dstColor.set(Color.RGB);
    }

    public StringProperty firstChannelSrcColorProperty() {
        return firstChannelSrcColor;
    }

    public StringProperty secondChannelSrcColorProperty() {
        return secondChannelSrcColor;
    }

    public StringProperty thirdChannelSrcColorProperty() {
        return thirdChannelSrcColor;
    }

    public StringProperty firstChannelDstColorProperty() {
        return firstChannelDstColor;
    }

    public StringProperty secondChannelDstColorProperty() {
        return secondChannelDstColor;
    }

    public StringProperty thirdChannelDstColorProperty() {
        return thirdChannelDstColor;
    }

    public ObjectProperty<ObservableList<Color>> srcColorsProperty() {
        return srcColors;
    }

    public ObjectProperty<Color> srcColorProperty() {
        return srcColor;
    }

    public ObjectProperty<ObservableList<Color>> dstColorsProperty() {
        return dstColors;
    }

    public ObjectProperty<Color> dstColorProperty() {
        return dstColor;
    }

    public StringProperty statusProperty() {
        return status;
    }
}

enum Color { //TODO replace to the ColorSpace and rename?
    HSV("HSV"),
    LAB("LAB"),
    RGB("RGB");

    private final String name;

    Color(final String name) {
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
