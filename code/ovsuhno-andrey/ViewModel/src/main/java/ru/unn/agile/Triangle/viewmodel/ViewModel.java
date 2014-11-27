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

public class ViewModel
{
    private final StringProperty aX = new SimpleStringProperty();
    private final StringProperty aY = new SimpleStringProperty();
    private final StringProperty bX = new SimpleStringProperty();
    private final StringProperty bY = new SimpleStringProperty();
    private final StringProperty cX = new SimpleStringProperty();
    private final StringProperty cY = new SimpleStringProperty();

    private final ObjectProperty<ObservableList<Operation>> operations =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Operation.values()));
    private final ObjectProperty<Operation> operation = new SimpleObjectProperty<>();
    private final BooleanProperty computationDisabled = new SimpleBooleanProperty();

    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    public ViewModel() {
        aX.set("");
        aY.set("");
        bX.set("");
        bY.set("");
        cX.set("");
        cY.set("");
        operation.set(Operation.PERIMETER);
        result.set("");
        status.set(Status.WAITING.toString());

        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(aX, aY, bX, bY, cX, cY);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        computationDisabled.bind(couldCalculate.not());

        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(aX);
            add(aY);
            add(bX);
            add(bY);
            add(cX);
            add(cY);
        } };

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public void compute() {
        if (computationDisabled.get()) {
            return;
        }

        Point a = new Point(aX.get(), aY.get());
        Point b = new Point(bX.get(), bY.get());
        Point c = new Point(cX.get(), cY.get());
        Triangle triangle = new Triangle(a, b, c);

        double[] doubleResult = operation.get().apply(triangle);

        result.set(StringFormatter.arrayFormat(doubleResult));
        status.set(Status.SUCCESS.toString());
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
    public final boolean getCalculationDisabled() {
        return computationDisabled.get();
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

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (aX.get().isEmpty() || aY.get().isEmpty()
         || bX.get().isEmpty() || bY.get().isEmpty()
         || cX.get().isEmpty() || cY.get().isEmpty()) {
            inputStatus = Status.WAITING;
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
            inputStatus = Status.BAD_FORMAT;
        }

        return inputStatus;
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
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
