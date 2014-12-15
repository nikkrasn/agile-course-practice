package ru.unn.agile.Matrix;

import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTest {
    private static final double DELTA = 0.001;

    @Test (expected = IllegalArgumentException.class)
    public void canCheckIncorrectlyInputtedMatrix() {
        SquareMatrix mat = new SquareMatrix(-333);
    }

    @Test
    public void canCreateMatrixWithSpecifiedSizes() {
        SquareMatrix mat = new SquareMatrix(4);
        assertNotNull(mat);
    }

    @Test
    public void canCreateEmptyMatrix() {
        SquareMatrix mat = new SquareMatrix(0);
        assertNotNull(mat);
    }

    @Test
    public void canInitialRowCount() {
        SquareMatrix mat = new SquareMatrix(1);
        assertTrue(1 == mat.getCountRows() && mat.getValueAt(0, 0) == 0);
    }

    @Test
    public void canSetInitialMatrixElement() {
        SquareMatrix mat = new SquareMatrix(4);
        mat.setValueAt(0, 0, -1);
        mat.setValueAt(0, 1, 0);
        mat.setValueAt(1, 0, -11.1);
        mat.setValueAt(1, 1, 1.1);
        assertTrue(4 == mat.getCountRows() && mat.getValueAt(1, 1) == 1.1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canCheckMatrixWithEmptiesRows() {
        SquareMatrix mat = new SquareMatrix(new double[][]{});
    }

    @Test (expected = IllegalArgumentException.class)
    public void canCheckMatrixWithEmptiesColumns() {
        SquareMatrix mat = new SquareMatrix(new double[][]{{}});
    }

    @Test (expected = IllegalArgumentException.class)
    public void canCheckMatrixIsNonSquare() {
        SquareMatrix mat = new SquareMatrix(new double[][]{{0.5, -2, 8.7}});
    }

    @Test
    public void canCheckMatrixIsSquare() {
        SquareMatrix mat = new SquareMatrix(new double[][]{{-1.67}});
        assertTrue(1 == mat.getCountRows() && mat.getValueAt(0, 0) == -1.67);
    }

    @Test
    public void canGetMatrixElement() {
        SquareMatrix mat = new SquareMatrix(new double[][]{{0.5, -2, 8.7}, {0, 8, 1},
                {76.32, -0.55, 0}});
        assertEquals(-0.55, mat.getValueAt(2, 1), DELTA);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canCheckLengthRowsForNonSquareMatrix() {
        double[][] d = {{1, 2.1}, {-1}};
        SquareMatrix.checkLengthRows(d);
    }

    @Test
    public void canCheckLengthRowsForSquareMatrix() {
        double[][] d = {{1.77, 2}, {-1, 0.54}};
        SquareMatrix.checkLengthRows(d);
    }

    @Test
    public void canCheckLengthRowsForEmptyMatrix() {
        double[][] d = {{}};
        SquareMatrix.checkLengthRows(d);
    }

    @Test
    public void canInitializeHigherOrderMatrix() {
        double[][] d = {{1, 2, 7, 9.87}, {1, -2, 3, -4},
                {1.1, 1.2, 1.3, 1.4}, {2.21, 0, 3.31, 4.41}};
        SquareMatrix mat = new SquareMatrix(d);
        assertTrue(mat.getValueAt(0, 0) == 1 && mat.getValueAt(0, 3) == 9.87
               &&  mat.getValueAt(2, 3) == 1.4 && mat.getValueAt(3, 2) == 3.31
        );
    }
}
