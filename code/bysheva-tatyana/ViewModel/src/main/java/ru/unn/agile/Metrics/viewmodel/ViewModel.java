package ru.unn.agile.Metrics.viewmodel;

import javafx.beans.binding.BooleanBinding;
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
    private final StringProperty vectorsDimension = new SimpleStringProperty();

    private final ObservableList<String> vector1Column =
            FXCollections.observableArrayList();

    private final ObservableList<String> vector2Column =
            FXCollections.observableArrayList();

    private final ObjectProperty<ObservableList<Operation>> operations =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Operation.values()));
    private final ObjectProperty<Operation> operation = new SimpleObjectProperty<>();
    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();

    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final SimpleListProperty<String> vector1Property =
            new SimpleListProperty<>(this, "vector1Column", vector1Column);
    private final SimpleListProperty<String> vector2Property =
            new SimpleListProperty<>(this, "vector2Column", vector2Column);

    public BooleanProperty calculationDisabledProperty() {
        return calculationDisabled;
    }
    public StringProperty vectorsDimensionProperty() {
        return vectorsDimension;
    }
    public ObjectProperty<Operation> operationProperty() {
        return operation;
    }
    public final SimpleListProperty<String> getVector1ValuesProperty() {
        return vector1Property;
    }
    public final SimpleListProperty<String> getVector2ValuesProperty() {
        return vector2Property;
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
                super.bind(vector1Column, vector2Column, vectorsDimension);
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

        vector1Property.addListener(listValuesPropertyChangeListener);
        vector2Property.addListener(listValuesPropertyChangeListener);

        vectorsDimensionProperty().set("3");

        getVector1ValuesProperty().add("1.0");
        getVector1ValuesProperty().add("2.0");
        getVector1ValuesProperty().add("3.0");

        getVector2ValuesProperty().add("0.0");
        getVector2ValuesProperty().add("1.0");
        getVector2ValuesProperty().add("2.0");
    }


    public void calculate() {
        if (calculationDisabled.get()) {
            return;
        }

        List<Float> vector1 = new ArrayList<Float>();
        List<Float> vector2 = new ArrayList<Float>();

        for (int i = 0; i < vector1Column.size(); i++) {
            vector1.add(Float.parseFloat(vector1Column.get(i)));
            vector2.add(Float.parseFloat(vector2Column.get(i)));
        }

        result.set(operation.get().apply(vector1, vector2).toString());
        status.set(Status.SUCCESS.toString());
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (vector1Column.isEmpty() || vector2Column.isEmpty() ||
                vectorsDimension.get().isEmpty() ||
                vector1Column.size() != vector2Column.size()) {
            inputStatus = Status.WAITING;
        }
        try {
            if (!vectorsDimension.get().isEmpty()) {
                Integer.parseInt(vectorsDimension.get());
            }
            if (!vector1Column.isEmpty() && !vector2Column.isEmpty()) {
                for (int i = 0; i < vector1Column.size(); i++) {
                   Float.parseFloat(vector1Column.get(i));
                   Float.parseFloat(vector2Column.get(i));
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
            ListChangeListener<String> {
        @Override
        public void onChanged(final ListChangeListener.Change<?
                extends String> change) {
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
