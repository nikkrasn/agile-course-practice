package ru.unn.agile.ColorConverter.model.Converters;

import ru.unn.agile.ColorConverter.model.ColorSpaces.*;

public final class LabConverter extends BaseConverter {

    public static final double EPSILON = 0.008856;
    public static final double KAPPA = 903.3;

    public static final double CUBIC_POWER = 3.0;

    public static final double B_DIVIDER = 200.0;
    public static final double A_DIVIDER = 500.0;

    public static final double NOMINATOR = 16.0;
    public static final double DENOMINATOR = 116.0;

    public static final double FACTOR = 7.787;

    @Override
    public void fromRgb(final Rgb srcColor, final ColorSpace3D dstColor) {

        Xyz xyz = new Xyz();
        xyz.initialize(srcColor);

        Xyz white = XyzConverter.getWhiteReference();
        double pivotedX = pivotXyz(xyz.getFirstChannel() / white.getFirstChannel());
        double pivotedY = pivotXyz(xyz.getSecondChannel() / white.getSecondChannel());
        double pivotedZ = pivotXyz(xyz.getThirdChannel() / white.getThirdChannel());

        double l = Math.max(0, DENOMINATOR * pivotedY - NOMINATOR);
        double a = A_DIVIDER * (pivotedX - pivotedY);
        double b = B_DIVIDER * (pivotedY - pivotedZ);

        dstColor.setChannels(l, a, b);
    }

    @Override
    public Rgb toRgbColor(final ColorSpace3D srcColor) {

        double l = srcColor.getFirstChannel();
        double a = srcColor.getSecondChannel();
        double b = srcColor.getThirdChannel();

        double y = (l + NOMINATOR) / DENOMINATOR;
        double x = a / A_DIVIDER + y;
        double z = y - b / B_DIVIDER;

        Xyz white = XyzConverter.getWhiteReference();
        double cubedX = x * x * x;
        double cubedZ = z * z * z;

        x = (cubedX > EPSILON ? cubedX : (x - NOMINATOR / DENOMINATOR) / FACTOR);
        y = (l > (KAPPA * EPSILON)
                ? Math.pow((l + NOMINATOR) / DENOMINATOR, CUBIC_POWER) : l / KAPPA);
        z = (cubedZ > EPSILON ? cubedZ : (z - NOMINATOR / DENOMINATOR) / FACTOR);

        Xyz xyz = new Xyz(
                white.getFirstChannel() * x,
                white.getSecondChannel() * y,
                white.getThirdChannel() * z);

        return xyz.toRgb();
    }

    private static double pivotXyz(final double n) {
        return n > EPSILON ? cubicRoot(n) : (KAPPA * n + NOMINATOR) / DENOMINATOR;
    }

    private static double cubicRoot(final double n) {
        return Math.pow(n, 1.0 / CUBIC_POWER);
    }
}
