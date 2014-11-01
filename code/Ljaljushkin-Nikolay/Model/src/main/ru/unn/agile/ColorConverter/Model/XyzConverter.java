package ru.unn.agile.ColorConverter.Model;

public final class XyzConverter {

    public static final double[][] TRANS_MAT_TO_RGB = {
            {3.2404542, -1.5371385, -0.4985314},
            {-0.9692660, 1.8760108, 0.0415560},
            {0.0556434, -0.2040259, 1.05722252}
    };

    public static final double[][] TRANS_MAT_TO_XYZ = {
            {0.4124, 0.3576, 0.1805},
            {0.2126, 0.7152, 0.0722},
            {0.0193, 0.1192, 0.9505}
    };

    public static final double PIVOT_POWER = 2.4;

    public static final double XYZ_THRESHOLD = 0.0031308;
    public static final double RGB_THRESHOLD = 0.04045;

    public static final double XYZ_DIVIDER = 100.0;

    public static final double FACTOR_TO_SUBTRACT = 0.055;
    public static final double FACTOR_TO_MULTIPLY = 1.055;

    public static final double RGB_FACTOR = 12.92;
    public static final double MAX_RGB = 255.0;

    public static final double X_WHITE = 95.05;
    public static final double Y_WHITE = 100.0;
    public static final double Z_WHITE = 108.9;

    private XyzConverter() {
    }

    public static void fromRgbToColorSpace(final Rgb srcColor, final Xyz dstColor) {

        double pivotedR = pivotRgb(srcColor.getR() / MAX_RGB);
        double pivotedG = pivotRgb(srcColor.getG() / MAX_RGB);
        double pivotedB = pivotRgb(srcColor.getB() / MAX_RGB);

        double x = applyTransformToXYZ(pivotedR, pivotedG, pivotedB, 0);
        double y = applyTransformToXYZ(pivotedR, pivotedG, pivotedB, 1);
        double z = applyTransformToXYZ(pivotedR, pivotedG, pivotedB, 2);

        dstColor.setX(x);
        dstColor.setY(y);
        dstColor.setZ(z);
    }

    public static Rgb toRgbColor(final Xyz srcColor) {

        double x = srcColor.getX();
        double y = srcColor.getY();
        double z = srcColor.getZ();

        double r = convertToRgbComponent(x, y, z, 0);
        double g = convertToRgbComponent(x, y, z, 1);
        double b = convertToRgbComponent(x, y, z, 2);

        return new Rgb(r, g, b);
    }

    private static double pivotRgb(final double n) {
        return XYZ_DIVIDER
                * (n > RGB_THRESHOLD
                ? Math.pow((n + FACTOR_TO_SUBTRACT) / FACTOR_TO_MULTIPLY, PIVOT_POWER)
                : n / RGB_FACTOR);
    }

    private static double applyTransformToXYZ(final double r,
                                              final double g,
                                              final double b,
                                              final int row) {
        return r * TRANS_MAT_TO_XYZ[row][0]
                + g * TRANS_MAT_TO_XYZ[row][1]
                + b * TRANS_MAT_TO_XYZ[row][2];
    }

    private static double convertToRgbComponent(
            final double x, final double y, final double z, final int row) {
        double result = 0;
        result = applyTransformToRGB(x / XYZ_DIVIDER, y / XYZ_DIVIDER, z / XYZ_DIVIDER, row);
        result = getArithmeticComponent(result);
        return toDigitalRgb(result);
    }

    private static double applyTransformToRGB(final double x,
                                              final double y,
                                              final double z,
                                              final int row) {
        return x * TRANS_MAT_TO_RGB[row][0]
                + y * TRANS_MAT_TO_RGB[row][1]
                + z * TRANS_MAT_TO_RGB[row][2];
    }

    private static double getArithmeticComponent(final double component) {
        return component > XYZ_THRESHOLD
                ? FACTOR_TO_MULTIPLY * Math.pow(component, 1 / PIVOT_POWER) - FACTOR_TO_SUBTRACT
                : RGB_FACTOR * component;
    }

    private static double toDigitalRgb(final double n) {
        double result = MAX_RGB * n;
        if (result < 0) {
            return 0;
        }
        if (result > MAX_RGB) {
            return MAX_RGB;
        }
        return result;
    }

    public static Xyz getWhiteReference() {
        return new Xyz(X_WHITE, Y_WHITE, Z_WHITE);
    }

}
