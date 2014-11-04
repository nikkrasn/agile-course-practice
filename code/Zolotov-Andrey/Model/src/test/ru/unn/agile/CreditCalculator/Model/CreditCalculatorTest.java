package ru.unn.agile.CreditCalculator.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class CreditCalculatorTest {

    @Test
    public void canCreateCreditCalculatorWithInitialValues() {
        CreditCalculator calculator = new CreditCalculator(1, '$', 1, 16.1, 11, true);
        assertNotNull(calculator);
    }

    @Test
    public void canPayMonthlyAnnuityPayment() {
        CreditCalculator calculator = new CreditCalculator(10000, 'R', 6 , 15, 11, true);
        assertTrue(calculator.monthlyPayment(1) > 1730 && calculator.monthlyPayment(1) < 1750);
    }

    @Test
    public void canPayFirstMonthlyDifferentiatedPayment() {
        CreditCalculator calculator = new CreditCalculator(10000, 'R', 6 , 15, 11, false);
        assertTrue(calculator.monthlyPayment(1) > 1790 && calculator.monthlyPayment(1) < 1800);
    }

    @Test
    public void canPayLastMonthlyDifferentiatedPayment() {
        CreditCalculator calculator = new CreditCalculator(10000, 'R', 6 , 15, 11, false);
        assertTrue(calculator.monthlyPayment(6) > 1680 && calculator.monthlyPayment(6) < 1690);
    }

    @Test
    public void canGetAllSummAnnuityPayment() {
        CreditCalculator calculator = new CreditCalculator(10000, 'R', 6 , 15, 11, true);
        assertTrue(calculator.allSumm() > 10430 && calculator.allSumm() < 10450);
    }

    @Test
    public void canGetAllSummDifferentiatedPayment() {
        CreditCalculator calculator = new CreditCalculator(10000, 'R', 6 , 15, 11, false);
        assertTrue(calculator.allSumm() > 10435 && calculator.allSumm() < 10440);
    }

    @Test
    public void canGetOverAnnuityPayment() {
        CreditCalculator calculator = new CreditCalculator(10000, 'R', 6 , 15, 11, true);
        assertTrue(calculator.overPayment() > 430 && calculator.overPayment() < 450);
    }

    @Test
    public void canGetOverDifferentiatedPayment() {
        CreditCalculator calculator = new CreditCalculator(10000, 'R', 6 , 15, 11, false);
        assertTrue(calculator.overPayment() > 435 && calculator.overPayment() < 440);
    }
}
