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
    private ILogger logger;
    private final StringProperty logs = new SimpleStringProperty();
    private final List<StringChangeListener> valueChangedListeners = new ArrayList<>();
    private final BooleanProperty conversionDisabled = new SimpleBooleanProperty();

    public ViewModel() {
        init();
    }

    public ViewModel(final ILogger logger) {
        setLogger(logger);
        init();
    }

    private void init() {
        setDefaultFieldValues();
        addListeners();
        bindConversionDisabledProperty();
    }

    private void setDefaultFieldValues() {
        firstChannelSrcColorString.set("");
        secondChannelSrcColorString.set("");
        thirdChannelSrcColorString.set("");

        firstChannelDstColorString.set("");
        secondChannelDstColorString.set("");
        thirdChannelDstColorString.set("");

        status.set(AppStatus.WAITING.toString());
        srcColor.set(Color.RGB);
        dstColor.set(Color.LAB);
    }

    private void addListeners() {
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
                status.set(getInputAppStatus().toString());
                addToLogSafely(LogEvents.SRC_COLOR_WAS_CHANGED
                        + "from " + oldValue.toString()
                        + " to " + newValue.toString());
                updateLogsProperty();
            }
        });

        dstColor.addListener(new ChangeListener<Color>() {
            @Override
            public void changed(final ObservableValue<? extends Color> observableValue,
                                final Color oldValue, final Color newValue) {
                addToLogSafely(LogEvents.DST_COLOR_WAS_CHANGED
                        + "from " + oldValue.toString()
                        + " to " + newValue.toString());
                updateLogsProperty();
            }
        });
    }

    private void bindConversionDisabledProperty() {
        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(
                        firstChannelSrcColorString,
                        secondChannelSrcColorString,
                        thirdChannelSrcColorString,
                        status
                );
            }

            @Override
            protected boolean computeValue() {
                return getInputAppStatus() == AppStatus.READY;
            }
        };
        conversionDisabled.bind(couldCalculate.not());
    }

    private void updateLogsProperty() {
        List<String> logMessages = getLogSafely();
        String record = new String();
        for (String message : logMessages) {
            record += message + "\n";
        }
        logs.set(record);
    }

    private void addToLogSafely(final String message) {
        if (logger != null) {
            logger.addToLog(message);
        }
    }

    private List<String> getLogSafely() {
        if (logger == null) {
            return new ArrayList<>();
        }
        return logger.getLog();
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

        status.set(AppStatus.SUCCESS.toString());

        StringBuilder message = new StringBuilder(LogEvents.CONVERT_WAS_PRESSED);
        message.append(" Src(" + getSrcColor().toString() + "): ")
                .append(getFirstChannelSrcColorString() + ", ")
                .append(getSecondChannelSrcColorString() + ", ")
                .append(getThirdChannelSrcColorString() + ".")
                .append(" Dst(" + getDstColor().toString() + "): ")
                .append(getFirstChannelDstColorString() + ", ")
                .append(getSecondChannelDstColorString() + ", ")
                .append(getThirdChannelDstColorString() + ".");
        addToLogSafely(message.toString());
        updateLogsProperty();
    }

    public void onFocusChanged(final Boolean oldValue, final Boolean newValue) {
        if (!oldValue && newValue) {
            return;
        }

        for (StringChangeListener listener : valueChangedListeners) {
            if (listener.isChanged()) {
                StringBuilder message = new StringBuilder(LogEvents.EDITING_SRC_COLOR_FINISHED);
                message.append("[ " + getFirstChannelSrcColorString() + " , ")
                        .append(getSecondChannelSrcColorString() + " , ")
                        .append(getThirdChannelSrcColorString() + " ]");
                addToLogSafely(message.toString());
                updateLogsProperty();
                listener.rememberPrevString();
                break;
            }
        }
    }

    public void setLogger(final ILogger logger) {
        this.logger = logger;
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

    public String getAppStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public StringProperty logsProperty() {
        return logs;
    }

    public String getLogs() {
        return logs.get();
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

    private AppStatus getInputAppStatus() {
        AppStatus inputAppStatus = AppStatus.READY;
        if (isSrcColorEmpty()) {
            inputAppStatus = AppStatus.WAITING;
        }
        try {
            parseSrcColor();
            ColorSpace3D srcColor = getSrcColorValue();
            srcColor.setChannels(firstChannelSrcColor, secondChannelSrcColor, thirdChannelSrcColor);
            srcColor.verifyChannels();
        } catch (NumberFormatException nfe) {
            inputAppStatus = AppStatus.BAD_FORMAT;
        } catch (IllegalArgumentException iae) {
            inputAppStatus = AppStatus.OUT_OF_RANGE;
        }
        return inputAppStatus;
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

    public List<String> getLog() {
        if (logger == null) {
            return new ArrayList<>();
        }
        return logger.getLog();
    }

    private class StringChangeListener implements ChangeListener<String> {
        private String prevString = new String();
        private String curString = new String();

        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldString, final String newString) {
            status.set(getInputAppStatus().toString());
            curString = newString;
        }

        public boolean isChanged() {
            return !prevString.equals(curString);
        }

        public void rememberPrevString() {
            prevString = curString;
        }
    }
}

enum AppStatus {
    WAITING("Please provide input data"),
    READY("Press 'Convert' or Enter"),
    OUT_OF_RANGE("Values of one(several) color channel(s) out of range"),
    BAD_FORMAT("Bad format"),
    SUCCESS("Success");

    private final String name;

    private AppStatus(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}

final class LogEvents {
    public static final String CONVERT_WAS_PRESSED = "Convert. ";
    public static final String SRC_COLOR_WAS_CHANGED = "Source color was changed ";
    public static final String DST_COLOR_WAS_CHANGED = "Destination color was changed ";
    public static final String EDITING_SRC_COLOR_FINISHED = "Updated values of source color: ";

    private LogEvents() {
    }
}
