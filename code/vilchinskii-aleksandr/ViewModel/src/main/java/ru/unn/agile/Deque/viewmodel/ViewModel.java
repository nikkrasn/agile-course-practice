package ru.unn.agile.Deque.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.unn.agile.Deque.model.Deque;

import java.util.NoSuchElementException;

public class ViewModel {
    private final StringProperty txtValue = new SimpleStringProperty();
    private final StringProperty status   = new SimpleStringProperty();

    private Deque<Integer> deque = Deque.create();

    private final BooleanProperty isAddingDisabled  = new SimpleBooleanProperty();

    private final ValueChangeListener valueChangedListener = new ValueChangeListener();

    public ViewModel() {
        txtValue.set("");
        status.set(Status.WAITING.toString());

        BooleanBinding canAdd = new BooleanBinding() {
            {
                super.bind(txtValue);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        isAddingDisabled.bind(canAdd.not());

        txtValue.addListener(valueChangedListener);
    }

    public void addFirst() {
        if (isAddingDisabled.get()) {
            return;
        }

        Integer item = Integer.parseInt(getTxtValue());
        deque.addFirst(item);
    }

    public void addLast() {
        if (isAddingDisabled.get()) {
            return;
        }

        Integer item = Integer.parseInt(getTxtValue());
        deque.addLast(item);
    }

    public void getFirst() {
        try {
            Integer item = deque.getFirst();
            txtValue.set(item.toString());
            status.set(Status.SUCCESS.toString());
        } catch (NoSuchElementException nsee) {
            status.set(Status.EMPTY.toString());
        }
    }

    public void getLast() {
        try {
            Integer item = deque.getLast();
            txtValue.set(item.toString());
            status.set(Status.SUCCESS.toString());
        } catch (NoSuchElementException nsee) {
            status.set(Status.EMPTY.toString());
        }
    }

    public void removeFirst() {
        try {
            Integer item = deque.removeFirst();
            txtValue.set(item.toString());
            status.set(Status.SUCCESS.toString());
        } catch (NoSuchElementException nsee) {
            status.set(Status.EMPTY.toString());
        }
    }

    public void removeLast() {
        try {
            Integer item = deque.removeLast();
            txtValue.set(item.toString());
            status.set(Status.SUCCESS.toString());
        } catch (NoSuchElementException nsee) {
            status.set(Status.EMPTY.toString());
        }
    }

    public StringProperty txtValueProperty() {
        return txtValue;
    }

    public final String getTxtValue() {
        return txtValue.get();
    }

    public BooleanProperty isAddingDisabledProperty() {
        return isAddingDisabled;
    }

    public final boolean getIsAddingDisabled() {
        return isAddingDisabled.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public final String getStatus() {
        return status.get();
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (getTxtValue().isEmpty()) {
            inputStatus = Status.WAITING;
            return inputStatus;
        }

        try {
            Integer.parseInt(getTxtValue());
        } catch (NumberFormatException nfe) {
            inputStatus = Status.BAD_FORMAT;
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
}

enum Status {
    WAITING("Please provide input data"),
    READY("Press add button"),
    BAD_FORMAT("Bad format"),
    EMPTY("Container is empty"),
    SUCCESS("Success");

    private final String name;

    private Status(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}