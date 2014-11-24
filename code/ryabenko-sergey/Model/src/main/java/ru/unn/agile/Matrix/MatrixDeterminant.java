package ru.unn.agile.Matrix;

public final class MatrixDeterminant {
    private final double sum;

    public MatrixDeterminant(final SquareMatrix matrix) {
        sum = calculation(matrix);
    }

    public static double calculation(final SquareMatrix matrix) {
        double sum = 0;
        if (matrix.getCountRows() == 0) {
            throw new IllegalArgumentException();
        }

        if (matrix.getCountRows() == 1) {
            return matrix.getValueAt(0, 0);
        }

        if (matrix.getCountRows() == 2) {
            return matrix.getValueAt(0, 0) * matrix.getValueAt(1, 1) - matrix.getValueAt(0, 1)
                    * matrix.getValueAt(1, 0);
        }

        for (int i = 0; i < matrix.getCountRows(); i++) {
            sum += changeSign(i) * matrix.getValueAt(0, i)
                    * calculation(createSubMatrix(matrix, 0, i));
        }
        return sum;
    }

    public static SquareMatrix createSubMatrix(final SquareMatrix matrix,
                                         final int excludingRow, final int excludingCol) {
        SquareMatrix mat = new SquareMatrix(matrix.getCountRows() - 1);
        int row = -1;

        for (int i = 0; i < matrix.getCountRows(); i++) {
            if (i == excludingRow) {
                continue;
            }
            row++;
            int col = -1;
            for (int j = 0; j < matrix.getCountRows(); j++) {
                if (j == excludingCol) {
                    continue;
                }
                mat.setValueAt(row, ++col, matrix.getValueAt(i, j));
            }
        }
        return mat;
    }

    public double getSum() {
        return sum;
    }

    private static int changeSign(final int i) {
        if (i % 2 == 0) {
            return 1;
        }
        return -1;
    }

}
