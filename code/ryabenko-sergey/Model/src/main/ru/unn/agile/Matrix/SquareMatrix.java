package ru.unn.agile.Matrix;

public class SquareMatrix {

    private final int countRows;
    private double[][] data;

    public SquareMatrix(final int countRows) {
        if (countRows < 0) {
            throw new IllegalArgumentException();
        }
        this.countRows = countRows;
        data = new double[countRows][countRows];
    }

    public SquareMatrix(final double[][] data) {
        if (data.length == 0) {
            throw new IllegalArgumentException();
        }
        this.countRows = data.length;
        if (countRows != data[0].length) {
            throw new IllegalArgumentException();
        }
        checkLengthRows(data);
        this.data = new double[countRows][countRows];
        initializeMatrix(data);

    }

    public void initializeMatrix(final double[][] data) {
        for (int i = 0; i < countRows; i++) {
            for (int j = 0; j < countRows; j++) {
                this.data[i][j] = data[i][j];
            }
        }
    }

    public int getCountRows() {
        return countRows;
    }

    public void checkLengthRows(final double[][] data) {
        int row = data.length;
        for (int i = 1; i < row; i++) {
            if (data[i].length != data[0].length) {
                throw new IllegalArgumentException();
            }
        }
    }

    public void setValueAt(final int row, final int col, final double value) {
        data[row][col] = value;
    }

    public double getValueAt(final int row, final int col) {
        return data[row][col];
    }

}
