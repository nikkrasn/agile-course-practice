package ru.unn.agile.ColorConverter.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class HsvTest {

    @Test
    public void canConvertWhiteHsvToRgb() {
        Hsv hsvColor = new Hsv(0, 0, 1);
        Rgb expectedColor = new Rgb(255, 255, 255);
        UtilsTest.expectedValuesForHsvColor(hsvColor, expectedColor);
    }

    @Test
    public void canConvertWhiteHsvToLab() {
        Hsv hsvColor = new Hsv(0, 0, 1);
        Lab expectedColor = new Lab(100, 0.01, -0.01);
        UtilsTest.expectedValuesForHsvColor(hsvColor, expectedColor);
    }

    @Test
    public void canConvertBlackHsvToRgb() {
        Hsv hsvColor = new Hsv(0, 0, 0);
        Rgb expectedColor = new Rgb(0, 0, 0);
        UtilsTest.expectedValuesForHsvColor(hsvColor, expectedColor);
    }

    @Test
    public void canConvertBlackHsvToLab() {
        Hsv hsvColor = new Hsv(0, 0, 0);
        Lab expectedColor = new Lab(0, 0, 0);
        UtilsTest.expectedValuesForHsvColor(hsvColor, expectedColor);
    }

    @Test
    public void canConvertAquamarineHsvToRgb() {
        Hsv hsvColor = new Hsv(159.84, 0.5, 1);
        Rgb expectedColor = new Rgb(127, 255, 212);
        UtilsTest.expectedValuesForHsvColor(hsvColor, expectedColor);
    }

    @Test
    public void canConvertAquamarineHsvToLab() {
        Hsv hsvColor = new Hsv(159.84, 0.5, 1);
        Lab expectedColor = new Lab(92.04, -45.52, 9.71);
        UtilsTest.expectedValuesForHsvColor(hsvColor, expectedColor);
    }

}
