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
    public void canConvertWhiteHsvToLab() {
        Hsv hsvColor = KnownColors.WHITE_HSV;
        Lab expectedColor = KnownColors.WHITE_LAB;
        UtilsTest.expectedValuesForKnownColor(hsvColor, expectedColor);
    }

    @Test
    public void canConvertBlackHsvToRgb() {
        Hsv hsvColor = KnownColors.BLACK_HSV;
        Rgb expectedColor = KnownColors.BLACK_RGB;
        UtilsTest.expectedValuesForKnownColor(hsvColor, expectedColor);
    }

    @Test
    public void canConvertBlackHsvToLab() {
        Hsv hsvColor = KnownColors.BLACK_HSV;
        Lab expectedColor = KnownColors.BLACK_LAB;
        UtilsTest.expectedValuesForKnownColor(hsvColor, expectedColor);
    }

    @Test
    public void canConvertAquamarineHsvToRgb() {
        Hsv hsvColor = KnownColors.AQUAMARINE_HSV;
        Rgb expectedColor = KnownColors.AQUAMARINE_RGB;
        UtilsTest.expectedValuesForKnownColor(hsvColor, expectedColor);
    }

    @Test
    public void canConvertAquamarineHsvToLab() {
        Hsv hsvColor = KnownColors.AQUAMARINE_HSV;
        Lab expectedColor = KnownColors.AQUAMARINE_LAB;
        UtilsTest.expectedValuesForKnownColor(hsvColor, expectedColor);
    }
}
