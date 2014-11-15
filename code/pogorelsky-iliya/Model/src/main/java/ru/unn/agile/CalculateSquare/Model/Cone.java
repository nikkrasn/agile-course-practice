package ru.unn.agile.CalculateSquare.Model;

public class Cone {

    private final double radius, height;

    public Cone(final double radius, final double height) {
        this.radius = radius;
        this.height = height;
    }

    public double calculateSquare() {
        return Math.PI * radius * (radius + Math.sqrt(Math.pow(radius, 2) + Math.pow(height, 2)));
    }
}

