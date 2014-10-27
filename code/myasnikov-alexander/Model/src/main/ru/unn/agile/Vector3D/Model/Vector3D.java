package ru.unn.agile.Vector3D.model;



public class Vector3D {

    double x;
    double y;
    double z;

    public Vector3D(double value1, double value2, double value3) {
        x = value1;
        y = value2;
        z = value3;
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

    public double GetCoordinateX() {
        return x;
    }

    public double GetCoordinateY() {
        return y;
    }

    public double GetCoordinateZ() {
        return z;
    }


}
