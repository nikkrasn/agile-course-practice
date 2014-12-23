package ru.unn.agile.StatisticalValues.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import ru.unn.agile.StatisticalValues.model.StatisticalValues;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty vectorsDimension = new SimpleStringProperty();

    private final ObservableList<Pair<Double, Double>> vectValProb =
            FXCollections.observableArrayList();
    private final ObjectProperty<ObservableList<StatisticalValues.Operation>> operations =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(StatisticalValues.Operation.values()));
    private final ObjectProperty<StatisticalValues.Operation> operation = new SimpleObjectProperty<>();
    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();

    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    public SimpleListProperty vectValProbProperty = new SimpleListProperty(this,"vectorsValues", vectValProb);

    public StringProperty vectDimensionProperty() {
        return vectorsDimension;
    }

    // FXML needs default c-tor for binding
    public ViewModel() {
        vectorsDimension.set("");

        operation.set(StatisticalValues.Operation.EXPECTED_VALUE);
        result.set("");
        status.set(Status.WAITING.toString());

        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(vectValProb);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        calculationDisabled.bind(couldCalculate.not());

        // Add listeners to the input text fields
        final List<Property> fields = new ArrayList<Property>() { {
            add(vectDimensionProperty());
            add(vectValProbProperty);
        } };

        for (Property field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public void calculate() {
        if (calculationDisabled.get()) {
            return;
        }

        List<Double> val = new ArrayList<Double>();
        List<Double> probabil = new ArrayList<Double>();

        for (int i = 0; i < vectValProb.size(); i++) {
            val.add(vectValProb.get(i).getKey());
            probabil.add(vectValProb.get(i).getValue());
        }

        StatisticalValues calculator = new StatisticalValues(probabil, val);

        result.set(operation.get().apply(calculator).toString());
        status.set(Status.SUCCESS.toString());
    }

    public ObjectProperty<ObservableList<StatisticalValues.Operation>> getOperationsProperty() {
        return operations;
    }

    public final ObservableList<StatisticalValues.Operation> getOperations() {
        return operations.get();
    }

    public ObjectProperty<StatisticalValues.Operation> operationProperty() {
        return operation;
    }

    public BooleanProperty calculationDisabledProperty() {
        return calculationDisabled;
    }

    public final boolean getCalculationDisabled() {
        return calculationDisabled.get();
    }

    public String getResult() {
        return result.get();
    }

    public StringProperty getStatusProperty() {
        return status;
    }

    public final String getStatus() {
        return status.get();
    }

    public StringProperty getResultProperty() {
        return result;
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (vectValProb.isEmpty() || vectorsDimension.get().isEmpty()) {
            inputStatus = Status.WAITING;
        }
        try {
            if (!vectorsDimension.get().isEmpty()) {
                Integer.parseInt(vectorsDimension.get());
            }
            if (!vectValProb.isEmpty()) {
                for (int i = 0; i < vectValProb.size(); i++) {
                    vectValProb.get(i).getKey();
                    vectValProb.get(i).getValue();
                }
            }
        } catch (NumberFormatException nfe) {
            inputStatus = Status.BAD_FORMAT;
        }

        return inputStatus;
    }

    private class ValueChangeListener implements ChangeListener<Object> {
        @Override
        public void changed(final ObservableValue<? extends Object> observable,
                            final Object oldValue, final Object newValue) {
            status.set(getInputStatus().toString());
        }
    }
}

enum Status {
    WAITING("Waiting for input data"),
    READY("Press 'Calculate' or Enter"),
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
