package ru.unn.agile.Deque.Model;

import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.*;

public class DequeTest {

    @Test
    public void isNewlyCreatedDequeEmpty() {
        Deque emptyDeque = Deque.createDeque();
        assertTrue(emptyDeque.isEmpty());
    }

    @Test
    public void isDequeNotEmptyAfterAddFirst() {
        Deque<Integer> deque = Deque.createDeque();
        deque.addFirst(1);
        assertFalse(deque.isEmpty());
    }

    @Test
    public void isDequeNotEmptyAfterAddLast() {
        Deque<Integer> deque = Deque.createDeque();
        deque.addLast(1);
        assertFalse(deque.isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void cannotAddNullItemToHead() {
        Deque<Integer> deque = Deque.createDeque();
        deque.addFirst(null);
    }

    @Test(expected = NullPointerException.class)
    public void cannotAddNullItemToTail() {
        Deque<Integer> deque = Deque.createDeque();
        deque.addLast(null);
    }

    @Test
    public void canAddItemToHead() {
        Deque<Integer> deque = Deque.createDeque();
        int itemToAdd = 1, firstInDeque = 0;
        deque.addFirst(itemToAdd);

        firstInDeque = deque.getFirst();

        assertEquals(itemToAdd, firstInDeque);
    }

    @Test
    public void canAddItemToTail() {
        Deque<Integer> deque = Deque.createDeque();
        int itemToAdd = 1, lastInDeque = 0;
        deque.addLast(itemToAdd);

        lastInDeque = deque.getLast();

        assertEquals(itemToAdd, lastInDeque);
    }

    @Test
    public void isTheOnlyTailItemTheFirstOne() {
        Deque<Integer> deque = Deque.createDeque();
        int itemToAdd = 1, firstInDeque = 0;
        deque.addLast(itemToAdd);

        firstInDeque = deque.getFirst();

        assertEquals(itemToAdd, firstInDeque);
    }

    @Test
    public void isTheOnlyHeadItemTheLastOne() {
        Deque<Integer> deque = Deque.createDeque();
        int itemToAdd = 1, lastInDeque = 0;
        deque.addLast(itemToAdd);

        lastInDeque = deque.getLast();

        assertEquals(itemToAdd, lastInDeque);
    }

    @Test
    public void cannotGetFirstItemFromEmptyDeque() {
        Deque deque = Deque.createDeque();
        Object item = deque.getFirst();
        assertNull(item);
    }

    @Test
    public void cannotGetLastItemFromEmptyDeque() {
        Deque deque = Deque.createDeque();
        Object item = deque.getLast();
        assertNull(item);
    }

    @Test
    public void isRemovedFirstItemEqualToRecentlyAdded() {
        Deque<Integer> deque = Deque.createDeque();
        int itemToAdd = 1, firstInDeque = 0;
        deque.addFirst(itemToAdd);

        firstInDeque = deque.removeFirst();

        assertEquals(itemToAdd, firstInDeque);
    }

    @Test
    public void isRemovedLastItemEqualToRecentlyAdded() {
        Deque<Integer> deque = Deque.createDeque();
        int itemToAdd = 1, lastInDeque = 0;
        deque.addLast(itemToAdd);

        lastInDeque = deque.removeLast();

        assertEquals(itemToAdd, lastInDeque);
    }

    @Test
    public void cannotRemoveFirstItemFromEmptyDeque() {
        Deque deque = Deque.createDeque();
        Object item = deque.removeFirst();
        assertNull(item);
    }

    @Test
    public void cannotRemoveLastItemFromEmptyDeque() {
        Deque deque = Deque.createDeque();
        Object item = deque.removeLast();
        assertNull(item);
    }

    @Test
    public void isDequeEmptyAfterRemoveFirst() {
        Deque<Integer> deque = Deque.createDeque();
        deque.addFirst(1);

        deque.removeFirst();

        assertTrue(deque.isEmpty());
    }

    @Test
    public void isDequeEmptyAfterRemoveLast() {
        Deque<Integer> deque = Deque.createDeque();
        deque.addLast(1);

        deque.removeLast();

        assertTrue(deque.isEmpty());
    }

    @Test
    public void areItemsAddedToTailEqualToItemsRemoveFromHead() {
        Deque<Integer> deque = Deque.createDeque();
        final int numberOfItems = 3;
        int[] itemsToAdd        = new int[numberOfItems];
        int[] itemsFromDeque    = new int[numberOfItems];
        int i = 0;
        fillInIntArrayIncrementally(itemsToAdd);
        addIntsFromArrayToDequeTail(deque, itemsToAdd);

        while (!deque.isEmpty()) {
            itemsFromDeque[i++] = deque.removeFirst();
        }

        assertTrue(Arrays.equals(itemsToAdd, itemsFromDeque));
    }

    @Test
    public void areItemsAddedToHeadEqualToItemsRemoveFromTail() {
        Deque<Integer> deque = Deque.createDeque();
        final int numberOfItems = 3;
        int[] itemsToAdd        = new int[numberOfItems];
        int[] itemsFromDeque    = new int[numberOfItems];
        int i = 0;
        fillInIntArrayIncrementally(itemsToAdd);
        addIntsFromArrayToDequeHead(deque, itemsToAdd);

        while (!deque.isEmpty()) {
            itemsFromDeque[i++] = deque.removeLast();
        }

        assertTrue(Arrays.equals(itemsToAdd, itemsFromDeque));
    }

    @Test
    public void canClearNotEmptyDeque() {
        Deque<Integer> deque = Deque.createDeque();
        final int numberOfItems = 3;
        int [] itemsToAdd       = new int[numberOfItems];
        fillInIntArrayIncrementally(itemsToAdd);
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

    private void fillInIntArrayIncrementally(final int[] itemsToAdd) {
        int i = 0;
        for (i = 0; i < itemsToAdd.length; ++i) {
            itemsToAdd[i] = i;
        }
    }
}
