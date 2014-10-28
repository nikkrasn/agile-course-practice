package ru.unn.agile.LeftistHeap.Model;

public class LeftistHeap<TValue> {
    private HeapNode<TValue> root;

    public LeftistHeap() {
    }

    public LeftistHeap(int rootKey, TValue rootValue) {
        root = new HeapNode<TValue>(rootKey, rootValue);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void add(int key, TValue value) {
        LeftistHeap<TValue> heap = new LeftistHeap<TValue>(key, value);

        merge(heap);
    }

    public HeapNode<TValue> extractMin() {
        if (isEmpty()) {
            return root;
        }

        HeapNode<TValue> min = root;

        root = innerMerge(root.leftChild, root.rightChild);

        min.leftChild = min.rightChild = null;
        return min;
    }

    public void merge(LeftistHeap<TValue> heapToMerge) {
        root = innerMerge(root, heapToMerge.root);
        heapToMerge.root = null;
    }

    private HeapNode<TValue> innerMerge(
            HeapNode<TValue> leftHeapRoot,
            HeapNode<TValue> rightHeapRoot){
        if (leftHeapRoot == null) {
            return rightHeapRoot;
        }

        if (rightHeapRoot == null) {
            return leftHeapRoot;
        }

        if (leftHeapRoot.getKey() > rightHeapRoot.getKey()) {
            HeapNode<TValue> temp = leftHeapRoot;
            leftHeapRoot = rightHeapRoot;
            rightHeapRoot = temp;
        }

        leftHeapRoot.rightChild = innerMerge(leftHeapRoot.rightChild, rightHeapRoot);

        if (leftHeapRoot.leftChild == null) {
            leftHeapRoot.leftChild = leftHeapRoot.rightChild;
            leftHeapRoot.rightChild = null;
        }
        else {
            if (leftHeapRoot.leftChild.distValue < leftHeapRoot.rightChild.distValue) {
                HeapNode<TValue> temp = leftHeapRoot.leftChild;
                leftHeapRoot.leftChild = leftHeapRoot.rightChild;
                leftHeapRoot.rightChild = temp;
            }

            leftHeapRoot.distValue = leftHeapRoot.rightChild.distValue + 1;
        }

        return leftHeapRoot;
    }
}