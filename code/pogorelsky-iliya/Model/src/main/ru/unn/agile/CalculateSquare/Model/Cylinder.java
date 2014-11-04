package ru.unn.agile.CalculateSquare.Model;

public class Cylinder {

    private final double radius, height;
    private static final int COEFF_CYLINDER = 2;

    public Cylinder(final double radius, final double height) {
        this.radius = radius;
        this.height = height;
    }

    public double calculateSquare() {
        return COEFF_CYLINDER * Math.PI * radius * (height + radius);
    }
}

