package ru.unn.agile.ConverterWeight.Model;

import org.junit.Test;
import static org.junit.Assert.*;
import static ru.unn.agile.ConverterWeight.Model.ConverterWeight.converter;
import ru.unn.agile.ConverterWeight.Model.ConverterWeight.*;

public class ConverterWeightTest {
    private final double delta = 0.0;

    @Test
    public void grammToKilogramInputNumberReturnInKilogram() {
        double result = converter(UnitWeight.GRAMM, UnitWeight.KILOGRAM, 1);
        assertEquals(0.001, result, delta);
    }

    @Test (expected = IllegalArgumentException.class)
    public void grammToKilogramInputNegativNumberReturnExeption() {
        converter(UnitWeight.GRAMM, UnitWeight.KILOGRAM, -1);
    }

    @Test
    public void grammToKilogramInputMaxDoubleReturnExeption() {
        converter(UnitWeight.GRAMM, UnitWeight.KILOGRAM, Double.MAX_VALUE);
    }

    @Test
    public void grammToCentnerInputNumberReturnInCentner() {
        double result = converter(UnitWeight.GRAMM, UnitWeight.CENTNER, 1);
        assertEquals(0.00001, result, delta);
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
        assertEquals(0.000001, result, delta);
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
    public void kilogramToGrammInputNumberReturnInGram() {
        double result = converter(UnitWeight.KILOGRAM, UnitWeight.GRAMM, 1);
        assertEquals(1000, result, delta);
    }

    @Test (expected = IllegalArgumentException.class)
    public void kilogramToGrammInputNegativNumberReturnExeption() {
        converter(UnitWeight.KILOGRAM, UnitWeight.GRAMM, -1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void kilogramToGrammInputMaxDoubleReturnExeption() {
        converter(UnitWeight.KILOGRAM, UnitWeight.GRAMM, Double.MAX_VALUE);
    }

    @Test
    public void kilogramToCentnerInputNumberReturnInCentner() {
        double result = converter(UnitWeight.KILOGRAM, UnitWeight.CENTNER, 1);
        assertEquals(0.01, result, delta);
    }

    @Test (expected = IllegalArgumentException.class)
    public void kilogramToCentnerInputNegativNumberReturnExeption() {
        converter(UnitWeight.KILOGRAM, UnitWeight.CENTNER, -1);
    }

    @Test
    public void kilogramToCentnerInputMaxDoubleReturnExeption() {
        converter(UnitWeight.KILOGRAM, UnitWeight.CENTNER, Double.MAX_VALUE);
    }

    @Test
    public void kilogramToTonInputNumberReturnInTon() {
        double result = converter(UnitWeight.KILOGRAM, UnitWeight.TON, 1);
        assertEquals(0.001, result, delta);
    }

    @Test (expected = IllegalArgumentException.class)
    public void kilogramToTonInputNegativNumberReturnExeption() {
        converter(UnitWeight.KILOGRAM, UnitWeight.TON, -1);
    }

    @Test
    public void kilogramToTonInputMaxDoubleReturnExeption() {
        converter(UnitWeight.KILOGRAM, UnitWeight.TON, Double.MAX_VALUE);
    }

    @Test
    public void centnerToGrammInputNumberReturnInGram() {
        double result = converter(UnitWeight.CENTNER, UnitWeight.GRAMM, 1);
        assertEquals(100000, result, delta);
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
    public void centnerToKilogramInputNumberReturnInKilogram() {
        double result = converter(UnitWeight.CENTNER, UnitWeight.KILOGRAM, 1);
        assertEquals(100, result, delta);
    }

    @Test (expected = IllegalArgumentException.class)
    public void centnerToKilogramInputNegativNumberReturnExeption() {
        converter(UnitWeight.CENTNER, UnitWeight.KILOGRAM, -1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void centnerToKillogramInputMaxDoubleReturnExeption() {
        converter(UnitWeight.CENTNER, UnitWeight.KILOGRAM, Double.MAX_VALUE);
    }

    @Test
    public void centnerToTonInputNumberReturnInTon() {
        double result = converter(UnitWeight.CENTNER, UnitWeight.TON, 1);
        assertEquals(0.1, result, delta);
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
        assertEquals(1000000, result, delta);
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
    public void tonToKilogramInputNumberReturnInKilogram() {
        double result = converter(UnitWeight.TON, UnitWeight.KILOGRAM, 1);
        assertEquals(1000, result, delta);
    }

    @Test (expected = IllegalArgumentException.class)
    public void tonToKilogramInputNegativNumberReturnExeption() {
        converter(UnitWeight.TON, UnitWeight.KILOGRAM, -1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void tonToKilogramInputMaxDoubleReturnExeption() {
        converter(UnitWeight.TON, UnitWeight.KILOGRAM, Double.MAX_VALUE);
    }

    @Test
    public void tonToCentnerInputNumberReturnInCentner() {
        double result = converter(UnitWeight.TON, UnitWeight.CENTNER, 1);
        assertEquals(10, result, delta);
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
