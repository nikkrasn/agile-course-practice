package ru.unn.agile.Matrix;

public final class MatrixDeterminant {

    private MatrixDeterminant() { }

    public static double determinant(final Matrix matrix) {
        double sum = 0;
        if (matrix.getSize() == 0) {
            throw new IllegalArgumentException();
        }

        if (matrix.getSize() == 1) {
            return matrix.getValueAt(0, 0);
        }

        if (matrix.getSize() == 2) {
            return matrix.getValueAt(0, 0) * matrix.getValueAt(1, 1) - matrix.getValueAt(0, 1)
                    * matrix.getValueAt(1, 0);
        }

        for (int i = 0; i < matrix.getCountColumns(); i++) {
            sum += changeSign(i) * matrix.getValueAt(0, i)
                    * determinant(createSubMatrix(matrix, 0, i));
        }
        return sum;
    }

    private static int changeSign(final int i) {
        if (i % 2 == 0) {
            return 1;
        }
        return -1;
    }

    public static Matrix createSubMatrix(final Matrix matrix,
                                         final int excludingRow, final int excludingCol) {
        Matrix mat = new Matrix(matrix.getCountRows() - 1);
        int row = -1;

        for (int i = 0; i < matrix.getCountRows(); i++) {
            if (i == excludingRow) {
                continue;
            }
            row++;
            int col = -1;
            for (int j = 0; j < matrix.getCountColumns(); j++) {
                if (j == excludingCol) {
                    continue;
                }
                mat.setValueAt(row, ++col, matrix.getValueAt(i, j));
            }
        }
        return mat;
    }

}
