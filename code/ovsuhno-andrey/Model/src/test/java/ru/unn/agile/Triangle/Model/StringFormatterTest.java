package ru.unn.agile.Triangle.Model;

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

    @Test
     public void canArrayFormatIntValue() {
        double[] value = new double[1];
        value[0] = 3;

        assertEquals("3.00", StringFormatter.arrayFormat(value));
    }

    @Test
    public void canArrayFormatDoubleValue() {
        double[] value = new double[1];
        value[0] = 3.01;

        assertEquals("3.01", StringFormatter.arrayFormat(value));
    }

    @Test
    public void canArrayFormatMultipleValues() {
        double[] values = new double[3];
        values[0] = 3;
        values[1] = 3.01;
        values[2] = 3.02;

        assertEquals("3.00 3.01 3.02", StringFormatter.arrayFormat(values));
    }
}
