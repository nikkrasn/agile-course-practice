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

    @Test(expected = IllegalArgumentException.class)
    public void cannotCreateRgbWithValuesLessThanMinimum() {
        new Rgb(Rgb.MIN_R - 1, Rgb.MIN_G - 1, Rgb.MIN_B - 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCreateRgbWithValuesMoreThatMaximum() {
        new Rgb(Rgb.MAX_R + 1, Rgb.MAX_G + 1, Rgb.MAX_B + 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotSetValuesLessThanMinimum() {
        Rgb color = new Rgb();
        color.setValues(Rgb.MIN_R - 1, Rgb.MIN_G - 1, Rgb.MIN_B - 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotSetValuesMoreThatMaximum() {
        Rgb color = new Rgb();
        color.setValues(Rgb.MAX_R + 1, Rgb.MAX_G + 1, Rgb.MAX_B + 1);
    }
}
