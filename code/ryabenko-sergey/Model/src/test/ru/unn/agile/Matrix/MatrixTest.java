package ru.unn.agile.Matrix;

import org.junit.Test;
import static org.junit.Assert.*;

public class MatrixTest {
    private final double delta = 0.001;

    @Test
    public void canCreateMatrixWithSpecifiedSizes() {
        Matrix mat = new Matrix(4, 4);
        assertNotNull(mat);
    }

    @Test
    public void canSetInitialRowCount() {
        Matrix mat = new Matrix(1, 4);
        assertEquals(1, mat.getCountRows());
    }

    @Test
    public void canSetInitialColumnCount() {
        Matrix mat = new Matrix(1, 4);
        assertEquals(4, mat.getCountColumns());
    }

    @Test
    public void canFindRowCountInMatrix() {
        double[][] d = {{0.5, -2, 8.7}};
        Matrix mat = new Matrix(d);
        assertEquals(mat.getCountRows(), 1);
    }

    @Test
    public void canFindColumnCountInMatrix() {
        double[][] d = {{0.5, -2, 8.7}};
        Matrix mat = new Matrix(d);
        assertEquals(3, mat.getCountColumns());
    }

    @Test
    public void canSetInitialMatrixElement() {
        double[][] d = {{0.5, -2, 8.7}, {0, 8, 1}, {76.32, -0.55, 0}};
        Matrix mat = new Matrix(d);
        assertEquals(-0.55, mat.getValueAt(2, 1), delta);
    }
}

