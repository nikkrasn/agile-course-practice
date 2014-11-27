package ru.unn.agile.LeftistHeap.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.unn.agile.LeftistHeap.Model.*;

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
            heap1.add(Integer.parseInt(key2Property().get()), value2Property().get());
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
        result1.set("Now is empty");

        status.set("OK");
    }

    public void merge2() {
        heap1.merge(heap2);

        result1.set("Merged with right heap");
        result2.set("Now is empty");

        status.set("OK");
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
}
