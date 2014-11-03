package ru.unn.agile.ColorConverter.Model.Tests;


import org.junit.Test;
import ru.unn.agile.ColorConverter.Model.ColorSpaces.*;
import ru.unn.agile.ColorConverter.Model.TestUtilities.KnownColors;
import ru.unn.agile.ColorConverter.Model.TestUtilities.UtilsTest;

public class HsvTest {

    @Test
    public void canConvertWhiteHsvToRgb() {
        Hsv hsvColor = KnownColors.WHITE_HSV;
        Rgb expectedColor = KnownColors.WHITE_RGB;
        UtilsTest.expectedValuesForKnownColor(hsvColor, expectedColor);
    }

    @Test
    public void canConvertWhiteHsvAndRgbToXyz() {
        Hsv hsvColor = KnownColors.WHITE_HSV;
        Rgb rgbColor = KnownColors.WHITE_RGB;
        Xyz expectedColor = KnownColors.WHITE_XYZ;
        UtilsTest.expectedValuesForTwoKnownColors(hsvColor, rgbColor, expectedColor);
    }

    @Test
    public void canConvertWhiteHsvToLab() {
        Hsv hsvColor = KnownColors.WHITE_HSV;
        Lab expectedColor = KnownColors.WHITE_LAB;
        UtilsTest.expectedValuesForKnownColor(hsvColor, expectedColor);
    }

    @Test
    public void canConvertWhiteHsvAndLabToXyz() {
        Hsv hsvColor = KnownColors.WHITE_HSV;
        Lab labColor = KnownColors.WHITE_LAB;
        Xyz expectedColor = KnownColors.WHITE_XYZ;
        UtilsTest.expectedValuesForTwoKnownColors(hsvColor, labColor, expectedColor);
    }

    @Test
    public void canConvertBlackHsvToRgb() {
        Hsv hsvColor = KnownColors.BLACK_HSV;
        Rgb expectedColor = KnownColors.BLACK_RGB;
        UtilsTest.expectedValuesForKnownColor(hsvColor, expectedColor);
    }

    @Test
    public void canConvertBlackHsvAndRgbToXyz() {
        Hsv hsvColor = KnownColors.BLACK_HSV;
        Rgb rgbColor = KnownColors.BLACK_RGB;
        Xyz expectedColor = KnownColors.BLACK_XYZ;
        UtilsTest.expectedValuesForTwoKnownColors(hsvColor, rgbColor, expectedColor);
    }

    @Test
    public void canConvertBlackHsvToLab() {
        Hsv hsvColor = KnownColors.BLACK_HSV;
        Lab expectedColor = KnownColors.BLACK_LAB;
        UtilsTest.expectedValuesForKnownColor(hsvColor, expectedColor);
    }

    @Test
    public void canConvertBlackHsvAndLabToXyz() {
        Hsv hsvColor = KnownColors.BLACK_HSV;
        Lab labColor = KnownColors.BLACK_LAB;
        Xyz expectedColor = KnownColors.BLACK_XYZ;
        UtilsTest.expectedValuesForTwoKnownColors(hsvColor, labColor, expectedColor);
    }

    @Test
    public void canConvertAquamarineHsvToRgb() {
        Hsv hsvColor = KnownColors.AQUAMARINE_HSV;
        Rgb expectedColor = KnownColors.AQUAMARINE_RGB;
        UtilsTest.expectedValuesForKnownColor(hsvColor, expectedColor);
    }

    @Test
    public void canConvertAquamarineHsvAndRgbToLab() {
        Hsv hsvColor = KnownColors.AQUAMARINE_HSV;
        Rgb rgbColor = KnownColors.AQUAMARINE_RGB;
        Lab expectedColor = KnownColors.AQUAMARINE_LAB;
        UtilsTest.expectedValuesForTwoKnownColors(hsvColor, rgbColor, expectedColor);
    }

    @Test
    public void canConvertAquamarineHsvToLab() {
        Hsv hsvColor = KnownColors.AQUAMARINE_HSV;
        Lab expectedColor = KnownColors.AQUAMARINE_LAB;
        UtilsTest.expectedValuesForKnownColor(hsvColor, expectedColor);
    }

    @Test
    public void canConvertAquamarineHsvAndLabToRgb() {
        Hsv hsvColor = KnownColors.AQUAMARINE_HSV;
        Lab labColor = KnownColors.AQUAMARINE_LAB;
        Rgb expectedColor = KnownColors.AQUAMARINE_RGB;
        UtilsTest.expectedValuesForTwoKnownColors(hsvColor, labColor, expectedColor);
    }
}
