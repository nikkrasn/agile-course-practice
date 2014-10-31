package ru.unn.agile.ConverterWeight.Model;

import org.junit.Test;
import static org.junit.Assert.*;
public class ConverterWeightTest {
private final double delta = 0.0;
    @Test
    public void grammToKilogramInputNULLReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.grammToKilogram("");
        assertEquals(0.0, result, delta);
    }

    @Test
    public void grammToKilogramInputNumberReturnInKilogram() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.grammToKilogram("1");
        assertEquals(0.001, result, delta);
    }

    @Test
    public void grammToKilogramInputNegativNumberReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.grammToKilogram("-1");
        assertEquals(0.0, result, delta);
    }

    @Test
    public void grammToCentnerInputNULLReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.grammToCentner("");
        assertEquals(0.0, result, delta);
    }

    @Test
    public void grammToCentnerInputNumberReturnInCentner() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.grammToCentner("1");
        assertEquals(0.00001, result, delta);
    }

    @Test
    public void grammToCentnerInputNegativNumberReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.grammToCentner("-1");
        assertEquals(0.0, result, delta);
    }

    @Test
    public void grammToTonInputNULLReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.grammToTon("");
        assertEquals(0.0, result, delta);
    }

    @Test
    public void grammToTonInputNumberReturnInTon() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.grammToTon("1");
        assertEquals(0.000001, result, delta);
    }

    @Test
    public void grammToTonInputNegativNumberReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.grammToTon("-1");
        assertEquals(0.0, result, delta);
    }

    @Test
    public void kilogramToGramInputNULLReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.kilogramToGram("");
        assertEquals(0.0, result, delta);
    }

    @Test
    public void kilogramToGramInputNumberReturnInGram() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.kilogramToGram("1");
        assertEquals(1000, result, delta);
    }

    @Test
    public void kilogramToGramInputNegativNumberReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.kilogramToGram("-1");
        assertEquals(0.0, result, delta);
    }

    @Test
    public void kilogramToCentnerInputNULLReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.kilogramToCentner("");
        assertEquals(0.0, result, delta);
    }

    @Test
    public void kilogramToCentnerInputNumberReturnInCentner() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.kilogramToCentner("1");
        assertEquals(0.01, result, delta);
    }

    @Test
    public void kilogramToCentnerInputNegativNumberReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.kilogramToCentner("-1");
        assertEquals(0.0, result, delta);
    }

    @Test
    public void kilogramToTonInputNULLReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.kilogramToTon("");
        assertEquals(0.0, result, delta);
    }

    @Test
    public void kilogramToTonInputNumberReturnInTon() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.kilogramToTon("1");
        assertEquals(0.001, result, delta);
    }

    @Test
    public void kilogramToTonInputNegativNumberReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.kilogramToTon("-1");
        assertEquals(0.0, result, delta);
    }

    @Test
    public void centnerToGramInputNULLReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.centnerToGram("");
        assertEquals(0.0, result, delta);
    }

    @Test
    public void centnerToGramInputNumberReturnInGram() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.centnerToGram("1");
        assertEquals(100000, result, delta);
    }

    @Test
    public void centnerToGramInputNegativNumberReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.centnerToGram("-1");
        assertEquals(0.0, result, delta);
    }

    @Test
    public void centnerToKilogramInputNULLReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.centnerToKilogram("");
        assertEquals(0.0, result, delta);
    }

    @Test
    public void centnerToKilogramInputNumberReturnInKilogram() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.centnerToKilogram("1");
        assertEquals(100, result, delta);
    }

    @Test
    public void centnerToKilogramInputNegativNumberReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.centnerToKilogram("-1");
        assertEquals(0.0, result, delta);
    }

    @Test
    public void centnerToTonInputNULLReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.centnerToTon("");
        assertEquals(0.0, result, delta);
    }

    @Test
    public void centnerToTonInputNumberReturnInTon() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.centnerToTon("1");
        assertEquals(0.1, result, delta);
    }

    @Test
    public void centnerToTonInputNegativNumberReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.centnerToTon("-1");
        assertEquals(0.0, result, delta);
    }

    @Test
    public void tonToGramInputNULLReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.tonToGram("");
        assertEquals(0.0, result, delta);
    }

    @Test
    public void tonToGramInputNumberReturnInGram() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.tonToGram("1");
        assertEquals(1000000, result, delta);
    }

    @Test
    public void tonToGramInputNegativNumberReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.tonToGram("-1");
        assertEquals(0.0, result, delta);
    }

    @Test
    public void tonToKilogramInputNULLReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.tonToKilogram("");
        assertEquals(0.0, result, delta);
    }

    @Test
    public void tonToKilogramInputNumberReturnInKilogram() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.tonToKilogram("1");
        assertEquals(1000, result, delta);
    }

    @Test
    public void tonToKilogramInputNegativNumberReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.tonToKilogram("-1");
        assertEquals(0.0, result, delta);
    }

    @Test
    public void tonToCentnerInputNULLReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.tonToCentner("");
        assertEquals(0.0, result, delta);
    }

    @Test
    public void tonToCentnerInputNumberReturnInCentner() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.tonToCentner("1");
        assertEquals(10, result, delta);
    }

    @Test
    public void tonToCentnerInputNegativNumberReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.tonToCentner("-1");
        assertEquals(0.0, result, delta);
    }
    private ConverterWeight createConverterWeight() {
        return new ConverterWeight();
    }
}
