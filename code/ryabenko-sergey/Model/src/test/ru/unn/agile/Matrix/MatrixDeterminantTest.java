package ru.unn.agile.Matrix;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MatrixDeterminantTest {
    private final double delta = 0.001;

    @Test (expected = IllegalArgumentException.class)
    public void canCheckMatrixIsEmpty() {
        double[][] d = {{}};
        Matrix mat = new Matrix(d);
        double det = MatrixDeterminant.determinant(mat);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canCheckMatrixIsSquare() {
        double[][] d = {{-3.55, 4, 1}, {1, 0, 0}};
        Matrix mat = new Matrix(d);
        double det = MatrixDeterminant.determinant(mat);
    }

    @Test
    public void canFindDeterminantForFirstOrderMatrix() {
        double[][] d = {{-3.55}};
        Matrix mat = new Matrix(d);
        double det = MatrixDeterminant.determinant(mat);
        assertEquals(-3.55, det, delta);
    }

    @Test
    public void canFindDeterminantForSecondOrderMatrix() {
        double[][] d = {{1.2, -1}, {-0.25, 2.4}};
        Matrix mat = new Matrix(d);
        double det = MatrixDeterminant.determinant(mat);
        assertEquals(1.2 * 2.4 + 1 * -0.25, det, delta);
    }

    @Test
    public void canFindDeterminantForHigherOrderMatrix() {
        double[][] d = {{1, 2, 3, 4}, {1, -2, 3, -4},
                {1.1, 1.2, 1.3, 1.4}, {2.21, 3.31, 4.41, 1.01}};
        Matrix mat = new Matrix(d);
        double det = MatrixDeterminant.determinant(mat);
        assertEquals(-36, det, delta);
    }

    @Test
    public void canFindDeterminantForIncorrectlyEnteredMatrix() {
        double[][] d = {{1, 2, 3}, {4, 5}, {6}};
        Matrix mat = new Matrix(d);
        double det = MatrixDeterminant.determinant(mat);
        assertEquals(-90, det, delta);
    }

    @Test
    public void canFindDeterminantForHalfEmptyMatrix() {
        double[][] d = {{}, {}, {1, 0, 1, 0, 1}, {}, {}};
        Matrix mat = new Matrix(d);
        double det = MatrixDeterminant.determinant(mat);
        assertEquals(0, det, delta);
    }

    @Test
     public void canCreateSubMatrix() {

        double[][] d = {{1, 0, 0}, {0, 1, 1}, {0, 1, 6}};
        double[][] shortD = {{1, 1}, {1, 6}};

        Matrix mat = new Matrix(d);
        Matrix shortMatrix = new Matrix(shortD);

        Matrix subMat = MatrixDeterminant.createSubMatrix(mat, 0, 0);

        assertTrue(subMat.getValueAt(0, 0) == shortMatrix.getValueAt(0, 0)
                        && subMat.getValueAt(0, 1) == shortMatrix.getValueAt(0, 1)
                        && subMat.getValueAt(1, 0) == shortMatrix.getValueAt(1, 0)
                        && subMat.getValueAt(1, 1) == shortMatrix.getValueAt(1, 1)
        );
    }

    @Test
    public void canCreateFirstOrderSubMatrix() {
        double[][] d = {{1, 2}, {3, -1}};
        Matrix mat = new Matrix(d);
        Matrix subMat = MatrixDeterminant.createSubMatrix(mat, 0, 1);
        assertTrue(subMat.getValueAt(0, 0) == 3 && subMat.getSize() == 1);
    }

    @Test
    public void canCreateEmptiesSubMatrix() {
        double[][] d = {{-10}};
        Matrix mat = new Matrix(d);
        Matrix subMat = MatrixDeterminant.createSubMatrix(mat, 0, 0);
        assertTrue(subMat.getCountRows() == 0);
    }

}
