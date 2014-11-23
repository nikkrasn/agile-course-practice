package ru.unn.agile.Converter.Model;

import org.junit.Test;
import static org.junit.Assert.*;
import static ru.unn.agile.Converter.Model.LengthConverter.*;


public class LengthConverterTest {
    private static final double DELTA = 0.001;

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
    public void isCorrectConvertationKmToMile() {
        double km = 2.;
        double mile = convertFromTo(Measure.KILOMETER, Measure.MILE, km);
        assertEquals(mile, 2. * 1000. / 1609.344, DELTA);
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
}
