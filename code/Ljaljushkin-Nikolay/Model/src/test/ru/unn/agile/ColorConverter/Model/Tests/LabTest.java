package ru.unn.agile.ColorConverter.Model.Tests;

import org.junit.Test;
import ru.unn.agile.ColorConverter.Model.ColorSpaces.*;
import ru.unn.agile.ColorConverter.Model.TestUtilities.KnownColors;
import ru.unn.agile.ColorConverter.Model.TestUtilities.UtilsTest;


public class LabTest {

    @Test
    public void canConvertWhiteLabToXyz() {
        Lab labColor = KnownColors.WHITE_LAB;
        Xyz expectedColor = KnownColors.WHITE_XYZ;
        UtilsTest.expectedValuesForKnownColor(labColor, expectedColor);
    }

    @Test
    public void canConvertWhiteLabAndXyzToRgb() {
        Lab labColor = KnownColors.WHITE_LAB;
        Xyz xyzColor = KnownColors.WHITE_XYZ;
        Rgb expectedColor = KnownColors.WHITE_RGB;
        UtilsTest.expectedValuesForTwoKnownColors(labColor, xyzColor, expectedColor);
    }

    @Test
    public void canConvertWhiteLabToRgb() {
        Lab labColor = KnownColors.WHITE_LAB;
        Rgb expectedColor = KnownColors.WHITE_RGB;
        UtilsTest.expectedValuesForKnownColor(labColor, expectedColor);
    }

    @Test
    public void canConvertWhiteLabAndRgbToXyz() {
        Lab labColor = KnownColors.WHITE_LAB;
        Rgb rgbColor = KnownColors.WHITE_RGB;
        Xyz expectedColor = KnownColors.WHITE_XYZ;
        UtilsTest.expectedValuesForTwoKnownColors(labColor, rgbColor, expectedColor);
    }

    @Test
    public void canConvertWhiteLabToHsv() {
        Lab labColor = KnownColors.WHITE_LAB;
        Hsv expectedColor = KnownColors.WHITE_HSV;
        UtilsTest.expectedValuesForKnownColor(labColor, expectedColor);
    }

    @Test
    public void canConvertBlackLabToXyz() {
        Lab labColor = KnownColors.BLACK_LAB;
        Xyz expectedColor = KnownColors.BLACK_XYZ;
        UtilsTest.expectedValuesForKnownColor(labColor, expectedColor);
    }

    @Test
    public void canConvertBlackLabAndXyzToRgb() {
        Lab labColor = KnownColors.BLACK_LAB;
        Xyz xyzColor = KnownColors.BLACK_XYZ;
        Rgb expectedColor = KnownColors.BLACK_RGB;
        UtilsTest.expectedValuesForTwoKnownColors(labColor, xyzColor, expectedColor);
    }

    @Test
    public void canConvertBlackLabToRgb() {
        Lab labColor = KnownColors.BLACK_LAB;
        Rgb expectedColor = KnownColors.BLACK_RGB;
        UtilsTest.expectedValuesForKnownColor(labColor, expectedColor);
    }

    @Test
    public void canConvertBlackLabAndRgbToXyz() {
        Lab labColor = KnownColors.BLACK_LAB;
        Rgb rgbColor = KnownColors.BLACK_RGB;
        Xyz expectedColor = KnownColors.BLACK_XYZ;
        UtilsTest.expectedValuesForTwoKnownColors(labColor, rgbColor, expectedColor);
    }

    @Test
    public void canConvertBlackLabToHsv() {
        Lab labColor = KnownColors.BLACK_LAB;
        Hsv expectedColor = KnownColors.BLACK_HSV;
        UtilsTest.expectedValuesForKnownColor(labColor, expectedColor);
    }

    @Test
    public void canConvertDarkRedLabToXyz() {
        Lab labColor = KnownColors.DARK_RED_LAB;
        Xyz expectedColor = KnownColors.DARK_RED_XYZ;
        UtilsTest.expectedValuesForKnownColor(labColor, expectedColor);
    }

    @Test
    public void canConvertDarkLabAndXyzToRgb() {
        Lab labColor = KnownColors.DARK_RED_LAB;
        Xyz xyzColor = KnownColors.DARK_RED_XYZ;
        Rgb expectedColor = KnownColors.DARK_RED_RGB;
        UtilsTest.expectedValuesForTwoKnownColors(labColor, xyzColor, expectedColor);
    }

    @Test
    public void canConvertDarkRedLabToRgb() {
        Lab labColor = KnownColors.DARK_RED_LAB;
        Rgb expectedColor = KnownColors.DARK_RED_RGB;
        UtilsTest.expectedValuesForKnownColor(labColor, expectedColor);
    }

    @Test
    public void canConvertDarkLabAndRgbToXyz() {
        Lab labColor = KnownColors.DARK_RED_LAB;
        Rgb rgbColor = KnownColors.DARK_RED_RGB;
        Xyz expectedColor = KnownColors.DARK_RED_XYZ;
        UtilsTest.expectedValuesForTwoKnownColors(labColor, rgbColor, expectedColor);
    }

    @Test
    public void canConvertDarkRedLabToHsv() {
        Lab labColor = KnownColors.DARK_RED_LAB;
        Hsv expectedColor = KnownColors.DARK_RED_HSV;
        UtilsTest.expectedValuesForKnownColor(labColor, expectedColor);
    }
}
