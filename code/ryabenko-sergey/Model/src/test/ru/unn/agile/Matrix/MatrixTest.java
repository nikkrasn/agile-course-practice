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
        Matrix mat = new Matrix(new double[][]{{0.5, -2, 8.7}});
        assertEquals(mat.getCountRows(), 1);
    }

    @Test
    public void canFindColumnCountInMatrix() {
        Matrix mat = new Matrix(new double[][]{{0.5, -2, 8.7}});
        assertEquals(3, mat.getCountColumns());
    }

    @Test
    public void canFindMatrixDimensionWithIncorrectlyInput() {
        Matrix mat = new Matrix(new double[][]{{}, {4, 5, 7, 8, 8, 8}, {1}, {1, 1, 1}});
        assertTrue(mat.getCountRows() == 4 && mat.getCountColumns() == 6);
    }

    @Test
    public void canSetInitialMatrixElement() {
        Matrix mat = new Matrix(new double[][]{{0.5, -2, 8.7}, {0, 8, 1}, {76.32, -0.55, 0}});
        assertEquals(-0.55, mat.getValueAt(2, 1), delta);
    }
}

