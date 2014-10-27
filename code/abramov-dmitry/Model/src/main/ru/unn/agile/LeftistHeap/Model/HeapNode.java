package ru.unn.agile.LeftistHeap.Model;

public class HeapNode<TKey, TValue> {
    private TKey key;
    private TValue value;

    public HeapNode<TKey, TValue> LeftChild;
    public HeapNode<TKey, TValue> RightChild;

    public HeapNode(TKey key, TValue value) {
        this.key = key;
        this.value = value;
    }

    public HeapNode(TKey key, TValue value, HeapNode<TKey, TValue> leftChild, HeapNode<TKey, TValue> rightChild) {
        this.key = key;
        this.value = value;
        LeftChild = leftChild;
        RightChild = rightChild;
    }

    public TKey GetKey() {
        return key;
    }

    public TValue GetValue() {
        return value;
    }
}