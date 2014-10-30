package ru.unn.agile.ConverterWeight.Model;

import org.junit.Test;
import static org.junit.Assert.*;
public class ConverterWeightTest {

    @Test
    public void GrammToKilogram_InputNULL_ReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.GrammToKilogram("");
        assertEquals(0.0, result, 0.0);
    }

    @Test
    public void GrammToKilogram_InputNumber_ReturnInKilogram() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.GrammToKilogram("1");
        assertEquals(0.001, result, 0.0);
    }

    @Test
    public void GrammToKilogram_InputNegativNumber_ReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.GrammToKilogram("-1");
        assertEquals(0.0, result, 0.0);
    }

    @Test
    public void GrammToCentner_InputNULL_ReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.GrammToCentner("");
        assertEquals(0.0, result, 0.0);
    }

    @Test
    public void GrammToCentner_InputNumber_ReturnInCentner() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.GrammToCentner("1");
        assertEquals(0.00001, result, 0.0);
    }

    @Test
    public void GrammToCentner_InputNegativNumber_ReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.GrammToCentner("-1");
        assertEquals(0.0, result, 0.0);
    }

    @Test
    public void GrammToTon_InputNULL_ReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.GrammToTon("");
        assertEquals(0.0, result, 0.0);
    }

    @Test
    public void GrammToTon_InputNumber_ReturnInTon() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.GrammToTon("1");
        assertEquals(0.000001, result, 0.0);
    }

    @Test
    public void GrammToTon_InputNegativNumber_ReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.GrammToTon("-1");
        assertEquals(0.0, result, 0.0);
    }

    @Test
    public void KilogramToGram_InputNULL_ReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.KilogramToGram("");
        assertEquals(0.0, result, 0.0);
    }

    @Test
    public void KilogramToGram_InputNumber_ReturnInGram() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.KilogramToGram("1");
        assertEquals(1000, result, 0.0);
    }

    @Test
    public void KilogramToGram_InputNegativNumber_ReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.GrammToKilogram("-1");
        assertEquals(0.0, result, 0.0);
    }

    @Test
    public void KilogramToCentner_InputNULL_ReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.KilogramToCentner("");
        assertEquals(0.0, result, 0.0);
    }

    @Test
    public void KilogramToCentner_InputNumber_ReturnInCentner() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.KilogramToCentner("1");
        assertEquals(0.01, result, 0.0);
    }

    @Test
    public void KilogramToCentner_InputNegativNumber_ReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.KilogramToCentner("-1");
        assertEquals(0.0, result, 0.0);
    }

    @Test
    public void KilogramToTon_InputNULL_ReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.KilogramToTon("");
        assertEquals(0.0, result, 0.0);
    }

    @Test
    public void KilogramToTon_InputNumber_ReturnInTon() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.KilogramToTon("1");
        assertEquals(0.001, result, 0.0);
    }

    @Test
    public void KilogramToTon_InputNegativNumber_ReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.KilogramToTon("-1");
        assertEquals(0.0, result, 0.0);
    }
//_______________------------------------------


    @Test
    public void CentnerToGram_InputNULL_ReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.CentnerToGram("");
        assertEquals(0.0, result, 0.0);
    }

    @Test
    public void CentnerToGram_InputNumber_ReturnInGram() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.CentnerToGram("1");
        assertEquals(100000, result, 0.0);
    }

    @Test
    public void CentnerToGram_InputNegativNumber_ReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.CentnerToGram("-1");
        assertEquals(0.0, result, 0.0);
    }

    @Test
    public void CentnerToKilogram_InputNULL_ReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.CentnerToKilogram("");
        assertEquals(0.0, result, 0.0);
    }

    @Test
    public void CentnerToKilogram_InputNumber_ReturnInKilogram() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.CentnerToKilogram("1");
        assertEquals(100, result, 0.0);
    }

    @Test
    public void CentnerToKilogram_InputNegativNumber_ReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.CentnerToKilogram("-1");
        assertEquals(0.0, result, 0.0);
    }

    @Test
    public void CentnerToTon_InputNULL_ReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.CentnerToTon("");
        assertEquals(0.0, result, 0.0);
    }

    @Test
    public void CentnerToTon_InputNumber_ReturnInTon() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.CentnerToTon("1");
        assertEquals(0.1, result, 0.0);
    }

    @Test
    public void CentnerToTon_InputNegativNumber_ReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.CentnerToTon("-1");
        assertEquals(0.0, result, 0.0);
    }

    //-----------------------------
    @Test
    public void TonToGram_InputNULL_ReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.TonToGram("");
        assertEquals(0.0, result, 0.0);
    }

    @Test
    public void TonToGram_InputNumber_ReturnInGram() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.TonToGram("1");
        assertEquals(1000000, result, 0.0);
    }

    @Test
    public void TonToGram_InputNegativNumber_ReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.TonToGram("-1");
        assertEquals(0.0, result, 0.0);
    }

    @Test
    public void TonToKilogram_InputNULL_ReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.TonToKilogram("");
        assertEquals(0.0, result, 0.0);
    }

    @Test
    public void TonToKilogram_InputNumber_ReturnInKilogram() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.TonToKilogram("1");
        assertEquals(1000, result, 0.0);
    }

    @Test
    public void TonToKilogram_InputNegativNumber_ReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.TonToKilogram("-1");
        assertEquals(0.0, result, 0.0);
    }

    @Test
    public void TonToCentner_InputNULL_ReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.TonToCentner("");
        assertEquals(0.0, result, 0.0);
    }

    @Test
    public void TonToCentner_InputNumber_ReturnInCentner() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.TonToCentner("1");
        assertEquals(100, result, 0.0);
    }

    @Test
    public void TonToCentner_InputNegativNumber_ReturnZero() {
        ConverterWeight converter = createConverterWeight();
        double result = converter.TonToCentner("-1");
        assertEquals(0.0, result, 0.0);
    }
    private ConverterWeight createConverterWeight() {
        return new ConverterWeight();
    }
}