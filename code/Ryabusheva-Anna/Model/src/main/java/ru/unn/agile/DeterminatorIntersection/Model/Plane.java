package ru.unn.agile.DeterminatorIntersection.Model;

public class Plane {
    private final double coefA;
    private final double coefB;
    private final double coefC;
    private final double coefD;

    public Plane(final double a, final double b, final double c, final double d) {
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

    public boolean isPointBelongPlane(final Point3D point) {
        return point.scalarMultiplication(getNormalVector()) == coefD;
    }
}
