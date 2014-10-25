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
    public void canAddItem() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        assertFalse(deque.isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void cannotAddNullItem() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(null);
    }

}
