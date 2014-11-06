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
        assertTrue(calculator.getMonthlyPayment(1) > 1730
                && calculator.getMonthlyPayment(1) < 1750);
    }

    @Test
    public void canPayFirstMonthlyDifferentiatedPayment() {
        CreditCalculator calculator = new CreditCalculator(10000, 'R', 6 , 15, 11, false);
        assertTrue(calculator.getMonthlyPayment(1) > 1790
                && calculator.getMonthlyPayment(1) < 1800);
    }

    @Test
    public void canPayLastMonthlyDifferentiatedPayment() {
        CreditCalculator calculator = new CreditCalculator(10000, 'R', 6 , 15, 11, false);
        assertTrue(calculator.getMonthlyPayment(6) > 1680
                && calculator.getMonthlyPayment(6) < 1690);
    }

    @Test
    public void canGetAllSumAnnuityPayment() {
        CreditCalculator calculator = new CreditCalculator(10000, 'R', 6 , 15, 11, true);
        assertTrue(calculator.getAllSum() > 10430 && calculator.getAllSum() < 10450);
    }

    @Test
    public void canGetAllSumDifferentiatedPayment() {
        CreditCalculator calculator = new CreditCalculator(10000, 'R', 6 , 15, 11, false);
        assertTrue(calculator.getAllSum() > 10435 && calculator.getAllSum() < 10440);
    }

    @Test
    public void canGetOverAnnuityPayment() {
        CreditCalculator calculator = new CreditCalculator(10000, 'R', 6 , 15, 11, true);
        assertTrue(calculator.getOverPayment() > 430 && calculator.getOverPayment() < 450);
    }

    @Test
    public void canGetOverDifferentiatedPayment() {
        CreditCalculator calculator = new CreditCalculator(10000, 'R', 6 , 15, 11, false);
        assertTrue(calculator.getOverPayment() > 435 && calculator.getOverPayment() < 440);
    }

    @Test
    public void canGetDateFinishPayment() {
        CreditCalculator calculator = new CreditCalculator(10000, 'R', 6 , 15, 11, false);
        assertTrue(calculator.getFinishDateOfPayment().equals("5.2015"));
    }

    @Test
    public void canGetDateDecemberFinishPayment() {
        CreditCalculator calculator = new CreditCalculator(10000, 'R', 13 , 15, 11, true);
        assertTrue(calculator.getFinishDateOfPayment().equals("12.2015"));
    }

    @Test
    public void canGetDateJanuaryFinishPayment() {
        CreditCalculator calculator = new CreditCalculator(10000, 'R', 14 , 15, 11, true);
        assertTrue(calculator.getFinishDateOfPayment().equals("1.2016"));
    }

    @Test
    public void canGetDateJanuaryStartPayment() {
        CreditCalculator calculator = new CreditCalculator(10000, 'R', 14 , 15, 1, true);
        assertTrue(calculator.getSartDateOfPayment().equals("2.2014"));
    }

    @Test
    public void canGetDateDecemberStartPayment() {
        CreditCalculator calculator = new CreditCalculator(10000, 'R', 14 , 15, 12, true);
        assertTrue(calculator.getSartDateOfPayment().equals("1.2015"));
    }
}
