package ru.unn.agile.LeftistHeap.Model;

public class HeapNode<TValue> {
    private int key;
    private final TValue value;

    private int distValue;

    private HeapNode<TValue> leftChild;
    private HeapNode<TValue> rightChild;

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

    public void setKey(final int value) {
        key = value;
    }

    public TValue getValue() {
        return value;
    }

    public int getDistValue() {
        return distValue;
    }

    public void setDistValue(final int value) {
        distValue = value;
    }

    public HeapNode<TValue> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(final HeapNode<TValue> value) {
        leftChild = value;
    }

    public HeapNode<TValue> getRightChild() {
        return rightChild;
    }

    public void setRightChild(final HeapNode<TValue> value) {
        rightChild = value;
    }
}
