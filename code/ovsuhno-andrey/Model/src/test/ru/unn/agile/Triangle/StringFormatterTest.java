package ru.unn.agile.Triangle;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringFormatterTest {
    @Test
    public void canFormatInteger() {
        double value = 1;
        assertEquals("1.00", StringFormatter.precisionFormat(value));
    }

    @Test
    public void canFormatNumberWithZeroAfterDecimalPoint() {
        double value = 2.03;
        assertEquals("2.03", StringFormatter.precisionFormat(value));
    }

    @Test
    public void canFormatNumberWithOneDigitAfterDecimalPoint() {
        double value = 1.2;
        assertEquals("1.20", StringFormatter.precisionFormat(value));
    }

    @Test
    public void canFormatNumberWithTwoDigitsAfterDecimalPoint() {
        double value = 1.23;
        assertEquals("1.23", StringFormatter.precisionFormat(value));
    }

    @Test
    public void canFormatNumberWithManyDigitsAfterDecimalPoint() {
        double value = 1.2345678;
        assertEquals("1.23", StringFormatter.precisionFormat(value));
    }

    @Test
    public void canFormatNegativeNumber() {
        double value = -1.2345678;
        assertEquals("-1.23", StringFormatter.precisionFormat(value));
    }

    @Test
    public void canConvertIntegerPointToString() {
        Point certainPoint = new Point(2, -4);
        assertEquals("(2.00, -4.00)", StringFormatter.format(certainPoint));
    }

    @Test
    public void canConvertFloatPointToString() {
        Point certainPoint = new Point(2.01, -4.10);
        assertEquals("(2.01, -4.10)", StringFormatter.format(certainPoint));
    }
}
