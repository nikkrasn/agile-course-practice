package ru.unn.agile.Matrix;

import org.junit.Test;
import static org.junit.Assert.*;

public class MatrixTest {
    private final double delta = 0.001;

    @Test (expected = IllegalArgumentException.class)
    public void canCheckIncorrectlyInputtedMatrix() {
        Matrix mat = new Matrix(-333);
    }

    @Test
    public void canCreateMatrixWithSpecifiedSizes() {
        Matrix mat = new Matrix(4);
        assertNotNull(mat);
    }

    @Test
    public void canSetInitialRowCount() {
        Matrix mat = new Matrix(1);
        assertEquals(1, mat.getCountRows());
    }

    @Test
    public void canSetInitialColumnCount() {
        Matrix mat = new Matrix(1);
        assertEquals(1, mat.getCountColumns());
    }

    @Test (expected = IllegalArgumentException.class)
    public void canCheckMatrixIsSquare() {
        Matrix mat = new Matrix(new double[][]{{0.5, -2, 8.7}});
    }

    @Test
    public void canSetInitialMatrixElement() {
        Matrix mat = new Matrix(new double[][]{{0.5, -2, 8.7}, {0, 8, 1}, {76.32, -0.55, 0}});
        assertEquals(-0.55, mat.getValueAt(2, 1), delta);
    }

    @Test
    public void canFindMaxLengthRowInEmptyMatrix() {
        double[][] d = {};
        Matrix mat = new Matrix(d);
        assertTrue(mat.getMaxLength(d) == 0);
    }

    @Test
    public void canInitializeIncorrectMatrix() {
        double[][] d = {{}, {3, -0.55}};
        Matrix mat = new Matrix(d);
        mat.initializeMatrix(d);
        assertTrue(mat.getValueAt(0, 0) == 0 && mat.getValueAt(0, 1) == 0
               &&  mat.getValueAt(1, 0) == 3 && mat.getValueAt(1, 1) == -0.55
        );
    }

    @Test
    public void canInitializeEmptiesMatrix() {
        double[][] d = {};
        Matrix mat = new Matrix(d);
        mat.initializeMatrix(d);
    }

    @Test
    public void canInitializeHigherOrderMatrix() {
        double[][] d = {{1, 2}, {1, -2, 3, -4},
                {1.1, 1.2, 1.3, 1.4}, {2.21, 3.31, 4.41}};
        Matrix mat = new Matrix(d);
        mat.initializeMatrix(d);
        assertTrue(mat.getValueAt(0, 0) == 1 && mat.getValueAt(0, 3) == 0
               &&  mat.getValueAt(2, 3) == 1.4 && mat.getValueAt(3, 2) == 4.41
        );
    }

}

