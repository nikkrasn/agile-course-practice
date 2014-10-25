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

        Node newHead = new Node();
        newHead.item = itemToAdd;

        first = newHead;
        if (last == null) {
            last = newHead;
        }
        ++size;
    }

    public void addLast(final Item itemToAdd) {
        if (itemToAdd == null) {
            throw new NullPointerException();
        }

        Node newTail = new Node();
        newTail.item = itemToAdd;

        if (first == null) {
            first = newTail;
        }
        last = newTail;
        ++size;
    }

    public Item getFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        return first.item;
    }

    public Item getLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        return last.item;
    }
}
