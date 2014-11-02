package ru.unn.agile.Converter.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class LengthConverterTest {
    private static final double DELTA = 0.001;

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnNegativeDouble() {
        double mile = -1.;
        LengthConverter.mileToInch(mile);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnLargeDouble() {
        double mile = Double.MAX_VALUE;
        LengthConverter.mileToM(mile);
    }

    @Test
    public void notThrowsOnLargeDouble() {
        double m = Double.MAX_VALUE;
        LengthConverter.mToMile(m);
    }

    @Test
    public void isCorrectConvertationMToKm() {
        double m = 2.;
        double km = LengthConverter.mToKm(m);
        assertEquals(km, 2. / 1000., DELTA);
    }

    @Test
    public void isCorrectConvertationMToMile() {
        double m = 2.;
        double mile = LengthConverter.mToMile(m);
        assertEquals(mile, 2. / 1609.344, DELTA);
    }

    @Test
    public void isCorrectConvertationMToInch() {
        double m = 2.;
        double inch = LengthConverter.mToInch(m);
        assertEquals(inch, 2. / 0.0254, DELTA);
    }

    @Test
    public void isCorrectConvertationKmToM() {
        double km = 2.;
        double m = LengthConverter.kmToM(km);
        assertEquals(m, 2. * 1000., DELTA);
    }

    @Test
    public void isCorrectConvertationKmToMile() {
        double km = 2.;
        double mile = LengthConverter.kmToMile(km);
        assertEquals(mile, 2. * 1000. / 1609.344, DELTA);
    }

    @Test
    public void isCorrectConvertationKmToInch() {
        double km = 2.;
        double inch = LengthConverter.kmToInch(km);
        assertEquals(inch, 2. * 1000. / 0.0254, DELTA);
    }

    @Test
    public void isCorrectConvertationMileToM() {
        double mile = 2.;
        double m = LengthConverter.mileToM(mile);
        assertEquals(m, 2. * 1609.344, DELTA);
    }

    @Test
    public void isCorrectConvertationMileTokm() {
        double mile = 2.;
        double km = LengthConverter.mileToKm(mile);
        assertEquals(km, 2. * 1609.344 / 1000., DELTA);
    }

    @Test
    public void isCorrectConvertationMileToInch() {
        double mile = 2.;
        double inch = LengthConverter.mileToInch(mile);
        assertEquals(inch, 2. * 1609.344 / 0.0254, DELTA);
    }

    @Test
    public void isCorrectConvertationInchToM() {
        double inch = 2.;
        double m = LengthConverter.inchToM(inch);
        assertEquals(m, 2. * 0.0254, DELTA);
    }

    @Test
    public void isCorrectConvertationInchToKm() {
        double inch = 2.;
        double km = LengthConverter.inchToKm(inch);
        assertEquals(km, 2. * 0.0254 / 1000, DELTA);
    }

    @Test
    public void isCorrectConvertationInchToMile() {
        double inch = 2.;
        double mile = LengthConverter.inchToMile(inch);
        assertEquals(mile, 2. * 0.0254 / 1609.344, DELTA);
    }
}
