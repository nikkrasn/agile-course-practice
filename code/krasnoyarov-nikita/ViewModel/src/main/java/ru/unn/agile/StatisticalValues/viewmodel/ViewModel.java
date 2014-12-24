package ru.unn.agile.StatisticalValues.viewmodel;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import ru.unn.agile.StatisticalValues.model.StatisticalValues;
import ru.unn.agile.StatisticalValues.model.Operation;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {

    private final ObjectProperty<ObservableList<Vectors>> vectProbVal =
            new SimpleObjectProperty<>(FXCollections.observableArrayList());

    private final StringProperty vectDimension = new SimpleStringProperty();

    private final ObjectProperty<ObservableList<Operation>> operations =
    new SimpleObjectProperty<>(FXCollections.observableArrayList(Operation.values()));

    private final ObjectProperty<Operation> operation = new SimpleObjectProperty<>();
    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();

    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty operationStatus = new SimpleStringProperty();


    public ObjectProperty<ObservableList<Operation>> getOperationsProperty() {
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

    public final String getResult() {
        return result.get();
    }

    public StringProperty getOperationStatusProperty() {
        return operationStatus;
    }

    public final String getOperationStatus() {
        return operationStatus.get();
    }

    public StringProperty getResultProperty() {
        return result;
    }

    public ObservableList<Vectors> getVectorsProbValues() {
        return vectProbVal.get();
    }

    private Boolean isVectorsValuesEmpty() {
        return getVectorsProbValues().isEmpty();
    }

    public final ObjectProperty<ObservableList<Vectors>> vectProbValueProperty() {
        return vectProbVal;
    }

    public StringProperty vectDimensionProperty() {
        return vectDimension;
    }
    public String getVectDimension() {
        return vectDimension.get();
    }

    private Boolean isVectorsDimensionEmpty() {
        return getVectDimension().equals("");
    }

    public ViewModel() {
        operation.set(Operation.EXPECTED_VALUE);
        result.set("");
        operationStatus.set(Status.READY.toString());

        vectDimension.addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable,
                                final String oldValue, final String newValue) {
                changeStatus();
            }
        });

        getVectorsProbValues().addListener(new ListChangeListener<Vectors>() {
            @Override
            public void onChanged(final Change<? extends Vectors> c) {
                changeStatus();
            }
        });

        vectDimension.set("1");
    }

    public void calculate() {
        if (getCalculationDisabled()) {
            return;
        }

        List<Double> values = new ArrayList<Double>();
        List<Double> probabilities = new ArrayList<Double>();

        for (Vectors vectorsValue : vectProbVal.get()) {
            probabilities.add(Double.parseDouble(vectorsValue.getFirstComponent()));
            values.add(Double.parseDouble(vectorsValue.getSecondComponent()));
        }

        StatisticalValues calculator = new StatisticalValues(values, probabilities);

        try {
            result.set(operation.get().apply(calculator).toString());
        } catch (IllegalArgumentException ex) {
            operationStatus.set(Status.BAD_FORMAT.toString());
            return;
        }
        operationStatus.set(Status.SUCCESS.toString());
    }

    private StringProperty changeStatus() {
        operationStatus.set(Status.READY.toString());
        if (isVectorsValuesEmpty() || isVectorsDimensionEmpty()) {
            operationStatus.set(Status.WAITING.toString());
        }
        try {
            if (!isVectorsDimensionEmpty()) {
                Integer size = Integer.parseInt(vectDimension.get());
                if (size <= 0) {
                   getOperationStatusProperty().set(Status.BAD_FORMAT.toString());
                }
                resizeVectors(size);
            }
            if (!isVectorsValuesEmpty()) {
                for (Vectors vectorsValue : vectProbVal.get()) {
                    Double.parseDouble(vectorsValue.getFirstComponent());
                    Double.parseDouble(vectorsValue.getSecondComponent());
                }
            }
        } catch (NumberFormatException nfe) {
            operationStatus.set(Status.BAD_FORMAT.toString());
        }
        calculationDisabled.set(!getOperationStatus().equals(Status.READY.toString()));

        return operationStatus;
    }

    private void resizeVectors(final Integer dimension) {
        if (dimension <= 0) {
            return;
        }
        if (dimension < vectProbVal.get().size()) {
            getVectorsProbValues().remove(dimension, vectProbVal.get().size());
        }
        while (dimension > vectProbVal.get().size()) {
            getVectorsProbValues().add(new Vectors("0.0", "0.0"));
        }
    }
}

enum Status {
    WAITING("Waiting for input data"),
    READY("Press 'Calculate'"),
    BAD_FORMAT("Bad format of values or probabilities"),
    SUCCESS("Success");

    private final String name;
    private Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
