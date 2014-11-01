package ru.unn.agile.Converter.Model;

/**
 * Created by Алексей on 28.10.2014.
 */

import org.junit.Test;
import static org.junit.Assert.*;

public class ConverterTest {
    private final double delta = 0.001;

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnNegativeDouble() {
        double mile = -1.;
        Converter.mileToinch(mile);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnLargeDouble() {
        double mile = Double.MAX_VALUE;
        Converter.mileTom(mile);
    }

    // Проверка на не выкидывание эксепшена
    // при работе с большими числами
    @Test
    public void notThrowsOnLargeDouble() {
        double m = Double.MAX_VALUE;
        Converter.mTomile(m);
    }

    // from m
    @Test
    public void isCorrectConvertationmTokm() {
        double m = 2.;
        double km = Converter.mTokm(m);
        assertEquals(km, 2. / 1000., delta);
    }
    @Test
    public void isCorrectConvertationmTomile() {
        double m = 2.;
        double mile = Converter.mTomile(m);
        assertEquals(mile, 2. / 1609.344, delta);
    }
    @Test
    public void isCorrectConvertationmToinch() {
        double m = 2.;
        double inch = Converter.mToinch(m);
        assertEquals(inch, 2. / 0.0254, delta);
    }

    // from km
    @Test
    public void isCorrectConvertationkmTom() {
        double km = 2.;
        double m = Converter.kmTom(km);
        assertEquals(m, 2. * 1000., delta);
    }
    @Test
    public void isCorrectConvertationkmTomile() {
        double km = 2.;
        double mile = Converter.kmTomile(km);
        assertEquals(mile, 2. * 1000. / 1609.344, delta);
    }
    @Test
    public void isCorrectConvertationkmToinch() {
        double km = 2.;
        double inch = Converter.kmToinch(km);
        assertEquals(inch, 2. * 1000. / 0.0254, delta);
    }

    // from mile
    @Test
    public void isCorrectConvertationmileTom() {
        double mile = 2.;
        double m = Converter.mileTom(mile);
        assertEquals(m, 2. * 1609.344, delta);
    }
    @Test
    public void isCorrectConvertationmileTokm() {
        double mile = 2.;
        double km = Converter.mileTokm(mile);
        assertEquals(km, 2. * 1609.344 / 1000., delta);
    }
    @Test
    public void isCorrectConvertationmileToinch() {
        double mile = 2.;
        double inch = Converter.mileToinch(mile);
        assertEquals(inch, 2. * 1609.344 / 0.0254, delta);
    }

    // from inch
    @Test
    public void isCorrectConvertationinchTom() {
        double inch = 2.;
        double m = Converter.inchTom(inch);
        assertEquals(m, 2. * 0.0254, delta);
    }
    @Test
    public void isCorrectConvertationinchTokm() {
        double inch = 2.;
        double km = Converter.inchTokm(inch);
        assertEquals(km, 2. * 0.0254 / 1000, delta);
    }
    @Test
    public void isCorrectConvertationinchTomile() {
        double inch = 2.;
        double mile = Converter.inchTomile(inch);
        assertEquals(mile, 2. * 0.0254 / 1609.344, delta);
    }
}
