package ru.unn.agile.ConverterWeight.Model;

import org.junit.Test;
import static org.junit.Assert.*;
public class ConverterWeightTest {
private final double delta = 0.0;

    @Test
    public void grammToKilogramInputNumberReturnInKilogram() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.grammToKilogram("1");
        assertEquals(0.001, result, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void grammToKilogramInputNegativNumberReturnExeption() {
        ConverterWeight converter = createConverterWeight();
        converter.grammToKilogram("-1");
    }

    @Test (expected = IllegalArgumentException.class)
    public void grammToKilogramInputMaxDoubleReturnExeption() {
        ConverterWeight converter = createConverterWeight();
        double tmp = Double.MAX_VALUE;
        String str = Double.toString(tmp);
        converter.grammToKilogram(str);
    }

    @Test
    public void grammToCentnerInputNumberReturnInCentner() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.grammToCentner("1");
        assertEquals(0.00001, result, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void grammToCentnerInputNegativNumberReturnExeption() {
        ConverterWeight converter = createConverterWeight();
        converter.grammToCentner("-1");
    }

    @Test (expected = IllegalArgumentException.class)
    public void grammToCentnerInputMaxDoubleReturnExeption() {
        ConverterWeight converter = createConverterWeight();
        double tmp = Double.MAX_VALUE;
        String str = Double.toString(tmp);
        converter.grammToCentner(str);
    }

    @Test
    public void grammToTonInputNumberReturnInTon() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.grammToTon("1");
        assertEquals(0.000001, result, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void grammToTonInputNegativNumberReturnExeption() {
        ConverterWeight converter = createConverterWeight();
        converter.grammToTon("-1");
    }

    @Test (expected = IllegalArgumentException.class)
    public void grammToTonInputMaxDoubleReturnExeption() {
        ConverterWeight converter = createConverterWeight();
        double tmp = Double.MAX_VALUE;
        String str = Double.toString(tmp);
        converter.grammToTon(str);
    }

    @Test
    public void kilogramToGramInputNumberReturnInGram() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.kilogramToGram("1");
        assertEquals(1000, result, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void kilogramToGramInputNegativNumberReturnExeption() {
        ConverterWeight converter = createConverterWeight();
        converter.kilogramToGram("-1");
    }

    @Test (expected = IllegalArgumentException.class)
    public void kilogramToGramInputMaxDoubleReturnExeption() {
        ConverterWeight converter = createConverterWeight();
        double tmp = Double.MAX_VALUE;
        String str = Double.toString(tmp);
        converter.kilogramToGram(str);
    }

    @Test
    public void kilogramToCentnerInputNumberReturnInCentner() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.kilogramToCentner("1");
        assertEquals(0.01, result, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void kilogramToCentnerInputNegativNumberReturnExeption() {
        ConverterWeight converter = createConverterWeight();
        converter.kilogramToCentner("-1");
    }

    @Test (expected = IllegalArgumentException.class)
    public void kilogramToCentnerInputMaxDoubleReturnExeption() {
        ConverterWeight converter = createConverterWeight();
        double tmp = Double.MAX_VALUE;
        String str = Double.toString(tmp);
        converter.kilogramToCentner(str);
    }

    @Test
    public void kilogramToTonInputNumberReturnInTon() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.kilogramToTon("1");
        assertEquals(0.001, result, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void kilogramToTonInputNegativNumberReturnExeption() {
        ConverterWeight converter = createConverterWeight();
        converter.kilogramToTon("-1");
    }

    @Test (expected = IllegalArgumentException.class)
    public void kilogramToTonInputMaxDoubleReturnExeption() {
        ConverterWeight converter = createConverterWeight();
        double tmp = Double.MAX_VALUE;
        String str = Double.toString(tmp);
        converter.kilogramToTon(str);
    }

    @Test
    public void centnerToGramInputNumberReturnInGram() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.centnerToGram("1");
        assertEquals(100000, result, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void centnerToGramInputNegativNumberReturnExeption() {
        ConverterWeight converter = createConverterWeight();
        converter.centnerToGram("-1");
    }

    @Test (expected = IllegalArgumentException.class)
    public void centnerToGramInputMaxDoubleReturnExeption() {
        ConverterWeight converter = createConverterWeight();
        double tmp = Double.MAX_VALUE;
        String str = Double.toString(tmp);
        converter.centnerToGram(str);
    }

    @Test
    public void centnerToKilogramInputNumberReturnInKilogram() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.centnerToKilogram("1");
        assertEquals(100, result, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void centnerToKilogramInputNegativNumberReturnExeption() {
        ConverterWeight converter = createConverterWeight();
        converter.centnerToKilogram("-1");
    }

    @Test (expected = IllegalArgumentException.class)
    public void centnerToKillogramInputMaxDoubleReturnExeption() {
        ConverterWeight converter = createConverterWeight();
        double tmp = Double.MAX_VALUE;
        String str = Double.toString(tmp);
        converter.centnerToKilogram(str);
    }

    @Test
    public void centnerToTonInputNumberReturnInTon() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.centnerToTon("1");
        assertEquals(0.1, result, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void centnerToTonInputNegativNumberReturnExeption() {
        ConverterWeight converter = createConverterWeight();
        converter.centnerToTon("-1");
    }

    @Test (expected = IllegalArgumentException.class)
    public void centnerToTonInputMaxDoubleReturnExeption() {
        ConverterWeight converter = createConverterWeight();
        double tmp = Double.MAX_VALUE;
        String str = Double.toString(tmp);
        converter.centnerToTon(str);
    }

    @Test
    public void tonToGramInputNumberReturnInGram() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.tonToGram("1");
        assertEquals(1000000, result, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void tonToGramInputNegativNumberReturnExeption() {
        ConverterWeight converter = createConverterWeight();
        converter.tonToGram("-1");
    }

    @Test (expected = IllegalArgumentException.class)
    public void tonToGramInputMaxDoubleReturnExeption() {
        ConverterWeight converter = createConverterWeight();
        double tmp = Double.MAX_VALUE;
        String str = Double.toString(tmp);
        converter.tonToGram(str);
    }

    @Test
    public void tonToKilogramInputNumberReturnInKilogram() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.tonToKilogram("1");
        assertEquals(1000, result, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void tonToKilogramInputNegativNumberReturnExeption() {
        ConverterWeight converter = createConverterWeight();
        converter.tonToKilogram("-1");
    }

    @Test (expected = IllegalArgumentException.class)
    public void tonToKilogramInputMaxDoubleReturnExeption() {
        ConverterWeight converter = createConverterWeight();
        double tmp = Double.MAX_VALUE;
        String str = Double.toString(tmp);
        converter.tonToKilogram(str);
    }

    @Test
    public void tonToCentnerInputNumberReturnInCentner() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.tonToCentner("1");
        assertEquals(10, result, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void tonToCentnerInputNegativNumberReturnExeption() {
        ConverterWeight converter = createConverterWeight();
        converter.tonToCentner("-1");
    }

    @Test (expected = IllegalArgumentException.class)
    public void tonToCentnerInputMaxDoubleReturnExeption() {
        ConverterWeight converter = createConverterWeight();
        double tmp = Double.MAX_VALUE;
        String str = Double.toString(tmp);
        converter.tonToCentner(str);
    }

    private ConverterWeight createConverterWeight() {
        return new ConverterWeight();
    }
}
