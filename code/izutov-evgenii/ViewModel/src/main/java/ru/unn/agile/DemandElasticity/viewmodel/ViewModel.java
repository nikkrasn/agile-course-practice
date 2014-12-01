package ru.unn.agile.DemandElasticity.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.DemandElasticity.Model.Coefficient;
import ru.unn.agile.DemandElasticity.Model.IPositiveRange;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty firstRangeStart = new SimpleStringProperty("");
    private final StringProperty firstRangeFinish = new SimpleStringProperty("");
    private final StringProperty secondRangeStart = new SimpleStringProperty("");
    private final StringProperty secondRangeFinish = new SimpleStringProperty("");

    private final ObjectProperty<ObservableList<DemandElasticityType>> demandElasticityTypes =
            new SimpleObjectProperty<>(
                    FXCollections.observableArrayList(DemandElasticityType.values()));
    private final ObjectProperty<DemandElasticityType> demandElasticityType =
            new SimpleObjectProperty<>(DemandElasticityType.ByPrice);
    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();

    private final StringProperty calcResult = new SimpleStringProperty("");
    private final StringProperty calcDescription = new SimpleStringProperty("");
    private final StringProperty calcStatus = new SimpleStringProperty(Status.WAITING.toString());

    private final List<TextFieldChangeListener> textFieldChangedListeners = new ArrayList<>();

    private final StringProperty firstRange =
            new SimpleStringProperty(DemandElasticityType.ByPrice.getFirstRangeName());
    private final StringProperty secondRange =
            new SimpleStringProperty(DemandElasticityType.ByPrice.getSecondRangeName());

    private final ComboBoxChangeListener comboBoxChangedListener = new ComboBoxChangeListener();

    public ViewModel() {
        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(firstRangeStart, firstRangeFinish, secondRangeStart, secondRangeFinish);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        calculationDisabled.bind(couldCalculate.not());

        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(firstRangeStart);
            add(firstRangeFinish);
            add(secondRangeStart);
            add(secondRangeFinish);
        } };

        for (StringProperty field : fields) {
            final TextFieldChangeListener listener = new TextFieldChangeListener();
            field.addListener(listener);
            textFieldChangedListeners.add(listener);
        }

        demandElasticityType.addListener(comboBoxChangedListener);
    }

    public void calculate() {
        if (calculationDisabled.get()) {
            return;
        }

        IPositiveRange firstRange = demandElasticityType.get().
                getFirstRange(Double.parseDouble(firstRangeStart.get()),
                              Double.parseDouble(firstRangeFinish.get()));
        IPositiveRange secondRange = demandElasticityType.get().
                getSecondRange(Double.parseDouble(secondRangeStart.get()),
                               Double.parseDouble(secondRangeFinish.get()));

        Coefficient answer;
        try {
            answer = demandElasticityType.get().calculate(firstRange, secondRange);
        } catch (ArithmeticException ae) {
            answer = null;
            calcResult.set("");
            calcDescription.set("");
            calcStatus.set(Status.WRONG_ARGUMENTS.toString());
        }

        if (answer != null) {
            calcResult.set(Double.toString(answer.getValue()));
            calcDescription.set(answer.getDescription());
            calcStatus.set(Status.SUCCESS.toString());
        }
    }

    public StringProperty firstRangeStartProperty() {
        return firstRangeStart;
    }

    public StringProperty firstRangeFinishProperty() {
        return firstRangeFinish;
    }

    public StringProperty secondRangeStartProperty() {
        return secondRangeStart;
    }

    public StringProperty secondRangeFinishProperty() {
        return secondRangeFinish;
    }

    public ObjectProperty<ObservableList<DemandElasticityType>> demandElasticityTypesProperty() {
        return demandElasticityTypes;
    }

    public final ObservableList<DemandElasticityType> getDemandElasticityTypes() {
        return demandElasticityTypes.get();
    }

    public ObjectProperty<DemandElasticityType> demandElasticityTypeProperty() {
        return demandElasticityType;
    }

    public BooleanProperty calculationDisabledProperty() {
        return calculationDisabled;
    }

    public final boolean getCalculationDisabled() {
        return calculationDisabled.get();
    }

    public StringProperty calcResultProperty() {
        return calcResult;
    }

    public final String getCalcResult() {
        return calcResult.get();
    }

    public StringProperty calcDescriptionProperty() {
        return calcDescription;
    }

    public final String getCalcDescription() {
        return calcDescription.get();
    }

    public StringProperty calcStatusProperty() {
        return calcStatus;
    }

    public final String getCalcStatus() {
        return calcStatus.get();
    }

    public StringProperty firstRangeProperty() {
        return firstRange;
    }

    public final String getFirstRange() {
        return firstRange.get();
    }

    public StringProperty secondRangeProperty() {
        return secondRange;
    }

    public final String getSecondRange() {
        return secondRange.get();
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (firstRangeStart.get().isEmpty() || firstRangeFinish.get().isEmpty()
                || secondRangeStart.get().isEmpty() || secondRangeFinish.get().isEmpty()) {
            inputStatus = Status.WAITING;
        }

        try {
            checkStringNumber(firstRangeStart.get());
            checkStringNumber(firstRangeFinish.get());
            checkStringNumber(secondRangeStart.get());
            checkStringNumber(secondRangeFinish.get());
        } catch (NumberFormatException nfe) {
            inputStatus = Status.NOT_NUMBER;
        } catch (IllegalArgumentException iae) {
            inputStatus = Status.NOT_POSITIVE;
        }

        return inputStatus;
    }

    private static void checkStringNumber(final String number) {
        if (!number.isEmpty()) {
            double value = Double.parseDouble(number);

            if (value < 0d) {
                throw new IllegalArgumentException("Value must be positive");
            }
        }
    }

    private class TextFieldChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            calcStatus.set(getInputStatus().toString());
        }
    }

    private class ComboBoxChangeListener implements ChangeListener<DemandElasticityType> {
        @Override
        public void changed(final ObservableValue<? extends DemandElasticityType> observable,
                            final DemandElasticityType oldValue,
                            final DemandElasticityType newValue) {
            firstRange.set(newValue.getFirstRangeName());
            secondRange.set(newValue.getSecondRangeName());
        }
    }
}
