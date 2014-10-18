package ru.unn.agile.ComplexNumber.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import ru.unn.agile.ComplexNumber.model.ComplexNumber;

import java.util.List;

public class ViewModel {
    private final StringProperty re1 = new SimpleStringProperty();
    private final StringProperty im1 = new SimpleStringProperty();
    private final StringProperty re2 = new SimpleStringProperty();
    private final StringProperty im2 = new SimpleStringProperty();
    private final ObjectProperty<ObservableList<Operation>> operations =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Operation.values()));
    private final ObjectProperty<Operation> operation = new SimpleObjectProperty<>();
    private final ReadOnlyBooleanWrapper calculationPossible = new ReadOnlyBooleanWrapper();
    private final StringProperty logs = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    private final ChangeListener<Boolean> focusChangedListener;
    private final EventHandler<ActionEvent> calculationFired;
    private final ChangeListener<Operation> operationChanged;

    private final ILogger logger;
    private boolean isInputChanged;

    public ViewModel(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = logger;

        re1.setValue("");
        im1.setValue("");
        re2.setValue("");
        im2.setValue("");
        status.setValue(Status.WAITING);
        operation.setValue(Operation.ADD);

        calculationFired = new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                ComplexNumber z1 = new ComplexNumber(re1.get(), im1.get());
                ComplexNumber z2 = new ComplexNumber(re2.get(), im2.get());

                switch (operation.get()) {
                    case ADD:
                        result.setValue(z1.add(z2).toString());
                        break;
                    case MULTIPLY:
                        result.setValue(z1.multiply(z2).toString());
                        break;
                    default:
                        throw new IllegalArgumentException("Only ADD and MULTIPLY are supported");
                }
                status.setValue(Status.SUCCESS);

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
        };

        operationChanged = new ChangeListener<Operation>() {
            @Override
            public void changed(final ObservableValue<? extends Operation> observable,
                                final Operation oldValue, final Operation newValue) {
                if (!oldValue.equals(newValue)) {
                    StringBuilder message = new StringBuilder(LogMessages.OPERATION_WAS_CHANGED);
                    message.append(newValue.toString());
                    logger.log(message.toString());
                    updateLogs();
                }
            }
        };

        focusChangedListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(final ObservableValue<? extends Boolean> observable,
                                final Boolean oldValue, final Boolean newValue) {
                if (isInputChanged && oldValue && !newValue) {
                    StringBuilder message = new StringBuilder(LogMessages.EDITING_FINISHED);
                    message.append("Input arguments are: [")
                            .append(re1.get()).append(";")
                            .append(im1.get()).append(";")
                            .append(re2.get()).append(";")
                            .append(im2.get()).append("]");
                    logger.log(message.toString());
                    updateLogs();

                    isInputChanged = false;
                }
            }
        };

        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(re1, im1, re2, im2);
            }
            @Override
            protected boolean computeValue() {
                try {
                    Double.parseDouble(re1.get());
                    Double.parseDouble(im1.get());
                    Double.parseDouble(re2.get());
                    Double.parseDouble(im2.get());
                    status.setValue(Status.READY);
                    return true;
                } catch (NumberFormatException nfe) {
                    if (re1.get().isEmpty() || im1.get().isEmpty()
                            || re2.get().isEmpty() || im2.get().isEmpty()) {
                        status.setValue(Status.WAITING);
                    } else {
                        status.setValue(Status.BAD_FORMAT);
                    }
                    return false;
                }
            }
        };
        calculationPossible.bind(couldCalculate);

        ChangeListener<String> valueChangedListener = new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable,
                                final String oldValue, final String newValue) {
                if (oldValue.equals(newValue)) {
                    return;
                }
                isInputChanged = true;
            }
        };
        re1.addListener(valueChangedListener);
        im1.addListener(valueChangedListener);
        re2.addListener(valueChangedListener);
        im2.addListener(valueChangedListener);
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
    public ObjectProperty operationsProperty() {
        return operations;
    }
    public ObjectProperty operationProperty() {
        return operation;
    }
    public ReadOnlyBooleanProperty isCalculationPossibleProperty() {
        return calculationPossible.getReadOnlyProperty();
    }
    public StringProperty logsProperty() {
        return logs;
    }
    public StringProperty resultProperty() {
        return result;
    }
    public StringProperty statusProperty() {
        return status;
    }
    public final ChangeListener<Boolean> getFocusChangeListener() {
        return focusChangedListener;
    }
    public final EventHandler<ActionEvent> getCalculationFiredEventHandler() {
        return calculationFired;
    }
    public final ChangeListener<Operation> getOperationChangedListener() {
        return operationChanged;
    }

    private void updateLogs() {
        List<String> fullLog = logger.getLog();
        String record = new String();
        for (String log : fullLog) {
            record += log + "\n";
        }
        logs.setValue(record);
    }

    public enum Operation {
        ADD("Add"),
        MULTIPLY("Mul");
        private final String name;

        private Operation(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public final List<String> getLog() {
        return logger.getLog();
    }
}

final class Status {
    public static final String WAITING = "Please provide input data";
    public static final String READY = "Press 'Calculate' or Enter";
    public static final String BAD_FORMAT = "Bad format";
    public static final String SUCCESS = "Success";

    private Status() { }
}

final class LogMessages {
    public static final String CALCULATE_WAS_PRESSED = "Calculate. ";
    public static final String OPERATION_WAS_CHANGED = "Operation was changed to ";
    public static final String EDITING_FINISHED = "Updated input. ";

    private LogMessages() { }
}
