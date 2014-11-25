package ru.unn.agile.ComplexProcent.ViewModel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import ru.unn.agile.ComplexProcent.Model.ComplexDeposit;

public class ViewModel {
    private final StringProperty txtBase = new SimpleStringProperty();
    private final StringProperty txtInterestCount = new SimpleStringProperty();
    private final StringProperty txtPercent = new SimpleStringProperty();

    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    private final ObjectProperty<LocalDate> dtPkrStart = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> dtPkrEnd = new SimpleObjectProperty<>();

    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    public ViewModel() {
        txtBase.set("");
        txtInterestCount.set("");
        txtPercent.set("");
        result.set("");
        dtPkrStart.set(LocalDate.now());
        status.set(Status.WAITING.toString());

        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(txtBase, txtInterestCount, txtPercent, dtPkrEnd, dtPkrStart);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        calculationDisabled.bind(couldCalculate.not());

        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(txtBase);
            add(txtInterestCount);
            add(txtPercent);
        } };

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        };
        final DataValueChangeListener endDataListener = new DataValueChangeListener();
        dtPkrEnd.addListener(endDataListener);
        final DataValueChangeListener startDataListener = new DataValueChangeListener();
        dtPkrEnd.addListener(startDataListener);


    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (txtBase.get().isEmpty() || txtPercent.get().isEmpty()
                || txtInterestCount.get().isEmpty() || (dtPkrEnd.getValue()==null)
                || (dtPkrStart.getValue()==null)) {
            inputStatus = Status.WAITING;
        }
        try {
            if (!txtInterestCount.get().isEmpty()) {
                Integer.parseInt(txtInterestCount.get());
            }
            if (!txtPercent.get().isEmpty()) {
                Double.parseDouble(txtPercent.get());
            }
            if (!txtBase.get().isEmpty()) {
                Double.parseDouble(txtBase.get());
            }
        }
        catch (NumberFormatException nfe) {
            return Status.BAD_FORMAT;
        }
            if (!((dtPkrEnd.getValue()==null) || (dtPkrEnd.getValue()==null))) {
                Integer.parseInt(txtInterestCount.get());
                if(dtPkrEnd.get().compareTo(dtPkrStart.get())<0)
                    return Status.BAD_DATE;
            }



        return inputStatus;
    }

    public GregorianCalendar getStartDate(final LocalDate dtPkr) {
        GregorianCalendar startDate= new GregorianCalendar();
        startDate.set(dtPkr.getYear(),dtPkr.getMonthValue(),dtPkr.getDayOfMonth());
        return startDate;
    }

    public GregorianCalendar getEndDate(final LocalDate dtPkr) {
        GregorianCalendar endDate= new GregorianCalendar();
        endDate.set(dtPkr.getYear(), dtPkr.getMonthValue(), dtPkr.getDayOfMonth());
        return endDate;
    }

    public void calculate() {
        ComplexDeposit calculatedDeposit = new ComplexDeposit(txtBase.get(),txtPercent.get(),txtInterestCount.get());
        GregorianCalendar startDate = getStartDate(dtPkrStart.get());
        GregorianCalendar endDate = getStartDate(dtPkrEnd.get());
        calculatedDeposit.setStartDate(startDate);
        calculatedDeposit.setFinishDate(endDate);
        result.set(String.format("%.2f", calculatedDeposit.getCapitalizedBase()));
        status.set(Status.SUCCESS.toString());
    };

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
        return txtInterestCount;
    }

    public StringProperty getTxtPercentProperty() {
        return txtPercent;
    }

    public StringProperty resultProperty() {
        return result;
    }

    public StringProperty statusProperty() {return status;}

    public final String getResult() {
        return result.get();
    }

    public BooleanProperty calculationDisabledProperty() {
        return calculationDisabled;
    }

    public final String getStatus(){return status.get();}

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