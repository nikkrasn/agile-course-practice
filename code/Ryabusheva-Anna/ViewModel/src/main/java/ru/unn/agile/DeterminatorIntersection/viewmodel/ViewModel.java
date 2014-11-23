package ru.unn.agile.DeterminatorIntersection.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.unn.agile.DeterminatorIntersection.Model.*;
import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty planeA = new SimpleStringProperty();
    private final StringProperty planeB = new SimpleStringProperty();
    private final StringProperty planeC = new SimpleStringProperty();
    private final StringProperty planeD = new SimpleStringProperty();

    private final StringProperty lineVectorX = new SimpleStringProperty();
    private final StringProperty lineVectorY = new SimpleStringProperty();
    private final StringProperty lineVectorZ = new SimpleStringProperty();

    private final StringProperty linePointX = new SimpleStringProperty();
    private final StringProperty linePointY = new SimpleStringProperty();
    private final StringProperty linePointZ = new SimpleStringProperty();

    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    private final BooleanProperty determinateDisabled = new SimpleBooleanProperty();

    public ViewModel() {
        setDefaultFieldsValue();
        bindDeterminateDisable();
        createFieldsValuesChangingListeners();
    }

    public void determinate() {
        if (determinateDisabled.get()) {
            return;
        }
        Point3D linePoint = new Point3D(getDoubleLinePX(), getDoubleLinePY(), getDoubleLinePZ());
        Point3D lineVector = new Point3D(getDoubleLineVX(), getDoubleLineVY(), getDoubleLineVZ());
        Line3D line = new Line3D(linePoint, lineVector);
        Plane plane = new Plane(getDoublePlA(), getDoublePlB(), getDoublePlC(), getDoublePlD());
        DeterminatorIntersection determ = new DeterminatorIntersection(line, plane);
        if (determ.isIntersection()) {
            result.set("Intersection determinate");
        } else {
            result.set("No intersection determinate");
        }
        status.set(Status.SUCCESS.toString());
    }

    public StringProperty planeAProperty() {
        return planeA;
    }

    public StringProperty planeBProperty() {
        return planeB;
    }

    public StringProperty planeCProperty() {
        return planeC;
    }

    public StringProperty planeDProperty() {
        return planeD;
    }

    public StringProperty getLineVXProperty() {
        return lineVectorX;
    }

    public StringProperty getLineVYProperty() {
        return lineVectorY;
    }

    public StringProperty getLineVZProperty() {
        return lineVectorZ;
    }

    public StringProperty getLinePXProperty() {
        return linePointX;
    }

    public StringProperty getLinePYProperty() {
        return linePointY;
    }

    public StringProperty getLinePZProperty() {
        return linePointZ;
    }

    public BooleanProperty determinateDisabledProperty() {
        return determinateDisabled;
    }

    public final boolean getDeterminateDisabled() {
        return determinateDisabled.get();
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

    private void bindDeterminateDisable() {
        BooleanBinding couldDeterminate = new BooleanBinding() {
            {
                super.bind(planeA, planeB, planeC, planeD,
                        linePointX, linePointY, linePointZ,
                        lineVectorX, lineVectorY, lineVectorZ);
            }

            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        determinateDisabled.bind(couldDeterminate.not());
    }

    private void createFieldsValuesChangingListeners() {
        final List<StringProperty> fields = new ArrayList<StringProperty>() {
            {
                add(planeA);
                add(planeB);
                add(planeC);
                add(planeD);
                add(lineVectorX);
                add(lineVectorY);
                add(lineVectorZ);
                add(linePointX);
                add(linePointY);
                add(linePointZ);
            }
        };
        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    private void setDefaultFieldsValue() {
        planeA.set("");
        planeB.set("");
        planeC.set("");
        planeD.set("");
        lineVectorX.set("");
        lineVectorY.set("");
        lineVectorZ.set("");
        linePointX.set("");
        linePointY.set("");
        linePointZ.set("");
        result.set("");
        status.set(Status.WAITING.toString());
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (isSomeOfFieldsValueEmpty()) {
            inputStatus = Status.WAITING;
        }
        try {
            parseAllFields();
        } catch (NumberFormatException cuntParseSomeOfField) {
            inputStatus = Status.BAD_FORMAT;
        }
        return inputStatus;
    }

    private void parseAllFields() {
        try {
            if (!planeA.get().isEmpty()) {
                Double.parseDouble(planeA.get());
            }
            if (!planeB.get().isEmpty()) {
                Double.parseDouble(planeB.get());
            }
            if (!planeC.get().isEmpty()) {
                Double.parseDouble(planeC.get());
            }
            if (!planeD.get().isEmpty()) {
                Double.parseDouble(planeD.get());
            }
            if (!lineVectorX.get().isEmpty()) {
                Double.parseDouble(lineVectorX.get());
            }
            if (!lineVectorY.get().isEmpty()) {
                Double.parseDouble(lineVectorY.get());
            }
            if (!lineVectorZ.get().isEmpty()) {
                Double.parseDouble(lineVectorZ.get());
            }
            if (!linePointX.get().isEmpty()) {
                Double.parseDouble(linePointX.get());
            }
            if (!linePointY.get().isEmpty()) {
                Double.parseDouble(linePointY.get());
            }
            if (!linePointZ.get().isEmpty()) {
                Double.parseDouble(linePointZ.get());
            }
        } catch (NumberFormatException cuntParseSomeOfField) {
            throw cuntParseSomeOfField;
        }
    }

    private boolean isSomeOfFieldsValueEmpty() {
        return planeA.get().isEmpty() || planeB.get().isEmpty()
                || planeC.get().isEmpty() || planeD.get().isEmpty()
                || linePointX.get().isEmpty() || linePointY.get().isEmpty()
                || linePointY.get().isEmpty() || lineVectorX.get().isEmpty()
                || lineVectorY.get().isEmpty() || lineVectorZ.get().isEmpty();
    }

    private double getDoublePlD() {
        return Double.parseDouble(planeD.get());
    }

    private double getDoublePlC() {
        return Double.parseDouble(planeC.get());
    }

    private double getDoublePlB() {
        return Double.parseDouble(planeB.get());
    }

    private double getDoublePlA() {
        return Double.parseDouble(planeA.get());
    }

    private double getDoubleLineVZ() {
        return Double.parseDouble(lineVectorZ.get());
    }

    private double getDoubleLineVY() {
        return Double.parseDouble(lineVectorY.get());
    }

    private double getDoubleLineVX() {
        return Double.parseDouble(lineVectorX.get());
    }

    private double getDoubleLinePZ() {
        return Double.parseDouble(linePointZ.get());
    }

    private double getDoubleLinePY() {
        return Double.parseDouble(linePointY.get());
    }

    private double getDoubleLinePX() {
        return Double.parseDouble(linePointX.get());
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
