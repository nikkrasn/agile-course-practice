package ru.unn.agile.ColorConverter.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class RgbTest {

    @Test
    public void canConvertWhiteRgbToHsv() {
        Rgb rgbColor = new Rgb(255, 255, 255);
        Hsv expectedColor = new Hsv(0, 0, 1);
        UtilsTest.expectedValuesForRgbColor(rgbColor, expectedColor);
    }

    @Test
    public void canConvertWhiteRgbToXyz() {
        Rgb rgbColor = new Rgb(255, 255, 255);
        Xyz expectedColor = new Xyz(95.05, 100, 108.9);
        UtilsTest.expectedValuesForRgbColor(rgbColor, expectedColor);
    }

    @Test
    public void canConvertWhiteRgbToLab() {
        Rgb rgbColor = new Rgb(255, 255, 255);
        Lab expectedColor = new Lab(100, 0.01, -0.01);
        UtilsTest.expectedValuesForRgbColor(rgbColor, expectedColor);
    }

    @Test
    public void canConvertBlackRgbToHsv() {
        Rgb rgbColor = new Rgb(0, 0, 0);
        Hsv expectedColor = new Hsv(0, 0, 0);
        UtilsTest.expectedValuesForRgbColor(rgbColor, expectedColor);
    }

    @Test
    public void canConvertBlackRgbToXyz() {
        Rgb rgbColor = new Rgb(0, 0, 0);
        Xyz expectedColor = new Xyz(0, 0, 0);
        UtilsTest.expectedValuesForRgbColor(rgbColor, expectedColor);
    }

    @Test
    public void canConvertBlackRgbToLab() {
        Rgb rgbColor = new Rgb(0, 0, 0);
        Lab expectedColor = new Lab(0, 0, 0);
        UtilsTest.expectedValuesForRgbColor(rgbColor, expectedColor);
    }

    @Test
    public void canConvertDarkRedRgbToHsv() {
        Rgb rgbColor = new Rgb(139, 0, 0);
        Hsv expectedColor = new Hsv(0, 1, 0.55);
        UtilsTest.expectedValuesForRgbColor(rgbColor, expectedColor);
    }

    @Test
    public void canConvertOrangeRgbToXyz() {
        Rgb rgbColor = new Rgb(255, 165, 0);
        Xyz expectedColor = new Xyz(54.69, 48.17, 6.41);
        UtilsTest.expectedValuesForRgbColor(rgbColor, expectedColor);
    }

    @Test
    public void canConvertOrangeRgbToLab() {
        Rgb rgbColor = new Rgb(255, 165, 0);
        Lab expectedColor = new Lab(74.93, 23.94, 78.96);
        UtilsTest.expectedValuesForRgbColor(rgbColor, expectedColor);
    }

}
