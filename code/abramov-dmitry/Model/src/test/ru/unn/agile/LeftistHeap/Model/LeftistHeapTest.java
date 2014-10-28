package ru.unn.agile.LeftistHeap.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class LeftistHeapTest {

    @Test
    public void canCreateLeftistHeap() {
        LeftistHeap heap = new LeftistHeap<String>();
        assertNotNull(heap);
    }

    @Test
    public void canCreateEmptyHeap() {
        LeftistHeap heap = new LeftistHeap<String>();

        boolean isEmpty = heap.isEmpty();

        assertTrue(isEmpty);
    }

    @Test
    public void canAddElementToHeap() {
        LeftistHeap heap = new LeftistHeap<String>();

        heap.add(1, "I am first");

        assertFalse(heap.isEmpty());
    }

    @Test
    public void canExtractMinFromHeap() {
        LeftistHeap heap = new LeftistHeap<String>();
        heap.add(13, "I wait a fortune");

        HeapNode<String> nodeWithMinKey = heap.extractMin();

        assertNotNull(nodeWithMinKey);
    }

    @Test
    public void canExtractMinFromHeapWithFewElements() {
        LeftistHeap heap = new LeftistHeap<String>();
        heap.add(5, "I am only second");
        heap.add(3, "I am first");

        assertEquals(heap.extractMin().getKey(), 3);
    }

    @Test
    public void canExtractAllElements() {
        LeftistHeap heap = new LeftistHeap<String>();
        heap.add(0, "I am lonely");

        HeapNode<String> nodeWithMinKey = heap.extractMin();

        assertTrue(heap.isEmpty());
    }

    @Test
    public void canExtractNotAllElements() {
        LeftistHeap heap = new LeftistHeap<String>();
        heap.add(-3, "I so lonely");
        heap.add(2, "No-o-o!!!");

        HeapNode<String> nodeWithMinKey = heap.extractMin();

        assertFalse(heap.isEmpty());
    }

    @Test
    public void canExtractMinFromEmptyHeap() {
        LeftistHeap heap = new LeftistHeap<String>();

        HeapNode<String> nodeWithMinKey = heap.extractMin();

        assertNull(nodeWithMinKey);
    }

    @Test
    public void canMergeTwoHeaps() {
        LeftistHeap heap1 = new LeftistHeap<String>();
        heap1.add(1, "I am the best");
        heap1.add(4, "It was close");
        heap1.add(5, "Top five");

        LeftistHeap heap2 = new LeftistHeap<String>();
        heap2.add(2, "2 slow for win");
        heap2.add(3, "It is good");
        heap2.add(6, "My imagine have limit");

        heap1.merge(heap2);

        assertEquals(heap1.extractMin().getKey(), 1);
        assertEquals(heap1.extractMin().getKey(), 2);
        assertEquals(heap1.extractMin().getKey(), 3);
        assertEquals(heap1.extractMin().getKey(), 4);
        assertEquals(heap1.extractMin().getKey(), 5);
        assertEquals(heap1.extractMin().getKey(), 6);
    }

    @Test
    public void canMergeWithEmptyHeap() {
        LeftistHeap heap1 = new LeftistHeap<String>();
        heap1.add(0, "Stop");
        heap1.add(2, "Copy");
        heap1.add(5, "Paste");

        LeftistHeap heap2 = new LeftistHeap<String>();

        heap1.merge(heap2);

        assertEquals(heap1.extractMin().getKey(), 0);
        assertEquals(heap1.extractMin().getKey(), 2);
        assertEquals(heap1.extractMin().getKey(), 5);
    }

    @Test
    public void canMergeToEmptyHeap() {
        LeftistHeap heap1 = new LeftistHeap<String>();

        LeftistHeap heap2 = new LeftistHeap<String>();
        heap2.add(24, "x");
        heap2.add(25, "y");
        heap2.add(26, "z");

        heap1.merge(heap2);

        assertEquals(heap1.extractMin().getKey(), 24);
        assertEquals(heap1.extractMin().getKey(), 25);
        assertEquals(heap1.extractMin().getKey(), 26);
    }
}
