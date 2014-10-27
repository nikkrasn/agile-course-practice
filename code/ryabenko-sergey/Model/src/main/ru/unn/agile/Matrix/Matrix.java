package ru.unn.agile.Matrix;

public class Matrix {

    private final int countRows;
    private final int countColumns;
    private double[][] data;

    public Matrix(final int countRows, final int countColumns) {
        this.countRows = countRows;
        this.countColumns = countColumns;
        data = new double[countRows][countColumns];
    }

    public Matrix(final double[][] data) {
        this.countRows = data.length;
        this.countColumns = data[0].length;
        this.data = new double[countRows][countColumns];
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
