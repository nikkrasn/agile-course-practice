package ru.unn.agile.Matrix;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MatrixDeterminantTest {
    private final double delta = 0.001;

    @Test (expected = IllegalArgumentException.class)
    public void canCheckMatrixIsSquare() {
        Matrix mat = new Matrix(new double[][]{{-3.55, 4, 1}, {1, 0, 0}});
        double det = MatrixDeterminant.determinant(mat);
    }

    @Test
    public void canFindDeterminantForEmptiesMatrix() {
        Matrix mat = new Matrix(new double[][]{{}});
        double det = MatrixDeterminant.determinant(mat);
        assertEquals(0, det, delta);
    }

    @Test
    public void canFindDeterminantForFirstOrderMatrix() {
        Matrix mat = new Matrix(new double[][]{{-3.55}});
        double det = MatrixDeterminant.determinant(mat);
        assertEquals(-3.55, det, delta);
    }

    @Test
    public void canFindDeterminantForSecondOrderMatrix() {
        Matrix mat = new Matrix(new double[][]{{1.2, -1}, {-0.25, 2.4}});
        double det = MatrixDeterminant.determinant(mat);
        assertEquals(1.2 * 2.4 + 1 * -0.25, det, delta);
    }

    @Test
    public void canFindDeterminantForHigherOrderMatrix() {
        Matrix mat = new Matrix(new double[][]{{1, 2, 3, 4}, {1, -2, 3, -4},
                {1.1, 1.2, 1.3, 1.4}, {2.21, 3.31, 4.41, 1.01}});
        double det = MatrixDeterminant.determinant(mat);
        assertEquals(-36, det, delta);
    }

    @Test
    public void canFindDeterminantForIncorrectlyEnteredMatrix() {
        Matrix mat = new Matrix(new double[][]{{1, 2, 3}, {4, 5}, {6}});
        double det = MatrixDeterminant.determinant(mat);
        assertEquals(-90, det, delta);
    }

    @Test
    public void canFindDeterminantForHalfEmptyMatrix() {
        Matrix mat = new Matrix(new double[][]{{}, {}, {1, 0, 1, 0, 1}, {}, {}});
        double det = MatrixDeterminant.determinant(mat);
        assertEquals(0, det, delta);
    }

    @Test
     public void canCreateSubMatrix() {

        Matrix mat = new Matrix(new double[][]{{1, 0, 0}, {0, 1, 1}, {0, 1, 6}});
        Matrix shortMatrix = new Matrix(new double[][]{{1, 1}, {1, 6}});

        Matrix subMat = MatrixDeterminant.createSubMatrix(mat, 0, 0);

        assertTrue(subMat.getValueAt(0, 0) == shortMatrix.getValueAt(0, 0)
                        && subMat.getValueAt(0, 1) == shortMatrix.getValueAt(0, 1)
                        && subMat.getValueAt(1, 0) == shortMatrix.getValueAt(1, 0)
                        && subMat.getValueAt(1, 1) == shortMatrix.getValueAt(1, 1)
        );
    }

    @Test
    public void canCreateFirstOrderSubMatrix() {
        Matrix mat = new Matrix(new double[][]{{1, 2}, {3, -1}});
        Matrix subMat = MatrixDeterminant.createSubMatrix(mat, 0, 1);
        assertTrue(subMat.getValueAt(0, 0) == 3 && subMat.getSize() == 1);
    }

    @Test
    public void canCreateEmptiesSubMatrix() {
        Matrix mat = new Matrix(new double[][]{{-10}});
        Matrix subMat = MatrixDeterminant.createSubMatrix(mat, 0, 0);
        assertTrue(subMat.getCountRows() == 0);
    }

}
