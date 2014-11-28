package ru.unn.agile.ConverterWeight.Model;

import org.junit.Test;
import static org.junit.Assert.*;
import static ru.unn.agile.ConverterWeight.Model.ConverterWeight.converter;
import ru.unn.agile.ConverterWeight.Model.ConverterWeight.*;

public class ConverterWeightTest {
    private static final double DELTA = 0.0001;

    @Test
    public void grammToKilogrammInputNumberReturnInKilogram() {
        double result = converter(UnitWeight.GRAMM, UnitWeight.KILOGRAMM, 1);
        assertEquals(0.001, result, DELTA);
    }

    @Test (expected = IllegalArgumentException.class)
    public void grammToKilogrammInputNegativNumberReturnExeption() {
        converter(UnitWeight.GRAMM, UnitWeight.KILOGRAMM, -1);
    }

    @Test
    public void grammToKilogrammInputMaxDoubleReturnExeption() {
        converter(UnitWeight.GRAMM, UnitWeight.KILOGRAMM, Double.MAX_VALUE);
    }

    @Test
    public void grammToCentnerInputNumberReturnInCentner() {
        double result = converter(UnitWeight.GRAMM, UnitWeight.CENTNER, 1);
        assertEquals(0.00001, result, DELTA);
    }

    @Test (expected = IllegalArgumentException.class)
    public void grammToCentnerInputNegativNumberReturnExeption() {
        converter(UnitWeight.GRAMM, UnitWeight.CENTNER, -1);
    }

    @Test
    public void grammToCentnerInputMaxDoubleReturnExeption() {
        converter(UnitWeight.GRAMM, UnitWeight.CENTNER, Double.MAX_VALUE);
    }

    @Test
    public void grammToTonInputNumberReturnInTon() {
        double result = converter(UnitWeight.GRAMM, UnitWeight.TON, 1);
        assertEquals(0.000001, result, DELTA);
    }

    @Test (expected = IllegalArgumentException.class)
    public void grammToTonInputNegativNumberReturnExeption() {
        converter(UnitWeight.GRAMM, UnitWeight.TON, -1);
    }

    @Test
    public void grammToTonInputMaxDoubleReturnExeption() {
        converter(UnitWeight.GRAMM, UnitWeight.TON, Double.MAX_VALUE);
    }

    @Test
    public void kilogrammToGrammInputNumberReturnInGram() {
        double result = converter(UnitWeight.KILOGRAMM, UnitWeight.GRAMM, 1);
        assertEquals(1000, result, DELTA);
    }

