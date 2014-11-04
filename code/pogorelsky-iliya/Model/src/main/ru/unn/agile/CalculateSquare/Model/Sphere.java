package ru.unn.agile.CalculateSquare.Model;

public class Sphere {

    private final double radius;
    private static final int CONSTANTA = 4;

    public Sphere(final double radius) {
        this.radius = radius;
    }

    public double calculateSquare() {
        return CONSTANTA * Math.PI * Math.pow(radius, 2);
    }
}
