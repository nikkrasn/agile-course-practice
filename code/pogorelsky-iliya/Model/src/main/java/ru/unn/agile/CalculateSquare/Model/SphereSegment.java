package ru.unn.agile.CalculateSquare.Model;

public class SphereSegment {
    private final double radius, height;
    private static final int COEFF_SQUARE = 2;

    public SphereSegment(final double radius, final double height) {
        this.radius = radius;
        this.height = height;
    }

    public double calculateSquare() {
        return COEFF_SQUARE * Math.PI * radius * height;
    }
}
