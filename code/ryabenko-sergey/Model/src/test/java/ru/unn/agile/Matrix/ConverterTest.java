package ru.unn.agile.Matrix;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConverterTest {
    private final double delta = 0.001;
    private double[][] data;

    @Test (expected = IllegalArgumentException.class)
    public void canCheckIncorrectSymbol() {
        Converter.stringToArray("n");
    }

    @Test (expected = IllegalArgumentException.class)
    public void canCheckNullSymbol() {
        Converter.stringToArray(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canCheckEmptyString() {
        Converter.stringToArray("           ");
    }

    @Test (expected = IllegalArgumentException.class)
    public void canCheckEmptyLines() {
        Converter.stringToArray(" \n\n\n");
    }

    @Test (expected = IllegalArgumentException.class)
    public void canCheckEmptyLinesWithIndent() {
        Converter.stringToArray(" \n \n");
    }

    @Test
    public void canConvertCorrectString() {
        data = Converter.stringToArray("7 8\n-0.1 4");
        SquareMatrix mat = new SquareMatrix(data);
        assertEquals(28.8, MatrixDeterminant.calculation(mat), delta);
    }

    @Test
    public void canConvertStringWithIncorrectTabulation() {
        data = Converter.stringToArray("    7  8   \n\n\n  -1          0.5    ");
        SquareMatrix mat = new SquareMatrix(data);
        assertEquals(11.5, MatrixDeterminant.calculation(mat), delta);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canCheckNonSquareMatrix() {
        data = Converter.stringToArray("1 0 0\n-1 6 7");
    }

}
