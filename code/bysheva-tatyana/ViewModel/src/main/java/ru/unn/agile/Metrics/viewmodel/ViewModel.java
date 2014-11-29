package ru.unn.agile.Metrics.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import ru.unn.agile.Metrics.Model.Metrics.Operation;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty vectorsDimension = new SimpleStringProperty();

    private final ObservableList<Pair<String, String>> vectorsValues =
            FXCollections.observableArrayList();

    private final ObjectProperty<ObservableList<Operation>> operations =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Operation.values()));
    private final ObjectProperty<Operation> operation = new SimpleObjectProperty<>();
    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();

    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final SimpleListProperty<Pair<String, String>> vectorsValuesProperty =
            new SimpleListProperty<>(this, "vectorsValues", vectorsValues);

    public BooleanProperty calculationDisabledProperty() {
        return calculationDisabled;
    }
    public StringProperty vectorsDimensionProperty() {
        return vectorsDimension;
    }
    public ObjectProperty<Operation> operationProperty() {
        return operation;
    }
    public final SimpleListProperty<Pair<String, String>> getVectorsValuesProperty() {
        return vectorsValuesProperty;
    }
    public ObjectProperty<ObservableList<Operation>> operationsProperty() {
        return operations;
    }
    public final ObservableList<Operation> getOperations() {
        return operations.get();
    }
    public final boolean getCalculationDisabled() {
        return calculationDisabled.get();
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

    // FXML needs default c-tor for binding
    public ViewModel() {
        vectorsDimension.set("");

        operation.set(Operation.METRIC_L1);
        result.set("");
        status.set(Status.WAITING.toString());

        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(vectorsValues, vectorsDimension);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        calculationDisabled.bind(couldCalculate.not());

        // Add listeners to the input text fields
        final StringPropertyChangeListener stringPropertyChangeListener
                = new StringPropertyChangeListener();

        vectorsDimensionProperty().addListener(stringPropertyChangeListener);

        final ListValuesPropertyChangeListener listValuesPropertyChangeListener
                = new ListValuesPropertyChangeListener();

        vectorsValuesProperty.addListener(listValuesPropertyChangeListener);
    }


    public void calculate() {
        if (calculationDisabled.get()) {
            return;
        }

        List<Float> vector1 = new ArrayList<Float>();
        List<Float> vector2 = new ArrayList<Float>();

        for (int i = 0; i < vectorsValues.size(); i++) {
            vector1.add(Float.parseFloat(vectorsValues.get(i).getKey()));
            vector2.add(Float.parseFloat(vectorsValues.get(i).getValue()));
        }

        result.set(operation.get().apply(vector1, vector2).toString());
        status.set(Status.SUCCESS.toString());
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (vectorsValues.isEmpty() || vectorsDimension.get().isEmpty()) {
            inputStatus = Status.WAITING;
        }
        try {
            if (!vectorsDimension.get().isEmpty()) {
                Integer.parseInt(vectorsDimension.get());
            }
            if (!vectorsValues.isEmpty()) {
                for (int i = 0; i < vectorsValues.size(); i++) {
                   Float.parseFloat(vectorsValues.get(i).getKey());
                   Float.parseFloat(vectorsValues.get(i).getValue());
                }
            }
        } catch (NumberFormatException nfe) {
            inputStatus = Status.BAD_FORMAT;
        }

        return inputStatus;
    }

    private class StringPropertyChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getInputStatus().toString());
        }
    }

    private class ListValuesPropertyChangeListener implements
            ListChangeListener<Pair<String, String>> {
        @Override
        public void onChanged(final ListChangeListener.Change<?
                extends Pair<String, String>> change) {
            status.set(getInputStatus().toString());
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
