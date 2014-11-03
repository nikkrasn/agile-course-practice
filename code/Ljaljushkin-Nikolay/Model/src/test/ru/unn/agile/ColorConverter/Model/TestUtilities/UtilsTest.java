package ru.unn.agile.ColorConverter.Model.TestUtilities;

import ru.unn.agile.ColorConverter.Model.ColorSpaces.*;

import static org.junit.Assert.*;

public final class UtilsTest {

    public static final double EPS = 0.01;

    private UtilsTest() {
    }

    public static void expectedValuesForKnownColor(final ColorSpace knownColor,
                                                   final Rgb expectedColor) {
        Rgb targetColor = (Rgb) knownColor.toColor(expectedColor.getClass());
        rgbAssert(expectedColor, targetColor);
    }

    public static void expectedValuesForKnownColor(final ColorSpace knownColor,
                                                   final Lab expectedColor) {
        Lab targetColor = (Lab) knownColor.toColor(expectedColor.getClass());
        labAssert(expectedColor, targetColor);
    }

    public static void expectedValuesForKnownColor(final ColorSpace knownColor,
                                                   final Xyz expectedColor) {
        Xyz targetColor = (Xyz) knownColor.toColor(expectedColor.getClass());
        xyzAssert(expectedColor, targetColor);
    }

    public static void expectedValuesForKnownColor(final ColorSpace knownColor,
                                                   final Hsv expectedColor) {
        Hsv targetColor = (Hsv) knownColor.toColor(expectedColor.getClass());
        hsvAssert(expectedColor, targetColor);
    }

    public static void expectedValuesForTwoKnownColors(final ColorSpace firstKnownColor,
                                                       final ColorSpace secondKnownColor,
                                                       final Xyz expectedColor) {
        Xyz firstTargetColor = (Xyz) firstKnownColor.toColor(expectedColor.getClass());
        Xyz secondTargetColor = (Xyz) secondKnownColor.toColor(expectedColor.getClass());
        xyzAssert(firstTargetColor, secondTargetColor);
    }

    public static void expectedValuesForTwoKnownColors(final ColorSpace firstKnownColor,
                                                       final ColorSpace secondKnownColor,
                                                       final Rgb expectedColor) {
        Rgb firstTargetColor = (Rgb) firstKnownColor.toColor(expectedColor.getClass());
        Rgb secondTargetColor = (Rgb) secondKnownColor.toColor(expectedColor.getClass());
        rgbAssert(firstTargetColor, secondTargetColor);
    }

    public static void expectedValuesForTwoKnownColors(final ColorSpace firstKnownColor,
                                                       final ColorSpace secondKnownColor,
                                                       final Lab expectedColor) {
        Lab firstTargetColor = (Lab) firstKnownColor.toColor(expectedColor.getClass());
        Lab secondTargetColor = (Lab) secondKnownColor.toColor(expectedColor.getClass());
        labAssert(firstTargetColor, secondTargetColor);
    }

    public static void expectedValuesForTwoKnownColors(final ColorSpace firstKnownColor,
                                                       final ColorSpace secondKnownColor,
                                                       final Hsv expectedColor) {
        Hsv firstTargetColor = (Hsv) firstKnownColor.toColor(expectedColor.getClass());
        Hsv secondTargetColor = (Hsv) secondKnownColor.toColor(expectedColor.getClass());
        hsvAssert(firstTargetColor, secondTargetColor);
    }

    private static void rgbAssert(final Rgb expectedColor, final Rgb targetColor) {
        assertTrue("(r)" + expectedColor.getR() + " != " + targetColor.getR(),
                isCloseEnough(expectedColor.getR(), targetColor.getR()));
        assertTrue("(g)" + expectedColor.getG() + " != " + targetColor.getG(),
                isCloseEnough(expectedColor.getG(), targetColor.getG()));
        assertTrue("(b)" + expectedColor.getB() + " != " + targetColor.getB(),
                isCloseEnough(expectedColor.getB(), targetColor.getB()));
    }

    private static void hsvAssert(final Hsv expectedColor, final Hsv targetColor) {
        assertTrue("(h)" + expectedColor.getH() + " != " + targetColor.getH(),
                isCloseEnough(expectedColor.getH(), targetColor.getH()));
        assertTrue("(s)" + expectedColor.getS() + " != " + targetColor.getS(),
                isCloseEnough(expectedColor.getS(), targetColor.getS()));
        assertTrue("(v)" + expectedColor.getV() + " != " + targetColor.getV(),
                isCloseEnough(expectedColor.getV(), targetColor.getV()));
    }

    private static void xyzAssert(final Xyz expectedColor, final Xyz targetColor) {
        assertTrue("(x)" + expectedColor.getX() + " != " + targetColor.getX(),
                isCloseEnough(expectedColor.getX(), targetColor.getX()));
        assertTrue("(y)" + expectedColor.getY() + " != " + targetColor.getY(),
                isCloseEnough(expectedColor.getY(), targetColor.getY()));
        assertTrue("(z)" + expectedColor.getZ() + " != " + targetColor.getZ(),
                isCloseEnough(expectedColor.getZ(), targetColor.getZ()));
    }

    private static void labAssert(final Lab expectedColor, final Lab targetColor) {
        assertTrue("(l)" + expectedColor.getL() + " != " + targetColor.getL(),
                isCloseEnough(expectedColor.getL(), targetColor.getL()));
        assertTrue("(a)" + expectedColor.getA() + " != " + targetColor.getA(),
                isCloseEnough(expectedColor.getA(), targetColor.getA()));
        assertTrue("(b)" + expectedColor.getB() + " != " + targetColor.getB(),
                isCloseEnough(expectedColor.getB(), targetColor.getB()));
    }

    private static boolean isCloseEnough(final double a, final double b) {
        double difference = Math.abs(a * EPS);
        if (a != 0.0 && b != 0.0) {
            return Math.abs(a - b) <= difference;
        } else {
            return Math.abs(a - b) <= EPS;
        }
    }
}
