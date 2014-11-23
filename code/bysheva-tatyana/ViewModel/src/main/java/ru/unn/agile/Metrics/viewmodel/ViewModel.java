package ru.unn.agile.Metrics.viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import ru.unn.agile.Metrics.Model.Metrics.Operation;

public class ViewModel {
    private final StringProperty vectorsDimension = new SimpleStringProperty();

    private final ObservableList<Pair<Float, Float>> vectorsValues =
            FXCollections.observableArrayList();

    private final ObjectProperty<ObservableList<Operation>> operations =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Operation.values()));
    private final ObjectProperty<Operation> operation = new SimpleObjectProperty<>();
    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();

    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    public StringProperty vectorsDimensionProperty() {
        return vectorsDimension;
    }
    public ObservableList vectorsValuesProperty() {
        return vectorsValues;
    }
    public ObjectProperty<Operation> operationProperty() {
        return operation;
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
        vectorsDimension.set("3");

        vectorsValues.add(new Pair<>(0.0f, 0.0f));
        vectorsValues.add(new Pair<>(0.0f, 0.0f));
        vectorsValues.add(new Pair<>(0.0f, 0.0f));

        operation.set(Operation.METRIC_L1);
        result.set("");
        status.set(Status.WAITING.toString());
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
