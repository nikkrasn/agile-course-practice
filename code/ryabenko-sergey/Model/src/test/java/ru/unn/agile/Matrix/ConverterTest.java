package ru.unn.agile.Matrix;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConverterTest {
    private static final double DELTA = 0.001;
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
        assertEquals(28.8, MatrixDeterminant.calculation(mat), DELTA);
    }

    @Test
    public void canConvertStringWithIncorrectTabulation() {
        data = Converter.stringToArray("    7  8   \n\n\n  -1          0.5    ");
        SquareMatrix mat = new SquareMatrix(data);
        assertEquals(11.5, MatrixDeterminant.calculation(mat), DELTA);
    }

    @Test
    public void canConvertEmptyArray() {
        String mat = Converter.arrayToString(new double[][]{});
        assertEquals(mat, "{ }");
    }

    @Test
    public void canConvertArrayWithEmptyValues() {
        String mat = Converter.arrayToString(new double[][]{{}});
        assertEquals(mat, "{ () }");
    }

    @Test
    public void canConvertArrayNonSymmetricalDimension() {
        String mat = Converter.arrayToString(new double[][]{{0.5, -2, 8.7}});
        assertEquals(mat, "{ (0.5,  -2.0,  8.7) }");
    }

    @Test
    public void canConvertArraySymmetricalDimension() {
        String mat = Converter.arrayToString(new double[][]{{0.5, -2, 1}, {0, 8, 1},
                {76.32, 0, 8}});
        assertEquals(mat, "{ (0.5,  -2.0,  1.0),  (0.0,  8.0,  1.0),  (76.32,  0.0,  8.0) }");
    }
}
