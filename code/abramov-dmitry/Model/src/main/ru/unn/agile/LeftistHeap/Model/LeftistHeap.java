package ru.unn.agile.LeftistHeap.Model;

public class LeftistHeap<TValue> {
    private HeapNode<TValue> root;

    public LeftistHeap() {
        // This constructor is intentionally empty. Nothing special is needed here.
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

        root = innerMerge(root.getLeftChild(), root.getRightChild());

        min.setLeftChild(null);
        min.setRightChild(null);
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

        leftHeapRootTmp.setRightChild(
                innerMerge(leftHeapRootTmp.getRightChild(), rightHeapRootTmp));

        if (leftHeapRootTmp.getLeftChild() == null) {
            leftHeapRootTmp.setLeftChild(leftHeapRootTmp.getRightChild());
            leftHeapRootTmp.setRightChild(null);
        } else {
            if (leftHeapRootTmp.getLeftChild().getDistValue()
                    < leftHeapRootTmp.getRightChild().getDistValue()) {
                HeapNode<TValue> temp = leftHeapRootTmp.getLeftChild();
                leftHeapRootTmp.setLeftChild(leftHeapRootTmp.getRightChild());
                leftHeapRootTmp.setRightChild(temp);
            }

            leftHeapRootTmp.setDistValue(leftHeapRootTmp.getRightChild().getDistValue() + 1);
        }

        return leftHeapRootTmp;
    }
}
