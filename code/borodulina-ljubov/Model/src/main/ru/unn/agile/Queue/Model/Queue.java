package ru.unn.agile.Queue.Model;

public class Queue<T> {
    private T first;

    public boolean isEmpty() {
        return first == null;
    }

    public void add(T item) {
        first = item;
    }

    public T element() {
        return first;
    }
}
