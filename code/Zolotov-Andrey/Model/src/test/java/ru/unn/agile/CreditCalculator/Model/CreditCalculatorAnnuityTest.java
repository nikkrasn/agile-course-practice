package ru.unn.agile.CreditCalculator.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class CreditCalculatorAnnuityTest {

    @Test
    public void canCreateCreditCalculatorAnnuity() {
        CreditCalculatorAnnuity calculator = new CreditCalculatorAnnuity
                .BuilderAnnuity(1, 1).build();
        assertNotNull(calculator);
    }

    @Test
    public void canCreateCreditCalculatorAnnuityWithInitialCurrency() {
        CreditCalculatorAnnuity calculator = new CreditCalculatorAnnuity
                .BuilderAnnuity(1, 1)
                .currency('$').build();
        assertTrue(calculator.getCurrency() == '$');
    }

    @Test
    public void canCreateCreditCalculatorAnnuityWithInitialInterestRate() {
        CreditCalculatorAnnuity calculator = new CreditCalculatorAnnuity
                .BuilderAnnuity(1, 1)
                .interestRate(16.1).build();
        assertTrue(calculator.getInterestRate() == 16.1);
    }

    @Test
    public void canCreateCreditCalculatorAnnuityWithInitialStartMonth() {
        CreditCalculatorAnnuity calculator = new CreditCalculatorAnnuity
                .BuilderAnnuity(1, 1)
                .startMonth(11).build();
        assertTrue(calculator.getStartMonth() == 11);
    }

    @Test
    public void canCreateCreditCalculatorAnnuityWithInitialValues() {
        CreditCalculatorAnnuity calculator = new CreditCalculatorAnnuity
                .BuilderAnnuity(1, 1)
                .currency('$')
                .interestRate(16.1)
                .startMonth(11).build();
        assertNotNull(calculator);
    }

    @Test
    public void canPayMonthlyAnnuityPaymentAnnuity() {
        CreditCalculator calculator = new CreditCalculatorAnnuity.BuilderAnnuity(10000, 6)
                .currency('R')
                .interestRate(15)
                .startMonth(11).build();
        assertTrue(calculator.getMonthlyPayment(1) > 1730
                && calculator.getMonthlyPayment(1) < 1750);
    }

    @Test
    public void canGetAllSumAnnuityPaymentAnnuity() {
        CreditCalculatorAnnuity calculator = new CreditCalculatorAnnuity.BuilderAnnuity(10000, 6)
                .currency('R')
                .interestRate(15)
                .startMonth(11).build();
        assertTrue(calculator.getAllSum() > 10430 && calculator.getAllSum() < 10450);
    }

    @Test
    public void canGetOverAnnuityPaymentAnnuity() {
        CreditCalculatorAnnuity calculator = new CreditCalculatorAnnuity.BuilderAnnuity(10000, 6)
                .currency('R')
                .interestRate(15)
                .startMonth(11).build();
        assertTrue(calculator.getOverPayment() > 430 && calculator.getOverPayment() < 450);
    }

    @Test
    public void canGetDateFinishPaymentAnnuity() {
        CreditCalculatorAnnuity calculator = new CreditCalculatorAnnuity.BuilderAnnuity(10000, 6)
                .currency('R')
                .interestRate(15)
                .startMonth(11).build();
        assertTrue(calculator.getFinishDateOfPayment().equals("5.2015"));
    }

    @Test
    public void canGetDateDecemberFinishPaymentAnnuity() {
        CreditCalculatorAnnuity calculator = new CreditCalculatorAnnuity.BuilderAnnuity(10000, 13)
                .currency('R')
                .interestRate(15)
                .startMonth(11).build();
        assertTrue(calculator.getFinishDateOfPayment().equals("12.2015"));
    }

    @Test
    public void canGetDateJanuaryFinishPaymentAnnuity() {
        CreditCalculatorAnnuity calculator = new CreditCalculatorAnnuity.BuilderAnnuity(10000, 14)
                .currency('R')
                .interestRate(15)
                .startMonth(11).build();
        assertTrue(calculator.getFinishDateOfPayment().equals("1.2016"));
    }

    @Test
    public void canGetDateJanuaryStartPaymentAnnuity() {
        CreditCalculatorAnnuity calculator = new CreditCalculatorAnnuity.BuilderAnnuity(10000, 14)
                .currency('R')
                .interestRate(15)
                .startMonth(1).build();
        assertTrue(calculator.getSartDateOfPayment().equals("2.2014"));
    }

    @Test
    public void canGetDateDecemberStartPaymentAnnuity() {
        CreditCalculatorAnnuity calculator = new CreditCalculatorAnnuity.BuilderAnnuity(10000, 14)
                .currency('R')
                .interestRate(15)
                .startMonth(12).build();
        assertTrue(calculator.getSartDateOfPayment().equals("1.2015"));
    }
}
