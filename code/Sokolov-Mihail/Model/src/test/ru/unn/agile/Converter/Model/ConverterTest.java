package ru.unn.agile.Converter.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class ConverterTest {
    private final double delta = 0.001;

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnNegativeDouble() {
        double ha = -1.0;
        Converter.haToAre(ha);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnLargeDouble() {
        double ha = Double.MAX_VALUE;
        Converter.haToSqm(ha);
    }

    @Test
    public void notThrowsOnLargeDouble() {
        double sqm = Double.MAX_VALUE;
        Converter.sqmToHa(sqm);
    }

    // from sqm
    @Test
    public void isCorrectConvertationsqmToSqkm() {
        double sqm = 2.0 * 1000000;
        double sqkm = Converter.sqmToSqkm(sqm);
        assertEquals(sqkm, 2.0, delta);
    }
    @Test
    public void isCorrectConvertationsqmToHa() {
        double sqm = 2.0 * 10000;
        double ha = Converter.sqmToHa(sqm);
        assertEquals(ha, 2.0, delta);
    }
    @Test
    public void isCorrectConvertationsqmToAre() {
        double sqm = 2.0 * 100;
        double are = Converter.sqmToAre(sqm);
        assertEquals(are, 2.0, delta);
    }

    // from sqkm
    @Test
    public void isCorrectConvertationsqkmToSqm() {
        double sqkm = 0.02;
        double sqm = Converter.sqkmToSqm(sqkm);
        assertEquals(sqm, 20000.0, delta);
    }
    @Test
    public void isCorrectConvertationsqkmToHa() {
        double sqkm = 0.02;
        double ha = Converter.sqkmToHa(sqkm);
        assertEquals(ha, 2.0, delta);
    }
    @Test
    public void isCorrectConvertationsqkmToAre() {
        double sqkm = 0.02;
        double are = Converter.sqkmToAre(sqkm);
        assertEquals(are, 200.0, delta);
    }

    // from ha
    @Test
    public void isCorrectConvertationhaToSqm() {
        double ha = 2.0;
        double sqm = Converter.haToSqm(ha);
        assertEquals(sqm, 20000.0, delta);
    }
    @Test
    public void isCorrectConvertationhaToSqkm() {
        double ha = 2.0;
        double sqkm = Converter.haToSqkm(ha);
        assertEquals(sqkm, 0.02, delta);
    }
    @Test
    public void isCorrectConvertationhaToAre() {
        double ha = 2.0;
        double are = Converter.haToAre(ha);
        assertEquals(are, 200.0, delta);
    }

    // from are
    @Test
    public void isCorrectConvertationareToSqm() {
        double are = 200.0;
        double sqm = Converter.areToSqm(are);
        assertEquals(sqm, 20000.0, delta);
    }
    @Test
    public void isCorrectConvertationareToSqkm() {
        double are = 200.0;
        double sqkm = Converter.areToSqkm(are);
        assertEquals(sqkm, 0.02, delta);
    }
    @Test
    public void isCorrectConvertationareToHa() {
        double are = 200.0;
        double ha = Converter.areToHa(are);
        assertEquals(ha, 2.0, delta);
    }
}
