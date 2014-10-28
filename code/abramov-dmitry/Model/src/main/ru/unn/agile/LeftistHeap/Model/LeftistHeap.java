package ru.unn.agile.LeftistHeap.Model;

import java.util.Collection;
import java.util.List;

public class LeftistHeap<TValue> {
    private HeapNode<TValue> root;

    public LeftistHeap()
    {
    }

    public LeftistHeap(int rootKey, TValue rootValue)
    {
        root = new HeapNode<TValue>(rootKey, rootValue);
    }

    public boolean IsEmpty() {
        return root == null;
    }

    public void Add(int key, TValue value) {
        LeftistHeap<TValue> heap = new LeftistHeap<TValue>(key, value);

        Merge(heap);
    }

    public HeapNode<TValue> ExtractMin() {
        if (IsEmpty()){
            return root;
        }

        HeapNode<TValue> min = root;

        root = innerMerge(root.LeftChild, root.RightChild);

        min.LeftChild = min.RightChild = null;
        return min;
    }

    public void Merge(LeftistHeap<TValue> heapToMerge) {
        root = innerMerge(root, heapToMerge.root);
        heapToMerge.root = null;
    }

    private HeapNode<TValue> innerMerge(HeapNode<TValue> leftHeapRoot, HeapNode<TValue> rightHeapRoot){
        if (leftHeapRoot == null) {
            return rightHeapRoot;
        }

        if (rightHeapRoot == null) {
            return leftHeapRoot;
        }

        if (leftHeapRoot.GetKey() > rightHeapRoot.GetKey()) {
            HeapNode<TValue> temp = leftHeapRoot;
            leftHeapRoot = rightHeapRoot;
            rightHeapRoot = temp;
        }

        leftHeapRoot.RightChild = innerMerge(leftHeapRoot.RightChild, rightHeapRoot);

        if(leftHeapRoot.LeftChild == null)
        {
            leftHeapRoot.LeftChild = leftHeapRoot.RightChild;
            leftHeapRoot.RightChild = null;
        }
        else
        {
            if(leftHeapRoot.LeftChild.DistValue < leftHeapRoot.RightChild.DistValue)
            {
                HeapNode<TValue> temp = leftHeapRoot.LeftChild;
                leftHeapRoot.LeftChild = leftHeapRoot.RightChild;
                leftHeapRoot.RightChild = temp;
            }

            leftHeapRoot.DistValue = leftHeapRoot.RightChild.DistValue + 1;
        }

        return leftHeapRoot;
    }
}
