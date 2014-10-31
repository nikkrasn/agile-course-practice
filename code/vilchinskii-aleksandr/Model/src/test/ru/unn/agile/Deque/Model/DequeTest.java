package ru.unn.agile.Deque.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.*;

public class DequeTest {
    private Deque<Integer> deque;

    @Before
    public void setUp() {
        deque = Deque.create();
    }

    @After
    public void tearDown() {
        deque = null;
    }

    @Test
    public void isNewlyCreatedDequeEmpty() {
        assertTrue(deque.isEmpty());
    }

    @Test
    public void isDequeNotEmptyAfterAddFirst() {
        deque.addFirst(1);
        assertFalse(deque.isEmpty());
    }

    @Test
    public void isDequeNotEmptyAfterAddLast() {
        deque.addLast(1);
        assertFalse(deque.isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void cannotAddNullItemToHead() {
        deque.addFirst(null);
    }

    @Test(expected = NullPointerException.class)
    public void cannotAddNullItemToTail() {
        deque.addLast(null);
    }

    @Test
    public void canAddItemToHead() {
        int itemToAdd = 1, firstInDeque = 0;
        deque.addFirst(itemToAdd);

        firstInDeque = deque.getFirst();

        assertEquals(itemToAdd, firstInDeque);
    }

    @Test
    public void canAddItemToTail() {
        int itemToAdd = 1, lastInDeque = 0;
        deque.addLast(itemToAdd);

        lastInDeque = deque.getLast();

        assertEquals(itemToAdd, lastInDeque);
    }

    @Test
    public void isTheOnlyTailItemTheFirstOne() {
        int itemToAdd = 1, firstInDeque = 0;
        deque.addLast(itemToAdd);

        firstInDeque = deque.getFirst();

        assertEquals(itemToAdd, firstInDeque);
    }

    @Test
    public void isTheOnlyHeadItemTheLastOne() {
        int itemToAdd = 1, lastInDeque = 0;
        deque.addLast(itemToAdd);

        lastInDeque = deque.getLast();

        assertEquals(itemToAdd, lastInDeque);
    }

    @Test
    public void cannotGetFirstItemFromEmptyDeque() {
        Object item = deque.getFirst();
        assertNull(item);
    }

    @Test
    public void cannotGetLastItemFromEmptyDeque() {
        Object item = deque.getLast();
        assertNull(item);
    }

    @Test
    public void isRemovedFirstItemEqualToRecentlyAdded() {
        int itemToAdd = 1, firstInDeque = 0;
        deque.addFirst(itemToAdd);

        firstInDeque = deque.removeFirst();

        assertEquals(itemToAdd, firstInDeque);
    }

    @Test
    public void isRemovedLastItemEqualToRecentlyAdded() {
        int itemToAdd = 1, lastInDeque = 0;
        deque.addLast(itemToAdd);

        lastInDeque = deque.removeLast();

        assertEquals(itemToAdd, lastInDeque);
    }

    @Test
    public void cannotRemoveFirstItemFromEmptyDeque() {
        Object item = deque.removeFirst();
        assertNull(item);
    }

    @Test
    public void cannotRemoveLastItemFromEmptyDeque() {
        Object item = deque.removeLast();
        assertNull(item);
    }

    @Test
    public void isDequeEmptyAfterRemoveFirst() {
        deque.addFirst(1);

        deque.removeFirst();

        assertTrue(deque.isEmpty());
    }

    @Test
    public void isDequeEmptyAfterRemoveLast() {
        deque.addLast(1);

        deque.removeLast();

        assertTrue(deque.isEmpty());
    }

    @Test
    public void areItemsAddedToTailEqualToItemsRemoveFromHead() {
        int[] itemsToAdd        = {0, 1, 2};
        int[] itemsFromDeque    = {0, 1, 2};
        int i = 0;
        addIntsFromArrayToDequeTail(deque, itemsToAdd);

        while (!deque.isEmpty()) {
            itemsFromDeque[i++] = deque.removeFirst();
        }

        assertTrue(Arrays.equals(itemsToAdd, itemsFromDeque));
    }

    @Test
    public void areItemsAddedToHeadEqualToItemsRemoveFromTail() {
        int[] itemsToAdd        = {0, 1, 2};
        int[] itemsFromDeque    = {0, 1, 2};
        int i = 0;
        addIntsFromArrayToDequeHead(deque, itemsToAdd);

        while (!deque.isEmpty()) {
            itemsFromDeque[i++] = deque.removeLast();
        }

        assertTrue(Arrays.equals(itemsToAdd, itemsFromDeque));
    }

    @Test
    public void canClearNotEmptyDeque() {
        int[] itemsToAdd        = {0, 1, 2};
        addIntsFromArrayToDequeHead(deque, itemsToAdd);

        deque.clear();

        assertTrue(deque.isEmpty());
    }

    private void addIntsFromArrayToDequeTail(final Deque<Integer> deque, final int[] itemsToAdd) {
        for (int item: itemsToAdd) {
            deque.addLast(item);
        }
    }

    private void addIntsFromArrayToDequeHead(final Deque<Integer> deque, final int[] itemsToAdd) {
        for (int item: itemsToAdd) {
            deque.addFirst(item);
        }
    }
}
