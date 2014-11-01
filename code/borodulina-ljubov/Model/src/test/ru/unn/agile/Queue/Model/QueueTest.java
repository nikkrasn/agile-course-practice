package ru.unn.agile.Queue.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class QueueTest {
    @Test
    public void isNewlyCreatedQueueEmpty() {
        Queue queue = new Queue();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void isQueueNotEmptyAfterAdd() {
        Queue<Integer> queue = new Queue<Integer>();
        queue.add(1);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void canAddItem() {
        Queue<Integer> queue = new Queue<Integer>();
        Integer itemToAdd = 1;
        queue.add(itemToAdd);
        assertEquals(itemToAdd, queue.element());
    }

    
}
