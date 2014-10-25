package ru.unn.agile.Deque.Model;

import org.junit.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Created by avilchin on 10/25/14.
 */

public class DequeTest {

    @Test
    public void isNewlyCreatedDequeEmpty() {
        Deque emptyDeque = new Deque();
        assertTrue(emptyDeque.isEmpty());
    }

    @Test
    public void isDequeNotEmptyAfterAddFirst() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        assertFalse(deque.isEmpty());
    }

    @Test
    public void isDequeNotEmptyAfterAddLast() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        assertFalse(deque.isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void cannotAddNullItemToHead() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(null);
    }

    @Test(expected = NullPointerException.class)
    public void cannotAddNullItemToTail() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(null);
    }

    @Test
    public void canAddItemToHead() {
        Deque<Integer> deque = new Deque<Integer>();
        int itemToAdd = 1, firstInDeque = 0;
        deque.addFirst(itemToAdd);

        firstInDeque = deque.getFirst();

        assertEquals(itemToAdd, firstInDeque);
    }

    @Test
    public void canAddItemToTail() {
        Deque<Integer> deque = new Deque<Integer>();
        int itemToAdd = 1, lastInDeque = 0;
        deque.addLast(itemToAdd);

        lastInDeque = deque.getLast();

        assertEquals(itemToAdd, lastInDeque);
    }

    @Test
    public void isTheOnlyTailItemTheFirstOne() {
        Deque<Integer> deque = new Deque<Integer>();
        int itemToAdd = 1, firstInDeque = 0;
        deque.addLast(itemToAdd);

        firstInDeque = deque.getFirst();

        assertEquals(itemToAdd, firstInDeque);
    }

    @Test
    public void isTheOnlyHeadItemTheLastOne() {
        Deque<Integer> deque = new Deque<Integer>();
        int itemToAdd = 1, lastInDeque = 0;
        deque.addLast(itemToAdd);

        lastInDeque = deque.getLast();

        assertEquals(itemToAdd, lastInDeque);
    }

    @Test (expected = NoSuchElementException.class)
    public void cannotGetFirstItemFromEmptyDeque() {
        Deque deque = new Deque();
        deque.getFirst();
    }

    @Test (expected = NoSuchElementException.class)
    public void cannotGetLastItemFromEmptyDeque() {
        Deque deque = new Deque();
        deque.getLast();
    }

    @Test
    public void isRemovedFirstItemEqualToRecentlyAdded() {
        Deque<Integer> deque = new Deque<Integer>();
        int itemToAdd = 1, firstInDeque = 0;
        deque.addFirst(itemToAdd);

        firstInDeque = deque.removeFirst();

        assertEquals(itemToAdd, firstInDeque);
    }

    @Test
    public void isRemovedLastItemEqualToRecentlyAdded() {
        Deque<Integer> deque = new Deque<Integer>();
        int itemToAdd = 1, lastInDeque = 0;
        deque.addLast(itemToAdd);

        lastInDeque = deque.removeLast();

        assertEquals(itemToAdd, lastInDeque);
    }

    @Test (expected = NoSuchElementException.class)
    public void cannotRemoveFirstItemFromEmptyDeque() {
        Deque deque = new Deque();
        deque.removeFirst();
    }

    @Test (expected = NoSuchElementException.class)
    public void cannotRemoveLastItemFromEmptyDeque() {
        Deque deque = new Deque();
        deque.removeLast();
    }

    @Test
    public void isDequeEmptyAfterRemoveFirst() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);

        deque.removeFirst();

        assertTrue(deque.isEmpty());
    }

    @Test
    public void isDequeEmptyAfterRemoveLast() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);

        deque.removeLast();

        assertTrue(deque.isEmpty());
    }

    @Test
    public void areItemsAddedToTailEqualToItemsRemoveFromHead() {
        Deque<Integer> deque = new Deque<Integer>();
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
        Deque<Integer> deque = new Deque<Integer>();
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

    private int addIntsFromArrayToDequeTail(final Deque<Integer> deque, final int[] itemsToAdd) {
        int i = 0;
        for (i = 0; i < itemsToAdd.length; ++i) {
            deque.addLast(itemsToAdd[i]);
        }
        return i;
    }

    private int addIntsFromArrayToDequeHead(final Deque<Integer> deque, final int[] itemsToAdd) {
        int i = 0;
        for (i = 0; i < itemsToAdd.length; ++i) {
            deque.addFirst(itemsToAdd[i]);
        }
        return i;
    }

    private int fillInIntArrayIncrementally(final int[] itemsToAdd) {
        int i = 0;
        for (i = 0; i < itemsToAdd.length; ++i) {
            itemsToAdd[i] = i;
        }
        return i;
    }
}
