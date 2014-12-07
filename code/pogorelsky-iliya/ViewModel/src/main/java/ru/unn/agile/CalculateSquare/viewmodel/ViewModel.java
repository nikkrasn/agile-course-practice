package ru.unn.agile.CalculateSquare.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.CalculateSquare.Model.Cone;
import ru.unn.agile.CalculateSquare.Model.Cylinder;
import ru.unn.agile.CalculateSquare.Model.SphereSector;
import ru.unn.agile.CalculateSquare.Model.SphereSegment;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty lblParam1Text = new SimpleStringProperty();
    private final StringProperty lblParam2Text = new SimpleStringProperty();
    private final StringProperty txtParam1 = new SimpleStringProperty();
    private final StringProperty txtParam2 = new SimpleStringProperty();
    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();
    private final StringProperty resultSquare = new SimpleStringProperty();
    private final StringProperty statusMessage = new SimpleStringProperty();
    private final StringProperty operation = new SimpleStringProperty();
    private final ObjectProperty<ObservableList<String>> operations =
            new SimpleObjectProperty<>(FXCollections.
                    observableArrayList("Cone", "Cylinder", "SphereSector", "SphereSegment"));
    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();
    private final List<ComboBoxElementChangeListener> comboBoxElementChangeListener =
            new ArrayList<>();

    public ViewModel() {
        txtParam1.set("");
        txtParam2.set("");
        lblParam1Text.set("r");
        lblParam2Text.set("h");

        resultSquare.set("");
        statusMessage.set(MessageStatus.WAITING.toString());
        operation.set("Cone");

        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(txtParam1);
                super.bind(txtParam2);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == MessageStatus.READY;
            }
        };
        calculationDisabled.bind(couldCalculate.not());

        BooleanBinding whatFormula = new BooleanBinding() {
            {
                super.bind(operation);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == MessageStatus.READY;
            }
        };

        final List<StringProperty> fieldsText = new ArrayList<StringProperty>() { {
            add(txtParam1);
            add(txtParam2);
        } };

        for (StringProperty field : fieldsText) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }

        final List<StringProperty> fieldsComboBox = new ArrayList<StringProperty>() { {
            add(operation);
        } };

        for (StringProperty field : fieldsComboBox) {
            final ComboBoxElementChangeListener listener = new ComboBoxElementChangeListener();
            field.addListener(listener);
            comboBoxElementChangeListener.add(listener);
        }
    }

    public void calculate() {
        if (calculationDisabled.get()) {
            return;
        }

        double param1 = Double.parseDouble(txtParam1.get());
        double param2 = Double.parseDouble(txtParam2.get());

        if (operation.get().toString().equalsIgnoreCase("Cone")) {
            Cone cone = new Cone(param1, param2);
            resultSquare.set(Double.toString(cone.calculateSquare()));
        }
        if (operation.get().toString().equalsIgnoreCase("Cylinder")) {
            Cylinder cylinder = new Cylinder(param1, param2);
            resultSquare.set(Double.toString(cylinder.calculateSquare()));
        }
        if (operation.get().toString().equalsIgnoreCase("SphereSector")) {
            SphereSector sphereSector = new SphereSector(param1, param2);
            resultSquare.set(Double.toString(sphereSector.calculateSquare()));
        }
        if (operation.get().toString().equalsIgnoreCase("SphereSegment")) {
            SphereSegment sphereSegment = new SphereSegment(param1, param2);
            resultSquare.set(Double.toString(sphereSegment.calculateSquare()));
        }
        statusMessage.set(MessageStatus.SUCCESS.toString());
    }

    public StringProperty lblParam1TextProperty() {
        return lblParam1Text;
    }
    public final String getLblParam1Text() {
        return lblParam1Text.get();
    }
    public StringProperty lblParam2TextProperty() {
        return lblParam2Text;
    }
    public final String getLblParam2Text() {
        return lblParam2Text.get();
    }

    public StringProperty txtParam1Property() {
        return txtParam1;
    }
    public StringProperty txtParam2Property() {
        return txtParam2;
    }

    public BooleanProperty calculationDisabledProperty() {
        return calculationDisabled;
    }
    public final boolean getCalculationDisabled() {
        return calculationDisabled.get();
    }

    public StringProperty operationProperty() {
        return operation;
    }
    public ObjectProperty<ObservableList<String>> operationsProperty() {
            return operations;
            }
    public final ObservableList<String> getOperations() {
            return operations.get();
    }

    public StringProperty resultSquareProperty() {
        return resultSquare;
    }
    public final String getSquareResult() {
            return resultSquare.get();
            }
    public StringProperty statusMessageProperty() {
            return statusMessage;
    }
    public final String getStatusMessage() {
            return statusMessage.get();
        }

    private MessageStatus getInputStatus() {
        MessageStatus inputStatus = MessageStatus.READY;
        if (txtParam1.get().isEmpty() || txtParam2.get().isEmpty()) {
            inputStatus = MessageStatus.WAITING;
        }
        try {
            if (!txtParam1.get().isEmpty()) {
                Double.parseDouble(txtParam1.get());
            }
            if (!txtParam2.get().isEmpty()) {
                Double.parseDouble(txtParam2.get());
            }
        } catch (NumberFormatException nfe) {
            inputStatus = MessageStatus.BAD_FORMAT;
        }
        return inputStatus;
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            statusMessage.set(getInputStatus().toString());
        }
    }

    private class ComboBoxElementChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            if (operation.get().toString().equalsIgnoreCase("SphereSector")) {
                lblParam2Text.set("R");
            } else {
                lblParam2Text.set("h");
            }
            txtParam1.set("");
            txtParam2.set("");
        }
    }
};

enum MessageStatus {
    WAITING("Please provide input data"),
    READY("Press 'Calculate' or Enter"),
    BAD_FORMAT("Bad format"),
    SUCCESS("Success");

    private final String name;
    private MessageStatus(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}


