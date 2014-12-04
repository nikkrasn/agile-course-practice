package ru.unn.agile.Stack.ViewModel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import ru.unn.agile.Stack.Model.Stack;

public class ViewModel {
    private final ObjectProperty<ObservableList<String>> stackTable = new SimpleObjectProperty<>();
    private final StringProperty top = new SimpleStringProperty();
    private final StringProperty pushText = new SimpleStringProperty();
    private final BooleanProperty isEmpty = new SimpleBooleanProperty();
    private final Stack<String> stack = new Stack<>();

    public StringProperty topProperty() {
        return top;
    }
    public final String getTop() {
        return top.get();
    }

    public StringProperty pushTextProperty() {
        return pushText;
    }
    public final String getPushText() {
        return pushText.get();
    }
    public final void setPushText(final String text) {
        pushText.set(text);
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

        updateProperties();
        pushText.set("Push me!");
    }

    public void push() {
        stack.push(pushText.get());
    }

    public void pop() {
        if (isPopButtonDisabled()) {
            return;
        }
        stack.pop();
    }

    private void updateProperties() {
        isEmpty.set(stack.isEmpty());
        top.set(getIsEmpty() ? "" : stack.top());
        stackTable.set(FXCollections.observableList(stack.toList()));
    }
}
