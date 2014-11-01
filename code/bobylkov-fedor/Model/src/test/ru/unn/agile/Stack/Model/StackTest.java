package ru.unn.agile.Stack.Model;

import org.junit.Before;
import org.junit.Test;
import java.util.EmptyStackException;
import static org.junit.Assert.*;

public class StackTest {
    private Stack<Object> stack;

    @Before
    public void setUpTest() {
        stack = new Stack<>();
    }

    @Test
    public void isEmptyStackEmpty() {
        assertTrue(stack.isEmpty());
    }

    @Test
    public void isNotEmptyStackNotEmpty() {
        stack.push(1);
        assertFalse(stack.isEmpty());
    }

    @Test
    public void isStackEmptyAfterPop() {
        stack.push(1);
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test (expected = EmptyStackException.class)
    public void cannotPopFromEmptyStack() {
        stack.pop();
    }

    @Test
    public void canPopFromNotEmptyStack() {
        stack.push(1);
        assertEquals(1, stack.pop());
    }

    @Test
    public void canPush() {
        stack.push(1);
        assertEquals(1, stack.top());
    }

    @Test (expected = EmptyStackException.class)
    public void cannotGetTopInEmptyStack() {
        stack.top();
    }

    @Test
    public void canGetTopInNotEmptyStack() {
        stack.push(1);
        assertEquals(1, stack.top());
    }

    @Test
    public void isTopCorrectAfterPush() {
        stack.push(1);
        assertEquals(1, stack.top());
        stack.push(2);
        assertEquals(2, stack.top());
    }

    @Test
    public void isTopCorrectAfterPop() {
        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.top());
        stack.pop();
        assertEquals(1, stack.top());
    }

    @Test
    public void isStackOrderCorrect() {
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    public void canObjectStackContainDifferentTypeElements() {
        stack.push(1);
        stack.push("str");
        assertEquals("str", stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    public void canCreateSpecificTypeStack() {
        Stack<String> stringStack = new Stack<>();
        stringStack.push("str");
        assertEquals("str", stringStack.top());
    }

    @Test
    public void canCreateStackFromStack() {
        stack.push(1);
        stack.push("str");

        Stack<Object> stackFromStack = new Stack<>(stack);
        assertEquals("str", stackFromStack.pop());
        assertEquals(1, stackFromStack.pop());
    }
}
