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

        boolean isEmpty = heap.IsEmpty();

        assertTrue(isEmpty);
    }

    @Test
    public void canAddElementToHeap() {
        LeftistHeap heap = new LeftistHeap<String>();

        heap.Add(1, "I am first");

        assertFalse(heap.IsEmpty());
    }

    @Test
    public void canExtractMinFromHeap() {
        LeftistHeap heap = new LeftistHeap<String>();
        heap.Add(1, "I am first");

        HeapNode<String> nodeWithMinKey = heap.ExtractMin();

        assertNotNull(nodeWithMinKey);
    }

    @Test
    public void canExtractMinFromHeapWithFewElements() {
        LeftistHeap heap = new LeftistHeap<String>();
        heap.Add(2, "I am only second");
        heap.Add(1, "I am first");

        assertEquals(heap.ExtractMin().GetKey(), 1);
    }

    @Test
    public void canExtractAllElements() {
        LeftistHeap heap = new LeftistHeap<String>();
        heap.Add(1, "I am lonely");

        HeapNode<String> nodeWithMinKey = heap.ExtractMin();

        assertTrue(heap.IsEmpty());
    }

    @Test
    public void canExtractNotAllElements() {
        LeftistHeap heap = new LeftistHeap<String>();
        heap.Add(1, "I am lonely");
        heap.Add(2, "No-o-o!!!");

        HeapNode<String> nodeWithMinKey = heap.ExtractMin();

        assertFalse(heap.IsEmpty());
    }

    @Test
    public void canExtractMinFromEmptyHeap() {
        LeftistHeap heap = new LeftistHeap<String>();

        HeapNode<String> nodeWithMinKey = heap.ExtractMin();

        assertNull(nodeWithMinKey);
    }

    @Test
    public void canMergeTwoHeaps() {
        LeftistHeap heap1 = new LeftistHeap<String>();
        heap1.Add(1, "I am first");
        heap1.Add(4, "It was close");
        heap1.Add(5, "Top five");

        LeftistHeap heap2 = new LeftistHeap<String>();
        heap2.Add(2, "2 slow for win");
        heap2.Add(3, "It is good");
        heap2.Add(6, "My imagine have limit");

        heap1.Merge(heap2);

        assertEquals(heap1.ExtractMin().GetKey(), 1);
        assertEquals(heap1.ExtractMin().GetKey(), 2);
        assertEquals(heap1.ExtractMin().GetKey(), 3);
        assertEquals(heap1.ExtractMin().GetKey(), 4);
        assertEquals(heap1.ExtractMin().GetKey(), 5);
        assertEquals(heap1.ExtractMin().GetKey(), 6);
    }

    @Test
    public void canMergeWithEmptyHeap() {
        LeftistHeap heap1 = new LeftistHeap<String>();
        heap1.Add(1, "I am first");
        heap1.Add(4, "It was close");
        heap1.Add(5, "Top five");

        LeftistHeap heap2 = new LeftistHeap<String>();

        heap1.Merge(heap2);

        assertEquals(heap1.ExtractMin().GetKey(), 1);
        assertEquals(heap1.ExtractMin().GetKey(), 4);
        assertEquals(heap1.ExtractMin().GetKey(), 5);
    }

    @Test
    public void canMergeToEmptyHeap() {
        LeftistHeap heap1 = new LeftistHeap<String>();

        LeftistHeap heap2 = new LeftistHeap<String>();
        heap2.Add(2, "2 slow for win");
        heap2.Add(3, "It is good");
        heap2.Add(6, "My imagine have limit");

        heap1.Merge(heap2);

        assertEquals(heap1.ExtractMin().GetKey(), 2);
        assertEquals(heap1.ExtractMin().GetKey(), 3);
        assertEquals(heap1.ExtractMin().GetKey(), 6);
    }
}
