package ru.unn.agile.Vector3D.Model;



public class Vector3D {

    private double x;
    private double y;
    private double z;

    public Vector3D(final double coordinateX, final double coordinateY, final double coordinateZ) {
        x = coordinateX;
        y = coordinateY;
        z = coordinateZ;
    }

    public double getNorm() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public void normalize() {
        double norm = getNorm();
        if (norm != 0) {
            x /= norm;
            y /= norm;
            z /= norm;
        }
    }

    public static double dotProduct(final Vector3D vector1, final Vector3D vector2) {
        double result = vector1.getCoordinateX() * vector2.getCoordinateX()
                      + vector1.getCoordinateY() * vector2.getCoordinateY()
                      + vector1.getCoordinateZ() * vector2.getCoordinateZ();
        return result;
    }

    public static Vector3D crossProduct(final Vector3D vector1, final Vector3D vector2) {
        double x = vector1.getCoordinateY() * vector2.getCoordinateZ()
                 - vector1.getCoordinateZ() * vector2.getCoordinateY();

        double y = vector1.getCoordinateZ() * vector2.getCoordinateX()
                 - vector1.getCoordinateX() * vector2.getCoordinateZ();

        double z = vector1.getCoordinateX() * vector2.getCoordinateY()
                 - vector1.getCoordinateY() * vector2.getCoordinateX();

        return new Vector3D(x, y, z);
    }

    public double getCoordinateX() {
        return x;
    }

    public double getCoordinateY() {
        return y;
    }

    public double getCoordinateZ() {
        return z;
    }

}
