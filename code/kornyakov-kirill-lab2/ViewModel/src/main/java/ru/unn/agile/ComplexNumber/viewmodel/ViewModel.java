package ru.unn.agile.ComplexNumber.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ViewModel {
    private final StringProperty re1 = new SimpleStringProperty();
    private final StringProperty im1 = new SimpleStringProperty();
    private final StringProperty re2 = new SimpleStringProperty();
    private final StringProperty im2 = new SimpleStringProperty();
    private final ObjectProperty<ObservableList<Operation>> operations =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Operation.values()));
    private final ReadOnlyBooleanWrapper calculationPossible = new ReadOnlyBooleanWrapper();
    private final StringProperty logs = new SimpleStringProperty();

    private final ILogger logger;
    private final ChangeListener<Boolean> focusChangedListener;
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

        focusChangedListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(final ObservableValue<? extends Boolean> observable,
                                final Boolean oldValue, final Boolean newValue) {
                if (isInputChanged && oldValue && !newValue) {
                    logger.log(LogMessages.EDITING_FINISHED
                            + "Input arguments are: ["
                            + re1.get() + "; "
                            + im1.get() + "; "
                            + re2.get() + "; "
                            + im2.get() + "]");

                    List<String> fullLog = logger.getLog();
                    String record = new String();
                    for (String log : fullLog) {
                        record += log + "\n";
                    }
                    logs.setValue(record);

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
                    return true;
                } catch (NumberFormatException nfe) {
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
    public ReadOnlyBooleanProperty isCalculationPossibleProperty() {
        return calculationPossible.getReadOnlyProperty();
    }
    public StringProperty logsProperty() {
        return logs;
    }

    public final ChangeListener<Boolean> getFocusChangeListener() {
        return focusChangedListener;
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

    public List<String> getLog() {
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
