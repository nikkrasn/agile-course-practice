package ru.unn.agile.Queue.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.unn.agile.Queue.model.Queue;

import java.util.NoSuchElementException;

public class ViewModel {
    private final StringProperty  txtToAdd          = new SimpleStringProperty();
    private final StringProperty  state             = new SimpleStringProperty();
    private final StringProperty  element           = new SimpleStringProperty();
    private final BooleanProperty isAddingDisabled  = new SimpleBooleanProperty();
    private final Queue<Integer>  queue             = new Queue<Integer>();

    private final InputChangeListener valueChangedListener = new InputChangeListener();

    private class InputChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            state.set(getInputStatus().toString());
        }
    }


    ViewModel() {
        txtToAdd.set("");
        setElementToEmpty();
        state.set(State.AWAITING.toString());

        BooleanBinding canAdd = new BooleanBinding() {
            {
                super.bind(txtToAdd);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == State.READY;
            }
        };
        isAddingDisabled.bind(canAdd.not());

        txtToAdd.addListener(valueChangedListener);
    }

    public void add() {
        if (isAddingDisabled.get()) {
            return;
        }

        Integer item = Integer.parseInt(getTxtToAdd());
        queue.add(item);
        updateElement();
    }

    public void remove() {
        try {
            Integer item = queue.remove();
            txtToAdd.set(item.toString());
            state.set(State.OK.toString());

        } catch (NoSuchElementException nsee) {
            state.set(State.EMPTY.toString());
        }
        updateElement();
    }

    public boolean isQueueEmpty() {
        return queue.isEmpty();
    }

    public BooleanProperty isAddingDisabledProperty() {
        return isAddingDisabled;
    }

    public final boolean getIsAddingDisabled() {
        return isAddingDisabled.get();
    }

    public StringProperty stateProperty() {
        return state;
    }

    public final String getState() {
        return state.get();
    }

    public StringProperty elementProperty() {
        return element;
    }

    public final String getElement() {
        return element.get();
    }

    public StringProperty txtToAddProperty() {
        return txtToAdd;
    }

    public final String getTxtToAdd() {
        return txtToAdd.get();
    }

    private State getInputStatus() {
        State inputState = State.READY;
        if (getTxtToAdd().isEmpty()) {
            inputState = State.AWAITING;
            return inputState;
        }

        try {
            Integer.parseInt(getTxtToAdd());
        } catch (NumberFormatException nfe) {
            inputState = State.BAD_INPUT;
        }

        return inputState;
    }

    private void updateElement() {
        if (isQueueEmpty()) {
            setElementToEmpty();
        } else {
            element.set(queue.element().toString());
        }
    }

    private void setElementToEmpty() {
        element.set("Empty");
    }
}

enum State {
    AWAITING("Insert an item"),
    READY("Press add button"),
    BAD_INPUT("Incorrect input"),
    EMPTY("Queue is empty"),
    OK("Success");

    private final String name;

    private State(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
