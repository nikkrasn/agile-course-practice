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
    private final StringProperty planeA = new SimpleStringProperty("");
    private final StringProperty planeB = new SimpleStringProperty("");
    private final StringProperty planeC = new SimpleStringProperty("");
    private final StringProperty planeD = new SimpleStringProperty("");

    private final StringProperty lineVectorX = new SimpleStringProperty("");
    private final StringProperty lineVectorY = new SimpleStringProperty("");
    private final StringProperty lineVectorZ = new SimpleStringProperty("");

    private final StringProperty linePointX = new SimpleStringProperty("");
    private final StringProperty linePointY = new SimpleStringProperty("");
    private final StringProperty linePointZ = new SimpleStringProperty("");

    private final StringProperty logs = new SimpleStringProperty("");
    private final StringProperty result = new SimpleStringProperty("");
    private final StringProperty status = new SimpleStringProperty(Status.WAITING.toString());

    private ILogger logger;
    private final List<ValueCachingChangeListener> valueChangedListeners = new ArrayList<>();

    private final BooleanProperty determinateDisabled = new SimpleBooleanProperty();

    public ViewModel() {
        init();
    }

    public ViewModel(final ILogger logger) {
        init();
        setLogger(logger);
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
        StringBuilder message = new StringBuilder(LoggerMessages.DETERMINATE_WAS_PRESSED);
        message.append("Arguments")
                .append(": PlaneA = ").append(planeA.get())
                .append("; PlaneB = ").append(planeB.get())
                .append("; PlaneC = ").append(planeC.get())
                .append("; PlaneD = ").append(planeD.get())
                .append("; LinePointX = ").append(linePointX.get())
                .append("; LinePointY = ").append(linePointY.get())
                .append("; LinePointZ = ").append(linePointZ.get())
                .append("; LineVectorX = ").append(lineVectorX.get())
                .append("; LineVectorY = ").append(lineVectorY.get())
                .append("; LineVectorZ = ").append(lineVectorZ.get());
        logger.log(message.toString());
        updateLogs();
    }

    public void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = logger;
    }

    public void onFocusChanged(final Boolean oldValue, final Boolean newValue) {
        if (!oldValue && newValue) {
            return;
        }

        for (ValueCachingChangeListener listener : valueChangedListeners) {
            if (listener.isChanged()) {
                StringBuilder message = new StringBuilder(LoggerMessages.EDITING_FINISHED);
                message.append("Input arguments are: [")
                        .append(planeA.get()).append("; ")
                        .append(planeB.get()).append("; ")
                        .append(planeC.get()).append("; ")
                        .append(planeD.get()).append("; ")
                        .append(linePointX.get()).append("; ")
                        .append(linePointY.get()).append("; ")
                        .append(linePointZ.get()).append("; ")
                        .append(lineVectorX.get()).append("; ")
                        .append(lineVectorY.get()).append("; ")
                        .append(lineVectorZ.get()).append("]");
                logger.log(message.toString());
                updateLogs();

                listener.cache();
                break;
            }
        }
    }

    public final List<String> getLog() {
        return logger.getLog();
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

    public StringProperty logsProperty() {
        return logs;
    }

    public final String getLogs() {
        return logs.get();
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

    private void init() {
        bindDeterminateDisable();
        createFieldsValuesChangingListeners();
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
            final ValueCachingChangeListener listener = new ValueCachingChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (isSomeOfFieldsValueEmpty()) {
            inputStatus = Status.WAITING;
        }
        if (cannotParseSomeOfFields()) {
            inputStatus = Status.BAD_FORMAT;
        }
        return inputStatus;
    }

    private boolean cannotParseSomeOfFields() {
        boolean cannotParse = false;
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
            cannotParse = true;
        }
        return cannotParse;
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

    private void updateLogs() {
        List<String> fullLog = logger.getLog();
        String record = new String();
        for (String log : fullLog) {
            record += log + "\n";
        }
        logs.set(record);
    }

    private class ValueCachingChangeListener implements ChangeListener<String> {
        private String prevValue = new String();
        private String curValue = new String();

        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            if (oldValue.equals(newValue)) {
                return;
            }
            status.set(getInputStatus().toString());
            curValue = newValue;
        }

        public boolean isChanged() {
            return !prevValue.equals(curValue);
        }

        public void cache() {
            prevValue = curValue;
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

final class LoggerMessages {
    public static final String DETERMINATE_WAS_PRESSED = "Calculate. ";
    public static final String EDITING_FINISHED = "Updated input. ";

    private LoggerMessages() { }
}
