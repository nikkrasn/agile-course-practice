package ru.unn.agile.LeftistHeap.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class HeapNodeTest {
    @Test
    public void canCreateHeapNodeWithKeyAndValue() {
        HeapNode<Integer, String> node = new HeapNode<Integer, String>(1, "it is string");

        assertNotNull(node);
        assertEquals(new Integer(1), node.GetKey());
        assertEquals(new String("it is string"), node.GetValue());
    }

    @Test
    public void canCreateHeapNodeWithoutChildrens() {
        HeapNode<Integer, String> node = new HeapNode<Integer, String>(2, "I am alone");

        assertNull(node.LeftChild);
        assertNull(node.RightChild);
    }

    @Test
    public void canCreateHeapNodeWithChilds() {
        HeapNode<Integer, String> node = new HeapNode<Integer, String>(
                5,
                "I have a large family",
                new HeapNode<Integer, String>(3, "I am son"),
                new HeapNode<Integer, String>(4, "I am daughter"));

        assertNotNull(node.LeftChild);
        assertNotNull(node.RightChild);
    }
}
