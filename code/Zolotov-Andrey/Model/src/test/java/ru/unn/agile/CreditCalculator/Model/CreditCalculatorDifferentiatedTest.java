package ru.unn.agile.CreditCalculator.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class CreditCalculatorDifferentiatedTest {

    @Test
    public void canCreateCreditCalculatorDifferentiated() {
        CreditCalculatorDifferentiated calculator = new CreditCalculatorDifferentiated
                .BuilderDifferentiated(1, 1).build();
        assertNotNull(calculator);
    }

    @Test
    public void canCreateCreditCalculatorDifferentiatedWithInitialCurrency() {
        CreditCalculatorDifferentiated calculator = new CreditCalculatorDifferentiated
                .BuilderDifferentiated(1, 1).currency('$').build();
        assertTrue(calculator.getCurrency() == '$');
    }

    @Test
    public void canCreateCreditCalculatorDifferentiatedWithInitialInterestRate() {
        CreditCalculatorDifferentiated calculator = new CreditCalculatorDifferentiated
                .BuilderDifferentiated(1, 1).interestRate(16.1).build();
        assertTrue(calculator.getInterestRate() == 16.1);
    }

    @Test
    public void canCreateCreditCalculatorDifferentiatedWithInitialStartMonth() {
        CreditCalculatorDifferentiated calculator = new CreditCalculatorDifferentiated
                .BuilderDifferentiated(1, 1).startMonth(11).build();
        assertTrue(calculator.getStartMonth() == 11);
    }

    @Test
    public void canCreateCreditCalculatorDifferentiatedWithInitialValues() {
        CreditCalculatorDifferentiated calculator = new CreditCalculatorDifferentiated
                .BuilderDifferentiated(1, 1)
                .currency('$')
                .interestRate(16.1)
                .startMonth(11).build();
        assertNotNull(calculator);
    }

    @Test
    public void canPayFirstMonthlyDifferentiatedPayment() {
        CreditCalculatorDifferentiated calculator = new CreditCalculatorDifferentiated
                .BuilderDifferentiated(10000, 6)
                .currency('R')
                .interestRate(15)
                .startMonth(11).build();
        assertTrue(calculator.getMonthlyPayment(1) > 1790
                && calculator.getMonthlyPayment(1) < 1800);
    }

    @Test
    public void canPayLastMonthlyDifferentiatedPayment() {
        CreditCalculatorDifferentiated calculator = new CreditCalculatorDifferentiated
                .BuilderDifferentiated(10000, 6)
                .currency('R')
                .interestRate(15)
                .startMonth(11).build();
        assertTrue(calculator.getMonthlyPayment(6) > 1680
                && calculator.getMonthlyPayment(6) < 1690);
    }

    @Test
    public void canGetAllSumDifferentiatedPayment() {
        CreditCalculatorDifferentiated calculator = new CreditCalculatorDifferentiated
                .BuilderDifferentiated(10000, 6)
                .currency('R')
                .interestRate(15)
                .startMonth(11).build();
        assertTrue(calculator.getAllSum() > 10435 && calculator.getAllSum() < 10440);
    }

    @Test
    public void canGetOverDifferentiatedPayment() {
        CreditCalculatorDifferentiated calculator = new CreditCalculatorDifferentiated
                .BuilderDifferentiated(10000, 6)
                .currency('R')
                .interestRate(15)
                .startMonth(11).build();
        assertTrue(calculator.getOverPayment() > 435 && calculator.getOverPayment() < 440);
    }

    @Test
    public void canGetDateFinishPaymentDifferentiated() {
        CreditCalculatorDifferentiated calculator = new CreditCalculatorDifferentiated
                .BuilderDifferentiated(10000, 6)
                .currency('R')
                .interestRate(15)
                .startMonth(11).build();
        assertTrue(calculator.getFinishDateOfPayment().equals("5.2015"));
    }

    @Test
    public void canGetDateDecemberFinishPaymentDifferentiated() {
        CreditCalculatorDifferentiated calculator = new CreditCalculatorDifferentiated
                .BuilderDifferentiated(10000, 13)
                .currency('R')
                .interestRate(15)
                .startMonth(11).build();
        assertTrue(calculator.getFinishDateOfPayment().equals("12.2015"));
    }

    @Test
    public void canGetDateJanuaryFinishPaymentDifferentiated() {
        CreditCalculatorDifferentiated calculator = new CreditCalculatorDifferentiated
                .BuilderDifferentiated(10000, 14)
                .currency('R')
                .interestRate(15)
                .startMonth(11).build();
        assertTrue(calculator.getFinishDateOfPayment().equals("1.2016"));
    }

    @Test
    public void canGetDateJanuaryStartPaymentDifferentiated() {
        CreditCalculatorDifferentiated calculator = new CreditCalculatorDifferentiated
                .BuilderDifferentiated(10000, 14)
                .currency('R')
                .interestRate(15)
                .startMonth(1).build();
        assertTrue(calculator.getSartDateOfPayment().equals("2.2014"));
    }

    @Test
    public void canGetDateDecemberStartPaymentDifferentiated() {
        CreditCalculatorDifferentiated calculator = new CreditCalculatorDifferentiated
                .BuilderDifferentiated(10000, 14)
                .currency('R')
                .interestRate(15)
                .startMonth(12).build();
        assertTrue(calculator.getSartDateOfPayment().equals("1.2015"));
    }
}
