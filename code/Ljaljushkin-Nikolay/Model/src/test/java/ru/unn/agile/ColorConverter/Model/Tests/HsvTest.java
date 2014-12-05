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
    public void cannotSetFirstChannelLessThanMinimum() {
        Hsv color = new Hsv();
        color.setChannels(Hsv.MIN_H - 1, Hsv.MIN_S, Hsv.MIN_V);
        color.verifyChannels();
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotSetSecondChannelLessThanMinimum() {
        Hsv color = new Hsv();
        color.setChannels(Hsv.MIN_H, Hsv.MIN_S - 1, Hsv.MIN_V);
        color.verifyChannels();
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotSetThirdChannelLessThanMinimum() {
        Hsv color = new Hsv();
        color.setChannels(Hsv.MIN_H, Hsv.MIN_S, Hsv.MIN_V - 1);
        color.verifyChannels();
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotSetFirstChannelMoreThatMaximum() {
        Hsv color = new Hsv();
        color.setChannels(Hsv.MAX_H + 1, Hsv.MAX_S, Hsv.MAX_V);
        color.verifyChannels();
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotSetSecondChannelMoreThatMaximum() {
        Hsv color = new Hsv();
        color.setChannels(Hsv.MAX_H, Hsv.MAX_S + 1, Hsv.MAX_V);
        color.verifyChannels();
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotSetThirdChannelMoreThatMaximum() {
        Hsv color = new Hsv();
        color.setChannels(Hsv.MAX_H, Hsv.MAX_S, Hsv.MAX_V + 1);
        color.verifyChannels();
    }

    @Test(expected = ClassCastException.class)
    public void cannotCompareWithAnotherColorSpace() {
        Hsv hsvColor = KnownColors.AQUAMARINE_HSV;
        Lab labColor = KnownColors.AQUAMARINE_LAB;
        hsvColor.isEqual(labColor);
    }
}
