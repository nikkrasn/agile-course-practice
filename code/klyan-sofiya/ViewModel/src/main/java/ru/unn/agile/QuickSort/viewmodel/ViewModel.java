package ru.unn.agile.QuickSort.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.unn.agile.QuickSort.Model.QuickSorting;

public class ViewModel {
    private final StringProperty sortedValues = new SimpleStringProperty();
    private final StringProperty unsortedValues = new SimpleStringProperty();

    private final BooleanProperty sortingDisabled = new SimpleBooleanProperty();

    private final StringProperty status = new SimpleStringProperty();

    private final InputChangeListener listener;
    private double[] unsortedArray = new double[0];

    public ViewModel() {
        unsortedValues.set("");
        sortedValues.set("");
        status.set(Status.WAITING.toString());

        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(unsortedValues);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        sortingDisabled.bind(couldCalculate.not());

        listener = new InputChangeListener();
        unsortedValues.addListener(listener);
    }

    public void sort() {
        if (sortingDisabled.get()) {
            return;
        }

        double[] values = unsortedArray.clone();
        QuickSorting.sort(values);
        String output = createOutputFromArray(values);

        sortedValues.set(output);
        status.set(Status.SUCCESS.toString());
    }

    public StringProperty unsortedArrayProperty() { return unsortedValues; }
    public StringProperty sortedArrayProperty() {
        return sortedValues;
    }

    public BooleanProperty sortingDisabledProperty() {
        return sortingDisabled;
    }
    public final boolean getSortingDisabled() {
        return sortingDisabled.get();
    }

    public StringProperty statusProperty() {
        return status;
    }
    public final String getStatus() {
        return status.get();
    }

    private String createOutputFromArray(final double[] values) {
        String output = "";
        if (values.length > 0) {
            for (int i = 0; i < values.length - 1; i++) {
                output += Double.toString(values[i]) + " ";
            }
            output += Double.toString(values[values.length - 1]);
        }
        return output;
    }

    private Status parseInputValues() {
        Status inputStatus = Status.READY;
        String[] splitValues = unsortedValues.get().split(" ");
        unsortedArray = new double[splitValues.length];
        try {
            for (int i = 0; i < splitValues.length; i++) {
                String value = splitValues[i];
                if (value != "") {
                   unsortedArray[i] = Double.parseDouble(value);
                }
            }
        } catch (NumberFormatException nfe) {
            inputStatus = Status.BAD_FORMAT;
        }
        return inputStatus;
    }

    private Status getInputStatus() {
        if (unsortedValues.get().isEmpty()) {
            return Status.WAITING;
        }
        return parseInputValues();
    }

    private class InputChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getInputStatus().toString());
        }
    }
}

enum Status {
    READY("Press 'Sort' or Enter"),
    BAD_FORMAT("Bad format"),
    SUCCESS("Success"),
    WAITING("Please provide input values separated with SPACE");

    private final String name;
    private Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
