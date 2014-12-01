package ru.unn.agile.Triangle.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.Triangle.Model.Point;
import ru.unn.agile.Triangle.Model.Triangle;
import ru.unn.agile.Triangle.Model.Triangle.Operation;
import ru.unn.agile.Triangle.Model.StringFormatter;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty aX = new SimpleStringProperty("");
    private final StringProperty aY = new SimpleStringProperty("");
    private final StringProperty bX = new SimpleStringProperty("");
    private final StringProperty bY = new SimpleStringProperty("");
    private final StringProperty cX = new SimpleStringProperty("");
    private final StringProperty cY = new SimpleStringProperty("");

    private final ObjectProperty<ObservableList<Operation>> operations =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Operation.values()));
    private final ObjectProperty<Operation> operation =
            new SimpleObjectProperty<>(Operation.PERIMETER);
    private final BooleanProperty computationDisabled = new SimpleBooleanProperty();

    private final StringProperty values = new SimpleStringProperty("");
    private final StringProperty dataStatus =
            new SimpleStringProperty(CurrentStatus.WAITING.toString());

    private final List<VariableListener> variableListeners = new ArrayList<>();

    public ViewModel() {
        BooleanBinding couldCompute = new BooleanBinding() {
            {
                super.bind(aX, aY, bX, bY, cX, cY);
            }
            @Override
            protected boolean computeValue() {
                return getDataStatus() == CurrentStatus.READY;
            }
        };
        computationDisabled.bind(couldCompute.not());

        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(aX);
            add(aY);
            add(bX);
            add(bY);
            add(cX);
            add(cY);
        } };

        for (StringProperty field : fields) {
            final VariableListener listener = new VariableListener();
            field.addListener(listener);
            variableListeners.add(listener);
        }
    }

    public void compute() {
        if (computationDisabled.get()) {
            return;
        }

        Point a = new Point(Double.parseDouble(aX.get()), Double.parseDouble(aY.get()));
        Point b = new Point(Double.parseDouble(bX.get()), Double.parseDouble(bY.get()));
        Point c = new Point(Double.parseDouble(cX.get()), Double.parseDouble(cY.get()));
        Triangle triangle = new Triangle(a, b, c);

        double[] doubleValues = operation.get().apply(triangle);

        values.set(StringFormatter.arrayFormat(doubleValues));
        dataStatus.set(CurrentStatus.SUCCESS.toString());
    }

    public StringProperty aXProperty() {
        return aX;
    }

    public StringProperty aYProperty() {
        return aY;
    }

    public StringProperty bXProperty() {
        return bX;
    }

    public StringProperty bYProperty() {
        return bY;
    }

    public StringProperty cXProperty() {
        return cX;
    }

    public StringProperty cYProperty() {
        return cY;
    }

    public ObjectProperty<ObservableList<Operation>> operationsProperty() {
        return operations;
    }

    public final ObservableList<Operation> getOperations() {
        return operations.get();
    }

    public ObjectProperty<Operation> operationProperty() {
        return operation;
    }

    public BooleanProperty computationDisabledProperty() {
        return computationDisabled;
    }

    public final boolean getComputationDisabled() {
        return computationDisabled.get();
    }

    public StringProperty valuesProperty() {
        return values;
    }

    public final String getValues() {
        return values.get();
    }

    public StringProperty statusProperty() {
        return dataStatus;
    }

    public final String getStatus() {
        return dataStatus.get();
    }

    private CurrentStatus getDataStatus() {
        CurrentStatus inputStatus = CurrentStatus.READY;
        if (aX.get().isEmpty() || aY.get().isEmpty()
         || bX.get().isEmpty() || bY.get().isEmpty()
         || cX.get().isEmpty() || cY.get().isEmpty()) {
            inputStatus = CurrentStatus.WAITING;
        }
        try {
            if (!aX.get().isEmpty()) {
                Double.parseDouble(aX.get());
            }
            if (!aY.get().isEmpty()) {
                Double.parseDouble(aY.get());
            }
            if (!bX.get().isEmpty()) {
                Double.parseDouble(bX.get());
            }
            if (!bY.get().isEmpty()) {
                Double.parseDouble(bY.get());
            }
            if (!cX.get().isEmpty()) {
                Double.parseDouble(cX.get());
            }
            if (!cY.get().isEmpty()) {
                Double.parseDouble(cY.get());
            }
        } catch (NumberFormatException nfe) {
            inputStatus = CurrentStatus.BAD_FORMAT;
        }

        return inputStatus;
    }

    private class VariableListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            dataStatus.set(getDataStatus().toString());
        }
    }
}

enum CurrentStatus {
    WAITING("Please provide input data"),
    READY("Press 'Compute!'"),
    BAD_FORMAT("Bad format"),
    SUCCESS("Successful computation");

    private final String name;
    private CurrentStatus(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
