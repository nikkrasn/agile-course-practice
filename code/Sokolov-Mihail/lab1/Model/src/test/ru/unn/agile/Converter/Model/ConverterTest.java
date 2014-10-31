package ru.unn.agile.Converter.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class ConverterTest {
    private final double delta = 0.001;

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnNegativeDouble() {
        double ha = -1.0;
        Converter.haToare(ha);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnLargeDouble() {
        double ha = Double.MAX_VALUE;
        Converter.haTosqm(ha);
    }

    @Test
    public void notThrowsOnLargeDouble() {
        double sqm = Double.MAX_VALUE;
        Converter.sqmToha(sqm);
    }

    // from sqm
    @Test
    public void isCorrectConvertationsqmTosqkm() {
        double sqm = 2.0 * 1000000;
        double sqkm = Converter.sqmTosqkm(sqm);
        assertEquals(sqkm, 2.0, delta);
    }
    @Test
    public void isCorrectConvertationsqmToha() {
        double sqm = 2.0 * 10000;
        double ha = Converter.sqmToha(sqm);
        assertEquals(ha, 2.0, delta);
    }
    @Test
    public void isCorrectConvertationsqmToare() {
        double sqm = 2.0 * 100;
        double are = Converter.sqmToare(sqm);
        assertEquals(are, 2.0, delta);
    }

    // from sqkm
    @Test
    public void isCorrectConvertationsqkmTosqm() {
        double sqkm = 0.02;
        double sqm = Converter.sqkmTosqm(sqkm);
        assertEquals(sqm, 20000.0, delta);
    }
    @Test
    public void isCorrectConvertationsqkmToha() {
        double sqkm = 0.02;
        double ha = Converter.sqkmToha(sqkm);
        assertEquals(ha, 2.0, delta);
    }
    @Test
    public void isCorrectConvertationsqkmToare() {
        double sqkm = 0.02;
        double are = Converter.sqkmToare(sqkm);
        assertEquals(are, 200.0, delta);
    }

    // from ha
    @Test
    public void isCorrectConvertationhaTosqm() {
        double ha = 2.0;
        double sqm = Converter.haTosqm(ha);
        assertEquals(sqm, 20000.0, delta);
    }
    @Test
    public void isCorrectConvertationhaTosqkm() {
        double ha = 2.0;
        double sqkm = Converter.haTosqkm(ha);
        assertEquals(sqkm, 0.02, delta);
    }
    @Test
    public void isCorrectConvertationhaToare() {
        double ha = 2.0;
        double are = Converter.haToare(ha);
        assertEquals(are, 200.0, delta);
    }

    // from are
    @Test
    public void isCorrectConvertationareTosqm() {
        double are = 200.0;
        double sqm = Converter.areTosqm(are);
        assertEquals(sqm, 20000.0, delta);
    }
    @Test
    public void isCorrectConvertationareTosqkm() {
        double are = 200.0;
        double sqkm = Converter.areTosqkm(are);
        assertEquals(sqkm, 0.02, delta);
    }
    @Test
    public void isCorrectConvertationareToha() {
        double are = 200.0;
        double ha = Converter.areToha(are);
        assertEquals(ha, 2.0, delta);
    }
}
