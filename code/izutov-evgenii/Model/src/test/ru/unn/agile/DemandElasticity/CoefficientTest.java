package ru.unn.agile.DemandElasticity;

import org.junit.Test;
import static org.junit.Assert.*;

public class CoefficientTest {
    private final double delta = 0.000001;

    @Test
    public void canCreateCoefficientWithInitialValues() {
        Coefficient coefficient = new Coefficient<>(DemandType.GiffenGood, 1);
        assertNotNull(coefficient);
    }

    @Test(expected = IllegalArgumentException.class)
    public void coefficientThrowsExceptionOnNullTypeInput() {
        new Coefficient<>(null, 1);
    }

    @Test
    public void canSetInitialType() {
        Coefficient coefficient = new Coefficient<>(DemandType.Inelastic, 1);
        assertEquals(DemandType.Inelastic, coefficient.getType());
    }

    @Test
    public void canSetInitialValue() {
        Coefficient coefficient = new Coefficient<>(DemandType.Elasticity, 17);
        assertEquals(17, coefficient.getValue(), delta);
    }

    @Test
    public void isUndefinedReturnsTrueOnUndefinedValue() {
        Coefficient coefficient = new Coefficient<>(DemandType.Undefined, Double.NaN);
        assertTrue(coefficient.isUndefined());
    }

    @Test
    public void isUndefinedReturnsFalseOnFiniteValue() {
        Coefficient coefficient = new Coefficient<>(DemandType.PerfectlyElasticity, 71);
        assertFalse(coefficient.isUndefined());
    }
}
