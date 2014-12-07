package ru.unn.agile.LengthConverter.Model;

import org.junit.Test;
import static org.junit.Assert.*;
import static ru.unn.agile.LengthConverter.Model.LengthConverter.*;


public class LengthConverterTest {
    private static final double DELTA = 1e-100;

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnNegativeDouble() {
        double mile = -1.;
        convertFromTo(Measure.METER, Measure.KILOMETER, mile);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnLargeDouble() {
        double mile = Double.MAX_VALUE;
        convertFromTo(Measure.MILE, Measure.METER, mile);
    }

    @Test
    public void notThrowsOnLargeDouble() {
        double m = Double.MAX_VALUE;
        convertFromTo(Measure.METER, Measure.MILE, m);
    }

    @Test
    public void isCorrectConvertationMToKm() {
        double m = 2.;
        double km = convertFromTo(Measure.METER, Measure.KILOMETER, m);
        assertEquals(km, 2. / 1000., DELTA);
    }

    @Test
    public void isCorrectConvertationMToMile() {
        double m = 2.;
        double mile = convertFromTo(Measure.METER, Measure.MILE, m);
        assertEquals(mile, 2. / 1609.344, DELTA);
    }

    @Test
    public void isCorrectConvertationMToInch() {
        double m = 2.;
        double inch = convertFromTo(Measure.METER, Measure.INCH, m);
        assertEquals(inch, 2. / 0.0254, DELTA);
    }

    @Test
    public void isCorrectConvertationKmToM() {
        double km = 2.;
        double m = convertFromTo(Measure.KILOMETER, Measure.METER, km);
        assertEquals(m, 2. * 1000., DELTA);
    }

    @Test
    public void isCorrectConvertationKmToMile() {
        double km = 2.;
        double mile = convertFromTo(Measure.KILOMETER, Measure.MILE, km);
        assertEquals(mile, 2. * 1000. / 1609.344, DELTA);
    }

    @Test
    public void isCorrectConvertationKmToInch() {
        double km = 2.;
        double inch = convertFromTo(Measure.KILOMETER, Measure.INCH, km);
        assertEquals(inch, 2. * 1000. / 0.0254, DELTA);
    }

    @Test
    public void isCorrectConvertationMileToM() {
        double mile = 2.;
        double m = convertFromTo(Measure.MILE, Measure.METER, mile);
        assertEquals(m, 2. * 1609.344, DELTA);
    }

    @Test
    public void isCorrectConvertationMileToKm() {
        double mile = 2.;
        double km = convertFromTo(Measure.MILE, Measure.KILOMETER, mile);
        assertEquals(km, 2. * 1609.344 / 1000, DELTA);
    }

    @Test
    public void isCorrectConvertationMileToInch() {
        double mile = 2.;
        double inch = convertFromTo(Measure.MILE, Measure.INCH, mile);
        assertEquals(inch, 2. * 1609.344 / 0.0254, DELTA);
    }

    @Test
    public void isCorrectConvertationInchToM() {
        double inch = 2.;
        double m = convertFromTo(Measure.INCH, Measure.METER, inch);
        assertEquals(m, 2. * 0.0254, DELTA);
    }

    @Test
    public void isCorrectConvertationInchToKm() {
        double inch = 2.;
        double km = convertFromTo(Measure.INCH, Measure.KILOMETER, inch);
        assertEquals(km, 2. * 0.0254 / 1000., DELTA);
    }

    @Test
    public void isCorrectConvertationInchToMile() {
        double inch = 2.;
        double mile = convertFromTo(Measure.INCH, Measure.MILE, inch);
        assertEquals(mile, 2. * 0.0254 / 1609.344, DELTA);
    }
}
