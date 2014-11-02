package ru.unn.agile.NumberInPositionalNotation.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class NotationTest {

    @Test
    public void canReturnBase() {
        Notation not = Notation.BINARY;
        assertEquals(2, not.getBase());
    }

    @Test
    public void canReturnBaseDec() {
        Notation not = Notation.DECIMAL;
        assertEquals(10, not.getBase());
    }

    @Test
    public void canReturnSymbols() {
        Notation not = Notation.HEXADECIMAL;
        assertEquals("0123456789abcdef.", not.getSymbols());
    }
}
