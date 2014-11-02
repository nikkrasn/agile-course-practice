package ru.unn.agile.ColorConverter.Model.Tests;

import org.junit.Test;
import ru.unn.agile.ColorConverter.Model.ColorSpaces.*;
import ru.unn.agile.ColorConverter.Model.TestUtilities.KnownColors;
import ru.unn.agile.ColorConverter.Model.TestUtilities.UtilsTest;

public class RgbTest {

    @Test
    public void canConvertWhiteRgbToHsv() {
        Rgb rgbColor = KnownColors.WHITE_RGB;
        Hsv expectedColor = KnownColors.WHITE_HSV;
        UtilsTest.expectedValuesForKnownColor(rgbColor, expectedColor);
    }

    @Test
    public void canConvertWhiteRgbToXyz() {
        Rgb rgbColor = KnownColors.WHITE_RGB;
        Xyz expectedColor = KnownColors.WHITE_XYZ;
        UtilsTest.expectedValuesForKnownColor(rgbColor, expectedColor);
    }

    @Test
    public void canConvertWhiteRgbToLab() {
        Rgb rgbColor = KnownColors.WHITE_RGB;
        Lab expectedColor = KnownColors.WHITE_LAB;
        UtilsTest.expectedValuesForKnownColor(rgbColor, expectedColor);
    }

    @Test
    public void canConvertBlackRgbToHsv() {
        Rgb rgbColor = KnownColors.BLACK_RGB;
        Hsv expectedColor = KnownColors.BLACK_HSV;
        UtilsTest.expectedValuesForKnownColor(rgbColor, expectedColor);
    }

    @Test
    public void canConvertBlackRgbToXyz()
            throws IllegalAccessException, CloneNotSupportedException, InstantiationException {
        Rgb rgbColor = KnownColors.BLACK_RGB;
        Xyz expectedColor = KnownColors.BLACK_XYZ;
        UtilsTest.expectedValuesForKnownColor(rgbColor, expectedColor);
    }

    @Test
    public void canConvertBlackRgbToLab() {
        Rgb rgbColor = KnownColors.BLACK_RGB;
        Lab expectedColor = KnownColors.BLACK_LAB;
        UtilsTest.expectedValuesForKnownColor(rgbColor, expectedColor);
    }

    @Test
    public void canConvertDarkRedRgbToHsv() {
        Rgb rgbColor = KnownColors.DARK_RED_RGB;
        Hsv expectedColor = KnownColors.DARK_RED_HSV;
        UtilsTest.expectedValuesForKnownColor(rgbColor, expectedColor);
    }

    @Test
    public void canConvertOrangeRgbToXyz()
            throws IllegalAccessException, CloneNotSupportedException, InstantiationException {
        Rgb rgbColor = KnownColors.ORANGE_RGB;
        Xyz expectedColor = KnownColors.ORANGE_XYZ;
        UtilsTest.expectedValuesForKnownColor(rgbColor, expectedColor);
    }

    @Test
    public void canConvertOrangeRgbToLab() {
        Rgb rgbColor = KnownColors.ORANGE_RGB;
        Lab expectedColor = KnownColors.ORANGE_LAB;
        UtilsTest.expectedValuesForKnownColor(rgbColor, expectedColor);
    }

}
