package ru.unn.agile.Stack.Model;

import org.junit.Test;
import java.util.EmptyStackException;
import static org.junit.Assert.*;

public class StackTest {
    @Test
    public void isEmptyStackEmpty() {
        Stack stack = new Stack();
        assertTrue(stack.isEmpty());
    }

    @Test(expected = EmptyStackException.class)
    public void callTopInEmptyStackThrowsException() {
        Stack stack = new Stack();
        stack.top();
    }
}
