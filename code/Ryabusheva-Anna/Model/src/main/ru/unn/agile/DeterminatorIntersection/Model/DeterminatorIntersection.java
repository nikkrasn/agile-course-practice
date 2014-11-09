package ru.unn.agile.DeterminatorIntersection.Model;

public class DeterminatorIntersection {
    private final Line3D line;
    private final Plane plane;

    DeterminatorIntersection(final Line3D inputLine, final Plane inputPlane) {
        this.line = inputLine;
        this.plane = inputPlane;
    }

    public Line3D getLine() {
        return line;
    }

    public Plane getPlane() {
        return plane;
    }

    public boolean isIntersection() {
        if (!isCorrectParams()) {
            return false;
        }
        return isLineBelongPlane() || isNotNullScalarMultiplication();
    }

    private boolean isCorrectParams() {
        return line.isCorrect() && plane.isCorrect();
    }

    private boolean isLineBelongPlane() {
        return plane.isPointBelongPlane(line.getPoint());
    }

    private boolean isNotNullScalarMultiplication() {
        Point3D planeNormal = plane.getNormalVector();
        return planeNormal.scalarMultiplication(line.getVector()) != 0;
    }
}
