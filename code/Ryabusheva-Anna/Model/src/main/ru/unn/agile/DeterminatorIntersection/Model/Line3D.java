package ru.unn.agile.DeterminatorIntersection.Model;

public class Line3D {
    private Point3D point;
    private Point3D vector;

    Line3D(Point3D inputPoint, Point3D inputVector) {
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
