package ru.unn.agile.CalculateSquare.Model;

public class Cylinder {

    private final double radius, height;
    private static final int CONSTANTA = 2;

    public Cylinder(final double radius, final double height) {
        this.radius = radius;
        this.height = height;
    }

    public double calculateSquare() {
        return CONSTANTA * Math.PI * radius * (height + radius);
    }
}

