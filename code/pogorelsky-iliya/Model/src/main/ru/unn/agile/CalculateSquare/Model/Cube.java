package ru.unn.agile.CalculateSquare.Model;

public class Cube {

    private final double edge;
    private static final int CONSTANTA = 6;

    public Cube(final double edge) {
        this.edge = edge;
    }

    public double calculateSquare() {
        return CONSTANTA * Math.pow(edge, 2);
    }
}
