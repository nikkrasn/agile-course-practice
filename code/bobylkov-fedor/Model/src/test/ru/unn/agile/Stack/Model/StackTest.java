package ru.unn.agile.Stack.Model;

import org.junit.Before;
import org.junit.Test;

import java.util.EmptyStackException;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;

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
        assertStackEquals(new Object[]{1}, stack);
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
        assertStackEquals(new Object[]{1, "str"}, stack);
    }

    @Test
    public void canCreateSpecificTypeStack() {
        Stack<String> stringStack = new Stack<>();
        stringStack.push("str");
        assertStackEquals(new Object[]{"str"}, stringStack);
    }

    @Test
    public void canCopyStack() {
        stack.push(1);
        stack.push(2);

        Stack<Object> stackFromStack = new Stack<>(stack);
        assertStackEquals(new Object[]{1, 2}, stackFromStack);
    }

    @Test (expected = IllegalArgumentException.class)
    public void cannotCopyStackFromNull() {
        stack = new Stack<>(null);
    }

    @Test
    public void isOriginalStackCorrectAfterCopying() {
        stack.push(1);
        stack.push(2);

        Stack<Object> stackFromStack = new Stack<>(stack);
        assertStackEquals(new Object[]{1, 2}, stack);
    }

    @Test
    public void canConvertStackToList() {
        stack.push(1);
        stack.push(2);

        List<Object> list = stack.toList();
        assertArrayEquals(new Object[] {1, 2}, list.toArray());
    }

    @Test
    public void canConvertEmptyStackToList() {
        List<Object> list = stack.toList();
        assertArrayEquals(new Object[] {}, list.toArray());
    }

    @Test
    public void canConvertSpecificTypeStackToSameTypeList() {
        Stack<String> stringStack = new Stack<>();
        stringStack.push("str");

        List<String> stringList = stringStack.toList();
        assertArrayEquals(new Object[] {"str"}, stringList.toArray());
    }

    private void assertStackEquals(final Object[] expected, final Stack actual) {
        assertArrayEquals(expected, actual.toList().toArray());
    }
}
