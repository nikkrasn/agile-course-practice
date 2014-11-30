package ru.unn.agile.QuadraticEquation.viewmodel;

import javafx.beans.property.*;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.unn.agile.QuadraticEquation.Model.QuadraticEquation;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty fCoef = new SimpleStringProperty();
    private final StringProperty sCoef = new SimpleStringProperty();
    private final StringProperty fTerm = new SimpleStringProperty();

    private final StringProperty firstRootResult = new SimpleStringProperty();
    private final StringProperty secondRootResult = new SimpleStringProperty();

    private final BooleanProperty solvingDisabled = new SimpleBooleanProperty();

    private final StringProperty status = new SimpleStringProperty();

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    private static final double ZERO = 0.0;
    private static final double DELTA = 0.0001;

    public ViewModel() {
        fCoef.set("");
        sCoef.set("");
        fTerm.set("");

        firstRootResult.set("");
        secondRootResult.set("");

        status.set(systemStatus.WAITING.toString());

        BooleanBinding couldSolve = new BooleanBinding() {
            {
                super.bind(fCoef, sCoef, fTerm);
            }
            @Override
            protected boolean computeValue() {
                return getEquationStatus() == systemStatus.READY;
            }
        };
        solvingDisabled.bind(couldSolve.not());

        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(fCoef);
            add(sCoef);
            add(fTerm);
        } };

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public void solve() {
        if (solvingDisabled.get()) {
            return;
        }

        QuadraticEquation equation = new QuadraticEquation(
                Double.parseDouble(fCoef.get()),
                Double.parseDouble(sCoef.get()),
                Double.parseDouble(fTerm.get()));

        try {
            String firstRoot = Double.toString(equation.getFirstRoot());
            String secondRoot = Double.toString(equation.getSecondRoot());

            if (!(firstRoot.isEmpty()) && !(secondRoot.isEmpty())) {
                firstRootResult.set("x = " + firstRoot);
                secondRootResult.set("x = " + secondRoot);
                status.set(systemStatus.SUCCESS.toString());
            }
        } catch (IllegalArgumentException iag) {
            status.set(systemStatus.NO_ROOTS.toString());
        }
    }

    public StringProperty firstCoefficientProperty() {
        return fCoef;
    }

    public StringProperty secondCoefficientProperty() {
        return sCoef;
    }

    public StringProperty freeTermProperty() {
        return fTerm;
    }

    public StringProperty firstRootResultProperty() {
        return firstRootResult;
    }

    public StringProperty secondRootResultProperty() {
        return secondRootResult;
    }

    public final String getFirstRootResult() {
        return firstRootResult.get();
    }

    public final String getSecondRootResult() {
        return secondRootResult.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public final String getStatus() {
        return status.get();
    }

    public BooleanProperty solvingDisabledProperty() {
        return solvingDisabled;
    }

    public final boolean getSolvingDisabledProperty() {
        return solvingDisabled.get();
    }

    private systemStatus getEquationStatus() {
        systemStatus equationStatus = systemStatus.READY;
        if (fCoef.get().isEmpty() || sCoef.get().isEmpty() || fTerm.get().isEmpty()) {
            equationStatus = systemStatus.WAITING;
        }
        try {
            double valueFirstCoefficient = Double.parseDouble(fCoef.get());
            if (Math.abs(valueFirstCoefficient - ZERO) < DELTA) {
                throw new IllegalArgumentException(
                        "The first coefficient can't be null in quadratic equation");
            }
        } catch (IllegalArgumentException iae) {
            equationStatus = systemStatus.INCORRECT_COEF;
        }
        try {
            if (!fCoef.get().isEmpty()) {
                Double.parseDouble(fCoef.get());
                }
            if (!sCoef.get().isEmpty()) {
                Double.parseDouble(sCoef.get());
            }
            if (!fTerm.get().isEmpty()) {
                Double.parseDouble(fTerm.get());
            }
        } catch (NumberFormatException nfe) {
                equationStatus = systemStatus.BAD_FORMAT;
        }
        return equationStatus;
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                        final String oldValue,
                        final String newValue) {
            status.set(getEquationStatus().toString());
    }
}
}
