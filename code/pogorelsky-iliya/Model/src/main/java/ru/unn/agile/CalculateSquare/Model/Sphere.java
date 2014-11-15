package ru.unn.agile.CalculateSquare.Model;

public class Sphere {

    private final double radius;
    private static final int COEFF_SPHERE = 4;

    public Sphere(final double radius) {
        this.radius = radius;
    }

    public double calculateSquare() {
        return COEFF_SPHERE * Math.PI * Math.pow(radius, 2);
    }
}
