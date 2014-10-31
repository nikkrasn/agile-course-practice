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

        HeapNode<TValue> subHeap = heapRoot;
        Stack<HeapNode<TValue>> stack = new Stack<HeapNode<TValue>>();

        int decreaseOfDist = 0;

        if (subHeap.getKey() != key) {
            stack.push(subHeap);
            while (!stack.empty()) {
                if (subHeap.getRightChild() != null && subHeap.getRightChild().getKey() <= key) {
                    subHeap = subHeap.getRightChild();

                    if (subHeap.getKey() == key) {
                        decreaseOfDist = stack.peek().getDistValue();
                        stack.peek().setRightChild(null);
                        break;
                    }
                } else {
                    HeapNode<TValue> peek = stack.peek();
                    if (peek.getLeftChild() != null && peek.getLeftChild().getKey() <= key) {
                        subHeap = peek.getLeftChild();
                    }

                    if (subHeap.getKey() < key) {
                        stack.pop();
                    }

                    if (subHeap.getKey() == key) {
                        decreaseOfDist = peek.getDistValue();
                        peek.setLeftChild(null);
                        break;
                    }
                }
            }
        }

        HeapNode<TValue> curentNode;

        if (subHeap.getKey() == key) {
            while (!stack.empty()) {
                curentNode = stack.pop();
                curentNode.setDistValue(curentNode.getDistValue() - decreaseOfDist);
                if (curentNode.getLeftChild() == null || curentNode.getRightChild() == null
                        || curentNode.getLeftChild().getDistValue()
                        < curentNode.getRightChild().getDistValue()) {
                    swapChildren(curentNode);
                }
            }

            subHeap.setKey(newValue);
            heapRoot = innerMerge(heapRoot, subHeap);
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

    private HeapNode<TValue> findByKey(final int key, final HeapNode<TValue> root) {
        if (root == null) {
            return null;
        }

        if (root.getKey() == key) {
            return root;
        }

        HeapNode<TValue> result = null;

        if (root.getRightChild() != null && root.getRightChild().getKey() < key) {
            result = findByKey(key, root.getRightChild());
        }


         if (result == null && root.getLeftChild() != null && root.getLeftChild().getKey() < key) {
             result = findByKey(key, root.getLeftChild());
         }

        return result;
    }

    private void swapChildren(final HeapNode<TValue> node) {
        HeapNode<TValue> tmp = node.getLeftChild();
        node.setLeftChild(node.getRightChild());
        node.setRightChild(tmp);
    }
}
