package ru.unn.agile.LeftistHeap.Model;

import java.util.Stack;

public class LeftistHeap<TValue> {
    private HeapNode<TValue> heapRoot;

    public LeftistHeap() {
        // This constructor is intentionally empty. Nothing special is needed here.
    }

    public LeftistHeap(final int rootKey, final TValue rootValue) {
        heapRoot = new HeapNode<TValue>(rootKey, rootValue);
    }

    public boolean isEmpty() {
        return heapRoot == null;
    }

    public void add(final int key, final TValue value) {
        LeftistHeap<TValue> heap = new LeftistHeap<TValue>(key, value);

        merge(heap);
    }

    public HeapNode<TValue> extractMin() {
        if (isEmpty()) {
            return heapRoot;
        }

        HeapNode<TValue> min = heapRoot;

        heapRoot = innerMerge(heapRoot.getLeftChild(), heapRoot.getRightChild());

        min.setLeftChild(null);
        min.setRightChild(null);
        return min;
    }

    public void merge(final LeftistHeap<TValue> heapToMerge) {
        heapRoot = innerMerge(heapRoot, heapToMerge.heapRoot);
        heapToMerge.heapRoot = null;
    }

    public boolean decreaseKey(final int key, final int newValue) {
        if (key < newValue) {
            throw new IllegalArgumentException("Can not change key to larger value");
        }

        Stack<HeapNode<TValue>> path = new Stack<HeapNode<TValue>>();
        HeapNode<TValue> nodeToDecreaseKey = cutSubHeapByRootKey(heapRoot, key, path);

        if (nodeToDecreaseKey != null) {
            int decreaseOfDist = nodeToDecreaseKey.getDistValue();
            updateDistOnPath(path, decreaseOfDist);

            nodeToDecreaseKey.setKey(newValue);
            heapRoot = innerMerge(heapRoot, nodeToDecreaseKey);
            return true;
        }

        return false;
    }

    public HeapNode<TValue> extractElementWithKey(final int key) {
        if (decreaseKey(key, Integer.MIN_VALUE)) {
            HeapNode<TValue> min = extractMin();
            min.setKey(key);
            return min;
        }

        return null;
    }

    private void updateDistOnPath(
        final Stack<HeapNode<TValue>> path,
        final int decreaseOfDist
    ) {
        HeapNode<TValue> currentNode;

        while (!path.empty()) {
            currentNode = path.pop();
            currentNode.setDistValue(currentNode.getDistValue() - decreaseOfDist);
            if (currentNode.getLeftChild() == null || currentNode.getRightChild() == null
                    || currentNode.getLeftChild().getDistValue()
                    < currentNode.getRightChild().getDistValue()) {
                swapChildren(currentNode);
            }
        }
    }

    private HeapNode<TValue> cutSubHeapByRootKey(
            final HeapNode<TValue> root,
            final int key,
            final Stack<HeapNode<TValue>> path) {
        HeapNode<TValue> nodeToDecreaseKey = heapRoot;

        if (nodeToDecreaseKey.getKey() != key) {
            path.push(nodeToDecreaseKey);
            while (!path.empty()) {
                if (nodeToDecreaseKey.getRightChild() != null
                        && nodeToDecreaseKey.getRightChild().getKey() <= key) {
                    nodeToDecreaseKey = nodeToDecreaseKey.getRightChild();

                    if (nodeToDecreaseKey.getKey() == key) {
                        path.peek().setRightChild(null);
                        break;
                    }
                } else {
                    HeapNode<TValue> peek = path.peek();
                    if (peek.getLeftChild() != null && peek.getLeftChild().getKey() <= key) {
                        nodeToDecreaseKey = peek.getLeftChild();
                    }

                    if (nodeToDecreaseKey.getKey() < key) {
                        path.pop();
                    }

                    if (nodeToDecreaseKey.getKey() == key) {
                        peek.setLeftChild(null);
                        break;
                    }
                }
            }
        }

        if (nodeToDecreaseKey.getKey() == key) {
            return nodeToDecreaseKey;
        } else {
            return null;
        }
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
                swapChildren(leftHeapRootTmp);
            }

            leftHeapRootTmp.setDistValue(leftHeapRootTmp.getRightChild().getDistValue() + 1);
        }

        return leftHeapRootTmp;
    }

    private void swapChildren(final HeapNode<TValue> node) {
        HeapNode<TValue> tmp = node.getLeftChild();
        node.setLeftChild(node.getRightChild());
        node.setRightChild(tmp);
    }
}
