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
}
