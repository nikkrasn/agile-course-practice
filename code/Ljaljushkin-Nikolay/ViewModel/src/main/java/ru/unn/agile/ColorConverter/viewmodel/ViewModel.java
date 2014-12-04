package ru.unn.agile.ColorConverter.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.ColorConverter.model.ColorSpaces.*;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty firstChannelSrcColorString = new SimpleStringProperty();
    private final StringProperty secondChannelSrcColorString = new SimpleStringProperty();
    private final StringProperty thirdChannelSrcColorString = new SimpleStringProperty();
    private final StringProperty firstChannelDstColorString = new SimpleStringProperty();
    private final StringProperty secondChannelDstColorString = new SimpleStringProperty();
    private final StringProperty thirdChannelDstColorString = new SimpleStringProperty();

    private double firstChannelSrcColor;
    private double secondChannelSrcColor;
    private double thirdChannelSrcColor;

    private final ObjectProperty<Color> srcColor = new SimpleObjectProperty<>();
    private final ObjectProperty<ObservableList<Color>> srcColors =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Color.values()));

    private final ObjectProperty<Color> dstColor = new SimpleObjectProperty<>();
    private final ObjectProperty<ObservableList<Color>> dstColors =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Color.values()));

    private final StringProperty status = new SimpleStringProperty();
    private final List<StringChangeListener> valueChangedListeners = new ArrayList<>();
    private final BooleanProperty conversionDisabled = new SimpleBooleanProperty();

    // FXML needs default c-tor for binding
    public ViewModel() {
        firstChannelSrcColorString.set("");
        secondChannelSrcColorString.set("");
        thirdChannelSrcColorString.set("");

        firstChannelDstColorString.set("");
        secondChannelDstColorString.set("");
        thirdChannelDstColorString.set("");

        status.set(Status.WAITING.toString());
        srcColor.set(Color.RGB);
        dstColor.set(Color.LAB);

        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(
                        firstChannelSrcColorString,
                        secondChannelSrcColorString,
                        thirdChannelSrcColorString
                );
            }

            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        conversionDisabled.bind(couldCalculate.not());

        final List<StringProperty> fields = new ArrayList<StringProperty>() {
            {
                add(firstChannelSrcColorString);
                add(secondChannelSrcColorString);
                add(thirdChannelSrcColorString);
            }
        };
        for (StringProperty field : fields) {
            final StringChangeListener listener = new StringChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }

        srcColor.addListener(new ChangeListener<Color>() {
            @Override
            public void changed(final ObservableValue<? extends Color> observableValue,
                                final Color oldValue, final Color newValue) {
                status.set(getInputStatus().toString());
            }
        });
    }

    public void convert() {
        if (getConversionDisabled()) {
            return;
        }

        ColorSpace3D srcColorValue = getSrcColorValue();
        ColorSpace3D dstColorValue = getDstColorValue();

        dstColorValue = (ColorSpace3D) srcColorValue.toColor(dstColorValue.getClass());
        getDstColor().setColor(dstColorValue);

        Double firstChannel = dstColorValue.getFirstChannel();
        Double secondChannel = dstColorValue.getSecondChannel();
        Double thirdChannel = dstColorValue.getThirdChannel();

        setFirstChannelDstColorString(firstChannel.toString());
        setSecondChannelDstColorString(secondChannel.toString());
        setThirdChannelDstColorString(thirdChannel.toString());

        status.set(Status.SUCCESS.toString());
    }

    public void setFirstChannelSrcColorString(final String value) {
        firstChannelSrcColorString.set(value);
    }

    public void setSecondChannelSrcColorString(final String value) {
        secondChannelSrcColorString.set(value);
    }

    public void setThirdChannelSrcColorString(final String value) {
        thirdChannelSrcColorString.set(value);
    }

    public void setFirstChannelDstColorString(final String value) {
        firstChannelDstColorString.set(value);
    }

    public void setSecondChannelDstColorString(final String value) {
        secondChannelDstColorString.set(value);
    }

    public void setThirdChannelDstColorString(final String value) {
        thirdChannelDstColorString.set(value);
    }

    public void setSrcColor(final Color value) {
        srcColor.set(value);
    }

    public void setDstColor(final Color value) {
        dstColor.set(value);
    }

    public String getFirstChannelSrcColorString() {
        return firstChannelSrcColorString.get();
    }

    public String getSecondChannelSrcColorString() {
        return secondChannelSrcColorString.get();
    }

    public String getThirdChannelSrcColorString() {
        return thirdChannelSrcColorString.get();
    }

    public String getFirstChannelDstColorString() {
        return firstChannelDstColorString.get();
    }

    public String getSecondChannelDstColorString() {
        return secondChannelDstColorString.get();
    }

    public String getThirdChannelDstColorString() {
        return thirdChannelDstColorString.get();
    }

    public StringProperty firstChannelSrcColorStringProperty() {
        return firstChannelSrcColorString;
    }

    public StringProperty secondChannelSrcColorStringProperty() {
        return secondChannelSrcColorString;
    }

    public StringProperty thirdChannelSrcColorStringProperty() {
        return thirdChannelSrcColorString;
    }

    public StringProperty firstChannelDstColorStringProperty() {
        return firstChannelDstColorString;
    }

    public StringProperty secondChannelDstColorStringProperty() {
        return secondChannelDstColorString;
    }

    public StringProperty thirdChannelDstColorStringProperty() {
        return thirdChannelDstColorString;
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public Color getSrcColor() {
        return srcColor.get();
    }

    public Color getDstColor() {
        return dstColor.get();
    }

    public final ObservableList<Color> getSrcColors() {
        return srcColors.get();
    }

    public final ObservableList<Color> getDstColors() {
        return dstColors.get();
    }

    public ColorSpace3D getSrcColorValue() {
        return getSrcColor().getValue();
    }

    public ColorSpace3D getDstColorValue() {
        return getDstColor().getValue();
    }

    public ObjectProperty<Color> srcColorProperty() {
        return srcColor;
    }

    public ObjectProperty<Color> dstColorProperty() {
        return dstColor;
    }

    public final boolean getConversionDisabled() {
        return conversionDisabled.get();
    }

    public BooleanProperty conversionDisabledProperty() {
        return conversionDisabled;
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (isSrcColorEmpty()) {
            inputStatus = Status.WAITING;
        }
        try {
            parseSrcColor();
            ColorSpace3D srcColor = getSrcColorValue();
            srcColor.setValues(firstChannelSrcColor, secondChannelSrcColor, thirdChannelSrcColor);
        } catch (NumberFormatException nfe) {
            inputStatus = Status.BAD_FORMAT;
        } catch (IllegalArgumentException iae) {
            inputStatus = Status.OUT_OF_RANGE;
        }
        return inputStatus;
    }

    private void parseSrcColor() {
        if (!getFirstChannelSrcColorString().isEmpty()) {
            firstChannelSrcColor = Double.parseDouble(getFirstChannelSrcColorString());
        }
        if (!getSecondChannelSrcColorString().isEmpty()) {
            secondChannelSrcColor = Double.parseDouble(getSecondChannelSrcColorString());
        }
        if (!getThirdChannelSrcColorString().isEmpty()) {
            thirdChannelSrcColor = Double.parseDouble(getThirdChannelSrcColorString());
        }
    }

    private boolean isSrcColorEmpty() {
        return getFirstChannelSrcColorString().isEmpty()
                || getSecondChannelSrcColorString().isEmpty()
                || getThirdChannelSrcColorString().isEmpty();
    }

    private class StringChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldString, final String newString) {
            status.set(getInputStatus().toString());
        }
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
