package ru.unn.agile.Queue.Model;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import java.util.ArrayList;
import java.util.List;

public class QueueTest {
    private Queue<Integer> queue;

    @Before
    public void setUp() {
        queue = new Queue<Integer>();
    }

    @After
    public void tearDown() {
        queue = null;
    }

    @Test
    public void isNewlyCreatedQueueEmpty() {
        assertTrue(queue.isEmpty());
    }

    @Test
    public void isQueueNotEmptyAfterAdd() {
        queue.add(1);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void canAddItem() {
        Integer itemToAdd = 1;

        queue.add(itemToAdd);

        assertEquals(itemToAdd, queue.element());
    }

    @Test
    public void canAddTwoItems() {
        List<Integer> itemsToAdd     = getIncrementalList(2);
        List<Integer> itemsFromQueue = getEmptyList();
        for (int item: itemsToAdd) {
            queue.add(item);
        }

        while (!queue.isEmpty()) {
            itemsFromQueue.add(queue.remove());
        }

        assertTrue(itemsToAdd.equals(itemsFromQueue));
    }

    private ArrayList<Integer> getEmptyList() {
        return new ArrayList<Integer>();
    }

    private List<Integer> getIncrementalList(final int count) {
        List<Integer> items = getEmptyList();
        fillInListIncrementally(items, count);
        return items;
    }

    private void fillInListIncrementally(final List<Integer> list, final int count) {
        for (int i = 1; i <= count; ++i) {
            list.add(i);
        }
    }
}
