package ru.unn.agile.Vector3D.model;



public class Vector3D {

    public Vector3D(double coordinateX, double coordinateY, double coordinateZ) {
        x = coordinateX;
        y = coordinateY;
        z = coordinateZ;
    }

    public double GetNorm() {
        return Math.sqrt(x*x + y*y + z*z);
    }

    public void Normalize() {
        double norm = GetNorm();
        if (norm != 0) {
            x /= norm;
            y /= norm;
            z /= norm;
        }
    }

    public static double DotProduct(Vector3D vector1, Vector3D vector2) {
        double result = vector1.GetCoordinateX() * vector2.GetCoordinateX() +
                        vector1.GetCoordinateY() * vector2.GetCoordinateY() +
                        vector1.GetCoordinateZ() * vector2.GetCoordinateZ();
        return result;
    }

    public static Vector3D CrossProduct(Vector3D vector1, Vector3D vector2) {
        double x = vector1.GetCoordinateY() * vector2.GetCoordinateZ() - vector1.GetCoordinateZ() * vector2.GetCoordinateY();
        double y = vector1.GetCoordinateZ() * vector2.GetCoordinateX() - vector1.GetCoordinateX() * vector2.GetCoordinateZ();
        double z = vector1.GetCoordinateX() * vector2.GetCoordinateY() - vector1.GetCoordinateY() * vector2.GetCoordinateX();
        return new Vector3D(x, y, z);
    }

    public double GetCoordinateX() {
        return x;
    }

    public double GetCoordinateY() {
        return y;
    }

    public double GetCoordinateZ() {
        return z;
    }

    private double x;
    private double y;
    private double z;
}
