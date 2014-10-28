package ru.unn.agile.ColorConverter.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class LabTest {

    @Test
    public void canConvertWhiteLabToXyz() {
        Lab labColor = new Lab(100, 0.01, -0.01);
        Xyz expectedColor = Xyz.getWhiteReference();

        UtilsTest.expectedValuesForLabColor(labColor, expectedColor);
    }

    @Test
    public void canConvertWhiteLabToRgb() {

        Lab labColor = new Lab(100, 0.01, -0.01);
        Rgb expectedColor = new Rgb(255, 255, 255);

        UtilsTest.expectedValuesForLabColor(labColor, expectedColor);
    }

    @Test
    public void canConvertBlackLabToXyz() {
        Lab labColor = new Lab(0, 0, 0);
        Xyz expectedColor = new Xyz(0, 0, 0);

        UtilsTest.expectedValuesForLabColor(labColor, expectedColor);
    }

    @Test
    public void canConvertBlackLabToRgb() {
        Lab labColor = new Lab(0, 0, 0);
        Rgb expectedColor = new Rgb(0, 0, 0);

        UtilsTest.expectedValuesForLabColor(labColor, expectedColor);
    }

    @Test
    public void canConvertDarkRedLabToXyz() {
        Lab labColor = new Lab(28.0847, 51.0104, 41.2945);
        Xyz expectedColor = new Xyz(10.6474, 5.4889, 0.4982);

        UtilsTest.expectedValuesForLabColor(labColor, expectedColor);
    }

    @Test
    public void canConvertDarkRedLabToRgb() {
        Lab labColor = new Lab(28.0847, 51.0104, 41.2945);
        Rgb expectedColor = new Rgb(139, 0, 0);

        UtilsTest.expectedValuesForLabColor(labColor, expectedColor);
    }





}
