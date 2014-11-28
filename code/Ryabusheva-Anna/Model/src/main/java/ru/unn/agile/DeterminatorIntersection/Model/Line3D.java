package ru.unn.agile.DeterminatorIntersection.Model;

public class Line3D {
    private final Point3D point;
    private final Point3D vector;

    public Line3D(final Point3D inputPoint, final Point3D inputVector) {
        this.point = inputPoint;
        this.vector = inputVector;
    }

    public Point3D getPoint() {
        return point;
    }

    public Point3D getVector() {
        return vector;
    }

    public boolean isCorrect() {
        return !vector.isNullPoint();
    }
}
