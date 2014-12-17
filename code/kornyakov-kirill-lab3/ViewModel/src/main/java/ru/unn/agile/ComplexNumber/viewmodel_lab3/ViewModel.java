package ru.unn.agile.ComplexNumber.viewmodel_lab3;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.ComplexNumber.model_lab3.ComplexNumber;
import ru.unn.agile.ComplexNumber.model_lab3.ComplexNumber.Operation;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty re1 = new SimpleStringProperty();
    private final StringProperty im1 = new SimpleStringProperty();
    private final StringProperty re2 = new SimpleStringProperty();
    private final StringProperty im2 = new SimpleStringProperty();
    private final ObjectProperty<ObservableList<Operation>> operations =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Operation.values()));
    private final ObjectProperty<Operation> operation = new SimpleObjectProperty<>();
    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();
    private final StringProperty logs = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    private ILogger logger;
    private List<ValueCachingChangeListener> valueChangedListeners;

    public void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = logger;
    }

    // FXML needs default c-tor for binding
    public ViewModel() {
        init();
    }

    public ViewModel(final ILogger logger) {
        setLogger(logger);
        init();
    }

    private void init() {
        re1.set("");
        im1.set("");
        re2.set("");
        im2.set("");
        status.set(Status.WAITING.toString());
        operation.set(Operation.ADD);
        result.set("");

        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(re1, im1, re2, im2);
            }
            @Override
            protected boolean computeValue() {
                if (getInputStatus() == Status.READY) {
                    return true;
                }
                return false;
            }
        };
        calculationDisabled.bind(couldCalculate.not());

        final List<StringProperty> vals = new ArrayList<StringProperty>() { {
            add(re1);
            add(im1);
            add(re2);
            add(im2);
        } };
        valueChangedListeners = new ArrayList<>();
        for (StringProperty val : vals) {
            final ValueCachingChangeListener listener = new ValueCachingChangeListener();
            val.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public void calculate() {
        if (calculationDisabled.get()) {
            return;
        }

        ComplexNumber z1 = new ComplexNumber(re1.get(), im1.get());
        ComplexNumber z2 = new ComplexNumber(re2.get(), im2.get());

        result.set(operation.get().apply(z1, z2).toString());
        status.set(Status.SUCCESS.toString());

        StringBuilder message = new StringBuilder(LogMessages.CALCULATE_WAS_PRESSED);
        message.append("Arguments")
                .append(": Re1 = ").append(re1.get())
                .append("; Im1 = ").append(im1.get())
                .append("; Re2 = ").append(re2.get())
                .append("; Im2 = ").append(im2.get())
                .append(" Operation: ").append(operation.get().toString()).append(".");
        logger.log(message.toString());
        updateLogs();
    }

    public void onOperationChanged(final Operation oldValue, final Operation newValue) {
        if (oldValue.equals(newValue)) {
            return;
        }
        StringBuilder message = new StringBuilder(LogMessages.OPERATION_WAS_CHANGED);
        message.append(newValue.toString());
        logger.log(message.toString());
        updateLogs();
    }

    public void onFocusChanged(final Boolean oldValue, final Boolean newValue) {
        if (!oldValue && newValue) {
            return;
        }

        for (ValueCachingChangeListener listener : valueChangedListeners) {
            if (listener.isChanged()) {
                StringBuilder message = new StringBuilder(LogMessages.EDITING_FINISHED);
                message.append("Input arguments are: [")
                        .append(re1.get()).append("; ")
                        .append(im1.get()).append("; ")
                        .append(re2.get()).append("; ")
                        .append(im2.get()).append("]");
                logger.log(message.toString());
                updateLogs();

                listener.cache();
                break;
            }
        }
    }

    public final List<String> getLog() {
        return logger.getLog();
    }

    public StringProperty re1Property() {
        return re1;
    }
    public StringProperty im1Property() {
        return im1;
    }
    public StringProperty re2Property() {
        return re2;
    }
    public StringProperty im2Property() {
        return im2;
    }
    public ObjectProperty<ObservableList<Operation>> operationsProperty() {
        return operations;
    }
    public final ObservableList<Operation> getOperations() {
        return operations.get();
    }
    public ObjectProperty<Operation> operationProperty() {
        return operation;
    }
    public BooleanProperty calculationDisabledProperty() {
        return calculationDisabled;
    }
    public final boolean getCalculationDisabled() {
        return calculationDisabled.get();
    }
    public StringProperty logsProperty() {
        return logs;
    }
    public final String getLogs() {
        return logs.get();
    }
    public StringProperty resultProperty() {
        return result;
    }
    public final String getResult() {
        return result.get();
    }
    public StringProperty statusProperty() {
        return status;
    }
    public final String getStatus() {
        return status.get();
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (re1.get().isEmpty() || im1.get().isEmpty()
         || re2.get().isEmpty() || im2.get().isEmpty()) {
            inputStatus = Status.WAITING;
        }
        try {
            if (!re1.get().isEmpty()) {
                Double.parseDouble(re1.get());
            }
            if (!im1.get().isEmpty()) {
                Double.parseDouble(im1.get());
            }
            if (!re2.get().isEmpty()) {
                Double.parseDouble(re2.get());
            }
            if (!im2.get().isEmpty()) {
                Double.parseDouble(im2.get());
            }
        } catch (NumberFormatException nfe) {
            inputStatus = Status.BAD_FORMAT;
        }

        return inputStatus;
    }

    private void updateLogs() {
        List<String> fullLog = logger.getLog();
        String record = new String();
        for (String log : fullLog) {
            record += log + "\n";
        }
        logs.set(record);
    }

    private class ValueCachingChangeListener implements ChangeListener<String> {
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

enum Status {
    WAITING("Please provide input data"),
    READY("Press 'Calculate' or Enter"),
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

final class LogMessages {
    public static final String CALCULATE_WAS_PRESSED = "Calculate. ";
    public static final String OPERATION_WAS_CHANGED = "Operation was changed to ";
    public static final String EDITING_FINISHED = "Updated input. ";

    private LogMessages() { }
}
