package ru.unn.agile.ColorConverter.Model;

public class Lab {
    public static final int POWER = 3;
    public static final double B_DIVIDER = 200.0;
    public static final double A_DIVIDER = 500.0;
    public static final double NOMINATOR = 16.0;
    public static final double DENOMINATOR = 116.0;
    public static final double FACTOR = 7.787;
    private final double l;
    private final double a;
    private final double b;

    public double getL() {
        return l;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    Lab(final double l, final double a, final double b) {
        this.l = l;
        this.a = a;
        this.b = b;
    }

    public Xyz toXyz() {
        double y = (l + NOMINATOR) / DENOMINATOR;
        double x = a / A_DIVIDER + y;
        double z = y - b / B_DIVIDER;

        Xyz white = Xyz.getWhiteReference();
        double cubedX = x * x * x;
        double cubedZ = z * z * z;

        x = (cubedX > Xyz.EPSILON ? cubedX : (x - NOMINATOR / DENOMINATOR) / FACTOR);
        y = (l > (Xyz.KAPPA * Xyz.EPSILON)
                ? Math.pow((l + NOMINATOR) / DENOMINATOR, POWER) : l / Xyz.KAPPA);
        z = (cubedZ > Xyz.EPSILON ? cubedZ : (z - NOMINATOR / DENOMINATOR) / FACTOR);

        return new Xyz(
                white.getX() * x,
                white.getY() * y,
                white.getZ() * z);
    }

    public Rgb toRgb() {
        return this.toXyz().toRgb();
    }

    public Hsv toHsv() {
        return this.toRgb().toHsv();
    }

}
