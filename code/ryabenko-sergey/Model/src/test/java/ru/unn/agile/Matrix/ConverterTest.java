package ru.unn.agile.Matrix;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConverterTest {
    private final double delta = 0.001;

    @Test (expected = IllegalArgumentException.class)
    public void canCheckIncorrectSymbol() {
        String text = "n";
        double[][] data = Converter.stringToArray(text);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canCheckEmptyString() {
        String text = "           ";
        double[][] data = Converter.stringToArray(text);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canCheckEmptyLines() {
        String text = " \n\n\n";
        double[][] data = Converter.stringToArray(text);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canCheckEmptyLinesWithIndent() {
        String text = " \n \n";
        double[][] data = Converter.stringToArray(text);
    }

    @Test
    public void canConvertCorrectString() {
        String text = "7 8\n-0.1 4";
        double[][] data = Converter.stringToArray(text);
        SquareMatrix mat = new SquareMatrix(data);
        assertEquals(28.8, MatrixDeterminant.calculation(mat), delta);
    }

    @Test
    public void canConvertStringWithIncorrectTabulation() {
        String text = "    7  8   \n\n\n  -1          0.5    ";
        double[][] data = Converter.stringToArray(text);
        SquareMatrix mat = new SquareMatrix(data);
        assertEquals(11.5, MatrixDeterminant.calculation(mat), delta);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canCheckNonSquareMatrix() {
        String text = "1 0 0\n-1 6 7";
        double[][] data = Converter.stringToArray(text);
    }

}
