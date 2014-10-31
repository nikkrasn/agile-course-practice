package ru.unn.agile.ColorConverter.Model;

public class Xyz {

    public static final double[][] TRANS_MAT = {
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

    public static final double X_WHITE = 95.05;
    public static final double Y_WHITE = 100.000;
    public static final double Z_WHITE = 108.9;

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
        double r = convertToRgbComponent(x, y, z, 0);
        double g = convertToRgbComponent(x, y, z, 1);
        double b = convertToRgbComponent(x, y, z, 2);
        return new Rgb(r, g, b);
    }

    private double applyTransformationMatrix(
            final double x, final double y, final double z, final int row) {
        return x * TRANS_MAT[row][0] + y * TRANS_MAT[row][1] + z * TRANS_MAT[row][2];
    }

    private double getArithmeticComponent(final double component) {
        return component > THRESHOLD
                ? FACTOR_TO_MULTIPLY * Math.pow(component, 1 / POWER) - FACTOR_TO_SUBTRACT
                : RGB_FACTOR * component;
    }

    private double convertToRgbComponent(
            final double x, final double y, final double z, final int row) {
        double result = 0;
        result = applyTransformationMatrix(x / XYZ_DIVIDER, y / XYZ_DIVIDER, z / XYZ_DIVIDER, row);
        result = getArithmeticComponent(result);
        return toDigital(result);
    }

    public Lab toLab() {

        Xyz white = Xyz.getWhiteReference();
        double pivotedX = pivotXyz(x / white.getX());
        double pivotedY = pivotXyz(y / white.getY());
        double pivotedZ = pivotXyz(z / white.getZ());

        double l = Math.max(0, Lab.DENOMINATOR * pivotedY - Lab.NOMINATOR);
        double a = Lab.A_DIVIDER * (pivotedX - pivotedY);
        double b = Lab.B_DIVIDER * (pivotedY - pivotedZ);

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
