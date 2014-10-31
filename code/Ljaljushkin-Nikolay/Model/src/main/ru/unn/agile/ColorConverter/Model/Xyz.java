package ru.unn.agile.ColorConverter.Model;

public class Xyz {

    public static final double[][] TRANSFORM_MAT = {
            {3.2404542, -1.5371385, -0.4985314},
            {-0.9692660, 1.8760108, 0.0415560},
            {0.0556434, -0.2040259, 1.05722252}
    };

    public static final double POWER = 2.4;
    public static final double THRESHOLD = 0.0031308;
    public static final double XYZ_DIVIDER = 100.0;
    public static final double RGB_FACTOR = 12.92;
    public static final double FACTOR_TO_SUBTRACT = 0.055;
    public static final double FACTOR_TO_MULTIPLY = 1.055;
    public static final double MAX_RGB = 255.0;

    public static final double X_WHITE = 95.047;
    public static final double Y_WHITE = 100.000;
    public static final double Z_WHITE = 108.883;

    public static final double EPSILON = 0.008856;
    public static final double KAPPA = 903.3;
    public static final double CUBIC_POWER = 3.0;

    private final double x;
    private final double y;
    private final double z;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public static Xyz getWhiteReference() {
        return new Xyz(X_WHITE, Y_WHITE, Z_WHITE);
    }

    Xyz(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Rgb toRgb() {
        double xx = x / XYZ_DIVIDER;
        double yy = y / XYZ_DIVIDER;
        double zz = z / XYZ_DIVIDER;

        double r = xx * TRANSFORM_MAT[0][0] + yy * TRANSFORM_MAT[0][1] + zz * TRANSFORM_MAT[0][2];
        double g = xx * TRANSFORM_MAT[1][0] + yy * TRANSFORM_MAT[1][1] + zz * TRANSFORM_MAT[1][2];
        double b = xx * TRANSFORM_MAT[2][0] + yy * TRANSFORM_MAT[2][1] + zz * TRANSFORM_MAT[2][2];

        r = r > THRESHOLD
                ? FACTOR_TO_MULTIPLY * Math.pow(r, 1 / POWER) - FACTOR_TO_SUBTRACT : RGB_FACTOR * r;
        g = g > THRESHOLD
                ? FACTOR_TO_MULTIPLY * Math.pow(g, 1 / POWER) - FACTOR_TO_SUBTRACT : RGB_FACTOR * g;
        b = b > THRESHOLD
                ? FACTOR_TO_MULTIPLY * Math.pow(b, 1 / POWER) - FACTOR_TO_SUBTRACT : RGB_FACTOR * b;

        return new Rgb(toDigital(r), toDigital(g), toDigital(b));
    }

    public Lab toLab() {

        Xyz white = Xyz.getWhiteReference();
        double xx = pivotXyz(x / white.getX());
        double yy = pivotXyz(y / white.getY());
        double zz = pivotXyz(z / white.getZ());

        double l = Math.max(0, Lab.DENOMINATOR * yy - Lab.NOMINATOR);
        double a = Lab.A_DIVIDER * (xx - yy);
        double b = Lab.B_DIVIDER * (yy - zz);

        return new Lab(l, a, b);
    }

    private static double toDigital(final double n) {
        double result = MAX_RGB * n;
        if (result < 0) {
            return 0;
        }
        if (result > MAX_RGB) {
            return MAX_RGB;
        }
        return result;
    }

    private static double pivotXyz(final double n) {
        return n > EPSILON ? cubicRoot(n) : (KAPPA * n + Lab.NOMINATOR) / Lab.DENOMINATOR;
    }

    private static double cubicRoot(final double n) {
        return Math.pow(n, 1.0 / CUBIC_POWER);
    }
}
