package ru.unn.agile.ColorConverter.Model;

import org.junit.Test;
import static org.junit.Assert.*;


public class RgbTest {

    @Test
    public void canConvertWhiteRgbToHsv() {
        Rgb rgbColor = new Rgb(255, 255, 255);
        Hsv expectedColor = new Hsv(0, 0, 1);

        UtilsTest.expectedValuesForRgbColor(rgbColor, expectedColor);
    }

    @Test
    public void canConvertBlackRgbToHsv() {
        Rgb rgbColor = new Rgb(0, 0, 0);
        Hsv expectedColor = new Hsv(0, 0, 0);

        UtilsTest.expectedValuesForRgbColor(rgbColor, expectedColor);
    }

    @Test
    public void canConvertPurpleRgbToHsv() {
        Rgb rgbColor = new Rgb(90, 0, 157);
        Hsv expectedColor = new Hsv(274, 1, 0.62);

        UtilsTest.expectedValuesForRgbColor(rgbColor, expectedColor);
    }


}
