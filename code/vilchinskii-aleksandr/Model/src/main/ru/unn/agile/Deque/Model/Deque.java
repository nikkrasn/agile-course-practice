package ru.unn.agile.Deque.Model;

import java.util.NoSuchElementException;

/**
 * Created by avilchin on 10/25/14.
 */

public class Deque<Item> {
    private Node first = null;
    private Node last  = null;
    private int  size  = 0;

    private class Node {
        private Item item;
        private Node prev;
        private Node next;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(final Item itemToAdd) {
        if (itemToAdd == null) {
            throw new NullPointerException();
        }

        Node oldFirst = first;
        first = new Node();
        first.item = itemToAdd;
        first.prev = null;
        first.next = oldFirst;

        if (last == null) {
            last = first;
        } else {
            oldFirst.prev = first;
        }
        ++size;
    }

    public void addLast(final Item itemToAdd) {
        if (itemToAdd == null) {
            throw new NullPointerException();
        }

        Node oldLast = last;
        last      = new Node();
        last.item = itemToAdd;
        last.prev = oldLast;
        last.next = null;

        if (first == null) {
            first = last;
        } else {
            oldLast.next = last;
        }

        ++size;
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
        first = first.next;

        --size;
        return itemToReturn;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item itemToReturn = last.item;
        last = last.prev;

        --size;
        return itemToReturn;
    }
}
