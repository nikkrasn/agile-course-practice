package ru.unn.agile.ComplexProcent.ViewModel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import ru.unn.agile.ComplexProcent.Model.ComplexDeposit;

public class ViewModel {
    private final StringProperty txtBase = new SimpleStringProperty("");
    private final StringProperty txtIntCount = new SimpleStringProperty("");
    private final StringProperty txtPercent = new SimpleStringProperty("");

    private final StringProperty result = new SimpleStringProperty("");
    private final StringProperty status = new SimpleStringProperty(Status.WAITING.toString());

    private final ObjectProperty<LocalDate> dtPkrStart = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> dtPkrEnd = new SimpleObjectProperty<>();

    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    public ViewModel() {
        Locale.setDefault(Locale.ENGLISH);
        dtPkrStart.set(LocalDate.now());
        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(txtBase, txtIntCount, txtPercent, dtPkrEnd, dtPkrStart);
            }

            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        calculationDisabled.bind(couldCalculate.not());
        final List<StringProperty> fields = new ArrayList<StringProperty>() {
            {
                add(txtBase);
                add(txtIntCount);
                add(txtPercent);
            }
        };
        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
        final DataValueChangeListener endDataListener = new DataValueChangeListener();
        dtPkrEnd.addListener(endDataListener);
        final DataValueChangeListener startDataListener = new DataValueChangeListener();
        dtPkrEnd.addListener(startDataListener);
    }

    public void calculate() {
        GregorianCalendar startDate = convertToGregorian(dtPkrStart.get());
        GregorianCalendar endDate = convertToGregorian(dtPkrEnd.get());
        ComplexDeposit calcDeposit = new ComplexDeposit();
        calcDeposit.setPercent(txtPercent.get())
                    .setBase(txtBase.get())
                    .setInterestCountInYear(txtIntCount.get())
                    .setStartDate(startDate)
                    .setFinishDate(endDate);
        result.set(String.format("%.2f", calcDeposit.getCapitalizedBase()));
        status.set(Status.SUCCESS.toString());
    }

    public final boolean getCalculationDisabled() {
        return calculationDisabled.get();
    }

    public ObjectProperty<LocalDate> dtPkrStartProperty() {
        return dtPkrStart;
    }

    public ObjectProperty<LocalDate> dtPkrEndProperty() {
        return dtPkrEnd;
    }

    public StringProperty getTxtBaseProperty() {
        return txtBase;
    }

    public StringProperty getTxtInterestCountProperty() {
        return txtIntCount;
    }

    public StringProperty getTxtPercentProperty() {
        return txtPercent;
    }

    public StringProperty resultProperty() {
        return result;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public final String getResult() {
        return result.get();
    }

    public BooleanProperty calculationDisabledProperty() {
        return calculationDisabled;
    }

    public final String getStatus() {
        return status.get();
    }

    private GregorianCalendar convertToGregorian(final LocalDate dtPkr) {
        GregorianCalendar startDate = new GregorianCalendar();
        startDate.set(dtPkr.getYear(), dtPkr.getMonthValue(), dtPkr.getDayOfMonth());
        return startDate;
    }

    private boolean hasNegativeFields() {
        try {
            if (Double.parseDouble(txtBase.get()) < 0
                    || Double.parseDouble(txtPercent.get()) < 0
                    || Integer.parseInt(txtIntCount.get()) < 0) {
                return true;
            }
            return false;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private boolean hasEmptyFields() {
        if (txtBase.get().isEmpty() || txtPercent.get().isEmpty() || txtIntCount.get().isEmpty()
                || dtPkrEnd.getValue() == null || dtPkrStart.getValue() == null) {
            return true;
        }
        return false;
    }

    private boolean incorrectDate() {
        if (!(dtPkrEnd.getValue() == null || dtPkrEnd.getValue() == null)) {
            Integer.parseInt(txtIntCount.get());
            if (dtPkrEnd.get().compareTo(dtPkrStart.get()) < 0) {
                return true;
            }
        }
        return false;
    }

    private boolean incorrectTxtData() {
        try {
            if (!txtIntCount.get().isEmpty()) {
                Integer.parseInt(txtIntCount.get());
            }
            if (!txtPercent.get().isEmpty()) {
                Double.parseDouble(txtPercent.get());
            }
            if (!txtBase.get().isEmpty()) {
                Double.parseDouble(txtBase.get());
            }
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (hasEmptyFields()) {
            inputStatus = Status.WAITING;
        }
        if (incorrectTxtData() || hasNegativeFields()) {
            return Status.BAD_FORMAT;
        }
        if (incorrectDate()) {
            return Status.BAD_DATE;
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

    private class DataValueChangeListener implements ChangeListener<LocalDate> {
        @Override
        public void changed(final ObservableValue<? extends LocalDate> observable,
                            final LocalDate oldValue, final LocalDate newValue) {
            status.set(getInputStatus().toString());
        }
    }
}

enum Status {
    WAITING("Заполните поля"),
    READY("Нажмите на кнопку 'Рассчитать'."),
    BAD_FORMAT("Что то ввели не так!"),
    SUCCESS("Готово!"),
    BAD_DATE("Неверная дата конца вклада");

    private final String name;

    private Status(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
