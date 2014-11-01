package ru.unn.agile.ComplexProcent.Model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ComplexDepositTest {
    private final double delta = 0.001;

    @Test
    public void canCreateDeposit() {
        ComplexDeposit deposit = new ComplexDeposit(1000, 4, 1);
        assertNotNull(deposit);
    }

    @Test
    public void canSetCapitalizedBaseInOnePeriod() {
        ComplexDeposit deposit = new ComplexDeposit(1000, 4.5, 1);
        assertEquals(1045, deposit.getCapitalizedBase(1), delta);
    }

    @Test
    public void canSetCapitalizedBaseInOnePeriodForFewYears() {
        ComplexDeposit deposit = new ComplexDeposit(1000, 4.5, 1);
        assertEquals(1092.025, deposit.getCapitalizedBase(2), delta);
    }

    @Test
    public void canSetCapitalizedBaseInFewPeriodForYear() {
        ComplexDeposit deposit = new ComplexDeposit(1000, 4.5, 3);
        assertEquals(1045.67838, deposit.getCapitalizedBase(1), delta);
    }

    @Test
    public void canSetCapitalizedBaseInFewPeriodForFewYears() {
        ComplexDeposit deposit = new ComplexDeposit(1000, 4.5, 3);
        assertEquals(1195.61817, deposit.getCapitalizedBase(4), delta);
    }

    private void assertIsEqualDeposit(final ComplexDeposit dept1, final ComplexDeposit dept2) {
        assertEquals(dept1.getBase(), dept2.getBase(), delta);
        assertEquals(dept1.getPercent(), dept2.getPercent(), delta);
        assertEquals(dept1.getInterestCountInYear(), dept2.getInterestCountInYear(), delta);
    }

    @Test
    public void canChangeBase() {
        ComplexDeposit firstDeposit = new ComplexDeposit(1000, 4.5, 3);
        ComplexDeposit secondDeposit = new ComplexDeposit(2000, 3.5, 3);
        firstDeposit.setBase(2000);
        assertEquals(firstDeposit.getBase(), secondDeposit.getBase(), delta);
    }
    
    @Test
    public void canChangePercent() {
        ComplexDeposit firstDeposit = new ComplexDeposit(1000, 4.5, 3);
        ComplexDeposit secondDeposit = new ComplexDeposit(2000, 3.5, 3);
        firstDeposit.setPercent(3.5);
        assertEquals(firstDeposit.getPercent(), secondDeposit.getPercent(), delta);
    }

    @Test
    public void canChangeInterestCount() {
        ComplexDeposit deposit1 = new ComplexDeposit(1000, 4.5, 1);
        ComplexDeposit deposit2 = new ComplexDeposit(2000, 3.5, 3);
        deposit1.setInterestCountInYear(3);
        assertEquals(deposit1.getInterestCountInYear(), deposit2.getInterestCountInYear(), delta);
    }

    @Test
    public void canChangeMultiply() {
        ComplexDeposit firstDeposit = new ComplexDeposit(1000, 4.5, 1);
        ComplexDeposit secondDeposit = new ComplexDeposit(2000, 3.5, 3);
        secondDeposit.setPercent(4.5).setBase(1000).setInterestCountInYear(1);
        assertIsEqualDeposit(firstDeposit, secondDeposit);
    }
}
