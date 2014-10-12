package ru.unn.agile.ComplexNumber.viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ViewModel {
    private final StringProperty re1 = new SimpleStringProperty();
    private final StringProperty im1 = new SimpleStringProperty();
    private final StringProperty re2 = new SimpleStringProperty();
    private final StringProperty im2 = new SimpleStringProperty();
    private final ObjectProperty<ObservableList<Operation>> operations =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Operation.values()));
    private final ReadOnlyBooleanWrapper calculationPossible = new ReadOnlyBooleanWrapper();

    public ViewModel() {
        calculationPossible.bind(re1.isNotNull().and(im1.isNotNull())
                .and(re2.isNotNull()).and(im2.isNotNull()));
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
}
