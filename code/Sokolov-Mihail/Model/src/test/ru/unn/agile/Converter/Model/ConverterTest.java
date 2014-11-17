package ru.unn.agile.Converter.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class ConverterTest {
    private static final double DELTA = 0.001;

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnNegativeDouble() {
        double ha = -1.0;
        AreaConverter.hectareToAre(ha);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnLargeDouble() {
        double ha = Double.MAX_VALUE;
        AreaConverter.hectareToSquareMeter(ha);
    }

    @Test
    public void notThrowsOnLargeDouble() {
        double sqm = Double.MAX_VALUE;
        AreaConverter.squareMeterToHectare(sqm);
    }

    @Test
    public void isCorrectConvertationSquareMeterToSquareKilometer() {
        double sqm = 2.0 * 1000000;
        double sqkm = AreaConverter.squareMeterToSquareKilometer(sqm);
        assertEquals(sqkm, 2.0, DELTA);
    }

    @Test
    public void isCorrectConvertationSquareMeterToHectare() {
        double sqm = 2.0 * 10000;
        double ha = AreaConverter.squareMeterToHectare(sqm);
        assertEquals(ha, 2.0, DELTA);
    }

    @Test
    public void isCorrectConvertationSquareMeterToAre() {
        double sqm = 2.0 * 100;
        double are = AreaConverter.squareMeterToAre(sqm);
        assertEquals(are, 2.0, DELTA);
    }

    @Test
    public void isCorrectConvertationSquareKilometerToSquareMeter() {
        double sqkm = 0.02;
        double sqm = AreaConverter.squareKilometerToSquareMeter(sqkm);
        assertEquals(sqm, 20000.0, DELTA);
    }

    @Test
    public void isCorrectConvertationSquareKilometerToHectare() {
        double sqkm = 0.02;
        double ha = AreaConverter.squareKilometerToHectare(sqkm);
        assertEquals(ha, 2.0, DELTA);
    }

    @Test
    public void isCorrectConvertationSquareKilometerToAre() {
        double sqkm = 0.02;
        double are = AreaConverter.squareKilometerToAre(sqkm);
        assertEquals(are, 200.0, DELTA);
    }

    @Test
    public void isCorrectConvertationHectareToSquareMeter() {
        double ha = 2.0;
        double sqm = AreaConverter.hectareToSquareMeter(ha);
        assertEquals(sqm, 20000.0, DELTA);
    }

    @Test
    public void isCorrectConvertationHectareToSquareKilometer() {
        double ha = 2.0;
        double sqkm = AreaConverter.hectareToSquareKilometer(ha);
        assertEquals(sqkm, 0.02, DELTA);
    }

    @Test
    public void isCorrectConvertationHectareToAre() {
        double ha = 2.0;
        double are = AreaConverter.hectareToAre(ha);
        assertEquals(are, 200.0, DELTA);
    }

    @Test
    public void isCorrectConvertationAreToSquareMeter() {
        double are = 200.0;
        double sqm = AreaConverter.areToSquareMeter(are);
        assertEquals(sqm, 20000.0, DELTA);
    }

    @Test
    public void isCorrectConvertationAreToSquareKilometer() {
        double are = 200.0;
        double sqkm = AreaConverter.areToSquareKilometer(are);
        assertEquals(sqkm, 0.02, DELTA);
    }

    @Test
    public void isCorrectConvertationAreToHectare() {
        double are = 200.0;
        double ha = AreaConverter.areToHectare(are);
        assertEquals(ha, 2.0, DELTA);
    }
}
