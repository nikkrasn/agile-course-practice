package ru.unn.agile.LeftistHeap.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import ru.unn.agile.LeftistHeap.Model.HeapNode;
import ru.unn.agile.LeftistHeap.Model.LeftistHeap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewModel {
    private final StringProperty key;

    private final StringProperty value;

    private final StringProperty newKey;

    private final StringProperty result;

    private final StringProperty status;

    private final ObjectProperty<ObservableList<LeftistHeap<String>>> heaps;

    private final ObjectProperty<LeftistHeap<String>> heap;

    public ViewModel() {
        key = new SimpleStringProperty("");
        newKey = new SimpleStringProperty("");
        value = new SimpleStringProperty("");
        result = new SimpleStringProperty("");
        status = new SimpleStringProperty("");

        LeftistHeap<String> defaultSelectedHeap = new LeftistHeap<String>("heap 1");

        heaps = new SimpleObjectProperty<ObservableList<LeftistHeap<String>>>(
                FXCollections.observableArrayList(
                        Arrays.asList(
                                defaultSelectedHeap,
                                new LeftistHeap<String>("heap 2"))));

        heap = new SimpleObjectProperty<>();
        heap.set(defaultSelectedHeap);

        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(key);
            add(value);
            add(newKey);
        } };

        List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public StringProperty keyProperty() {
        return key;
    }

    public StringProperty valueProperty() {
        return value;
    }

    public StringProperty newKeyProperty() {
        return newKey;
    }

    public StringProperty resultProperty() {
        return result;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public String getStatus() {
        return statusProperty().get();
    }

    public String getResult() {
        return resultProperty().get();
    }

    public ObjectProperty<ObservableList<LeftistHeap<String>>> heapsProperty() {
        return heaps;
    }
    public final ObservableList<LeftistHeap<String>> getHeaps() {
        return heaps.get();
    }

    public ObjectProperty<LeftistHeap<String>> heapProperty() {
        return heap;
    }

    public void add() {
        String key = keyProperty().get();

        if (validNumber(key)) {
            getSelectedHeap().add(Integer.parseInt(keyProperty().get()), valueProperty().get());
            setOutput(
                    String.format("New element added to heap '%s'", getSelectedHeap().getName()),
                    Status.OK);
            return;
        }

        setOutput("Element was not added", Status.BAD_INPUT);
    }

    public void extract() {
        String key = keyProperty().get();

        if (validNumber(key)) {
            HeapNode<String> node = getSelectedHeap().extractElementWithKey(
                    Integer.parseInt(keyProperty().get()));

            String message =
                    node == null
                            ? String.format(
                                "Heap '%s' not contain elements with key '%s'",
                                getSelectedHeap().getName(),
                                key)
                            : node.toString();

            setOutput(
                    message,
                    Status.OK);

            return;
        }

        setOutput("Any element was not deleted", Status.BAD_INPUT);
    }

    public void extractMinimum() {
        HeapNode<String> node = getSelectedHeap().extractMin();

        String message =
                node == null
                        ? String.format("Heap '%s' is empty", getSelectedHeap().getName())
                        : node.toString();

        setOutput(message, Status.OK);
    }

    public void merge() {
        heaps.get().get(1).merge(heaps.get().get(0));

        setOutput(
                String.format(
                        "Merged heap '%s' with heap '%s'",
                        getHeap(1).getName(),
                        getHeap(0).getName()),
                Status.OK);
    }

    public void decreaseKey() {
        String key = keyProperty().get();
        String newKey = newKeyProperty().get();

        if (validNumber(key) && validNumber(newKey)) {
            try {
                if (getSelectedHeap().decreaseKey(
                        Integer.parseInt(key),
                        Integer.parseInt(newKey))) {

                    setOutput("Key decreased", Status.OK);
                    return;
                } else {
                    setOutput(
                            String.format(
                                    "Key '%s' not found in heap '%s'",
                                    key,
                                    getSelectedHeap().getName()),
                            Status.OK);
                    return;
                }
            } catch (IllegalArgumentException exception) {
                setOutput("New key must be less than current", Status.OK);
                return;
            }
        }

        setOutput("Any key was not decreased", Status.BAD_INPUT);
    }

    private Boolean validNumber(final String number) {
        if (number.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }

    private void setOutput(final String message, final Status actionStatus) {
        result.set(message);
        status.set(actionStatus.toString());
    }

    private LeftistHeap<String> getSelectedHeap() {
        return heap.get();
    }

    private LeftistHeap<String> getHeap(final int index) {
        return heaps.get().get(index);
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getStatus());
        }
    }
}

enum Status {
    BAD_INPUT("Bad input"),
    OK("OK");

    private final String name;
    private Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