    @Test (expected = IllegalArgumentException.class)
    public void kilogrammToGrammInputNegativNumberReturnExeption() {
        converter(UnitWeight.KILOGRAMM, UnitWeight.GRAMM, -1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void kilogrammToGrammInputMaxDoubleReturnExeption() {
        converter(UnitWeight.KILOGRAMM, UnitWeight.GRAMM, Double.MAX_VALUE);
    }

    @Test
    public void kilogrammToCentnerInputNumberReturnInCentner() {
        double result = converter(UnitWeight.KILOGRAMM, UnitWeight.CENTNER, 1);
        assertEquals(0.01, result, DELTA);
    }

    @Test (expected = IllegalArgumentException.class)
    public void kilogrammToCentnerInputNegativNumberReturnExeption() {
        converter(UnitWeight.KILOGRAMM, UnitWeight.CENTNER, -1);
    }

    @Test
    public void kilogrammToCentnerInputMaxDoubleReturnExeption() {
        converter(UnitWeight.KILOGRAMM, UnitWeight.CENTNER, Double.MAX_VALUE);
    }

    @Test
    public void kilogrammToTonInputNumberReturnInTon() {
        double result = converter(UnitWeight.KILOGRAMM, UnitWeight.TON, 1);
        assertEquals(0.001, result, DELTA);
    }

    @Test (expected = IllegalArgumentException.class)
    public void kilogrammToTonInputNegativNumberReturnExeption() {
        converter(UnitWeight.KILOGRAMM, UnitWeight.TON, -1);
    }

    @Test
    public void kilogrammToTonInputMaxDoubleReturnExeption() {
        converter(UnitWeight.KILOGRAMM, UnitWeight.TON, Double.MAX_VALUE);
    }

    @Test
    public void centnerToGrammInputNumberReturnInGram() {
        double result = converter(UnitWeight.CENTNER, UnitWeight.GRAMM, 1);
        assertEquals(100000, result, DELTA);
    }

    @Test (expected = IllegalArgumentException.class)
    public void centnerToGrammInputNegativNumberReturnExeption() {
        converter(UnitWeight.CENTNER, UnitWeight.GRAMM, -1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void centnerToGrammInputMaxDoubleReturnExeption() {
        converter(UnitWeight.CENTNER, UnitWeight.GRAMM, Double.MAX_VALUE);
    }

    @Test
    public void centnerToKilogrammInputNumberReturnInKilogram() {
        double result = converter(UnitWeight.CENTNER, UnitWeight.KILOGRAMM, 1);
        assertEquals(100, result, DELTA);
    }

    @Test (expected = IllegalArgumentException.class)
    public void centnerToKilogrammInputNegativNumberReturnExeption() {
        converter(UnitWeight.CENTNER, UnitWeight.KILOGRAMM, -1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void centnerToKillogrammInputMaxDoubleReturnExeption() {
        converter(UnitWeight.CENTNER, UnitWeight.KILOGRAMM, Double.MAX_VALUE);
    }

    @Test
    public void centnerToTonInputNumberReturnInTon() {
        double result = converter(UnitWeight.CENTNER, UnitWeight.TON, 1);
        assertEquals(0.1, result, DELTA);
    }

    @Test (expected = IllegalArgumentException.class)
    public void centnerToTonInputNegativNumberReturnExeption() {
        converter(UnitWeight.CENTNER, UnitWeight.TON, -1);
    }

    @Test
    public void centnerToTonInputMaxDoubleReturnExeption() {
        converter(UnitWeight.CENTNER, UnitWeight.TON, Double.MAX_VALUE);
    }

    @Test
    public void tonToGrammInputNumberReturnInGram() {
        double result = converter(UnitWeight.TON, UnitWeight.GRAMM, 1);
        assertEquals(1000000, result, DELTA);
    }

    @Test (expected = IllegalArgumentException.class)
    public void tonToGramInputNegativNumberReturnExeption() {
        converter(UnitWeight.TON, UnitWeight.GRAMM, -1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void tonToGramInputMaxDoubleReturnExeption() {
        converter(UnitWeight.TON, UnitWeight.GRAMM, Double.MAX_VALUE);
    }

    @Test
    public void tonToKilogrammInputNumberReturnInKilogram() {
        double result = converter(UnitWeight.TON, UnitWeight.KILOGRAMM, 1);
        assertEquals(1000, result, DELTA);
    }

    @Test (expected = IllegalArgumentException.class)
    public void tonToKilogrammInputNegativNumberReturnExeption() {
        converter(UnitWeight.TON, UnitWeight.KILOGRAMM, -1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void tonToKilogrammInputMaxDoubleReturnExeption() {
        converter(UnitWeight.TON, UnitWeight.KILOGRAMM, Double.MAX_VALUE);
    }

    @Test
    public void tonToCentnerInputNumberReturnInCentner() {
        double result = converter(UnitWeight.TON, UnitWeight.CENTNER, 1);
        assertEquals(10, result, DELTA);
    }

    @Test (expected = IllegalArgumentException.class)
    public void tonToCentnerInputNegativNumberReturnExeption() {
        converter(UnitWeight.TON, UnitWeight.CENTNER, -1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void tonToCentnerInputMaxDoubleReturnExeption() {
        converter(UnitWeight.TON, UnitWeight.CENTNER, Double.MAX_VALUE);
    }
}
