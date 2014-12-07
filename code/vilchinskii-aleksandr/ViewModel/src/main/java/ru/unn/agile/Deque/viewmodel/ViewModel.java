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

    private class InputChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getInputStatus().toString());
        }
    }

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
        getItem(new GetFirstCommand());
    }

    public void getLast() {
        getItem(new GetLastCommand());
    }

    public void removeFirst() {
        getItem(new RemoveFirstCommand());
    }

    public void removeLast() {
        getItem(new RemoveLastCommand());
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

    public boolean isDequeEmpty() {
        return deque.isEmpty();
    }

    public interface Command {
        Integer execute(final Deque<Integer> deque);
    }

    public class GetFirstCommand implements Command {
        public Integer execute(final Deque<Integer> deque) {
            return deque.getFirst();
        }
    }

    public class GetLastCommand implements Command {
        public Integer execute(final Deque<Integer> deque) {
            return deque.getLast();
        }
    }

    public class RemoveFirstCommand implements Command {
        public Integer execute(final Deque<Integer> deque) {
            return deque.removeFirst();
        }
    }

    public class RemoveLastCommand implements Command {
        public Integer execute(final Deque<Integer> deque) {
            return deque.removeLast();
        }
    }

    private void getItem(final Command getter) {
        try {
            Integer item = getter.execute(deque);
            txtItem.set(item.toString());
            status.set(Status.SUCCESS.toString());
        } catch (NoSuchElementException nsee) {
            status.set(Status.EMPTY.toString());
        }
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
