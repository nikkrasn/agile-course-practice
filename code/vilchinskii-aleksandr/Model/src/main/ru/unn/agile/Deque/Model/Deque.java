package ru.unn.agile.Deque.Model;

/**
 * Created by avilchin on 10/25/14.
 */

public class Deque<Item> {
    private int  size  = 0;

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(final Item itemToAdd) {
        if (itemToAdd == null) {
            throw new NullPointerException();
        }

        ++size;
    }
}
