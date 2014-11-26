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
    private static final DemandElasticityType DEFAULT_DEMAND_ELASTICITY_TYPE =
            DemandElasticityType.ByPrice;
    private static final String DEFAULT_TEXT_VALUE = "";

    private final StringProperty start1 = new SimpleStringProperty();
    private final StringProperty finish1 = new SimpleStringProperty();
    private final StringProperty start2 = new SimpleStringProperty();
    private final StringProperty finish2 = new SimpleStringProperty();

    private final ObjectProperty<ObservableList<DemandElasticityType>> demandElasticityTypes =
            new SimpleObjectProperty<>(
                    FXCollections.observableArrayList(DemandElasticityType.values()));
    private final ObjectProperty<DemandElasticityType> demandElasticityType =
            new SimpleObjectProperty<>();
    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();

    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    private final List<TextFieldChangeListener> textFieldChangedListeners = new ArrayList<>();

    private final StringProperty firstRange = new SimpleStringProperty();
    private final StringProperty secondRange = new SimpleStringProperty();

    private final ComboBoxChangeListener comboBoxChangedListener = new ComboBoxChangeListener();

    public ViewModel() {
        firstRange.set(DEFAULT_DEMAND_ELASTICITY_TYPE.getFirstRangeName());
        secondRange.set(DEFAULT_DEMAND_ELASTICITY_TYPE.getSecondRangeName());
        start1.set(DEFAULT_TEXT_VALUE);
        finish1.set(DEFAULT_TEXT_VALUE);
        start2.set(DEFAULT_TEXT_VALUE);
        finish2.set(DEFAULT_TEXT_VALUE);
        demandElasticityType.set(DEFAULT_DEMAND_ELASTICITY_TYPE);
        result.set(DEFAULT_TEXT_VALUE);
        description.set(DEFAULT_TEXT_VALUE);
        status.set(Status.WAITING.toString());

        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(start1, finish1, start2, finish2);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        calculationDisabled.bind(couldCalculate.not());

        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(start1);
            add(finish1);
            add(start2);
            add(finish2);
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

        IPositiveRange range1 = demandElasticityType.get().
                getFirstRange(Double.parseDouble(start1.get()), Double.parseDouble(finish1.get()));
        IPositiveRange range2 = demandElasticityType.get().
                getSecondRange(Double.parseDouble(start2.get()), Double.parseDouble(finish2.get()));

        Coefficient answer;
        try {
            answer = demandElasticityType.get().calculate(range1, range2);
        } catch (ArithmeticException ae) {
            answer = null;
            result.set("");
            description.set("");
            status.set(Status.WRONG_ARGUMENTS.toString());
        }

        if (answer != null) {
            result.set(Double.toString(answer.getValue()));
            description.set(answer.getDescription());
            status.set(Status.SUCCESS.toString());
        }
    }

    public StringProperty start1Property() {
        return start1;
    }
    public StringProperty finish1Property() {
        return finish1;
    }
    public StringProperty start2Property() {
        return start2;
    }
    public StringProperty finish2Property() {
        return finish2;
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

    public StringProperty resultProperty() {
        return result;
    }
    public final String getResult() {
        return result.get();
    }
    public StringProperty descriptionProperty() {
        return description;
    }
    public final String getDescription() {
        return description.get();
    }
    public StringProperty statusProperty() {
        return status;
    }
    public final String getStatus() {
        return status.get();
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
        if (start1.get().isEmpty() || finish1.get().isEmpty()
                || start2.get().isEmpty() || finish2.get().isEmpty()) {
            inputStatus = Status.WAITING;
        }

        try {
            checkStringNumber(start1.get());
            checkStringNumber(finish1.get());
            checkStringNumber(start2.get());
            checkStringNumber(finish2.get());
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
            status.set(getInputStatus().toString());
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
