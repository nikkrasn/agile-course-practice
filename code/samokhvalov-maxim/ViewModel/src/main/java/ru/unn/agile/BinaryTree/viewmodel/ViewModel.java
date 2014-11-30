package ru.unn.agile.BinaryTree.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.BinaryTree.model.BinaryTree;
import ru.unn.agile.BinaryTree.model.Node;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty key = new SimpleStringProperty();
    private final StringProperty value = new SimpleStringProperty();

    private final ObjectProperty<ObservableList<Operation>> operations =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Operation.values()));
    private final ObjectProperty<Operation> operation = new SimpleObjectProperty<>();
    private final BooleanProperty executeDisabled = new SimpleBooleanProperty();
    private final BooleanProperty keyFieldDisabled = new SimpleBooleanProperty();
    private final BooleanProperty valueFieldDisabled = new SimpleBooleanProperty();

    private final StringProperty resultKey = new SimpleStringProperty();
    private final StringProperty resultValue = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    private final BinaryTree tree = new BinaryTree();

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();
    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getInputStatus().toString());
        }
    }

    public ViewModel() {
        key.set("");
        value.set("");
        operation.set(Operation.INSERT);
        resultKey.set("—");
        resultValue.set("—");
        status.set(Status.WAITING.toString());
        bindDeterminate();
        createFieldsValueChangingListeners();
    }

    public void execute() {
        if (executeDisabled.get()) {
            return;
        }

        String tempKey = "—";
        String tempValue = "—";
        String statusResult = Status.SUCCESS.toString();

        switch(operation.get()) {
            case INSERT:
                Node node = new Node(Integer.parseInt(key.get()), value.get());
                tree.insert(node);
                break;
            case FIND:
                ArrayList<Node> nodes = tree.findByValue(value.get());
                if (nodes.isEmpty()) {
                    statusResult = Status.NODE_NOT_FOUND.toString();
                } else {
                    tempKey = Integer.toString(getFirstNodeKey(nodes));
                    tempValue = getFirstNodeValue(nodes).toString();
                }
                break;
            case DELETE:
                tree.delete(Integer.parseInt(key.get()));
            case GET_ROOT:
                Node result = tree.getRoot();
                tempKey = Integer.toString(result.getKey());
                tempValue = result.getValue().toString();
                break;
            default:
                break;
        }

        resultKey.set(tempKey);
        resultValue.set(tempValue);
        status.set(statusResult);
    }

    public StringProperty keyProperty() {
        return key;
    }
    public StringProperty valueProperty() {
        return value;
    }
    public ObjectProperty<ObservableList<Operation>> operationsProperty() {
        return operations;
    }
    public final ObservableList<Operation> getOperations() {
        return operations.get();
    }
    public ObjectProperty<Operation> operationProperty() {
        return operation;
    }
    public BooleanProperty executeDisabledProperty() {
        return executeDisabled;
    }
    public final boolean getExecuteDisabled() {
        return executeDisabled.get();
    }
    public BooleanProperty valueFieldDisabledProperty() {
        return valueFieldDisabled;
    }
    public final boolean getValueFieldDisabled() {
        return valueFieldDisabled.get();
    }
    public BooleanProperty keyFieldDisabledProperty() {
        return keyFieldDisabled;
    }
    public final boolean getKeyFieldDisabled() {
        return keyFieldDisabled.get();
    }

    public StringProperty resultKeyProperty() {
        return resultKey;
    }
    public final String getResultKey() {
        return resultKey.get();
    }
    public StringProperty resultValueProperty() {
        return resultValue;
    }
    public final String getResultValue() {
        return resultValue.get();
    }
    public StringProperty statusProperty() {
        return status;
    }
    public final String getStatus() {
        return status.get();
    }

    private Status getInputStatus() {

        Status inputStatus = Status.READY;

        if (operation.get() == Operation.INSERT && (key.get().isEmpty() || value.get().isEmpty())) {
            inputStatus = Status.WAITING;
        }

        if (operation.get() == Operation.DELETE && key.get().isEmpty()) {
            inputStatus = Status.WAITING;
        }

        if (operation.get() == Operation.FIND && value.get().isEmpty()) {
            inputStatus = Status.WAITING;
        }

        if (operationProperty().get() == Operation.GET_ROOT) {
            inputStatus = Status.READY;
        }

        try {
            if (!key.get().isEmpty()) {
                Integer.parseInt(key.get());
            }
        } catch (NumberFormatException nfe) {
            inputStatus = Status.BAD_FORMAT;
        }

        status.set(inputStatus.toString());
        return inputStatus;
    }

    private void bindDeterminate() {
        BooleanBinding couldExecute = new BooleanBinding() {
            {
                super.bind(key, value, operation);
            }
            @Override
            protected boolean computeValue() {
                Status tempStatus = getInputStatus();
                status.set(tempStatus.toString());
                return tempStatus == Status.READY;
            }
        };
        executeDisabled.bind(couldExecute.not());

        BooleanBinding couldChangeKeyField = new BooleanBinding() {
            {
                super.bind(operation);
            }
            @Override
            protected boolean computeValue() {
                return operation.get() != Operation.FIND && operation.get() != Operation.GET_ROOT;
            }
        };

        keyFieldDisabled.bind(couldChangeKeyField.not());

        BooleanBinding couldChangeValueField = new BooleanBinding() {
            {
                super.bind(operation);
            }
            @Override
            protected boolean computeValue() {
                return operation.get() == Operation.INSERT || operation.get() == Operation.FIND;
            }
        };

        valueFieldDisabled.bind(couldChangeValueField.not());
    }

    private void createFieldsValueChangingListeners() {
        final List<StringProperty> stringFields = new ArrayList<StringProperty>() { {
            add(key);
            add(value);
        } };

        for (StringProperty field : stringFields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    private int getFirstNodeKey(ArrayList<Node> nodes) {
        return nodes.get(0).getKey();
    }

    private Object getFirstNodeValue(ArrayList<Node> nodes) {
        return nodes.get(0).getValue();
    }
}

enum Status {
    WAITING("Please input data"),
    READY("Press 'Execute'"),
    NODE_NOT_FOUND("Node not found"),
    BAD_FORMAT("Bad format"),
    SUCCESS("Success");

    private final String name;
    private Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
