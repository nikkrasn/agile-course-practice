package ru.unn.agile.LeftistHeap.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class HeapNodeTest {
    @Test
    public void canCreateHeapNodeWithKeyAndValue() {
        HeapNode<String> node = new HeapNode<String>(1, "it is string");

        assertNotNull(node);
        assertEquals(1, node.getKey());
        assertEquals(new String("it is string"), node.getValue());
    }

    @Test
    public void canCreateHeapNodeWithoutChildren() {
        HeapNode<String> node = new HeapNode<String>(2, "I am alone");

        assertNull(node.leftChild);
        assertNull(node.rightChild);
    }

    @Test
    public void canCreateHeapNodeWithChildren() {
        HeapNode<String> node = new HeapNode<String>(
                5,
                "I have a large family",
                new HeapNode<String>(3, "I am son"),
                new HeapNode<String>(4, "I am daughter"));

        assertNotNull(node.leftChild);
        assertNotNull(node.rightChild);
    }

    @Test
    public void canSetChild() {
        HeapNode<String> node = new HeapNode<String>(1, "I am alone");

        HeapNode<String> companion = new HeapNode<String>(2, "You never walk alone");

        node.leftChild = companion;

        assertNotNull(node.leftChild);
        assertNull(node.rightChild);
    }
}
