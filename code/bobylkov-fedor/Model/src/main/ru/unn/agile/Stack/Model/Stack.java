package ru.unn.agile.Stack.Model;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class Stack<T> {
    private final List<T> elements;

    public Stack() {
        elements = new ArrayList<>();
    }

    public boolean isEmpty() {
        return stackSize() == 0;
    }

    public T top() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }

        return elements.get(lastElementIndex());
    }

    public T pop() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }

        return elements.remove(lastElementIndex());
    }

    public void push(final T element) {
        elements.add(element);
    }

    private int stackSize() {
        return elements.size();
    }

    private int lastElementIndex() {
        return stackSize() - 1;
    }
}
