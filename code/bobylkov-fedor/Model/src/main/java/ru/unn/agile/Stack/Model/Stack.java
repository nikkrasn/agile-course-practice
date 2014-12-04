package ru.unn.agile.Stack.Model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class Stack<T> {
    private final ListProperty<T> elementsProperty = new SimpleListProperty<>();

    public Stack() {
        elementsProperty.set(FXCollections.observableList(new ArrayList<>()));
    }

    public Stack(final Stack<T> otherStack) {
        if (otherStack == null) {
            throw new IllegalArgumentException(
                    "Cannot create stack from stack: argument is null.");
        }

        elementsProperty.set(FXCollections.observableList(otherStack.toList()));
    }

    public Stack(final List<T> list) {
        if (list == null) {
            throw new IllegalArgumentException(
                    "Cannot create stack from list: argument is null.");
        }

        elementsProperty.set(FXCollections.observableList(list));
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T top() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return elements().get(lastElementIndex());
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return elements().remove(lastElementIndex());
    }

    public void push(final T element) {
        elements().add(element);
    }

    public List<T> toList() {
        return new ArrayList<>(elements());
    }

    //TBD: add tests for listeners
    public void addListener(final ListChangeListener<T> listener) {
        elementsProperty.addListener(listener);
    }

    public void removeListener(final ListChangeListener<T> listener) {
        elementsProperty.removeListener(listener);
    }

    private List<T> elements() {
        return elementsProperty.get();
    }

    private int size() {
        return elements().size();
    }

    private int lastElementIndex() {
        return size() - 1;
    }
}
