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

    public ObjectProperty<LeftistHeap<String>> operationProperty() {
        return heap;
    }

    public void add() {
        String key = keyProperty().get();

        if (validNumber(key)) {
            heap.get().add(Integer.parseInt(keyProperty().get()), valueProperty().get());
            status.set("OK");
            return;
        }

        result.set("");
        status.set("Bad input");
    }

    public void delete() {
        String key = keyProperty().get();

        if (validNumber(key)) {
            HeapNode<String> result = heap.get().extractElementWithKey(
                    Integer.parseInt(keyProperty().get()));

            if (result == null) {
                this.result.set(String.format("Heap not contain elements with key '%s'", key));
            } else {
                this.result.set(result.toString());
            }
            status.set("OK");
            return;
        }

        result.set("");
        status.set("Bad input");
    }

    public void getMinimum() {
        HeapNode<String> result = heap.get().extractMin();
        if (result == null) {
            this.result.set("Heap is empty");
        } else {
            this.result.set(result.toString());
        }

        status.set("OK");
    }

    public void merge() {
        heaps.get().get(1).merge(heaps.get().get(0));

        result.set("Merged");

        status.set("OK");
    }

    public void decreaseKey() {
        String key = keyProperty().get();
        String newKey = newKeyProperty().get();

        if (validNumber(key) && validNumber(newKey)) {
            try {
                if (heap.get().decreaseKey(Integer.parseInt(key), Integer.parseInt(newKey))) {
                    result.set("Key decreased");
                    status.set("OK");
                    return;
                } else {
                    result.set("Key not found");
                    status.set("OK");
                    return;
                }
            } catch (IllegalArgumentException exception) {
                result.set("New key must be less than current");
                status.set("OK");
                return;
            }
        }

        result.set("");
        status.set("Bad input");
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

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getStatus());
        }
    }
}
