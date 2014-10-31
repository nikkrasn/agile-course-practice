package ru.unn.agile.Stack.Model;

import java.util.EmptyStackException;

public class Stack<T> {
    public boolean isEmpty() {
        return true;
    }

    public T top() {
        throw new EmptyStackException();
    }

    public T pop() {
        throw new EmptyStackException();
    }
}
