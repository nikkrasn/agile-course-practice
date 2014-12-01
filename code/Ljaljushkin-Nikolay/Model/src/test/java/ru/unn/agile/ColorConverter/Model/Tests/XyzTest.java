package ru.unn.agile.ColorConverter.model.Tests;

import org.junit.Test;
import static org.junit.Assert.*;
import ru.unn.agile.ColorConverter.model.ColorSpaces.*;
import ru.unn.agile.ColorConverter.model.TestUtilities.*;

public class XyzTest {

    @Test
    public void canConvertWhiteXyzToRgb() {
        Xyz xyzColor = KnownColors.WHITE_XYZ;
        Rgb expectedColor = KnownColors.WHITE_RGB;
        Rgb targetColor = (Rgb) xyzColor.toColor(expectedColor.getClass());
        assertTrue(expectedColor.isEqual(targetColor));
    }

    @Test
    public void canConvertBlackXyzToRgb() {
        Xyz xyzColor = KnownColors.BLACK_XYZ;
        Rgb expectedColor = KnownColors.BLACK_RGB;
        Rgb targetColor = (Rgb) xyzColor.toColor(expectedColor.getClass());
        assertTrue(expectedColor.isEqual(targetColor));
    }

    @Test
    public void canConvertBlackXyzToLab() {
        Xyz xyzColor = KnownColors.BLACK_XYZ;
        Lab expectedColor = KnownColors.BLACK_LAB;
        Lab targetColor = (Lab) xyzColor.toColor(expectedColor.getClass());
        assertTrue(expectedColor.isEqual(targetColor));
    }

    @Test
    public void canConvertOrangeXyzToRgb() {
        Xyz xyzColor = KnownColors.ORANGE_XYZ;
        Rgb expectedColor = KnownColors.ORANGE_RGB;
        Rgb targetColor = (Rgb) xyzColor.toColor(expectedColor.getClass());
        assertTrue(expectedColor.isEqual(targetColor));
    }

    @Test
    public void canConvertOrangeXyzToLab() {
        Xyz xyzColor = KnownColors.ORANGE_XYZ;
        Lab expectedColor = KnownColors.ORANGE_LAB;
        Lab targetColor = (Lab) xyzColor.toColor(expectedColor.getClass());
        assertTrue(expectedColor.isEqual(targetColor));
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCreateXyzWithValuesLessThanMinimum() {
        new Xyz(Xyz.MIN_X - 1, Xyz.MIN_Y - 1, Xyz.MIN_Z - 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCreateXyzWithValuesMoreThatMaximum() {
        new Xyz(Xyz.MAX_X + 1, Xyz.MAX_Y + 1, Xyz.MAX_Z + 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotSetValuesLessThanMinimum() {
        Xyz color = new Xyz();
        color.setValues(Xyz.MIN_X - 1, Xyz.MIN_Y - 1, Xyz.MIN_Z - 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotSetValuesMoreThatMaximum() {
        Xyz color = new Xyz();
        color.setValues(Xyz.MAX_X + 1, Xyz.MAX_Y + 1, Xyz.MAX_Z + 1);
    }
}
