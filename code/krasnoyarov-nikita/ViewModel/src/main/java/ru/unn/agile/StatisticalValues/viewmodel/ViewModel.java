package ru.unn.agile.StatisticalValues.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import ru.unn.agile.StatisticalValues.model.StatisticalValues;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty vectorsDimension = new SimpleStringProperty();

    private final ObservableList<Pair<String, String>> vectProbVal =
            FXCollections.observableArrayList();
    private final ObjectProperty<ObservableList<StatisticalValues.Operation>> operations =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(StatisticalValues.Operation.values()));
    private final ObjectProperty<StatisticalValues.Operation> operation = new SimpleObjectProperty<>();
    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();

    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    public SimpleListProperty vectProbValProperty = new SimpleListProperty(this,"vectorsValues", vectProbVal);

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
                super.bind(vectProbVal);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        calculationDisabled.bind(couldCalculate.not());

        final StringChangeListener stringChangeListener
                = new StringChangeListener();
        vectDimensionProperty().addListener(stringChangeListener);

        final ListPropertyChangeListener listChangeListener
                = new ListPropertyChangeListener();
        vectProbValProperty.addListener(listChangeListener);
    }

    public void calculate() {
        if (calculationDisabled.get()) {
            return;
        }

        List<Double> val = new ArrayList<Double>();
        List<Double> probabil = new ArrayList<Double>();

        for (int i = 0; i < vectProbVal.size(); i++) {
            probabil.add(Double.parseDouble(vectProbVal.get(i).getKey()));
            val.add(Double.parseDouble(vectProbVal.get(i).getValue()));
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
        if (vectProbVal.isEmpty() || vectorsDimension.get().isEmpty()) {
            inputStatus = Status.WAITING;
        }
        try {
            if (!vectorsDimension.get().isEmpty()) {
                Integer.parseInt(vectorsDimension.get());
            }
            if (!vectProbVal.isEmpty()) {
                for (int i = 0; i < vectProbVal.size(); i++) {
                    Double.parseDouble(vectProbVal.get(i).getKey());
                    Double.parseDouble(vectProbVal.get(i).getValue());
                }
            }
        } catch (NumberFormatException nfe) {
            inputStatus = Status.BAD_FORMAT;
        }

        return inputStatus;
    }

    private class StringChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getInputStatus().toString());
        }
    }

    private class ListPropertyChangeListener implements ListChangeListener<Pair<String, String>> {
        @Override
        public void onChanged(ListChangeListener.Change<? extends Pair<String, String>> change) {
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
