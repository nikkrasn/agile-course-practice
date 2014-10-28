package ru.unn.agile.ColorConverter.Model;

import static org.junit.Assert.*;

public final class UtilsTest {

    private UtilsTest() {
        //not called
    }
    public static void expectedValuesForHsvColor(final Hsv hsvColor, final Rgb expectedColor)
    {
        Rgb targetColor = hsvColor.toRgb();

        assertTrue("(r)" + expectedColor.getR() + " != " + targetColor.getR(),
                isCloseEnough(expectedColor.getR(), targetColor.getR()));
        assertTrue("(g)" + expectedColor.getG() + " != " + targetColor.getG(),
                isCloseEnough(expectedColor.getG(), targetColor.getG()));
        assertTrue("(b)" + expectedColor.getB() + " != " + targetColor.getB(),
                isCloseEnough(expectedColor.getB(), targetColor.getB()));
    }

    public static void expectedValuesForRgbColor(final Rgb rgbColor, final Hsv expectedColor)
    {
        Hsv targetColor = rgbColor.toHsv();

        assertTrue("(h)" + expectedColor.getH() + " != " + targetColor.getH(),
                isCloseEnough(expectedColor.getH(), targetColor.getH()));
        assertTrue("(s)" + expectedColor.getS() + " != " + targetColor.getS(),
                isCloseEnough(expectedColor.getS(), targetColor.getS()));
        assertTrue("(v)" + expectedColor.getV() + " != " + targetColor.getV(),
                isCloseEnough(expectedColor.getV(), targetColor.getV()));
    }

    protected static boolean isCloseEnough(final double a, final double b) {
        double difference = Math.abs(a * .01);
        return Math.abs(a - b) <= difference;
    }
}
