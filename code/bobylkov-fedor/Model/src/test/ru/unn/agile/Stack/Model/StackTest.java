package ru.unn.agile.Stack.Model;

import org.junit.Test;
import java.util.EmptyStackException;
import static org.junit.Assert.*;

public class StackTest {
    @Test
    public void emptyStackIsEmpty() {
        Stack emptyStack = new Stack();
        assertTrue(emptyStack.isEmpty());
    }

    @Test
    public void notEmptyStackIsNotEmpty() {
        Stack<Object> notEmptyStack = new Stack<>();
        notEmptyStack.push(1);
        assertFalse(notEmptyStack.isEmpty());
    }

    @Test
    public void canEmptyStackWithPop() {
        Stack<Object> stack = new Stack<>();
        stack.push(1);
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test(expected = EmptyStackException.class)
    public void popFromEmptyStackThrowsCorrectException() {
        Stack emptyStack = new Stack();
        emptyStack.pop();
    }

    @Test
    public void canPopElement() {
        Stack<Object> stack = new Stack<>();
        stack.push(1);
        assertEquals(1, stack.pop());
    }

    @Test
    public void canPushElement() {
        Stack<Object> stack = new Stack<>();
        stack.push(1);
        assertEquals(1, stack.top());
    }

    @Test(expected = EmptyStackException.class)
    public void topInEmptyStackThrowsCorrectException() {
        Stack emptyStack = new Stack();
        emptyStack.top();
    }

    @Test
    public void canGetTopElement() {
        Stack<Object> stack = new Stack<>();
        stack.push(1);

        assertEquals(1, stack.top());
    }

    @Test
    public void stackOrderIsCorrect() {
        Stack<Object> stack = new Stack<>();

        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }
}
