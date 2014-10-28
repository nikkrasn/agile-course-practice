package ru.unn.agile.ColorConverter.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class RgbTest {

    @Test
    public void canConvertBlackRgbToHsv() {
        Rgb rgbColor = new Rgb(0, 0, 0);
        Hsv expectedColor = new Hsv(0, 0, 0);

        expectedValuesForRgbColor(rgbColor, expectedColor);
    }

    @Test
    public void canConvertWhiteRgbToHsv() {
        Rgb rgbColor = new Rgb(255, 255, 255);
        Hsv expectedColor = new Hsv(0, 0, 1);

        expectedValuesForRgbColor(rgbColor, expectedColor);
    }

    @Test
    public void canConvertPurpleRgbToHsv() {
        Rgb rgbColor = new Rgb(90, 0, 157);
        Hsv expectedColor = new Hsv(274, 1, 0.62);

        expectedValuesForRgbColor(rgbColor, expectedColor);
    }

    private static void expectedValuesForRgbColor(final Rgb rgbColor, final Hsv expectedColor)
    {
        Hsv targetColor = rgbColor.toHsv();

        assertTrue("(h)" + expectedColor.getR() + " != " + targetColor.getR(),
                isCloseEnough(expectedColor.getR(), targetColor.getR()));
        assertTrue("(s)" + expectedColor.getG() + " != " + targetColor.getG(),
                isCloseEnough(expectedColor.getG(), targetColor.getG()));
        assertTrue("(v)" + expectedColor.getB() + " != " + targetColor.getB(),
                isCloseEnough(expectedColor.getB(), targetColor.getB()));
    }

    private static boolean isCloseEnough(final double a, final double b) {
        double difference = Math.abs(a * .01);
        return Math.abs(a - b) <= difference;
    }

}
