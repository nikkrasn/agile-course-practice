package ru.unn.agile.ColorConverter.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class XyzTest {

    @Test
    public void canConvertWhiteXyzToRgb() {
        Xyz xyzColor = new Xyz(95.05, 100, 108.9);
        Rgb expectedColor = new Rgb(255, 255, 255);

        UtilsTest.expectedValuesForXyzColor(xyzColor, expectedColor);
    }

    @Test
    public void canConvertBlackXyzToRgb() {
        Xyz xyzColor = new Xyz(0, 0, 0);
        Rgb expectedColor = new Rgb(0, 0, 0);

        UtilsTest.expectedValuesForXyzColor(xyzColor, expectedColor);
    }

    @Test
    public void canConvertOrangeXyzToRgb() {
        Xyz xyzColor = new Xyz(54.69, 48.17, 6.41);
        Rgb expectedColor = new Rgb(255, 165, 0);

        UtilsTest.expectedValuesForXyzColor(xyzColor, expectedColor);
    }

}
