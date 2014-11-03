package ru.unn.agile.ColorConverter.Model.Tests;

import org.junit.Test;
import ru.unn.agile.ColorConverter.Model.ColorSpaces.*;
import ru.unn.agile.ColorConverter.Model.TestUtilities.*;

public class HsvTest {

    @Test
    public void canConvertWhiteHsvToRgb() {
        Hsv hsvColor = KnownColors.WHITE_HSV;
        Rgb expectedColor = KnownColors.WHITE_RGB;
        Rgb targetColor = (Rgb) hsvColor.toColor(expectedColor.getClass());
        UtilsTest.rgbAssert(expectedColor, targetColor);
    }

    @Test
    public void canConvertWhiteHsvToLab() {
        Hsv hsvColor = KnownColors.WHITE_HSV;
        Lab expectedColor = KnownColors.WHITE_LAB;
        Lab targetColor = (Lab) hsvColor.toColor(expectedColor.getClass());
        UtilsTest.labAssert(expectedColor, targetColor);
    }

    @Test
    public void canConvertBlackHsvToRgb() {
        Hsv hsvColor = KnownColors.BLACK_HSV;
        Rgb expectedColor = KnownColors.BLACK_RGB;
        Rgb targetColor = (Rgb) hsvColor.toColor(expectedColor.getClass());
        UtilsTest.rgbAssert(expectedColor, targetColor);
    }

    @Test
    public void canConvertBlackHsvToLab() {
        Hsv hsvColor = KnownColors.BLACK_HSV;
        Lab expectedColor = KnownColors.BLACK_LAB;
        Lab targetColor = (Lab) hsvColor.toColor(expectedColor.getClass());
        UtilsTest.labAssert(expectedColor, targetColor);
    }

    @Test
    public void canConvertAquamarineHsvToRgb() {
        Hsv hsvColor = KnownColors.AQUAMARINE_HSV;
        Rgb expectedColor = KnownColors.AQUAMARINE_RGB;
        Rgb targetColor = (Rgb) hsvColor.toColor(expectedColor.getClass());
        UtilsTest.rgbAssert(expectedColor, targetColor);
    }

    @Test
    public void canConvertAquamarineHsvToLab() {
        Hsv hsvColor = KnownColors.AQUAMARINE_HSV;
        Lab expectedColor = KnownColors.AQUAMARINE_LAB;
        Lab targetColor = (Lab) hsvColor.toColor(expectedColor.getClass());
        UtilsTest.labAssert(expectedColor, targetColor);
    }
}
