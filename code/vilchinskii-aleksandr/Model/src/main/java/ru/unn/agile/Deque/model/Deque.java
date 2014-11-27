package ru.unn.agile.Deque.model;

import java.util.NoSuchElementException;

public final class Deque<Item> {
    private Node first;
    private Node last;

    private Deque() {
    }

    public static <Item> Deque<Item> create() {
        return new Deque<Item>();
    }

    private class Node {
        private Item item;
        private Node prev;
        private Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void addFirst(final Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.prev = null;
        first.next = oldFirst;

        if (last == null) {
            last = first;
        } else {
            oldFirst.prev = first;
        }
    }

    public void addLast(final Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        Node oldLast = last;
        last      = new Node();
        last.item = item;
        last.prev = oldLast;
        last.next = null;

        if (first == null) {
            first = last;
        } else {
            oldLast.next = last;
        }
    }

    public Item getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return first.item;
    }

    public Item getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return last.item;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item itemToReturn = first.item;
        if (first.equals(last)) {
            first = null;
            last  = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        return itemToReturn;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item itemToReturn = last.item;
        if (first.equals(last)) {
            first = null;
            last  = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        return itemToReturn;
    }

    public Item peekFirst() {
        if (isEmpty()) {
            return null;
        }
        return first.item;
    }

    public Item peekLast() {
        if (isEmpty()) {
            return null;
        }
        return last.item;
    }

    public Item pollFirst() {
        if (isEmpty()) {
            return null;
        }

        Item itemToReturn = first.item;
        if (first.equals(last)) {
            first = null;
            last  = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        return itemToReturn;
    }

    public Item pollLast() {
        if (isEmpty()) {
            return null;
        }

        Item itemToReturn = last.item;
        if (first.equals(last)) {
            first = null;
            last  = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        return itemToReturn;
    }

    public void clear() {
        while (!isEmpty()) {
            if (first.equals(last)) {
                first = null;
                last  = null;
            } else {
                first = first.next;
                first.prev = null;
            }
        }
    }
}
