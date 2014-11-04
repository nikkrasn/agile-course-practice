package ru.unn.agile.CreditCalculator.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class CreditCalculatorTest {

    @Test
    public void canCreateCreditCalculatorWithInitialValues() {
        CreditCalculator calculator = new CreditCalculator(1, '$', 1, 16.1, 11, true);
        assertNotNull(calculator);
    }
}
