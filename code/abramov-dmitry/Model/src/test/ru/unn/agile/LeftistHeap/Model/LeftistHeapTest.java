package ru.unn.agile.LeftistHeap.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class LeftistHeapTest {

    @Test
    public void canCreateLeftistHeap() {
        LeftistHeap<String> heap = new LeftistHeap<String>();
        assertNotNull(heap);
    }

    @Test
    public void canCreateEmptyHeap() {
        LeftistHeap<String> heap = new LeftistHeap<String>();

        boolean isEmpty = heap.isEmpty();

        assertTrue(isEmpty);
    }

    @Test
    public void canAddElementToHeap() {
        LeftistHeap<String> heap = new LeftistHeap<String>();

        heap.add(1, new String("I am first"));

        assertFalse(heap.isEmpty());
    }

    @Test
    public void canExtractMinFromHeap() {
        LeftistHeap<String> heap = new LeftistHeap<String>();
        heap.add(13, "I wait a fortune");

        HeapNode<String> nodeWithMinKey = heap.extractMin();

        assertNotNull(nodeWithMinKey);
    }

    @Test
    public void canExtractMinFromHeapWithFewElements() {
        LeftistHeap<String> heap = new LeftistHeap<String>();
        heap.add(5, "I am only second");
        heap.add(3, "I am first");

        assertEquals(heap.extractMin().getKey(), 3);
    }

    @Test
    public void canExtractAllElements() {
        LeftistHeap<String> heap = new LeftistHeap<String>();
        heap.add(0, "I am lonely");

        HeapNode<String> nodeWithMinKey = heap.extractMin();

        assertTrue(heap.isEmpty());
    }

    @Test
    public void canExtractNotAllElements() {
        LeftistHeap<String> heap = new LeftistHeap<String>();
        heap.add(-3, "I so lonely");
        heap.add(2, "No-o-o!!!");

        HeapNode<String> nodeWithMinKey = heap.extractMin();

        assertFalse(heap.isEmpty());
    }

    @Test
    public void canExtractMinFromEmptyHeap() {
        LeftistHeap<String> heap = new LeftistHeap<String>();

        HeapNode<String> nodeWithMinKey = heap.extractMin();

        assertNull(nodeWithMinKey);
    }

    @Test
    public void canMergeTwoHeaps() {
        LeftistHeap<String> heap1 = new LeftistHeap<String>();
        heap1.add(1, "I am the best");
        heap1.add(4, "It was close");
        heap1.add(5, "Top five");

        LeftistHeap<String> heap2 = new LeftistHeap<String>();
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
        LeftistHeap<String> heap1 = new LeftistHeap<String>();
        heap1.add(0, "Stop");
        heap1.add(2, "Copy");
        heap1.add(5, "Paste");

        LeftistHeap<String> heap2 = new LeftistHeap<String>();

        heap1.merge(heap2);

        assertEquals(heap1.extractMin().getKey(), 0);
        assertEquals(heap1.extractMin().getKey(), 2);
        assertEquals(heap1.extractMin().getKey(), 5);
    }

    @Test
    public void canMergeToEmptyHeap() {
        LeftistHeap<String> heap1 = new LeftistHeap<String>();

        LeftistHeap<String> heap2 = new LeftistHeap<String>();
        heap2.add(24, "x");
        heap2.add(25, "y");
        heap2.add(26, "z");

        heap1.merge(heap2);

        assertEquals(heap1.extractMin().getKey(), 24);
        assertEquals(heap1.extractMin().getKey(), 25);
        assertEquals(heap1.extractMin().getKey(), 26);
    }

    @Test
    public void canDecreaseKey() {
        LeftistHeap<String> heap = new LeftistHeap<String>();
        heap.add(3, "x");
        heap.add(5, "y");
        heap.add(7, "z");

        boolean result = heap.decreaseKey(7, 1);

        assertEquals(result, true);
        assertEquals(heap.extractMin().getKey(), 1);
        assertEquals(heap.extractMin().getKey(), 3);
        assertEquals(heap.extractMin().getKey(), 5);
    }

    @Test
    public void canDecreaseNegativeKey() {
        LeftistHeap<String> heap = new LeftistHeap<String>();
        heap.add(-4, "x");
        heap.add(-6, "y");
        heap.add(-7, "z");

        boolean result = heap.decreaseKey(-6, -8);

        assertEquals(result, true);
        assertEquals(heap.extractMin().getKey(), -8);
        assertEquals(heap.extractMin().getKey(), -7);
        assertEquals(heap.extractMin().getKey(), -4);
    }

    @Test(expected = IllegalArgumentException.class)
     public void canNotChangeKeyToLarger() {
        LeftistHeap<String> heap = new LeftistHeap<String>();
        heap.add(-4, "x");

        heap.decreaseKey(-4, -3);
    }

    @Test
    public void canGetElementByKeyFromRoot() {
        LeftistHeap<String> heap = new LeftistHeap<String>();
        heap.add(13, "It's a kind of magic");

        HeapNode<String> node = heap.extractElementWithKey(13);

        assertNotNull(node);
    }

    @Test
    public void canGetElementByKey() {
        LeftistHeap<String> heap = new LeftistHeap<String>();
        heap.add(11, "a");
        heap.add(13, "b");
        heap.add(15, "c");

        HeapNode<String> node = heap.extractElementWithKey(13);

        assertEquals(node.getKey(), 13);
    }

    @Test
    public void canNotGetElementThatNotExist() {
        LeftistHeap<String> heap = new LeftistHeap<String>();
        heap.add(1, "Alone");

        HeapNode<String> node = heap.extractElementWithKey(4);

        assertNull(node);
    }

    @Test
    public void canNotGetElementThatNotExistFromHeapWithFewElements() {
        LeftistHeap<Integer> heap = new LeftistHeap<Integer>();
        heap.add(4, 4);
        heap.add(8, 8);
        heap.add(15, 15);
        heap.add(16, 16);
        heap.add(23, 23);
        heap.add(42, 42);

        HeapNode<Integer> node = heap.extractElementWithKey(17);

        assertNull(node);
    }

    @Test
    public void dontChangeHeapIfExtractElementThatNotExist() {
        LeftistHeap<String> heap = new LeftistHeap<String>();
        heap.add(1, "First");
        heap.add(2, "Second");
        heap.add(3, "Third");

        HeapNode<String> node = heap.extractElementWithKey(4);

        assertEquals(heap.extractMin().getKey(), 1);
        assertEquals(heap.extractMin().getKey(), 2);
        assertEquals(heap.extractMin().getKey(), 3);
    }
}
