package ru.unn.agile.Queue.model;

import java.util.NoSuchElementException;

public class Queue<T> {
    private Node first;

    private class Node {
        private T    item;
        private Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void add(final T item) {
        if (item == null) {
            throw new NullPointerException();
        }

        Node newLast = new Node();
        newLast.item = item;
        newLast.next = null;

        if (isEmpty()) {
            first = newLast;
        } else {
            Node last = getLast();
            last.next = newLast;
        }
    }

    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        T itemToReturn = first.item;
        first = first.next;
        return itemToReturn;
    }

    public T element() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return first.item;
    }

    public T poll() {
        if (isEmpty()) {
            return null;
        }

        T itemToReturn = first.item;
        first = first.next;
        return itemToReturn;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return first.item;
    }

    public void clear() {
        while (!isEmpty()) {
           first = first.next;
        }
    }

    private Node getLast() {
        Node temp = first;
        while (temp.next != null) {
            temp = temp.next;
        }
        return temp;
    }
}
