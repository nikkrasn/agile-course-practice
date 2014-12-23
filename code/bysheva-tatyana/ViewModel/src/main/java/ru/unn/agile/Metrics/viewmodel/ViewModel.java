package ru.unn.agile.Metrics.viewmodel;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import ru.unn.agile.Metrics.Model.Metrics.Operation;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private ILogger logger;

    private final ListProperty<Operation> operations =
            new SimpleListProperty<>(FXCollections.observableArrayList(Operation.values()));
    private final ObjectProperty<Operation> currentOperation = new SimpleObjectProperty<>();

    private final StringProperty vectorsDimension = new SimpleStringProperty();
    private final ObjectProperty<ObservableList<Components>> vectorsValues =
            new SimpleObjectProperty<>(FXCollections.observableArrayList());

    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();
    private final StringProperty metricResult = new SimpleStringProperty();
    private final StringProperty currentStatus = new SimpleStringProperty();

    public BooleanProperty calculationDisabledProperty() {
        return calculationDisabled;
    }
    public final Boolean getCalculationDisabled() {
        return calculationDisabled.get();
    }
    public StringProperty vectorsDimensionProperty() {
        return vectorsDimension;
    }
    public String getVectorsDimension() {
        return vectorsDimension.get();
    }
    public ObjectProperty<Operation> currentOperationProperty() {
        return currentOperation;
    }
    public Operation getCurrentOperation() {
        return currentOperation.get();
    }
    public final ObjectProperty<ObservableList<Components>> vectorsValuesProperty() {
        return vectorsValues;
    }
    public ObservableList<Components> getVectorsValues() {
        return vectorsValues.get();
    }
    public ListProperty<Operation> operationsProperty() {
        return operations;
    }
    public final ObservableList<Operation> getOperations() {
        return operations.get();
    }
    public StringProperty metricResultProperty() {
        return metricResult;
    }
    public final String getMetricResult() {
        return metricResult.get();
    }
    public StringProperty currentStatusProperty() {
        return currentStatus;
    }
    public final String getCurrentStatus() {
        return currentStatus.get();
    }

    public ViewModel() {
        currentOperation.set(Operation.METRIC_L1);
        metricResult.set("");
        currentStatus.set(CurrentStatus.READY.toString());

        vectorsDimension.addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable,
                                final String oldValue, final String newValue) {
                updateStatus();
            }
        });

        getVectorsValues().addListener(new ListChangeListener<Components>() {
            @Override
            public void onChanged(final Change<? extends Components> c) {
                updateStatus();
            }
        });

        vectorsDimension.set("1");
    }

    public void calculate() {
        if (getCalculationDisabled()) {
            return;
        }

        List<Float> vector1 = new ArrayList<>();
        List<Float> vector2 = new ArrayList<>();

        for (Components vectorsValue : vectorsValues.get()) {
            vector1.add(Float.parseFloat(vectorsValue.getComponent1()));
            vector2.add(Float.parseFloat(vectorsValue.getComponent2()));
        }

        metricResult.set(getCurrentOperation().apply(vector1, vector2).toString());
        currentStatus.set(CurrentStatus.SUCCESS.toString());
    }

    public List<String> getLog() {
        return logger == null
                ? new ArrayList<>()
                : logger.getLog();
    }

    private Boolean isVectorsValuesEmpty() {
        return getVectorsValues().isEmpty();
    }

    private Boolean isVectorsDimensionEmpty() {
        return getVectorsDimension().equals("");
    }

    private void updateStatus() {
        currentStatus.set(CurrentStatus.READY.toString());
        if (isVectorsValuesEmpty() || isVectorsDimensionEmpty()) {
            currentStatus.set(CurrentStatus.WAITING.toString());
        }
        try {
            if (!isVectorsDimensionEmpty()) {
                Integer newSize = Integer.parseInt(vectorsDimension.get());
                if (newSize <= 0) {
                    currentStatus.set(CurrentStatus.BAD_FORMAT.toString());
                }
                fetchVectorsDimension(newSize);
            }
            if (!isVectorsValuesEmpty()) {
                parseVectorsValues();
            }
        } catch (NumberFormatException nfe) {
            currentStatus.set(CurrentStatus.BAD_FORMAT.toString());
        }
        calculationDisabled.set(!getCurrentStatus().equals(CurrentStatus.READY.toString()));
    }

    private void parseVectorsValues() {
        for (Components vectorsValue : vectorsValues.get()) {
            Float.parseFloat(vectorsValue.getComponent1());
            Float.parseFloat(vectorsValue.getComponent2());
        }
    }

    private void fetchVectorsDimension(final Integer newSize) {
        if (newSize <= 0) {
            return;
        }
        if (newSize < vectorsValues.get().size()) {
            getVectorsValues().remove(newSize, vectorsValues.get().size());
        }
        while (newSize > vectorsValues.get().size()) {
            getVectorsValues().add(new Components("0.0f", "0.0f"));
        }
    }
}

enum CurrentStatus {
    WAITING("Please provide input data"),
    READY("Press 'Calculate' or Enter"),
    BAD_FORMAT("Bad format"),
    SUCCESS("Success");

    private final String name;
    private CurrentStatus(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
