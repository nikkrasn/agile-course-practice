package ru.unn.agile.ColorConverter.Model;

public class Rgb {

    // Observer. = 2°, Illuminant = D65
    public static final double[][] TRANS_MAT_TO_XYZ = {
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
        double eps = EPS;
        double rr = r / MAX_RGB;
        double gg = g / MAX_RGB;
        double bb = b / MAX_RGB;

        double max = Math.max(rr, Math.max(gg, bb));
        double min = Math.min(rr, Math.min(gg, bb));
        double delta = max - min;

        double h = 0;
        if (delta >= EPS) {
            if (max == rr) {
                h = DEGREES * ((gg - bb) / delta % R_DIVIDER);
            } else if (max == gg) {
                h = DEGREES * ((bb - rr) / delta + G_OFFSET);
            } else if (max == bb) {
                h = DEGREES * ((rr - gg) / delta + B_OFFSET);
            }
        }

        double s = max <= 0 ? 0 : 1 - min / max;
        double v = max;

        return new Hsv(h, s, v);
    }

    public Xyz toXyz() {
        double rr = pivotRgb(r / MAX_RGB);
        double gg = pivotRgb(g / MAX_RGB);
        double bb = pivotRgb(b / MAX_RGB);

        double x = 0;
        x += rr * TRANS_MAT_TO_XYZ[0][0];
        x += gg * TRANS_MAT_TO_XYZ[0][1];
        x += bb * TRANS_MAT_TO_XYZ[0][2];
        double y = 0;
        y += rr * TRANS_MAT_TO_XYZ[1][0];
        y += gg * TRANS_MAT_TO_XYZ[1][1];
        y += bb * TRANS_MAT_TO_XYZ[1][2];
        double z = 0;
        z += rr * TRANS_MAT_TO_XYZ[2][0];
        z += gg * TRANS_MAT_TO_XYZ[2][1];
        z += bb * TRANS_MAT_TO_XYZ[2][2];

        return new Xyz(x, y, z);
    }

    public Lab toLab() {
        return this.toXyz().toLab();
    }

    private static double pivotRgb(final double n) {
        return Xyz.XYZ_DIVIDER
                * (n > THRESHOLD
                ? Math.pow((n + Xyz.FACTOR_TO_SUBTRACT) / Xyz.FACTOR_TO_MULTIPLY, Xyz.POWER)
                : n / Xyz.RGB_FACTOR);
    }
}
