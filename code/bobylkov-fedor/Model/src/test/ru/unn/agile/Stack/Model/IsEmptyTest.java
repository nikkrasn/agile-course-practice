package ru.unn.agile.Stack.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class IsEmptyTest {
    @Test
    public void isEmptyStackEmpty() {
        Stack stack = new Stack();
        assertTrue(stack.isEmpty());
    }
}
