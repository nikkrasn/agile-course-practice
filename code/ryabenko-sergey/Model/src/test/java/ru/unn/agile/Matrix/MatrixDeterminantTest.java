package ru.unn.agile.Matrix;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MatrixDeterminantTest {
    private final double delta = 0.001;

    @Test (expected = IllegalArgumentException.class)
    public void canCheckMatrixIsSquare() {
        SquareMatrix mat = new SquareMatrix(new double[][]{{-3.55, 4, 1}, {1, 0, 0}});
        double det = MatrixDeterminant.calculation(mat);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canFindDeterminantForEmptyMatrix() {
        SquareMatrix mat = new SquareMatrix(0);
        double det = MatrixDeterminant.calculation(mat);
    }

    @Test
    public void canFindDeterminantForMatrixWithEmptyValues() {
        SquareMatrix mat = new SquareMatrix(1);
        double det = MatrixDeterminant.calculation(mat);
        assertTrue(det == 0);
    }

    @Test
    public void canFindDeterminantForFirstOrderMatrix() {
        SquareMatrix mat = new SquareMatrix(new double[][]{{-3.55}});
        double det = MatrixDeterminant.calculation(mat);
        assertEquals(-3.55, det, delta);
    }

    @Test
    public void canFindDeterminantForSecondOrderMatrix() {
        SquareMatrix mat = new SquareMatrix(new double[][]{{1.2, -1}, {-0.25, 2.4}});
        double det = MatrixDeterminant.calculation(mat);
        assertEquals(1.2 * 2.4 + 1 * -0.25, det, delta);
    }

    @Test
    public void canFindDeterminantForHigherOrderMatrix() {
        SquareMatrix mat = new SquareMatrix(new double[][]{{1, 2, 3, 4}, {1, -2, 3, -4},
                {1.1, 1.2, 1.3, 1.4}, {2.21, 3.31, 4.41, 1.01}});
        double det = MatrixDeterminant.calculation(mat);
        assertEquals(-36, det, delta);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canCheckIncorrectlyEnteredMatrix() {
        SquareMatrix mat = new SquareMatrix(new double[][]{{1, 2, 3}, {4, 5}, {6}});
        double det = MatrixDeterminant.calculation(mat);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canCheckHalfEmptyMatrix() {
        SquareMatrix mat = new SquareMatrix(new double[][]{{}, {}, {1, 0, 1, 0, 1}, {}, {}});
        double det = MatrixDeterminant.calculation(mat);
    }

    @Test
     public void canCreateSubMatrix() {

        SquareMatrix mat = new SquareMatrix(new double[][]{{1, 0, 0}, {0, 1, 1}, {0, 1, 6}});
        SquareMatrix shortMatrix = new SquareMatrix(new double[][]{{1, 1}, {1, 6}});

        SquareMatrix subMat = MatrixDeterminant.createSubMatrix(mat, 0, 0);

        assertTrue(subMat.getValueAt(0, 0) == shortMatrix.getValueAt(0, 0)
                        && subMat.getValueAt(0, 1) == shortMatrix.getValueAt(0, 1)
                        && subMat.getValueAt(1, 0) == shortMatrix.getValueAt(1, 0)
                        && subMat.getValueAt(1, 1) == shortMatrix.getValueAt(1, 1)
        );
    }

    @Test
    public void canCreateFirstOrderSubMatrix() {
        SquareMatrix mat = new SquareMatrix(new double[][]{{1, 2}, {3, -1}});
        SquareMatrix subMat = MatrixDeterminant.createSubMatrix(mat, 0, 1);
        assertTrue(subMat.getValueAt(0, 0) == 3 && subMat.getCountRows() == 1);
    }

    @Test
    public void canCreateEmptiesSubMatrix() {
        SquareMatrix mat = new SquareMatrix(new double[][]{{-10}});
        SquareMatrix subMat = MatrixDeterminant.createSubMatrix(mat, 0, 0);
        assertTrue(subMat.getCountRows() == 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void cannotCreateSubMatrixIfDefaultMatrixIsEmpty() {
        SquareMatrix mat = new SquareMatrix(0);
        SquareMatrix subMat = MatrixDeterminant.createSubMatrix(mat, 0, 0);
    }


}
