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

    private final ObjectProperty<ObservableList<Pairs>> pairProbVal =
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

    public ObservableList<Pairs> getVectorsProbValues() {
        return pairProbVal.get();
    }

    private Boolean isVectorsValuesEmpty() {
        return getVectorsProbValues().isEmpty();
    }

    public final ObjectProperty<ObservableList<Pairs>> vectProbValueProperty() {
        return pairProbVal;
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

        getVectorsProbValues().addListener(new ListChangeListener<Pairs>() {
            @Override
            public void onChanged(final Change<? extends Pairs> c) {
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

        for (Pairs vectorsValue : pairProbVal.get()) {
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
                for (Pairs vectorsValue : pairProbVal.get()) {
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
        if (dimension < pairProbVal.get().size()) {
            getVectorsProbValues().remove(dimension, pairProbVal.get().size());
        }
        while (dimension > pairProbVal.get().size()) {
            getVectorsProbValues().add(new Pairs("0.0", "0.0"));
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
