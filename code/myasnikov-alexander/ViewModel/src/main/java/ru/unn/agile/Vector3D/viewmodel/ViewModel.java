package ru.unn.agile.Vector3D.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import ru.unn.agile.Vector3D.Model.Vector3D;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty vector1CoordinateX = new SimpleStringProperty("");
    private final StringProperty vector1CoordinateY = new SimpleStringProperty("");
    private final StringProperty vector1CoordinateZ = new SimpleStringProperty("");

    private final StringProperty vector2CoordinateX = new SimpleStringProperty("");
    private final StringProperty vector2CoordinateY = new SimpleStringProperty("");
    private final StringProperty vector2CoordinateZ = new SimpleStringProperty("");

    private final StringProperty result = new SimpleStringProperty("");
    private final StringProperty status = new SimpleStringProperty("");
    private final StringProperty logs = new SimpleStringProperty();

    private final ObjectProperty<VectorOperation> operationList = new SimpleObjectProperty<>();
    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();

    private final ObjectProperty<ObservableList<VectorOperation>> operations =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(VectorOperation.values()));

    private ILogger logger;

    private List<ValueCachingChangeListener> valueChangedListeners;

    public void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger - parameter can't be null");
        }
        this.logger = logger;
    }

    public ViewModel() {
        initialization();
    }

    public ViewModel(final ILogger logger) {
        setLogger(logger);
        initialization();
    }

    private void initialization() {
        status.setValue(StatusOperation.WAITING.toString());

        operationList.set(VectorOperation.NORM);

        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(vector1CoordinateX, vector1CoordinateY, vector1CoordinateZ,
                        vector2CoordinateX, vector2CoordinateY, vector2CoordinateZ,
                        operationList);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == StatusOperation.READY;
            }
        };
        calculationDisabled.bind(couldCalculate.not());

        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(vector1CoordinateX);
            add(vector1CoordinateY);
            add(vector1CoordinateZ);

            add(vector2CoordinateX);
            add(vector2CoordinateY);
            add(vector2CoordinateZ);
        } };

        valueChangedListeners = new ArrayList<>();
        for (StringProperty val : fields) {
            final ValueCachingChangeListener listener = new ValueCachingChangeListener();
            val.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public StringProperty getVector1CoordinateX() {
        return vector1CoordinateX;
    }

    public StringProperty getVector1CoordinateY() {
        return vector1CoordinateY;
    }

    public StringProperty getVector1CoordinateZ() {
        return vector1CoordinateZ;
    }

    public StringProperty getVector2CoordinateX() {
        return vector2CoordinateX;
    }

    public StringProperty getVector2CoordinateY() {
        return vector2CoordinateY;
    }

    public StringProperty getVector2CoordinateZ() {
        return vector2CoordinateZ;
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

    public final List<String> getLog() {
        return logger.getLog();
    }

    public ObjectProperty<ObservableList<VectorOperation>> operationsProperty() {
     return operations;
    }
    public final ObservableList<VectorOperation> getOperations() {
       return operations.get();
    }

    public ObjectProperty<VectorOperation> operationProperty() {
        return operationList;
    }

    public void calculate() {
        if (calculationDisabled.get()) {
            return;
        }

        Vector3D v1 = new Vector3D(
                Double.parseDouble(vector1CoordinateX.get()),
                Double.parseDouble(vector1CoordinateY.get()),
                Double.parseDouble(vector1CoordinateZ.get()));
        Vector3D v2 = new Vector3D(0, 0, 0);
        switch (operationList.get()) {
            case NORM:
                result.set(String.format("%.3f", v1.getNorm()));
                break;
            case NORMALAZE:
                v1.normalize();
                result.set(v1.toString());
                break;
            case DOTPRODUCT:
                v2 = new Vector3D(
                        Double.parseDouble(vector2CoordinateX.get()),
                        Double.parseDouble(vector2CoordinateY.get()),
                        Double.parseDouble(vector2CoordinateZ.get()));
                double dotProduct = Vector3D.dotProduct(v1, v2);
                result.set(String.format("%.3f", dotProduct));
                break;
            case CROSSPRODUCT:
                v2 = new Vector3D(
                        Double.parseDouble(vector2CoordinateX.get()),
                        Double.parseDouble(vector2CoordinateY.get()),
                        Double.parseDouble(vector2CoordinateZ.get()));
                Vector3D v3 = Vector3D.crossProduct(v1, v2);
                result.set(v3.toString());
                break;
            default:
                break;
        }
        status.set(StatusOperation.SUCCESS.toString());

        StringBuilder message = new StringBuilder(LogMessages.CALCULATE_WAS_PRESSED);
        message.append("Input :")
                .append("Vector1 = ").append(v1.toString())
                .append(", Vector2 = ").append(v2.toString())
                .append(", Operation = ").append(operationList.get().toString());
        message.append(". Output :")
                .append("Result = ").append(getResult()).append(".");
        logger.log(message.toString());
        updateLogs();
    }

    public void onOperationChanged(final VectorOperation oldValue, final VectorOperation newValue) {
        if (oldValue.equals(newValue)) {
            return;
        }
        StringBuilder log = new StringBuilder(LogMessages.OPERATION_WAS_CHANGED);
        log.append(newValue.toString());
        logger.log(log.toString());
        updateLogs();
    }

    private class ValueCachingChangeListener implements ChangeListener<String> {
        private String curValue = new String();
        private String prevValue = new String();

        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            if (oldValue.equals(newValue)) {
                return;
            }
            curValue = newValue;
            status.set(getInputStatus().toString());
        }

        public void cache() {
            prevValue = curValue;
        }

        public boolean isChanged() {
            return !prevValue.equals(curValue);
        }

    }

    public void onFocusChanged(final Boolean oldValue, final Boolean newValue) {
        if (!oldValue && newValue) {
            return;
        }

        for (ValueCachingChangeListener listener : valueChangedListeners) {
            if (listener.isChanged()) {
                StringBuilder message = new StringBuilder(LogMessages.EDITING_FINISHED);
                message.append("Input arguments are: [")
                        .append(vector1CoordinateX.get()).append("; ")
                        .append(vector1CoordinateY.get()).append("; ")
                        .append(vector1CoordinateZ.get()).append("; ")
                        .append(vector2CoordinateX.get()).append("; ")
                        .append(vector2CoordinateY.get()).append("; ")
                        .append(vector2CoordinateZ.get()).append("]");
                logger.log(message.toString());
                updateLogs();

                listener.cache();
                break;
            }
        }
    }

    public BooleanProperty calculationDisabledProperty() {
        return calculationDisabled;
    }
    public final boolean getCalculationDisabled() {
        return calculationDisabled.get();
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getInputStatus().toString());
        }
    }

    public StringProperty logsProperty() {
       return logs;
   }

    public final String getLogs() {
       return logs.get();
    }

    private StatusOperation getInputStatus() {
        StatusOperation inputStatus = StatusOperation.READY;
        VectorOperation operation = operationList.get();
        boolean isEmptyVector1 = vector1CoordinateX.get().isEmpty()
                                || vector1CoordinateY.get().isEmpty()
                                || vector1CoordinateZ.get().isEmpty();
        boolean isEmptyVector2 = vector2CoordinateX.get().isEmpty()
                                || vector2CoordinateY.get().isEmpty()
                                || vector2CoordinateZ.get().isEmpty();
        if (operation == VectorOperation.DOTPRODUCT
                || operation == VectorOperation.CROSSPRODUCT) {
            if (isEmptyVector1 || isEmptyVector2) {
                inputStatus = StatusOperation.WAITING;
            }
        } else {
            if (isEmptyVector1) {
                inputStatus = StatusOperation.WAITING;
            }
        }
        try {
            if (!vector1CoordinateX.get().isEmpty()) {
                Double.parseDouble(vector1CoordinateX.get());
            }
            if (!vector1CoordinateY.get().isEmpty()) {
                Double.parseDouble(vector1CoordinateY.get());
            }
            if (!vector1CoordinateZ.get().isEmpty()) {
                Double.parseDouble(vector1CoordinateZ.get());
            }
            if (operation == VectorOperation.DOTPRODUCT
                    || operation == VectorOperation.CROSSPRODUCT) {
                if (!vector2CoordinateX.get().isEmpty()) {
                    Double.parseDouble(vector2CoordinateX.get());
                }
                if (!vector2CoordinateY.get().isEmpty()) {
                    Double.parseDouble(vector2CoordinateY.get());
                }
                if (!vector2CoordinateZ.get().isEmpty()) {
                    Double.parseDouble(vector2CoordinateZ.get());
                }
            }
        } catch (NumberFormatException nfe) {
            inputStatus = StatusOperation.BAD_FORMAT;
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
}

enum StatusOperation {
    READY("Press 'Calculate' or Enter"),
    WAITING("Please provide input data"),
    BAD_FORMAT("Bad format"),
    SUCCESS("Success");

    private final String name;
    public String toString() {
        return name;
    }

    private StatusOperation(final String name) {
        this.name = name;
    }
}

final class LogMessages {
    public static final String EDITING_FINISHED = "Updated input. ";
    public static final String CALCULATE_WAS_PRESSED = "Calculate. ";
    public static final String OPERATION_WAS_CHANGED = "Operation was changed to ";

    private LogMessages() { }
}
