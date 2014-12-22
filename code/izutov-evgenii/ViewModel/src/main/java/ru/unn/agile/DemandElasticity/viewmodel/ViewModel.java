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

    private final StringProperty logMessages = new SimpleStringProperty();

    private final StringProperty calcResult = new SimpleStringProperty("");
    private final StringProperty calcDescription = new SimpleStringProperty("");
    private final StringProperty calcStatus = new SimpleStringProperty(Status.WAITING.toString());

    private final List<TextFieldChangeListener> textFieldChangedListeners = new ArrayList<>();

    private final StringProperty firstRange =
            new SimpleStringProperty(DemandElasticityType.ByPrice.getFirstRangeName());
    private final StringProperty secondRange =
            new SimpleStringProperty(DemandElasticityType.ByPrice.getSecondRangeName());

    private final ComboBoxChangeListener comboBoxChangedListener = new ComboBoxChangeListener();

    private ILogger logger;

    public ViewModel() {
        initViewModel();
    }

    public ViewModel(final ILogger logger) {
        setLogger(logger);
        initViewModel();
    }

    public void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger can not be null");
        }
        this.logger = logger;
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

            StringBuilder logMessage = new StringBuilder(LoggerMessages.CALCULATE_WAS_COMPLETED);
            logMessage.append("Arguments")
                    .append(": FirstRangeStart = ").append(firstRangeStart.get())
                    .append("; FirstRangeFinish = ").append(firstRangeFinish.get())
                    .append("; SecondRangeStart = ").append(secondRangeStart.get())
                    .append("; SecondRangeFinish = ").append(secondRangeFinish.get())
                    .append(" Demand elasticity type: ")
                    .append(demandElasticityType.get().toString()).append(".");
            logger.logMessage(logMessage.toString());
            updateLogMessages();
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

    public StringProperty logsProperty() {
        return logMessages;
    }

    public final String getLogs() {
        return logMessages.get();
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

    public final List<String> getFullLog() {
        return logger.getFullLog();
    }

    public void onDemandElasticityTypeChanged(final DemandElasticityType oldType,
                                              final DemandElasticityType newType) {
        if (oldType.equals(newType)) {
            return;
        }

        if (comboBoxChangedListener.isChanged()) {
            StringBuilder logMessage = new StringBuilder(
                    LoggerMessages.DEMAND_ELASTICITY_TYPE_WAS_CHANGED);
            logMessage.append(newType.toString());
            logger.logMessage(logMessage.toString());
            updateLogMessages();

            comboBoxChangedListener.cache();
        }
    }

    public void onFocusChanged(final Boolean oldValue, final Boolean newValue) {
        if (!oldValue && newValue) {
            return;
        }

        for (TextFieldChangeListener listener : textFieldChangedListeners) {
            if (listener.isChanged()) {
                StringBuilder logMessage = new StringBuilder(LoggerMessages.INPUT_WAS_UPDATED);
                logMessage.append("[")
                        .append(firstRangeStart.get()).append("; ")
                        .append(firstRangeFinish.get()).append("; ")
                        .append(secondRangeStart.get()).append("; ")
                        .append(secondRangeFinish.get()).append("]");
                logger.logMessage(logMessage.toString());
                updateLogMessages();

                listener.cache();
                break;
            }
        }
    }

    private void initViewModel() {
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

    private void updateLogMessages() {
        List<String> allLogMessages = logger.getFullLog();

        StringBuilder messages = new StringBuilder();
        for (String message : allLogMessages) {
            messages.append(message)
                    .append("\n");
        }

        logMessages.set(messages.toString());
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
        private String prevFieldValue = new String();
        private String curFieldValue = new String();

        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            if (oldValue.equals(newValue)) {
                return;
            }

            calcStatus.set(getInputStatus().toString());

            curFieldValue = newValue;
        }

        public boolean isChanged() {
            return !prevFieldValue.equals(curFieldValue);
        }

        public void cache() {
            prevFieldValue = curFieldValue;
        }
    }

    private class ComboBoxChangeListener implements ChangeListener<DemandElasticityType> {
        private DemandElasticityType prevFieldValue = DemandElasticityType.ByPrice;
        private DemandElasticityType curFieldValue = DemandElasticityType.ByIncome;

        @Override
        public void changed(final ObservableValue<? extends DemandElasticityType> observable,
                            final DemandElasticityType oldValue,
                            final DemandElasticityType newValue) {
            if (oldValue.equals(newValue)) {
                return;
            }

            firstRange.set(newValue.getFirstRangeName());
            secondRange.set(newValue.getSecondRangeName());

            curFieldValue = newValue;
        }

        public boolean isChanged() {
            return !prevFieldValue.equals(curFieldValue);
        }

        public void cache() {
            prevFieldValue = curFieldValue;
        }
    }
}
