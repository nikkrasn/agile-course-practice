package ru.unn.agile.Vector3D.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import ru.unn.agile.Vector3D.Model.Vector3D;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty vector1CoordinateX = new SimpleStringProperty("");
    private final StringProperty vector1CoordinateY = new SimpleStringProperty("");
    private final StringProperty vector1CoordinateZ = new SimpleStringProperty("");

    private final StringProperty vector2CoordinateX = new SimpleStringProperty("");
    private final StringProperty vector2CoordinateY = new SimpleStringProperty("");
    private final StringProperty vector2CoordinateZ = new SimpleStringProperty("");

    private final StringProperty result = new SimpleStringProperty("");
    private final StringProperty status = new SimpleStringProperty("");

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();
    private final ObjectProperty<VectorOperation> operationList = new SimpleObjectProperty<>();
    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();

    private final ObjectProperty<ObservableList<VectorOperation>> operations =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(VectorOperation.values()));

    public ViewModel() {
        status.setValue(Status.WAITING.toString());

        operationList.set(VectorOperation.NORM);

        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(vector1CoordinateX, vector1CoordinateY, vector1CoordinateZ,
                            vector2CoordinateX, vector2CoordinateY, vector2CoordinateZ);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        calculationDisabled.bind(couldCalculate.not());

        // Add listeners to the input text fields
        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(vector1CoordinateX);
            add(vector1CoordinateY);
            add(vector1CoordinateZ);

            add(vector2CoordinateX);
            add(vector2CoordinateY);
            add(vector2CoordinateZ);
        } };

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public StringProperty getVector1CoordinateX() {
        return vector1CoordinateX;
    }

    public StringProperty getVector1CoordinateY() {
        return vector1CoordinateY;
    }

    public StringProperty getVector1CoordinateZ() {
        return vector1CoordinateZ;
    }

    public StringProperty getVector2CoordinateX() {
        return vector2CoordinateX;
    }

    public StringProperty getVector2CoordinateY() {
        return vector2CoordinateY;
    }

    public StringProperty getVector2CoordinateZ() {
        return vector2CoordinateZ;
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

    public ObjectProperty<ObservableList<VectorOperation>> operationsProperty() {
     return operations;
    }
    public final ObservableList<VectorOperation> getOperations() {
       return operations.get();
    }

    public ObjectProperty<VectorOperation> operationProperty() {
        return operationList;
    }

    public void calculate() {
        if (calculationDisabled.get()) {
            return;
        }

        Vector3D v1 = new Vector3D(
                Double.parseDouble(vector1CoordinateX.get()),
                Double.parseDouble(vector1CoordinateY.get()),
                Double.parseDouble(vector1CoordinateZ.get()));
        Vector3D v2;
        switch (operationList.get()) {
            case NORM:
                result.set(String.format("%.3f", v1.getNorm()));
                break;
            case NORMALAZE:
                v1.normalize();
                result.set(String.format("(%.3f, %.3f, %.3f)",
                        v1.getCoordinateX(),
                        v1.getCoordinateY(),
                        v1.getCoordinateZ()));
                break;
            case DOTPRODUCT:
                v2 = new Vector3D(
                        Double.parseDouble(vector2CoordinateX.get()),
                        Double.parseDouble(vector2CoordinateY.get()),
                        Double.parseDouble(vector2CoordinateZ.get()));
                double dotProduct = Vector3D.dotProduct(v1, v2);
                result.set(String.format("%.3f", dotProduct));
                break;
            case CROSSPRODUCT:
                v2 = new Vector3D(
                        Double.parseDouble(vector2CoordinateX.get()),
                        Double.parseDouble(vector2CoordinateY.get()),
                        Double.parseDouble(vector2CoordinateZ.get()));
                Vector3D v3 = Vector3D.crossProduct(v1, v2);
                result.set(String.format("(%.3f, %.3f, %.3f)",
                        v1.getCoordinateX(),
                        v1.getCoordinateY(),
                        v1.getCoordinateZ()));
                break;
            default:
                break;
        }
        status.set(Status.SUCCESS.toString());
    }

    public BooleanProperty calculationDisabledProperty() {
        return calculationDisabled;
    }
    public final boolean getCalculationDisabled() {
        return calculationDisabled.get();
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getInputStatus().toString());
        }
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        VectorOperation operation = operationList.get();
        boolean isEmptyVector1 = vector1CoordinateX.get().isEmpty()
                                || vector1CoordinateY.get().isEmpty()
                                || vector1CoordinateZ.get().isEmpty();
        boolean isEmptyVector2 = vector2CoordinateX.get().isEmpty()
                                || vector2CoordinateY.get().isEmpty()
                                || vector2CoordinateZ.get().isEmpty();
        if (operation == VectorOperation.DOTPRODUCT
                || operation == VectorOperation.CROSSPRODUCT) {
            if (isEmptyVector1 || isEmptyVector2) {
                inputStatus = Status.WAITING;
            }
        } else {
            if (isEmptyVector1) {
                inputStatus = Status.WAITING;
            }
        }
        try {
            if (!vector1CoordinateX.get().isEmpty()) {
                Double.parseDouble(vector1CoordinateX.get());
            }
            if (!vector1CoordinateY.get().isEmpty()) {
                Double.parseDouble(vector1CoordinateY.get());
            }
            if (!vector1CoordinateZ.get().isEmpty()) {
                Double.parseDouble(vector1CoordinateZ.get());
            }
            if (operation == VectorOperation.DOTPRODUCT
                    || operation == VectorOperation.CROSSPRODUCT) {
                if (!vector2CoordinateX.get().isEmpty()) {
                    Double.parseDouble(vector2CoordinateX.get());
                }
                if (!vector2CoordinateY.get().isEmpty()) {
                    Double.parseDouble(vector2CoordinateY.get());
                }
                if (!vector2CoordinateZ.get().isEmpty()) {
                    Double.parseDouble(vector2CoordinateZ.get());
                }
            }
        } catch (NumberFormatException nfe) {
            inputStatus = Status.BAD_FORMAT;
        }

        return inputStatus;
    }
}

enum Status {
    READY("Press 'Calculate' or Enter"),
    WAITING("Please provide input data"),
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
