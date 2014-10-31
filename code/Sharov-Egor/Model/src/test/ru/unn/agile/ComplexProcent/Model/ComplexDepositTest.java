package ru.unn.agile.ComplexProcent.Model;


import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ComplexDepositTest {

    private final double delta = 0.001;

    @Test
    public void canCreateDeposit() {
        ComplexDeposit deposit = new ComplexDeposit(1000,4,1);
        assertNotNull(deposit);
    }
    @Test
    public void canSetCapitalizedBaseInOnePeriod() {
        ComplexDeposit deposit = new ComplexDeposit(1000,4.5,1);
        assertEquals(1045,deposit.getCapitalizedBase(1),delta);
    }

    @Test
    public void canSetCapitalizedBaseInOnePeriodForFewYears() {
        ComplexDeposit deposit = new ComplexDeposit(1000,4.5,1);
        assertEquals(1092.025,deposit.getCapitalizedBase(2),delta);
    }

    @Test
    public void canSetCapitalizedBaseInFewPeriodForYear() {
        ComplexDeposit deposit = new ComplexDeposit(1000,4.5,3);
        assertEquals(1045.67838,deposit.getCapitalizedBase(1),delta);
    }

    @Test
    public void canSetCapitalizedBaseInFewPeriodForFewYears() {
        ComplexDeposit deposit = new ComplexDeposit(1000,4.5,3);
        assertEquals(1195.61817,deposit.getCapitalizedBase(4),delta);
    }

    private void assertIsEqualDeposit(ComplexDeposit deposit1,ComplexDeposit deposit2) {
        assertEquals(deposit1.getBase(),deposit2.getBase(),delta);
        assertEquals(deposit1.getPercent(),deposit2.getPercent(),delta);
        assertEquals(deposit1.getInterestCountInYear(),deposit2.getInterestCountInYear(),delta);
    }

    @Test
    public void canChangeBase() {
        ComplexDeposit firstDeposit = new ComplexDeposit(1000,4.5,3);
        ComplexDeposit secondDeposit = new ComplexDeposit(2000,3.5,3);
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
        ComplexDeposit firstDeposit = new ComplexDeposit(1000, 4.5, 1);
        ComplexDeposit secondDeposit = new ComplexDeposit(2000, 3.5, 3);
        firstDeposit.setInterestCountInYear(3);
        assertEquals(firstDeposit.getInterestCountInYear(), secondDeposit.getInterestCountInYear(),delta);
    }
    @Test
    public void canChangeMultiply() {
        ComplexDeposit firstDeposit = new ComplexDeposit(1000, 4.5, 1);
        ComplexDeposit secondDeposit = new ComplexDeposit(2000, 3.5, 3);
        secondDeposit.setPercent(4.5).setBase(1000).setInterestCountInYear(1);
        assertIsEqualDeposit(firstDeposit, secondDeposit);

    }

}