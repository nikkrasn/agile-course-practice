package ru.unn.agile.Stack.Model;

import org.junit.Test;
import java.util.EmptyStackException;

import static org.junit.Assert.assertEquals;

public class PopTest {
    @Test(expected = EmptyStackException.class)
    public void callPopInEmptyStackThrowsException() {
        Stack stack = new Stack();
        stack.pop();
    }

    @Test
    public void canPopPushedElement() {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        assertEquals(1, (long) stack.pop());
    }
}
