package ru.unn.agile.ColorConverter.Model;

import static org.junit.Assert.*;

public final class UtilsTest {

    public static final double EPS = 0.01;

    private UtilsTest() {
    }

    protected static void expectedValuesForKnownColor(final ColorSpace knownColor,
                                                      final Rgb expectedColor) {
        Rgb targetColor = (Rgb) knownColor.toColor(expectedColor.getClass());
        rgbAssert(expectedColor, targetColor);
    }

    protected static void expectedValuesForKnownColor(final ColorSpace knownColor,
                                                      final Lab expectedColor) {
        Lab targetColor = (Lab) knownColor.toColor(expectedColor.getClass());
        labAssert(expectedColor, targetColor);
    }

    protected static void expectedValuesForKnownColor(final ColorSpace knownColor,
                                                      final Xyz expectedColor) {
        Xyz targetColor = (Xyz) knownColor.toColor(expectedColor.getClass());
        xyzAssert(expectedColor, targetColor);
    }

    protected static void expectedValuesForKnownColor(final ColorSpace knownColor,
                                                      final Hsv expectedColor) {
        Hsv targetColor = (Hsv) knownColor.toColor(expectedColor.getClass());
        hsvAssert(expectedColor, targetColor);
    }

    protected static void rgbAssert(final Rgb expectedColor, final Rgb targetColor) {
        assertTrue("(r)" + expectedColor.getR() + " != " + targetColor.getR(),
                isCloseEnough(expectedColor.getR(), targetColor.getR()));
        assertTrue("(g)" + expectedColor.getG() + " != " + targetColor.getG(),
                isCloseEnough(expectedColor.getG(), targetColor.getG()));
        assertTrue("(b)" + expectedColor.getB() + " != " + targetColor.getB(),
                isCloseEnough(expectedColor.getB(), targetColor.getB()));
    }

    protected static void hsvAssert(final Hsv expectedColor, final Hsv targetColor) {
        assertTrue("(h)" + expectedColor.getH() + " != " + targetColor.getH(),
                isCloseEnough(expectedColor.getH(), targetColor.getH()));
        assertTrue("(s)" + expectedColor.getS() + " != " + targetColor.getS(),
                isCloseEnough(expectedColor.getS(), targetColor.getS()));
        assertTrue("(v)" + expectedColor.getV() + " != " + targetColor.getV(),
                isCloseEnough(expectedColor.getV(), targetColor.getV()));
    }

    protected static void xyzAssert(final Xyz expectedColor, final Xyz targetColor) {
        assertTrue("(x)" + expectedColor.getX() + " != " + targetColor.getX(),
                isCloseEnough(expectedColor.getX(), targetColor.getX()));
        assertTrue("(y)" + expectedColor.getY() + " != " + targetColor.getY(),
                isCloseEnough(expectedColor.getY(), targetColor.getY()));
        assertTrue("(z)" + expectedColor.getZ() + " != " + targetColor.getZ(),
                isCloseEnough(expectedColor.getZ(), targetColor.getZ()));
    }

    protected static void labAssert(final Lab expectedColor, final Lab targetColor) {
        assertTrue("(l)" + expectedColor.getL() + " != " + targetColor.getL(),
                isCloseEnough(expectedColor.getL(), targetColor.getL()));
        assertTrue("(a)" + expectedColor.getA() + " != " + targetColor.getA(),
                isCloseEnough(expectedColor.getA(), targetColor.getA()));
        assertTrue("(b)" + expectedColor.getB() + " != " + targetColor.getB(),
                isCloseEnough(expectedColor.getB(), targetColor.getB()));
    }

    protected static boolean isCloseEnough(final double a, final double b) {
        double difference = Math.abs(a * EPS);
        if (a != 0.0 && b != 0.0) {
            return Math.abs(a - b) <= difference;
        } else {
            return Math.abs(a - b) <= EPS;
        }
    }


}
