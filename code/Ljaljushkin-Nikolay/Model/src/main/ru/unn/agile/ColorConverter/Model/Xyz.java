package ru.unn.agile.ColorConverter.Model;

public class Xyz extends ColorSpace {

    @Override
    public void initialize(final Rgb color) {
        XyzConverter.fromRgbToColorSpace(color, this);
    }

    @Override
    public Rgb toRgb() {
        return XyzConverter.toRgbColor(this);
    }

    private double x;
    private double y;
    private double z;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setX(final double x) {
        this.x = x;
    }

    public void setY(final double y) {
        this.y = y;
    }

    public void setZ(final double z) {
        this.z = z;
    }

    Xyz() {
        x = 0;
        y = 0;
        z = 0;
    }

    Xyz(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

//    public Rgb toRgb() {
//        double r = convertToRgbComponent(x, y, z, 0);
//        double g = convertToRgbComponent(x, y, z, 1);
//        double b = convertToRgbComponent(x, y, z, 2);
//        return new Rgb(r, g, b);
//    }

//    public Lab toLab() {
//
//        Xyz white = getWhiteReference();
//        double pivotedX = pivotXyz(x / white.getX());
//        double pivotedY = pivotXyz(y / white.getY());
//        double pivotedZ = pivotXyz(z / white.getZ());
//
//        double l = Math.max(0, Lab.DENOMINATOR * pivotedY - Lab.NOMINATOR);
//        double a = Lab.A_DIVIDER * (pivotedX - pivotedY);
//        double b = Lab.B_DIVIDER * (pivotedY - pivotedZ);
//
//        return new Lab(l, a, b);
//    }
}




