package ru.unn.agile.Matrix;

public class Matrix {

    private final int countRows;
    private final int countColumns;
    private double[][] data;

    public Matrix(final int countRows) {
        if (countRows < 0) {
            throw new IllegalArgumentException();
        }
        this.countRows = countRows;
        this.countColumns = countRows;
        data = new double[countRows][countColumns];
    }

    public Matrix(final double[][] data) {
        if (data.length == 0) {
            throw new IllegalArgumentException();
        }
        this.countRows = data.length;
        if (countRows != data[0].length) {
            throw new IllegalArgumentException();
        }
        checkLengthRows(data);
        this.countColumns = countRows;
        this.data = new double[countRows][countColumns];
        initializeMatrix(data);

    }

    public void initializeMatrix(final double[][] data) {
        for (int i = 0; i < countRows; i++) {
            for (int j = 0; j < countColumns; j++) {
                    this.data[i][j] = data[i][j];
            }
        }
    }

    public boolean isSquare() {
        return countRows == countColumns;
    }

    public int getSize() {
        if (isSquare()) {
            return countRows;
        }
        throw new IllegalArgumentException();
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

    public int getCountColumns() {
        return countColumns;
    }

    public void setValueAt(final int row, final int col, final double value) {
        data[row][col] = value;
    }

    public double getValueAt(final int row, final int col) {
        return data[row][col];
    }

}
