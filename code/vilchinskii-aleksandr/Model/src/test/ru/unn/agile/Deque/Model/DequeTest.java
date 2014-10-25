package ru.unn.agile.Deque.Model;

import org.junit.Test;
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

}
