package ru.unn.agile.CalculateSquare.Model;

public class Cube {

    private final double edge;
    private static final int COEFF_SQUARE = 6;

    public Cube(final double edge) {
        this.edge = edge;
    }

    public double calculateSquare() {
        return COEFF_SQUARE * Math.pow(edge, 2);
    }
}
