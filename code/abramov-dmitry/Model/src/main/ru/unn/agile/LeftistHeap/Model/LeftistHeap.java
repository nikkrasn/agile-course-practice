package ru.unn.agile.LeftistHeap.Model;

public class LeftistHeap<TValue> {

    private boolean isEmpty = true;

    public boolean IsEmpty() {
        return isEmpty;
    }

    public void Add(int key, TValue value) {
        isEmpty = false;
    }
}
