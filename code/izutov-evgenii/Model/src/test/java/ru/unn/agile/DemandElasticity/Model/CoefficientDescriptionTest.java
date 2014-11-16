package ru.unn.agile.DemandElasticity.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class CoefficientDescriptionTest {
    private final double delta = 0.000001;

    @Test
    public void canCreateCoefficientDescriptionWithInitialValues() {
        CoefficientDescription description =
                new CoefficientDescription<>(DemandType.GiffenGood, 1d);

        assertNotNull(description);
    }

    @Test(expected = IllegalArgumentException.class)
    public void coefficientDescriptionThrowsExceptionOnNullTypeInput() {
        new CoefficientDescription<>(null, 1d);
    }

    @Test
    public void canSetInitialType() {
        CoefficientDescription description = new CoefficientDescription<>(DemandType.Inelastic, 1d);

        assertEquals(DemandType.Inelastic, description.getType());
    }

    @Test
    public void canSetInitialValue() {
        CoefficientDescription description = new CoefficientDescription<>(GoodType.Neutral, 17d);

        assertEquals(17d, description.getValue(), delta);
    }

    @Test
    public void isValueUndefinedReturnsTrueOnUndefinedValue() {
        CoefficientDescription description =
                new CoefficientDescription<>(DemandType.Undefined, Double.NaN);

        assertTrue(description.isValueUndefined());
    }

    @Test
    public void isValueUndefinedReturnsFalseOnFiniteValue() {
        CoefficientDescription description =
                new CoefficientDescription<>(DemandType.PerfectlyElasticity, 71d);

        assertFalse(description.isValueUndefined());
    }
}
