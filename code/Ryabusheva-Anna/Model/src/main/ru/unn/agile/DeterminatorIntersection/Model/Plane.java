package ru.unn.agile.DeterminatorIntersection.Model;

public class Plane {
    private double coefA;
    private double coefB;
    private double coefC;
    private double coefD;

    Plane(double a, double b, double c, double d) {
        this.coefA = a;
        this.coefB = b;
        this.coefC = c;
        this.coefD = d;
    }

    public boolean isCorrect() {
        return !(coefA == 0 && coefB == 0 && coefC == 0 && coefD != 0);
    }

    public Point3D getNormalVector() {
        return new Point3D(coefA, coefB, coefC);
    }

    public boolean isPointBelongPlane(Point3D point) {
        return (point.scalarMultiplication(getNormalVector()) == coefD);
    }
}
