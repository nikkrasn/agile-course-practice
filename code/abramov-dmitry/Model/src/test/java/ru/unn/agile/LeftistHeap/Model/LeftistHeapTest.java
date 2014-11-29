package ru.unn.agile.LeftistHeap.Model;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class LeftistHeapTest {

    @Test
    public void canCreateLeftistHeap() {
        LeftistHeap<String> heap = new LeftistHeap<String>();
        assertNotNull(heap);
        assertEquals("heap", heap.getName());
    }

    @Test
    public void canCreateLeftistHeapWithName() {
        LeftistHeap<String> heap = new LeftistHeap<String>("Best heap");
        assertNotNull(heap);
        assertEquals("Best heap", heap.getName());
    }

    @Test
    public void checkToStringOverride() {
        LeftistHeap<String> heap = new LeftistHeap<String>("I am heap");
        assertEquals("I am heap", heap.toString());
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

        assertEquals(3, heap.extractMin().getKey());
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

        assertArrayEquals(new int[] {1, 2, 3, 4, 5, 6}, getKeys(heap1));
    }

    @Test
    public void canMergeWithEmptyHeap() {
        LeftistHeap<String> heap1 = new LeftistHeap<String>();
        heap1.add(0, "Stop");
        heap1.add(2, "Copy");
        heap1.add(5, "Paste");

        LeftistHeap<String> heap2 = new LeftistHeap<String>();

        heap1.merge(heap2);

        assertArrayEquals(new int[] {0, 2, 5}, getKeys(heap1));
    }

    @Test
    public void canMergeToEmptyHeap() {
        LeftistHeap<String> heap1 = new LeftistHeap<String>();

        LeftistHeap<String> heap2 = new LeftistHeap<String>();
        heap2.add(24, "x");
        heap2.add(25, "y");
        heap2.add(26, "z");

        heap1.merge(heap2);

        assertArrayEquals(new int[] {24, 25, 26}, getKeys(heap1));
    }

    @Test
    public void canDecreaseKey() {
        LeftistHeap<String> heap = new LeftistHeap<String>();
        heap.add(3, "x");
        heap.add(5, "y");
        heap.add(7, "z");

        boolean result = heap.decreaseKey(7, 1);

        assertEquals(result, true);
        assertArrayEquals(new int[] {1, 3, 5}, getKeys(heap));
    }

    @Test
    public void canDecreaseNegativeKey() {
        LeftistHeap<String> heap = new LeftistHeap<String>();
        heap.add(-4, "x");
        heap.add(-6, "y");
        heap.add(-7, "z");

        boolean result = heap.decreaseKey(-6, -8);

        assertEquals(result, true);
        assertArrayEquals(new int[] {-8, -7, -4}, getKeys(heap));
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

        assertEquals(13, node.getKey());
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

        assertArrayEquals(new int[] {1, 2, 3}, getKeys(heap));
    }

    @Test
    public void canMergeWithEmptyHeapAsNull() {
        LeftistHeap<String> heap1 = new LeftistHeap<String>();
        heap1.add(0, "Stop");
        heap1.add(2, "Copy");
        heap1.add(5, "Paste");

        heap1.merge(null);

        assertArrayEquals(new int[] {0, 2, 5}, getKeys(heap1));
    }

    @Test
    public void canNotDecreaseKeyInEmpryHeap() {
        LeftistHeap<String> heap = new LeftistHeap<String>();

        boolean result = heap.decreaseKey(2, 1);

        assertFalse(result);
    }

    @Test
    public void canNotDecreaseKeyIfNewKeyLessThanAllKeysInHeap() {
        LeftistHeap<String> heap = new LeftistHeap<String>();
        heap.add(5, "five");

        boolean result = heap.decreaseKey(4, 1);

        assertFalse(result);
    }

    private <TValue> int[] getKeys(final LeftistHeap<TValue> heap) {
        List<Integer> listOfKeys = new ArrayList<Integer>();

        while (!heap.isEmpty()) {
            listOfKeys.add(heap.extractMin().getKey());
        }

        int[] arrayOfKeys = new int[listOfKeys.size()];
        for (int i = 0; i < listOfKeys.size(); i++) {
            arrayOfKeys[i] = listOfKeys.get(i);
        }

        return arrayOfKeys;
    }
}
