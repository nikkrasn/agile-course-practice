package ru.unn.agile.LeftistHeap.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.unn.agile.LeftistHeap.Model.*;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty key1 = new SimpleStringProperty();
    private final StringProperty key2 = new SimpleStringProperty();

    private final StringProperty value1 = new SimpleStringProperty();
    private final StringProperty value2 = new SimpleStringProperty();

    private final StringProperty newKey1 = new SimpleStringProperty();
    private final StringProperty newKey2 = new SimpleStringProperty();

    private final StringProperty result1 = new SimpleStringProperty();
    private final StringProperty result2 = new SimpleStringProperty();

    private final StringProperty status = new SimpleStringProperty();

    private final LeftistHeap<String> heap1 = new LeftistHeap<String>();
    private final LeftistHeap<String> heap2 = new LeftistHeap<String>();

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    public ViewModel() {
        key1.set("");
        key2.set("");

        value1.set("");
        value2.set("");

        newKey1.set("");
        newKey2.set("");

        result1.set("");
        result2.set("");

        status.set("");

        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(key1);
            add(value1);
            add(newKey1);

            add(key2);
            add(value2);
            add(newKey2);
        } };

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public StringProperty key1Property() {
        return key1;
    }

    public StringProperty key2Property() {
        return key2;
    }

    public StringProperty value1Property() {
        return value1;
    }

    public StringProperty value2Property() {
        return value2;
    }

    public StringProperty newKey1Property() {
        return newKey1;
    }

    public StringProperty newKey2Property() {
        return newKey2;
    }

    public StringProperty result1Property() {
        return result1;
    }

    public StringProperty result2Property() {
        return result2;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public String getStatus() {
        return statusProperty().get();
    }

    public String getResult1() {
        return result1Property().get();
    }

    public String getResult2() {
        return result2Property().get();
    }

    public void add1() {
        String key = key1Property().get();

        if (validNumber(key)) {
            heap1.add(Integer.parseInt(key1Property().get()), value1Property().get());
            status.set("OK");
            return;
        }

        result1.set("");
        status.set("Bad input");
    }

    public void add2() {
        String key = key2Property().get();

        if (validNumber(key)) {
            heap2.add(Integer.parseInt(key2Property().get()), value2Property().get());
            status.set("OK");
            return;
        }

        result2.set("");
        status.set("Bad input");
    }

    public void delete1() {
        String key = key1Property().get();

        if (validNumber(key)) {
            HeapNode<String> result = heap1.extractElementWithKey(
                    Integer.parseInt(key1Property().get()));

            if (result == null) {
                result1.set(String.format("Heap not contain elements with key '%s'", key));
            } else {
                result1.set(result.toString());
            }
            status.set("OK");
            return;
        }

        result1.set("");
        status.set("Bad input");
    }

    public void delete2() {
        String key = key2Property().get();

        if (validNumber(key)) {
            HeapNode<String> result = heap2.extractElementWithKey(
                    Integer.parseInt(key2Property().get()));

            if (result == null) {
                result2.set(String.format("Heap not contain elements with key '%s'", key));
            } else {
                result2.set(result.toString());
            }

            status.set("OK");
            return;
        }

        result1.set("");
        status.set("Bad input");
    }

    public void getMinimum1() {
        HeapNode<String> result = heap1.extractMin();
        if (result == null) {
            result1.set("Heap is empty");
        } else {
            result1.set(result.toString());
        }

        status.set("OK");
    }

    public void getMinimum2() {
        HeapNode<String> result = heap2.extractMin();
        if (result == null) {
            result2.set("Heap is empty");
        } else {
            result2.set(result.toString());
        }

        status.set("OK");
    }

    public void merge1() {
        heap2.merge(heap1);

        result2.set("Merged with left heap");
        result1.set("Heap is empty");

        status.set("OK");
    }

    public void merge2() {
        heap1.merge(heap2);

        result1.set("Merged with right heap");
        result2.set("Heap is empty");

        status.set("OK");
    }

    public void decreaseKey1() {
        String key = key1Property().get();
        String newKey = newKey1Property().get();

        if (validNumber(key) && validNumber(newKey)) {
            try {
                if (heap1.decreaseKey(Integer.parseInt(key), Integer.parseInt(newKey))) {
                    result1.set("Key decreased");
                    status.set("OK");
                    return;
                } else {
                    result1.set("Key not found");
                    status.set("OK");
                    return;
                }
            } catch (IllegalArgumentException exception) {
                result1.set("New key must be less than current");
                status.set("OK");
                return;
            }
        }

        result1.set("");
        status.set("Bad input");
    }

    public void decreaseKey2() {
        String key = key2Property().get();
        String newKey = newKey2Property().get();

        if (validNumber(key) && validNumber(newKey)) {
            try {
                if (heap2.decreaseKey(Integer.parseInt(key), Integer.parseInt(newKey))) {
                    result2.set("Key decreased");
                    status.set("OK");
                    return;
                } else {
                    result2.set("Key not found");
                    status.set("OK");
                    return;
                }
            } catch (IllegalArgumentException exception) {
                result2.set("New key must be less than current");
                status.set("OK");
                return;
            }
        }

        result2.set("");
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
