package ru.unn.agile.QuadraticEquation.viewmodel;

import javafx.beans.property.*;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.unn.agile.QuadraticEquation.Model.QuadraticEquation;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty firstCoef = new SimpleStringProperty("");
    private final StringProperty secondCoef = new SimpleStringProperty("");
    private final StringProperty thirdCoef = new SimpleStringProperty("");

    private final StringProperty firstRootResult = new SimpleStringProperty("");
    private final StringProperty secondRootResult = new SimpleStringProperty("");

    private final BooleanProperty solvingDisabled = new SimpleBooleanProperty();

    private final StringProperty status = new SimpleStringProperty(systemStatus.WAITING.toString());

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    public ViewModel() {
        bindEquationDisabled();
        createFieldsValueChangedListeners();
    }

    private void bindEquationDisabled() {
        BooleanBinding couldSolve = new BooleanBinding() {
            {
                super.bind(firstCoef, secondCoef, thirdCoef);
            }

            @Override
            protected boolean computeValue() {
                return getEquationStatus() == systemStatus.READY;
            }
        };
        solvingDisabled.bind(couldSolve.not());
    }

    private void createFieldsValueChangedListeners() {
        final List<StringProperty> fields = new ArrayList<StringProperty>() {
            {
                add(firstCoef);
                add(secondCoef);
                add(thirdCoef);
            }
        };
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
        try {
            QuadraticEquation equation = new QuadraticEquation(
                    Double.parseDouble(firstCoef.get()),
                    Double.parseDouble(secondCoef.get()),
                    Double.parseDouble(thirdCoef.get()));

            firstRootResult.set("x = " + equation.getFirstRoot());
            secondRootResult.set("x = " + equation.getSecondRoot());
            status.set(systemStatus.SUCCESS.toString());
        } catch (ArithmeticException iag) {
            status.set(systemStatus.NO_ROOTS.toString());
        } catch (IllegalArgumentException ae) {
            status.set(systemStatus.INCORRECT_COEF.toString());
        }
    }

    public StringProperty firstCoefficientProperty() {
        return firstCoef;
    }

    public StringProperty secondCoefficientProperty() {
        return secondCoef;
    }

    public StringProperty thirdCoefficientProperty() {
        return thirdCoef;
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
        if (firstCoef.get().isEmpty() || secondCoef.get().isEmpty() || thirdCoef.get().isEmpty()) {
            equationStatus = systemStatus.WAITING;
        }
        try {
            if (!firstCoef.get().isEmpty()) {
                Double.parseDouble(firstCoef.get());
            }
            if (!secondCoef.get().isEmpty()) {
                Double.parseDouble(secondCoef.get());
            }
            if (!thirdCoef.get().isEmpty()) {
                Double.parseDouble(thirdCoef.get());
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
