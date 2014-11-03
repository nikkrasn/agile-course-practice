package ru.unn.agile.ColorConverter.Model.Tests;

import org.junit.Test;
import ru.unn.agile.ColorConverter.Model.ColorSpaces.*;
import ru.unn.agile.ColorConverter.Model.TestUtilities.*;


public class XyzTest {

    @Test
    public void canConvertWhiteXyzToRgb() {
        Xyz xyzColor = KnownColors.WHITE_XYZ;
        Rgb expectedColor = KnownColors.WHITE_RGB;
        Rgb targetColor = (Rgb) xyzColor.toColor(expectedColor.getClass());
        UtilsTest.rgbAssert(expectedColor, targetColor);
    }

    @Test
    public void canConvertBlackXyzToRgb() {
        Xyz xyzColor = KnownColors.BLACK_XYZ;
        Rgb expectedColor = KnownColors.BLACK_RGB;
        Rgb targetColor = (Rgb) xyzColor.toColor(expectedColor.getClass());
        UtilsTest.rgbAssert(expectedColor, targetColor);
    }

    @Test
    public void canConvertBlackXyzToLab() {
        Xyz xyzColor = KnownColors.BLACK_XYZ;
        Lab expectedColor = KnownColors.BLACK_LAB;
        Lab targetColor = (Lab) xyzColor.toColor(expectedColor.getClass());
        UtilsTest.labAssert(expectedColor, targetColor);
    }

    @Test
    public void canConvertOrangeXyzToRgb() {
        Xyz xyzColor = KnownColors.ORANGE_XYZ;
        Rgb expectedColor = KnownColors.ORANGE_RGB;
        Rgb targetColor = (Rgb) xyzColor.toColor(expectedColor.getClass());
        UtilsTest.rgbAssert(expectedColor, targetColor);
    }

    @Test
    public void canConvertOrangeXyzToLab() {
        Xyz xyzColor = KnownColors.ORANGE_XYZ;
        Lab expectedColor = KnownColors.ORANGE_LAB;
        Lab targetColor = (Lab) xyzColor.toColor(expectedColor.getClass());
        UtilsTest.labAssert(expectedColor, targetColor);
    }
}
