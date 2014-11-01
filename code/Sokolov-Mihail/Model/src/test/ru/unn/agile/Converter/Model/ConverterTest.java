package ru.unn.agile.Converter.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class ConverterTest {
    private final double delta = 0.001;

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnNegativeDouble() {
        double ha = -1.0;
        Converter.hectareToAre(ha);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnLargeDouble() {
        double ha = Double.MAX_VALUE;
        Converter.hectareToSquareMeter(ha);
    }

    @Test
    public void notThrowsOnLargeDouble() {
        double sqm = Double.MAX_VALUE;
        Converter.squareMeterToHectare(sqm);
    }

    // from SquareMeter
    @Test
    public void isCorrectConvertationSquareMeterToSquareKilometer() {
        double sqm = 2.0 * 1000000;
        double sqkm = Converter.squareMeterToSquareKilometer(sqm);
        assertEquals(sqkm, 2.0, delta);
    }
    @Test
    public void isCorrectConvertationSquareMeterToHectare() {
        double sqm = 2.0 * 10000;
        double ha = Converter.squareMeterToHectare(sqm);
        assertEquals(ha, 2.0, delta);
    }
    @Test
    public void isCorrectConvertationSquareMeterToAre() {
        double sqm = 2.0 * 100;
        double are = Converter.squareMeterToAre(sqm);
        assertEquals(are, 2.0, delta);
    }

    // from sqkm
    @Test
    public void isCorrectConvertationSquareKilometerToSquareMeter() {
        double sqkm = 0.02;
        double sqm = Converter.squareKilometerToSquareMeter(sqkm);
        assertEquals(sqm, 20000.0, delta);
    }
    @Test
    public void isCorrectConvertationSquareKilometerToHectare() {
        double sqkm = 0.02;
        double ha = Converter.squareKilometerToHectare(sqkm);
        assertEquals(ha, 2.0, delta);
    }
    @Test
    public void isCorrectConvertationSquareKilometerToAre() {
        double sqkm = 0.02;
        double are = Converter.squareKilometerToAre(sqkm);
        assertEquals(are, 200.0, delta);
    }

    // from ha
    @Test
    public void isCorrectConvertationHectareToSquareMeter() {
        double ha = 2.0;
        double sqm = Converter.hectareToSquareMeter(ha);
        assertEquals(sqm, 20000.0, delta);
    }
    @Test
    public void isCorrectConvertationHectareToSquareKilometer() {
        double ha = 2.0;
        double sqkm = Converter.hectareToSquareKilometer(ha);
        assertEquals(sqkm, 0.02, delta);
    }
    @Test
    public void isCorrectConvertationHectareToAre() {
        double ha = 2.0;
        double are = Converter.hectareToAre(ha);
        assertEquals(are, 200.0, delta);
    }

    // from are
    @Test
    public void isCorrectConvertationAreToSquareMeter() {
        double are = 200.0;
        double sqm = Converter.areToSquareMeter(are);
        assertEquals(sqm, 20000.0, delta);
    }
    @Test
    public void isCorrectConvertationAreToSquareKilometer() {
        double are = 200.0;
        double sqkm = Converter.areToSquareKilometer(are);
        assertEquals(sqkm, 0.02, delta);
    }
    @Test
    public void isCorrectConvertationAreToHectare() {
        double are = 200.0;
        double ha = Converter.areToHectare(are);
        assertEquals(ha, 2.0, delta);
    }
}
