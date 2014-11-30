package ru.unn.agile.LeftistHeap.Model;

import java.util.Stack;

public class LeftistHeap<TValue> {
    private final String name;

    private HeapNode<TValue> heapRoot;

    public LeftistHeap() {
        name = "heap";
    }

    public LeftistHeap(final String heapName) {
        name = heapName;
    }

    @Override
    public String toString() {
        return getName();
    }

    public boolean isEmpty() {
        return heapRoot == null;
    }

    public String getName() {
        return name;
    }

    public void add(final int key, final TValue value) {
        HeapNode<TValue> newNode = new HeapNode<TValue>(key, value);

        heapRoot = innerMerge(heapRoot, newNode);
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
        if (heapToMerge != null) {
            heapRoot = innerMerge(heapRoot, heapToMerge.heapRoot);
            heapToMerge.heapRoot = null;
        }
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
            final int decreaseOfDist) {
        HeapNode<TValue> currentNode;

        while (!path.empty()) {
            currentNode = path.pop();
            currentNode.setDistValue(currentNode.getDistValue() - decreaseOfDist);
            if (isNeedToSwapChildren(currentNode)) {
                swapChildren(currentNode);
            }
        }
    }

    private boolean isNeedToSwapChildren(final HeapNode<TValue> node) {
        if (node.getLeftChild() == null) {
            return true;
        }

        if (node.getRightChild() == null) {
            return false;
        }

        if (node.getLeftChild().getDistValue()
            < node.getRightChild().getDistValue()) {
            return true;
        }

        return false;
    }

    private HeapNode<TValue> cutSubHeapByRootKey(
            final HeapNode<TValue> root,
            final int key,
            final Stack<HeapNode<TValue>> path) {
        if (heapRoot == null) {
            return null;
        }

        HeapNode<TValue> nodeToDecreaseKey = heapRoot;

        if (nodeToDecreaseKey.getKey() != key) {
            path.push(nodeToDecreaseKey);
            while (!path.empty()) {
                if (nodeToDecreaseKey.getKey() > key) {
                    return null;
                }

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
            if (isNeedToSwapChildren(leftHeapRootTmp)) {
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
