package ru.unn.agile.ColorConverter.Model;

public class Rgb {

    public static final double[][] TRANS_MAT = {
            {0.4124, 0.3576, 0.1805},
            {0.2126, 0.7152, 0.0722},
            {0.0193, 0.1192, 0.9505}
    };
    public static final double MAX_RGB = 255.0;
    public static final double R_DIVIDER = 6.0;
    public static final int G_OFFSET = 2;
    public static final int B_OFFSET = 4;
    public static final int DEGREES = 60;
    public static final double EPS = 0.0001;
    public static final double THRESHOLD = 0.04045;

    private final double r;
    private final double g;
    private final double b;

    public double getG() {
        return g;
    }

    public double getR() {
        return r;
    }

    public double getB() {
        return b;
    }

    public Rgb(final double r, final double g, final double b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Hsv toHsv() {
        double arithmeticR = r / MAX_RGB;
        double arithmeticG = g / MAX_RGB;
        double arithmeticB = b / MAX_RGB;

        double max = Math.max(arithmeticR, Math.max(arithmeticG, arithmeticB));
        double min = Math.min(arithmeticR, Math.min(arithmeticG, arithmeticB));
        double delta = max - min;

        double h = 0;
        if (delta >= EPS) {
            if (max == arithmeticR) {
                h = DEGREES * ((arithmeticG - arithmeticB) / delta % R_DIVIDER);
            } else if (max == arithmeticG) {
                h = DEGREES * ((arithmeticB - arithmeticR) / delta + G_OFFSET);
            } else if (max == arithmeticB) {
                h = DEGREES * ((arithmeticR - arithmeticG) / delta + B_OFFSET);
            }
        }

        double s = max <= 0 ? 0 : 1 - min / max;
        double v = max;

        return new Hsv(h, s, v);
    }

    public Xyz toXyz() {
        double pivotedR = pivotRgb(r / MAX_RGB);
        double pivotedG = pivotRgb(g / MAX_RGB);
        double pivotedB = pivotRgb(b / MAX_RGB);

        double x = applyTransform(pivotedR, pivotedG, pivotedB, 0);
        double y = applyTransform(pivotedR, pivotedG, pivotedB, 1);
        double z = applyTransform(pivotedR, pivotedG, pivotedB, 2);

        return new Xyz(x, y, z);
    }

    public Lab toLab() {
        return this.toXyz().toLab();
    }

    private double applyTransform(final double r, final double g, final double b, final int row) {
        return r * TRANS_MAT[row][0] + g * TRANS_MAT[row][1] + b * TRANS_MAT[row][2];
    }

    private static double pivotRgb(final double n) {
        return Xyz.XYZ_DIVIDER
                * (n > THRESHOLD
                ? Math.pow((n + Xyz.FACTOR_TO_SUBTRACT) / Xyz.FACTOR_TO_MULTIPLY, Xyz.POWER)
                : n / Xyz.RGB_FACTOR);
    }
}
