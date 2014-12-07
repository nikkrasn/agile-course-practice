package ru.unn.agile.AreaConverter.Model;

import org.junit.Test;
import ru.unn.agile.AreaConverter.Model.AreaConverter.Measures;

import static org.junit.Assert.assertEquals;

public class ConverterTest {
    private static final double DELTA = 1e-100;

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnNegativeDouble() {
        double ha = -1.0;
        Measures from = Measures.Hectare;
        Measures to = Measures.Are;

        AreaConverter.fromTo(ha, from, to);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnLargeDouble() {
        double ha = Double.MAX_VALUE;
        Measures from = Measures.Hectare;
        Measures to = Measures.SquareMeter;

        AreaConverter.fromTo(ha, from, to);
    }

    @Test
    public void notThrowsOnLargeDouble() {
        double sqm = Double.MAX_VALUE;
        Measures from = Measures.SquareMeter;
        Measures to = Measures.Hectare;

        AreaConverter.fromTo(sqm, from, to);
    }

    @Test
    public void isCorrectConvertationSquareMeterToSquareMeter() {
        double sqm = 2.0;
        Measures from = Measures.SquareMeter;
        Measures to = Measures.SquareMeter;

        double sqm2 = AreaConverter.fromTo(sqm, from, to);

        assertEquals(sqm2, 2.0, DELTA);
    }

    @Test
    public void isCorrectConvertationSquareMeterToSquareKilometer() {
        double sqm = 2.0 * 1000000;
        Measures from = Measures.SquareMeter;
        Measures to = Measures.SquareKilometer;

        double sqkm = AreaConverter.fromTo(sqm, from, to);

        assertEquals(sqkm, 2.0, DELTA);
    }

    @Test
    public void isCorrectConvertationSquareMeterToHectare() {
        double sqm = 2.0 * 10000;
        Measures from = Measures.SquareMeter;
        Measures to = Measures.Hectare;

        double ha = AreaConverter.fromTo(sqm, from, to);

        assertEquals(ha, 2.0, DELTA);
    }

    @Test
    public void isCorrectConvertationSquareMeterToAre() {
        double sqm = 2.0 * 100;
        Measures from = Measures.SquareMeter;
        Measures to = Measures.Are;

        double are = AreaConverter.fromTo(sqm, from, to);

        assertEquals(are, 2.0, DELTA);
    }

    @Test
    public void isCorrectConvertationSquareKilometerToSquareMeter() {
        double sqkm = 0.02;
        Measures from = Measures.SquareKilometer;
        Measures to = Measures.SquareMeter;

        double sqm = AreaConverter.fromTo(sqkm, from, to);

        assertEquals(sqm, 20000.0, DELTA);
    }

    @Test
    public void isCorrectConvertationSquareKilometerToSquareKilometer() {
        double sqkm = 0.02;
        Measures from = Measures.SquareKilometer;
        Measures to = Measures.SquareKilometer;

        double sqkm2 = AreaConverter.fromTo(sqkm, from, to);

        assertEquals(sqkm2, 0.02, DELTA);
    }

    @Test
    public void isCorrectConvertationSquareKilometerToHectare() {
        double sqkm = 0.02;
        Measures from = Measures.SquareKilometer;
        Measures to = Measures.Hectare;

        double ha = AreaConverter.fromTo(sqkm, from, to);

        assertEquals(ha, 2.0, DELTA);
    }

    @Test
    public void isCorrectConvertationSquareKilometerToAre() {
        double sqkm = 0.02;
        Measures from = Measures.SquareKilometer;
        Measures to = Measures.Are;

        double are = AreaConverter.fromTo(sqkm, from, to);

        assertEquals(are, 200.0, DELTA);
    }

    @Test
    public void isCorrectConvertationHectareToSquareMeter() {
        double ha = 2.0;
        Measures from = Measures.Hectare;
        Measures to = Measures.SquareMeter;

        double sqm = AreaConverter.fromTo(ha, from, to);

        assertEquals(sqm, 20000.0, DELTA);
    }

    @Test
    public void isCorrectConvertationHectareToSquareKilometer() {
        double ha = 2.0;
        Measures from = Measures.Hectare;
        Measures to = Measures.SquareKilometer;

        double sqkm = AreaConverter.fromTo(ha, from, to);

        assertEquals(sqkm, 0.02, DELTA);
    }

    @Test
    public void isCorrectConvertationHectareToAre() {
        double ha = 2.0;
        Measures from = Measures.Hectare;
        Measures to = Measures.Are;

        double are = AreaConverter.fromTo(ha, from, to);

        assertEquals(are, 200.0, DELTA);
    }

    @Test
    public void isCorrectConvertationHectareToHectare() {
        double ha = 2.0;
        Measures from = Measures.Hectare;
        Measures to = Measures.Hectare;

        double ha2 = AreaConverter.fromTo(ha, from, to);

        assertEquals(ha2, 2.0, DELTA);
    }

    @Test
    public void isCorrectConvertationAreToSquareMeter() {
        double are = 200.0;
        Measures from = Measures.Are;
        Measures to = Measures.SquareMeter;

        double sqm = AreaConverter.fromTo(are, from, to);

        assertEquals(sqm, 20000.0, DELTA);
    }

    @Test
    public void isCorrectConvertationAreToSquareKilometer() {
        double are = 200.0;
        Measures from = Measures.Are;
        Measures to = Measures.SquareKilometer;

        double sqkm = AreaConverter.fromTo(are, from, to);

        assertEquals(sqkm, 0.02, DELTA);
    }

    @Test
    public void isCorrectConvertationAreToAre() {
        double are = 200.0;
        Measures from = Measures.Are;
        Measures to = Measures.Are;

        double are2 = AreaConverter.fromTo(are, from, to);

        assertEquals(are2, 200.0, DELTA);
    }

    @Test
    public void isCorrectConvertationAreToHectare() {
        double are = 200.0;
        Measures from = Measures.Are;
        Measures to = Measures.Hectare;

        double ha = AreaConverter.fromTo(are, from, to);

        assertEquals(ha, 2.0, DELTA);
    }
}
