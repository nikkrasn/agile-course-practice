package ru.unn.agile.Stack.ViewModel;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import ru.unn.agile.Stack.Model.Stack;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private ILogger logger;

    private final ObjectProperty<ObservableList<String>> stackTable = new SimpleObjectProperty<>();
    private final StringProperty top = new SimpleStringProperty();
    private final StringProperty textToPush = new SimpleStringProperty();
    private final BooleanProperty isEmpty = new SimpleBooleanProperty();
    private final Stack<String> stack = new Stack<>();

    public StringProperty topProperty() {
        return top;
    }
    public final String getTop() {
        return top.get();
    }

    public StringProperty textToPushProperty() {
        return textToPush;
    }
    public final String getTextToPush() {
        return textToPush.get();
    }
    public final void setTextToPush(final String text) {
        textToPush.set(text);
    }

    public ObjectProperty<ObservableList<String>> stackTableProperty() {
        return stackTable;
    }
    public final ObservableList<String> getStackTable() {
        return stackTable.get();
    }

    public BooleanProperty isEmptyProperty() {
        return isEmpty;
    }
    public final Boolean getIsEmpty() {
        return isEmpty.get();
    }
    public Boolean isPopButtonDisabled() {
        return getIsEmpty();
    }

    public ViewModel() {
        stack.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(final Change<? extends String> c) {
                updateProperties();
            }
        });
        textToPush.addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable,
                                final String oldValue,
                                final String newValue) {
                log("Text-To-Push changed to: " + newValue);
            }
        });
        top.addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable,
                                final String oldValue,
                                final String newValue) {
                log("Top changed to :" + newValue);
            }
        });

        updateProperties();
        textToPush.set("Push me!");
    }

    public void push() {
        stack.push(textToPush.get());
        log("Pushed: " + textToPush.get());
    }

    public void pop() {
        if (isPopButtonDisabled()) {
            return;
        }
        String popped = stack.pop();
        log("Popped: " + popped);
    }

    public void setLogger(final ILogger newLogger) {
        logger = newLogger;
    }

    public List<LogMessage> getLog() {
        if (logger != null) {
            return logger.getLog();
        }
        return new ArrayList<>();
    }

    private void log(final String message) {
        if (logger != null) {
            logger.log(message);
        }
    }

    private void updateProperties() {
        isEmpty.set(stack.isEmpty());
        top.set(getIsEmpty() ? "" : stack.top());
        stackTable.set(FXCollections.observableList(stack.toList()));
    }
}
