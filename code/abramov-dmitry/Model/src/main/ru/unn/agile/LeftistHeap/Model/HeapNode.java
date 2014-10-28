package ru.unn.agile.LeftistHeap.Model;

public class HeapNode<TValue> {
    private int key;
    private TValue value;

    public int distValue;

    public HeapNode<TValue> leftChild;
    public HeapNode<TValue> rightChild;

    public HeapNode(final int key, final TValue value) {
        this.key = key;
        this.value = value;
        this.distValue = 0;
    }

    public HeapNode(
            final int key,
            final TValue value,
            final HeapNode<TValue> leftChild,
            final HeapNode<TValue> rightChild) {
        this.key = key;
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.distValue = 0;
    }

    public int getKey() {
        return key;
    }

    public TValue getValue() {
        return value;
    }
}
