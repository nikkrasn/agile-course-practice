package ru.unn.agile.Queue.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class QueueTest {
    @Test
    public void isNewlyCreatedQueueEmpty() {
        Queue queue = new Queue();
        assertTrue(queue.isEmpty());
    }
}
