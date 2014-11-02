package ru.unn.agile.ColorConverter.Model.Tests;

import org.junit.Test;
import ru.unn.agile.ColorConverter.Model.ColorSpaces.*;
import ru.unn.agile.ColorConverter.Model.TestUtilities.*;


public class XyzTest {

    @Test
    public void canConvertWhiteXyzToRgb() {
        Xyz xyzColor = KnownColors.WHITE_XYZ;
        Rgb expectedColor = KnownColors.WHITE_RGB;
        UtilsTest.expectedValuesForKnownColor(xyzColor, expectedColor);
    }

    @Test
    public void canConvertBlackXyzToRgb() {
        Xyz xyzColor = KnownColors.BLACK_XYZ;
        Rgb expectedColor = KnownColors.BLACK_RGB;
        UtilsTest.expectedValuesForKnownColor(xyzColor, expectedColor);
    }

    @Test
    public void canConvertBlackXyzToLab() {
        Xyz xyzColor = KnownColors.BLACK_XYZ;
        Lab expectedColor = KnownColors.BLACK_LAB;
        UtilsTest.expectedValuesForKnownColor(xyzColor, expectedColor);
    }

    @Test
    public void canConvertOrangeXyzToRgb() {
        Xyz xyzColor = KnownColors.ORANGE_XYZ;
        Rgb expectedColor = KnownColors.ORANGE_RGB;
        UtilsTest.expectedValuesForKnownColor(xyzColor, expectedColor);
    }

    @Test
    public void canConvertOrangeXyzToLab() {
        Xyz xyzColor = KnownColors.ORANGE_XYZ;
        Lab expectedColor = KnownColors.ORANGE_LAB;
        UtilsTest.expectedValuesForKnownColor(xyzColor, expectedColor);
    }
}
