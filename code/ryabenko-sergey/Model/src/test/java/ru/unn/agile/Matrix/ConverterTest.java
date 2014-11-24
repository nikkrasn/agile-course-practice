package ru.unn.agile.Matrix;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConverterTest {
    private final double delta = 0.001;

    @Test (expected = IllegalArgumentException.class)
    public void canCheckIncorrectSymbol() {
        String text = "n";
        Converter converter = new Converter(text);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canCheckEmptyString() {
        String text = "           ";
        Converter converter = new Converter(text);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canCheckEmptyLines() {
        String text = " \n\n\n";
        Converter converter = new Converter(text);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canCheckEmptyLinesWithIndent() {
        String text = " \n \n";
        Converter converter = new Converter(text);
    }

    @Test
    public void canConvertCorrectString() {
        String text = "7 8\n-0.1 4";
        Converter converter = new Converter(text);
        SquareMatrix mat = new SquareMatrix(converter.getData());
        assertEquals(28.8, MatrixDeterminant.calculation(mat), delta);
    }

    @Test
    public void canConvertStringWithIncorrectTabulation() {
        String text = "    7  8   \n\n\n  -1          0.5    ";
        Converter converter = new Converter(text);
        SquareMatrix mat = new SquareMatrix(converter.getData());
        assertEquals(11.5, MatrixDeterminant.calculation(mat), delta);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canCheckNonSquareMatrix() {
        String text = "1 0 0\n-1 6 7";
        Converter converter = new Converter(text);
    }

}
