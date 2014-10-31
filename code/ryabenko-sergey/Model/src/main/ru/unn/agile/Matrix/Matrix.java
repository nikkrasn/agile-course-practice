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
        this.countRows = data.length;
        if (countRows < getMaxLength(data)) {
            throw new IllegalArgumentException();
        }
        this.countColumns = countRows;
        this.data = new double[countRows][countColumns];
        for (int i = 0; i < countRows; i++) {
            for (int j = 0; j < countColumns; j++) {
                this.data[i][j] = 1;
            }
        }
        initializeMatrix(data);
    }


    public void initializeMatrix(final double[][] data) {
        for (int i = 0; i < countRows; i++) {
            if (data[i].length < countColumns) {
                for (int k = data[i].length; k < countColumns; k++) {
                    this.data[i][k] = 0;
                }
            }
            for (int j = 0; j < countColumns; j++) {
                if (this.data[i][j] != 0) {
                    this.data[i][j] = data[i][j];
                }
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

    public int getMaxLength(final double[][] data) {
        int row = data.length;
        int maxLength = 0;
        for (int i = 0; i < row; i++) {
            if (data[i].length > maxLength) {
                maxLength = data[i].length;
            }
        }
        return maxLength;
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
