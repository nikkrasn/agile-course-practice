package ru.unn.agile.Deque.Model;

import org.junit.Test;

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
    public void isTheOnlyTailElementTheFirstOne() {
        Deque<Integer> deque = new Deque<Integer>();
        int itemToAdd = 1, firstInDeque = 0;
        deque.addLast(itemToAdd);

        firstInDeque = deque.getFirst();

        assertEquals(itemToAdd, firstInDeque);
    }

    @Test
    public void isTheOnlyHeadElementTheLastOne() {
        Deque<Integer> deque = new Deque<Integer>();
        int itemToAdd = 1, lastInDeque = 0;
        deque.addLast(itemToAdd);

        lastInDeque = deque.getLast();

        assertEquals(itemToAdd, lastInDeque);
    }

    @Test (expected = NoSuchElementException.class)
    public void cannotGetFirstElementFromEmptyDeque() {
        Deque deque = new Deque();
        deque.getFirst();
    }

    @Test (expected = NoSuchElementException.class)
    public void cannotGetLastElementFromEmptyDeque() {
        Deque deque = new Deque();
        deque.getLast();
    }

    @Test
    public void isRemovedFirstElementEqualToRecentlyAdded() {
        Deque<Integer> deque = new Deque<Integer>();
        int itemToAdd = 1, firstInDeque = 0;
        deque.addFirst(itemToAdd);

        firstInDeque = deque.removeFirst();

        assertEquals(itemToAdd, firstInDeque);
    }

    @Test
    public void isRemovedLastElementEqualToRecentlyAdded() {
        Deque<Integer> deque = new Deque<Integer>();
        int itemToAdd = 1, lastInDeque = 0;
        deque.addLast(itemToAdd);

        lastInDeque = deque.removeLast();

        assertEquals(itemToAdd, lastInDeque);
    }

    @Test (expected = NoSuchElementException.class)
    public void cannotRemoveFirstElementFromEmptyDeque() {
        Deque deque = new Deque();
        deque.removeFirst();
    }

    @Test (expected = NoSuchElementException.class)
    public void cannotRemoveLastElementFromEmptyDeque() {
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
}
