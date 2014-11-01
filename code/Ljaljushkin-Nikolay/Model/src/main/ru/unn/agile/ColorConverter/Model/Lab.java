package ru.unn.agile.ColorConverter.Model;

public class Lab extends ColorSpace {

    @Override
    public void initialize(final Rgb color) {
        LabConverter.fromRgbToColorSpace(color, this);
    }

    @Override
    public Rgb toRgb() {
        return LabConverter.toRgbColor(this);
    }

    private double l;
    private double a;
    private double b;

    public double getL() {
        return l;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public void setL(final double l) {
        this.l = l;
    }

    public void setA(final double a) {
        this.a = a;
    }

    public void setB(final double b) {
        this.b = b;
    }

    Lab() {
        l = 0;
        a = 0;
        b = 0;
    }

    Lab(final double l, final double a, final double b) {
        this.l = l;
        this.a = a;
        this.b = b;
    }

//    public Xyz toXyz() {
//        double y = (l + NOMINATOR) / DENOMINATOR;
//        double x = a / A_DIVIDER + y;
//        double z = y - b / B_DIVIDER;
//
//        Xyz white = Xyz.getWhiteReference();
//        double cubedX = x * x * x;
//        double cubedZ = z * z * z;
//
//        x = (cubedX > Xyz.EPSILON ? cubedX : (x - NOMINATOR / DENOMINATOR) / FACTOR);
//        y = (l > (Xyz.KAPPA * Xyz.EPSILON)
//                ? Math.pow((l + NOMINATOR) / DENOMINATOR, POWER) : l / Xyz.KAPPA);
//        z = (cubedZ > Xyz.EPSILON ? cubedZ : (z - NOMINATOR / DENOMINATOR) / FACTOR);
//
//        return new Xyz(
//                white.getX() * x,
//                white.getY() * y,
//                white.getZ() * z);
//    }
//
//    public Rgb toRgb() {
//        return this.toXyz().toRgb();
//    }
//
//    public Hsv toHsv() {
//        return this.toRgb().toHsv();
//    }

}
