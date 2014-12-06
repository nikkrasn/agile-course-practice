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
    private final StringProperty txtItem = new SimpleStringProperty();
    private final StringProperty status   = new SimpleStringProperty();
    private final BooleanProperty isAddingDisabled  = new SimpleBooleanProperty();

    private final Deque<Integer> deque = Deque.create();

    private final InputChangeListener valueChangedListener = new InputChangeListener();

    public ViewModel() {
        txtItem.set("");
        status.set(Status.WAITING.toString());

        BooleanBinding canAdd = new BooleanBinding() {
            {
                super.bind(txtItem);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        isAddingDisabled.bind(canAdd.not());

        txtItem.addListener(valueChangedListener);
    }

    public void addFirst() {
        if (isAddingDisabled.get()) {
            return;
        }

        Integer item = Integer.parseInt(getTxtItem());
        deque.addFirst(item);
    }

    public void addLast() {
        if (isAddingDisabled.get()) {
            return;
        }

        Integer item = Integer.parseInt(getTxtItem());
        deque.addLast(item);
    }

    public void getFirst() {
        try {
            Integer item = deque.getFirst();
            txtItem.set(item.toString());
            status.set(Status.SUCCESS.toString());
        } catch (NoSuchElementException nsee) {
            status.set(Status.EMPTY.toString());
        }
    }

    public void getLast() {
        try {
            Integer item = deque.getLast();
            txtItem.set(item.toString());
            status.set(Status.SUCCESS.toString());
        } catch (NoSuchElementException nsee) {
            status.set(Status.EMPTY.toString());
        }
    }

    public void removeFirst() {
        try {
            Integer item = deque.removeFirst();
            txtItem.set(item.toString());
            status.set(Status.SUCCESS.toString());
        } catch (NoSuchElementException nsee) {
            status.set(Status.EMPTY.toString());
        }
    }

    public void removeLast() {
        try {
            Integer item = deque.removeLast();
            txtItem.set(item.toString());
            status.set(Status.SUCCESS.toString());
        } catch (NoSuchElementException nsee) {
            status.set(Status.EMPTY.toString());
        }
    }

    public StringProperty txtItemProperty() {
        return txtItem;
    }

    public final String getTxtItem() {
        return txtItem.get();
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
        if (getTxtItem().isEmpty()) {
            inputStatus = Status.WAITING;
            return inputStatus;
        }

        try {
            Integer.parseInt(getTxtItem());
        } catch (NumberFormatException nfe) {
            inputStatus = Status.BAD_FORMAT;
        }

        return inputStatus;
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
    WAITING("Insert an item please"),
    READY("Press add button"),
    BAD_FORMAT("Incorrect data"),
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
