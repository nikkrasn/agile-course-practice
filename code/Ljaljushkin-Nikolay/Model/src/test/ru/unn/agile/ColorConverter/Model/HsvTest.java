package ru.unn.agile.ColorConverter.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class HsvTest {

    @Test
    public void canConvertWhiteHsvToRgb() {
        Hsv hsvColor = new Hsv(0, 0, 1);
        Rgb expectedColor = new Rgb(255, 255, 255);

        UtilsTest.expectedValuesForHsvColor(hsvColor, expectedColor);
    }

    @Test
    public void canConvertBlackHsvToRgb() {
        Hsv hsvColor = new Hsv(274, 1, 0.62);
        Rgb expectedColor = new Rgb(90, 0, 157);

        UtilsTest.expectedValuesForHsvColor(hsvColor, expectedColor);
    }

    @Test
    public void canConvertAquamarineHsvToRgb() {
        Hsv hsvColor = new Hsv(160, 0.5, 1);
        Rgb expectedColor = new Rgb(127, 255, 212);

        UtilsTest.expectedValuesForHsvColor(hsvColor, expectedColor);
    }

}
