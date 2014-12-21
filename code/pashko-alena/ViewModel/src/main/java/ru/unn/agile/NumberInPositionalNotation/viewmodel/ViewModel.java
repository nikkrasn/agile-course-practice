package ru.unn.agile.NumberInPositionalNotation.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import ru.unn.agile.NumberInPositionalNotation.Model.NumberInPositionalNotation;
import ru.unn.agile.NumberInPositionalNotation.Model.Notation;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty inputNumber = new SimpleStringProperty();

    private final ObjectProperty<ObservableList<Notation>> notations =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Notation.values()));
    private final ObjectProperty<Notation> inputNotation = new SimpleObjectProperty<>();
    private final ObjectProperty<Notation> outputNotation = new SimpleObjectProperty<>();
    private final BooleanProperty convertDisabled = new SimpleBooleanProperty();
    private final StringProperty inputNotationStr = new SimpleStringProperty();

    private final StringProperty status = new SimpleStringProperty();
    private final StringProperty outputNumber = new SimpleStringProperty();

    private final List<NumberInPosNotationListener> valueChangedListeners = new ArrayList<>();
    private ILogger logger;
    private final StringProperty logs = new SimpleStringProperty();

    public void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = logger;
    }
    public ViewModel() {
        init();
    }

    public ViewModel(final ILogger logger) {
        setLogger(logger);
        init();
    }

    private void init() {
        inputNumber.set("");
        inputNotation.set(Notation.BINARY);
        outputNotation.set(Notation.DECIMAL);
        inputNotationStr.set(inputNotation.toString());
        outputNumber.set("");
        status.set(InputStatus.WAITING.toString());

        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(inputNumber, inputNotationStr);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == InputStatus.READY;
            }
        };
        convertDisabled.bind(couldCalculate.not());

        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(inputNumber);
            add(inputNotationStr);
        } };

        for (StringProperty field : fields) {
            final NumberInPosNotationListener listener = new NumberInPosNotationListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }
    public void convert() {
        if (convertDisabled.get()) {
            return;
        }
        NumberInPositionalNotation input =
                new NumberInPositionalNotation(inputNumber.get(), inputNotation.get());
        NumberInPositionalNotation output;
        output = input.convertToNotation(outputNotation.get());

        outputNumber.set(output.getValue());
        status.set(InputStatus.SUCCESS.toString());

        StringBuilder message = new StringBuilder(LogMessages.CONVERT_WAS_PRESSED);
        message.append("Arguments")
                .append(": Input Number = ").append(inputNumber.get())
                .append("; Input Notation = ").append(inputNotation.get().toString())
                .append("; Output Notation = ").append(outputNotation.get().toString()).append(".");
        logger.log(message.toString());
        updateLogs();
    }

    public void onNotationChanged(final Notation oldValue, final Notation newValue,
                                  final String position) {
        if (oldValue.equals(newValue)) {
            return;
        }
        StringBuilder message = new StringBuilder(LogMessages.NOTATION_WAS_CHANGED);
        message.append(position);
        message.append(": ");
        message.append(newValue.toString());
        logger.log(message.toString());
        updateLogs();
    }


    private void updateLogs() {
        List<String> fullLog = logger.getLog();
        String record = new String();
        for (String log : fullLog) {
            record += log + "\n";
        }
        logs.set(record);
    }

    public void changedArguments(final Boolean oldValue, final Boolean newValue) {
        if (!oldValue && newValue) {
            return;
        }

        for (NumberInPosNotationListener listener : valueChangedListeners) {
            if (listener.isChanged()) {
                StringBuilder message = new StringBuilder(LogMessages.EDITING_FINISHED);
                message.append("Input arguments are: [")
                        .append(inputNumber.get()).append("; ")
                        .append(inputNotation.get().toString()).append("; ")
                        .append(outputNotation.get().toString()).append("]");
                logger.log(message.toString());
                updateLogs();

                listener.cache();
                break;
            }
        }
    }

    public StringProperty inputNumberProperty() {
        return inputNumber;
    }

    public ObjectProperty<Notation> inputNotationProperty() {
        return inputNotation;
    }

    public ObjectProperty<Notation> outputNotationProperty() {
        return outputNotation;
    }

    public StringProperty outputNumberProperty() {
        return outputNumber;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public BooleanProperty convertDisabledProperty() {
        return convertDisabled;
    }

    public ObjectProperty<ObservableList<Notation>> notationsProperty() {
        return notations;
    }

    public final boolean getConvertDisabled() {
        return convertDisabled.get();
    }

    public final String getOutputNumber() {
        return outputNumber.get();
    }

    public final List<String> getLog() {
        return logger.getLog();
    }

    public StringProperty logsProperty() {
        return logs;
    }

    public final String getLogs() {
        return logs.get();
    }
    public final ObservableList<Notation> getNotations() {
        inputNotationStr.set(inputNotation.toString());
        return notations.get();
    }

    public final String getStatus() {
        return status.get();
    }

    private InputStatus getInputStatus() {
        InputStatus inputInputStatus;
        if (inputNumber.get().isEmpty()) {
            inputInputStatus = InputStatus.WAITING;
        } else {
            NumberInPositionalNotation number =
                    new NumberInPositionalNotation(inputNumber.get(), inputNotation.get());
            if (number.checkInputData()) {
                inputInputStatus = InputStatus.READY;
            } else {
                inputInputStatus = InputStatus.BAD_FORMAT;
            }
        }
        return inputInputStatus;
    }


    private class NumberInPosNotationListener implements ChangeListener<String> {
        private String prevValue = new String();
        private String curValue = new String();
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            if (oldValue.equals(newValue)) {
                return;
            }
            status.set(getInputStatus().toString());
            curValue = newValue;
        }

        public boolean isChanged() {
            return !prevValue.equals(curValue);
        }
        public void cache() {
            prevValue = curValue;
        }
    }
}

enum InputStatus {
    WAITING("Please provide input data"),
    READY("Press 'Convert' or Enter"),
    BAD_FORMAT("Bad format"),
    SUCCESS("Success");

    private final String name;

    private InputStatus(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
final class LogMessages {
    public static final String CONVERT_WAS_PRESSED = "Convert. ";
    public static final String NOTATION_WAS_CHANGED = "Notation was changed to ";
    public static final String EDITING_FINISHED = "Updated input. ";

    private LogMessages() { }
}
