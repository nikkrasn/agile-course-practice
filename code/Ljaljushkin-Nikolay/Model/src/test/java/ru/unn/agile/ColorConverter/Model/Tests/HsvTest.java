package ru.unn.agile.ColorConverter.model.Tests;

import org.junit.Test;
import static org.junit.Assert.*;
import ru.unn.agile.ColorConverter.model.ColorSpaces.*;
import ru.unn.agile.ColorConverter.model.TestUtilities.*;

public class HsvTest {

    @Test
    public void canConvertWhiteHsvToRgb() {
        Hsv hsvColor = KnownColors.WHITE_HSV;
        Rgb expectedColor = KnownColors.WHITE_RGB;
        Rgb targetColor = (Rgb) hsvColor.toColor(expectedColor.getClass());
        assertTrue(expectedColor.isEqual(targetColor));
    }

    @Test
    public void canConvertWhiteHsvToLab() {
        Hsv hsvColor = KnownColors.WHITE_HSV;
        Lab expectedColor = KnownColors.WHITE_LAB;
        Lab targetColor = (Lab) hsvColor.toColor(expectedColor.getClass());
        assertTrue(expectedColor.isEqual(targetColor));
    }

    @Test
    public void canConvertBlackHsvToRgb() {
        Hsv hsvColor = KnownColors.BLACK_HSV;
        Rgb expectedColor = KnownColors.BLACK_RGB;
        Rgb targetColor = (Rgb) hsvColor.toColor(expectedColor.getClass());
        assertTrue(expectedColor.isEqual(targetColor));
    }

    @Test
    public void canConvertBlackHsvToLab() {
        Hsv hsvColor = KnownColors.BLACK_HSV;
        Lab expectedColor = KnownColors.BLACK_LAB;
        Lab targetColor = (Lab) hsvColor.toColor(expectedColor.getClass());
        assertTrue(expectedColor.isEqual(targetColor));
    }

    @Test
    public void canConvertAquamarineHsvToRgb() {
        Hsv hsvColor = KnownColors.AQUAMARINE_HSV;
        Rgb expectedColor = KnownColors.AQUAMARINE_RGB;
        Rgb targetColor = (Rgb) hsvColor.toColor(expectedColor.getClass());
        assertTrue(expectedColor.isEqual(targetColor));
    }

    @Test
    public void canConvertAquamarineHsvToLab() {
        Hsv hsvColor = KnownColors.AQUAMARINE_HSV;
        Lab expectedColor = KnownColors.AQUAMARINE_LAB;
        Lab targetColor = (Lab) hsvColor.toColor(expectedColor.getClass());
        assertTrue(expectedColor.isEqual(targetColor));
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCreateHsvWithValuesLessThanMinimum() {
        new Hsv(Hsv.MIN_H - 1, Hsv.MIN_S - 1, Hsv.MIN_V - 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCreateHsvWithValuesMoreThatMaximum() {
        new Hsv(Hsv.MAX_H + 1, Hsv.MAX_S + 1, Hsv.MAX_V + 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotSetValuesLessThanMinimum() {
        Hsv color = new Hsv();
        color.setValues(Hsv.MIN_H - 1, Hsv.MIN_S - 1, Hsv.MIN_V - 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotSetValuesMoreThatMaximum() {
        Hsv color = new Hsv();
        color.setValues(Hsv.MAX_H + 1, Hsv.MAX_S + 1, Hsv.MAX_V + 1);
    }
}
