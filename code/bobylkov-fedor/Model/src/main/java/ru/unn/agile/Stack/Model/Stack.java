package ru.unn.agile.Stack.Model;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class Stack<T> {
    private final List<T> elements;

    public Stack() {
        elements = new ArrayList<>();
    }

    public Stack(final Stack<T> otherStack) {
        if (otherStack == null) {
            throw new IllegalArgumentException(
                    "Cannot create stack with copy-constructor: argument is null.");
        }

        elements = otherStack.toList();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T top() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return elements.get(lastElementIndex());
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return elements.remove(lastElementIndex());
    }

    public void push(final T element) {
        elements.add(element);
    }

    public List<T> toList() {
        return new ArrayList<>(elements);
    }

    private int size() {
        return elements.size();
    }

    private int lastElementIndex() {
        return size() - 1;
    }
}
