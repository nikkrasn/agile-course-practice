package ru.unn.agile.LeftistHeap.Model;

public class LeftistHeap<TValue> {
    private HeapNode<TValue> root;

    public LeftistHeap() {
    }

    public LeftistHeap(final int rootKey, final TValue rootValue) {
        root = new HeapNode<TValue>(rootKey, rootValue);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void add(final int key, final TValue value) {
        LeftistHeap<TValue> heap = new LeftistHeap<TValue>(key, value);

        merge(heap);
    }

    public HeapNode<TValue> extractMin() {
        if (isEmpty()) {
            return root;
        }

        HeapNode<TValue> min = root;

        root = innerMerge(root.leftChild, root.rightChild);

        min.leftChild = null;
        min.rightChild = null;
        return min;
    }

    public void merge(final LeftistHeap<TValue> heapToMerge) {
        root = innerMerge(root, heapToMerge.root);
        heapToMerge.root = null;
    }

    private HeapNode<TValue> innerMerge(
            final HeapNode<TValue> leftHeapRoot,
            final HeapNode<TValue> rightHeapRoot) {
        if (leftHeapRoot == null) {
            return rightHeapRoot;
        }

        if (rightHeapRoot == null) {
            return leftHeapRoot;
        }

        HeapNode<TValue> leftHeapRootTmp = leftHeapRoot;
        HeapNode<TValue> rightHeapRootTmp = rightHeapRoot;

        if (leftHeapRoot.getKey() > rightHeapRoot.getKey()) {
            HeapNode<TValue> tmp = leftHeapRootTmp;
            leftHeapRootTmp = rightHeapRoot;
            rightHeapRootTmp = tmp;
        }

        leftHeapRootTmp.rightChild = innerMerge(leftHeapRootTmp.rightChild, rightHeapRootTmp);

        if (leftHeapRootTmp.leftChild == null) {
            leftHeapRootTmp.leftChild = leftHeapRootTmp.rightChild;
            leftHeapRootTmp.rightChild = null;
        } else {
            if (leftHeapRootTmp.leftChild.distValue < leftHeapRootTmp.rightChild.distValue) {
                HeapNode<TValue> temp = leftHeapRootTmp.leftChild;
                leftHeapRootTmp.leftChild = leftHeapRootTmp.rightChild;
                leftHeapRootTmp.rightChild = temp;
            }

            leftHeapRootTmp.distValue = leftHeapRootTmp.rightChild.distValue + 1;
        }

        return leftHeapRootTmp;
    }
}
