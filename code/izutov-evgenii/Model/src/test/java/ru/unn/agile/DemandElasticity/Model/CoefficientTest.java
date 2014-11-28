package ru.unn.agile.DemandElasticity.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class CoefficientTest {
    private final double delta = 0.000001;

    @Test
    public void canCreateCoefficientWithInitialValues() {
        Coefficient coefficient = new Coefficient(1d);

        assertNotNull(coefficient);
    }

    @Test
    public void canSetInitialValue() {
        Coefficient coefficient = new Coefficient(17d);

        assertEquals(17d, coefficient.getValue(), delta);
    }

    @Test
    public void isValueUndefinedReturnsTrueOnUndefinedValue() {
        Coefficient coefficient = new Coefficient(Double.NaN);

        assertTrue(coefficient.isValueUndefined());
    }

    @Test
    public void isValueUndefinedReturnsFalseOnFiniteValue() {
        Coefficient coefficient = new Coefficient(71d);

        assertFalse(coefficient.isValueUndefined());
    }
}
