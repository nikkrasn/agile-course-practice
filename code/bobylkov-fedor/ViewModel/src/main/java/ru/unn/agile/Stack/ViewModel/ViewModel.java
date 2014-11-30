package ru.unn.agile.Stack.ViewModel;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.Stack.Model.Stack;

public class ViewModel {
    private final ObjectProperty<ObservableList<String>> stackTable = new SimpleObjectProperty<>();
    private final StringProperty top = new SimpleStringProperty();
    private final StringProperty pushText = new SimpleStringProperty();
    private final BooleanProperty isEmpty = new SimpleBooleanProperty();
    private final ObjectProperty<Stack<String>> stack = new SimpleObjectProperty<>();
    public StringProperty topProperty() {
        return top;
    }
    public StringProperty pushTextProperty() {
        return pushText;
    }
    public ObjectProperty<ObservableList<String>> stackTableProperty() {
        return stackTable;
    }
    public BooleanProperty isPopButtonDisabled() {
        return isEmpty;
    }

    public ViewModel() {
        stack.addListener(new ChangeListener<Stack<String>>() {
            @Override
            public void changed(final ObservableValue<? extends Stack<String>> observable,
                                final Stack<String> oldValue, final Stack<String> newValue) {
                isEmpty.set(newValue.isEmpty());
                top.set(isEmpty.get() ? "" : newValue.top());
                stackTable.set(FXCollections.observableList(newValue.toList()));
            }
        });

        stack.set(new Stack<>());
        pushText.set("Push me!");
    }

    public void push() {
        stack.get().push(pushText.get());
    }
}
