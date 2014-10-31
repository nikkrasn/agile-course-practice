package ru.unn.agile.Stack.Model;

import org.junit.Test;
import java.util.EmptyStackException;

public class TopTest {
    @Test(expected = EmptyStackException.class)
    public void callTopInEmptyStackThrowsException() {
        Stack stack = new Stack();
        stack.top();
    }
}
