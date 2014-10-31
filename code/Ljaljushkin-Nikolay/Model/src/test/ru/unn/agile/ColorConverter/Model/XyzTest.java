package ru.unn.agile.ColorConverter.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class XyzTest {

    @Test
    public void canConvertWhiteXyzToRgb() {
        Xyz xyzColor = Xyz.getWhiteReference();
        Rgb expectedColor = new Rgb(255, 255, 255);

        UtilsTest.expectedValuesForXyzColor(xyzColor, expectedColor);
    }

    @Test
    public void canConvertWhiteXyzToLab() {
        Xyz xyzColor = Xyz.getWhiteReference();
        Lab expectedColor = new Lab(100, 0.01, -0.01);

        UtilsTest.expectedValuesForXyzColor(xyzColor, expectedColor);
    }

    @Test
    public void canConvertBlackXyzToRgb() {
        Xyz xyzColor = new Xyz(0, 0, 0);
        Rgb expectedColor = new Rgb(0, 0, 0);

        UtilsTest.expectedValuesForXyzColor(xyzColor, expectedColor);
    }

    @Test
    public void canConvertBlackXyzToLab() {
        Xyz xyzColor = new Xyz(0, 0, 0);
        Lab expectedColor = new Lab(0, 0, 0);

        UtilsTest.expectedValuesForXyzColor(xyzColor, expectedColor);
    }

    @Test
    public void canConvertOrangeXyzToRgb() {
        Xyz xyzColor = new Xyz(54.69, 48.17, 6.41);
        Rgb expectedColor = new Rgb(255, 165, 0);

        UtilsTest.expectedValuesForXyzColor(xyzColor, expectedColor);
    }

    @Test
    public void canConvertOrangeXyzToLab() {
        Xyz xyzColor = new Xyz(54.69, 48.17, 6.41);
        Lab expectedColor = new Lab(74.93, 23.94, 78.96);

        UtilsTest.expectedValuesForXyzColor(xyzColor, expectedColor);
    }
}
