package ru.unn.agile.ColorConverter.model.Tests;

import org.junit.Test;
import static org.junit.Assert.*;
import ru.unn.agile.ColorConverter.model.ColorSpaces.*;
import ru.unn.agile.ColorConverter.model.TestUtilities.KnownColors;

public class RgbTest {

    @Test
    public void canConvertWhiteRgbToHsv() {
        Rgb rgbColor = KnownColors.WHITE_RGB;
        Hsv expectedColor = KnownColors.WHITE_HSV;
        Hsv targetColor = (Hsv) rgbColor.toColor(expectedColor.getClass());
        assertTrue(expectedColor.isEqual(targetColor));
    }

    @Test
    public void canConvertWhiteRgbToXyz() {
        Rgb rgbColor = KnownColors.WHITE_RGB;
        Xyz expectedColor = KnownColors.WHITE_XYZ;
        Xyz targetColor = (Xyz) rgbColor.toColor(expectedColor.getClass());
        assertTrue(expectedColor.isEqual(targetColor));
    }

    @Test
    public void canConvertWhiteRgbToLab() {
        Rgb rgbColor = KnownColors.WHITE_RGB;
        Lab expectedColor = KnownColors.WHITE_LAB;
        Lab targetColor = (Lab) rgbColor.toColor(expectedColor.getClass());
        assertTrue(expectedColor.isEqual(targetColor));
    }

    @Test
    public void canConvertBlackRgbToHsv() {
        Rgb rgbColor = KnownColors.BLACK_RGB;
        Hsv expectedColor = KnownColors.BLACK_HSV;
        Hsv targetColor = (Hsv) rgbColor.toColor(expectedColor.getClass());
        assertTrue(expectedColor.isEqual(targetColor));
    }

    @Test
    public void canConvertBlackRgbToXyz()
            throws IllegalAccessException, CloneNotSupportedException, InstantiationException {
        Rgb rgbColor = KnownColors.BLACK_RGB;
        Xyz expectedColor = KnownColors.BLACK_XYZ;
        Xyz targetColor = (Xyz) rgbColor.toColor(expectedColor.getClass());
        assertTrue(expectedColor.isEqual(targetColor));
    }

    @Test
    public void canConvertBlackRgbToLab() {
        Rgb rgbColor = KnownColors.BLACK_RGB;
        Lab expectedColor = KnownColors.BLACK_LAB;
        Lab targetColor = (Lab) rgbColor.toColor(expectedColor.getClass());
        assertTrue(expectedColor.isEqual(targetColor));
    }

    @Test
    public void canConvertDarkRedRgbToHsv() {
        Rgb rgbColor = KnownColors.DARK_RED_RGB;
        Hsv expectedColor = KnownColors.DARK_RED_HSV;
        Hsv targetColor = (Hsv) rgbColor.toColor(expectedColor.getClass());
        assertTrue(expectedColor.isEqual(targetColor));
    }

    @Test
    public void canConvertOrangeRgbToXyz() {
        Rgb rgbColor = KnownColors.ORANGE_RGB;
        Xyz expectedColor = KnownColors.ORANGE_XYZ;
        Xyz targetColor = (Xyz) rgbColor.toColor(expectedColor.getClass());
        assertTrue(expectedColor.isEqual(targetColor));
    }

    @Test
    public void canConvertOrangeRgbToLab() {
        Rgb rgbColor = KnownColors.ORANGE_RGB;
        Lab expectedColor = KnownColors.ORANGE_LAB;
        Lab targetColor = (Lab) rgbColor.toColor(expectedColor.getClass());
        assertTrue(expectedColor.isEqual(targetColor));
    }
}
