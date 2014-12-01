package ru.unn.agile.ColorConverter.model.Tests;

import org.junit.Test;
import static org.junit.Assert.*;
import ru.unn.agile.ColorConverter.model.ColorSpaces.*;
import ru.unn.agile.ColorConverter.model.TestUtilities.KnownColors;

public class LabTest {

    @Test
    public void canConvertWhiteLabToXyz() {
        Lab labColor = KnownColors.WHITE_LAB;
        Xyz expectedColor = KnownColors.WHITE_XYZ;
        Xyz targetColor = (Xyz) labColor.toColor(expectedColor.getClass());
        assertTrue(expectedColor.isEqual(targetColor));
    }

    @Test
    public void canConvertWhiteLabToRgb() {
        Lab labColor = KnownColors.WHITE_LAB;
        Rgb expectedColor = KnownColors.WHITE_RGB;
        Rgb targetColor = (Rgb) labColor.toColor(expectedColor.getClass());
        assertTrue(expectedColor.isEqual(targetColor));
    }

    @Test
    public void canConvertWhiteLabToHsv() {
        Lab labColor = KnownColors.WHITE_LAB;
        Hsv expectedColor = KnownColors.WHITE_HSV;
        Hsv targetColor = (Hsv) labColor.toColor(expectedColor.getClass());
        assertTrue(expectedColor.isEqual(targetColor));
    }

    @Test
    public void canConvertBlackLabToXyz() {
        Lab labColor = KnownColors.BLACK_LAB;
        Xyz expectedColor = KnownColors.BLACK_XYZ;
        Xyz targetColor = (Xyz) labColor.toColor(expectedColor.getClass());
        assertTrue(expectedColor.isEqual(targetColor));
    }

    @Test
    public void canConvertBlackLabToRgb() {
        Lab labColor = KnownColors.BLACK_LAB;
        Rgb expectedColor = KnownColors.BLACK_RGB;
        Rgb targetColor = (Rgb) labColor.toColor(expectedColor.getClass());
        assertTrue(expectedColor.isEqual(targetColor));
    }

    @Test
    public void canConvertBlackLabToHsv() {
        Lab labColor = KnownColors.BLACK_LAB;
        Hsv expectedColor = KnownColors.BLACK_HSV;
        Hsv targetColor = (Hsv) labColor.toColor(expectedColor.getClass());
        assertTrue(expectedColor.isEqual(targetColor));
    }

    @Test
    public void canConvertDarkRedLabToXyz() {
        Lab labColor = KnownColors.DARK_RED_LAB;
        Xyz expectedColor = KnownColors.DARK_RED_XYZ;
        Xyz targetColor = (Xyz) labColor.toColor(expectedColor.getClass());
        assertTrue(expectedColor.isEqual(targetColor));
    }

    @Test
    public void canConvertDarkRedLabToRgb() {
        Lab labColor = KnownColors.DARK_RED_LAB;
        Rgb expectedColor = KnownColors.DARK_RED_RGB;
        Rgb targetColor = (Rgb) labColor.toColor(expectedColor.getClass());
        assertTrue(expectedColor.isEqual(targetColor));
    }

    @Test
    public void canConvertDarkRedLabToHsv() {
        Lab labColor = KnownColors.DARK_RED_LAB;
        Hsv expectedColor = KnownColors.DARK_RED_HSV;
        Hsv targetColor = (Hsv) labColor.toColor(expectedColor.getClass());
        assertTrue(expectedColor.isEqual(targetColor));
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCreateLabWithValuesLessThanMinimum() {
        new Lab(Lab.MIN_L - 1, Lab.MIN_A - 1, Lab.MIN_B - 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCreateLabWithValuesMoreThatMaximum() {
        new Lab(Lab.MAX_L + 1, Lab.MAX_A + 1, Lab.MAX_B + 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotSetValuesLessThanMinimum() {
        Lab color = new Lab();
        color.setValues(Lab.MIN_L - 1, Lab.MIN_A - 1, Lab.MIN_B - 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotSetValuesMoreThatMaximum() {
        Lab color = new Lab();
        color.setValues(Lab.MAX_L + 1, Lab.MAX_A + 1, Lab.MAX_B + 1);
    }
}
