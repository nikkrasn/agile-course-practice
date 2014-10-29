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

    @Test
    public void canFindMaxLengthRow() {
        double[][] d = {{}, {4, 5, 7, 8, 8, 8}, {1}, {1, -1.556785, 1}, {-1, 1, 1, -1, 1, 0}};
        Matrix mat = new Matrix(d);
        assertTrue(mat.getMaxLength(d) == 6);
    }

    @Test
    public void canFindMaxLengthRowInEmptyMatrix() {
        double[][] d = {{}};
        Matrix mat = new Matrix(d);
        assertTrue(mat.getMaxLength(d) == 0);
    }

    @Test
    public void isPreInitializationMatrix() {
        Matrix mat = new Matrix(new double[][]{{}, {3, 0}});
        mat.preInitialization(mat.getCountRows(), mat.getCountColumns());
        assertTrue(mat.getValueAt(0, 0) == 1 && mat.getValueAt(0, 1) == 1
               &&  mat.getValueAt(1, 0) == 1 && mat.getValueAt(1, 1) == 1
        );
    }

    @Test
    public void isInitializationHigherOrderMatrix() {
        Matrix mat = new Matrix(new double[][] {{1, 2}, {1, -2, 3, -4},
                {1.1, 1.2, 1.3, 1.4}, {2.21, 3.31, 4.41}});
        mat.preInitialization(mat.getCountRows(), mat.getCountColumns());
        assertTrue(mat.getValueAt(0, 0) == 1 && mat.getValueAt(0, 3) == 1
               &&  mat.getValueAt(2, 3) == 1 && mat.getValueAt(3, 2) == 1
        );
    }

    @Test
    public void canPreInitializationEmptiesMatrix() {
        Matrix mat = new Matrix(new double[][]{{}});
        mat.preInitialization(mat.getCountRows(), mat.getCountColumns());
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
        double[][] d = {{}};
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

    @Test (expected = IllegalArgumentException.class)
    public void canCheckPreInitializationMatrix() {
        Matrix mat = new Matrix(5, 5);
        mat.preInitialization(10, 10);
    }

}

