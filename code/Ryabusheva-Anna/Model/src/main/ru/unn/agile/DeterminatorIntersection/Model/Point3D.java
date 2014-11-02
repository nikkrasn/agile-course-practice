package ru.unn.agile.DeterminatorIntersection.Model;

public class Point3D {
    private double x;
    private double y;
    private double z;

    Point3D(double inputX, double inputY, double inputZ) {
        this.x = inputX;
        this.y = inputY;
        this.z = inputZ;
    }

    public boolean isNullPoint() {
        return (x == 0 && y == 0 && z == 0);
    }

    public double scalarMultiplication(Point3D mPoint) {
        return x * mPoint.x + y * mPoint.y + z * mPoint.z;
    }
}
