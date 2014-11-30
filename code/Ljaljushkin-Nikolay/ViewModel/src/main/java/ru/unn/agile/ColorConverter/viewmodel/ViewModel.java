package ru.unn.agile.ColorConverter.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.ColorConverter.model.ColorSpaces.ColorSpace;

import java.util.ArrayList;
import java.util.List;

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

    private final StringProperty status = new SimpleStringProperty();
    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();
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
        dstColor.set(Color.LAB);

        final List<StringProperty> fields = new ArrayList<StringProperty>() {
            {
                add(firstChannelSrcColor);
                add(secondChannelSrcColor);
                add(thirdChannelSrcColor);
                add(firstChannelDstColor);
                add(secondChannelDstColor);
                add(thirdChannelDstColor);
            }
        };
        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }
    public void convert() {
        String temp = getStatus();
    }
    public void setFirstChannelSrcColor(final String value) {
        firstChannelSrcColor.set(value);
    }
    public void setSecondChannelSrcColor(final String value) {
        secondChannelSrcColor.set(value);
    }
    public void setThirdChannelSrcColor(final String value) {
        thirdChannelSrcColor.set(value);
    }
    public void setFirstChannelDstColor(final String value) {
        firstChannelDstColor.set(value);
    }
    public void setSecondChannelDstColor(final String value) {
        secondChannelDstColor.set(value);
    }
    public void setThirdChannelDstColor(final String value) {
        thirdChannelDstColor.set(value);
    }
    public void setStatus(final String value) {
        status.set(value);
    }
    public String getFirstChannelSrcColor() {
        return firstChannelSrcColor.get();
    }
    public String getSecondChannelSrcColor() {
        return secondChannelSrcColor.get();
    }
    public String getThirdChannelSrcColor() {
        return thirdChannelSrcColor.get();
    }
    public String getFirstChannelDstColor() {
        return firstChannelDstColor.get();
    }
    public String getSecondChannelDstColor() {
        return secondChannelDstColor.get();
    }
    public String getThirdChannelDstColor() {
        return thirdChannelDstColor.get();
    }
    public String getStatus() {
        return status.get();
    }
    public Color getSrcColorValue() {
        return srcColor.get();
    }
    public Color getDstColorValue() {
        return dstColor.get();
    }
    public String getSrcColor() {
        return getSrcColorValue().toString();
    }
    public String getDstColor() {
        return getDstColorValue().toString();
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (isSrcColorEmpty() || isDstColorEmpty()) {
            inputStatus = Status.WAITING;
        }
        try {
            parseDstColor();
            parseSrcColor();
        } catch (NumberFormatException nfe) {
            inputStatus = Status.BAD_FORMAT;
        }
        return inputStatus;
    }
    private void parseSrcColor() {
        if (!getFirstChannelSrcColor().isEmpty()) {
            Double.parseDouble(getFirstChannelSrcColor());
        }
        if (!getSecondChannelSrcColor().isEmpty()) {
            Double.parseDouble(getSecondChannelSrcColor());
        }
        if (!getThirdChannelSrcColor().isEmpty()) {
            Double.parseDouble(getThirdChannelSrcColor());
        }
    }
    private void parseDstColor() {
        if (!getFirstChannelDstColor().isEmpty()) {
            Double.parseDouble(getFirstChannelDstColor());
        }
        if (!getSecondChannelDstColor().isEmpty()) {
            Double.parseDouble(getSecondChannelDstColor());
        }
        if (!getThirdChannelDstColor().isEmpty()) {
            Double.parseDouble(getThirdChannelDstColor());
        }
    }
    private boolean isSrcColorEmpty() {
        return getFirstChannelSrcColor().isEmpty()
                || getSecondChannelSrcColor().isEmpty()
                || getThirdChannelSrcColor().isEmpty();
    }
    private boolean isDstColorEmpty() {
        return getFirstChannelDstColor().isEmpty()
                || getSecondChannelDstColor().isEmpty()
                || getThirdChannelDstColor().isEmpty();
    }
    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getInputStatus().toString());
        }
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
