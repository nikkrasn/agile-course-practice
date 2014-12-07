package ru.unn.agile.CreditCalculator.ViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.CreditCalculator.ViewModel.ViewModel.Currency;
import ru.unn.agile.CreditCalculator.ViewModel.ViewModel.Status;
import ru.unn.agile.CreditCalculator.ViewModel.ViewModel.TypePayment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.getSum());
        assertEquals("", viewModel.getPaymentPeriod());
        assertEquals("", viewModel.getInterestRate());
        assertEquals("", viewModel.getStartMonth());
        assertEquals(Currency.RUB, viewModel.getCurrency());
        assertEquals(TypePayment.Annuity, viewModel.getTypePayment());
        assertEquals("", viewModel.getAllSum());
        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusWaitingInTheBeginning() {
        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusWaitingWhenCalculateWithEmptyFields() {
        viewModel.calculate();

        assertEquals(Status.WAITING, viewModel.getStatus());
    }
    @Test
    public void isStatusSuccessAnnuity() {
        viewModel.setSum("1");
        viewModel.setPaymentPeriod("1");
        viewModel.setInterestRate("16.1");
        viewModel.setStartMonth("4");
        viewModel.setTypePayment(TypePayment.Annuity);
        viewModel.setCurrency(Currency.RUB);
        viewModel.calculate();

        assertEquals(Status.SUCCESS, viewModel.getStatus());
    }
    @Test
    public void isStatusSuccessDifferentiated() {
        viewModel.setSum("1");
        viewModel.setPaymentPeriod("1");
        viewModel.setInterestRate("16.1");
        viewModel.setStartMonth("4");
        viewModel.setTypePayment(TypePayment.Differentiated);
        viewModel.setCurrency(Currency.RUB);
        viewModel.calculate();

        assertEquals(Status.SUCCESS, viewModel.getStatus());
    }
    @Test
    public void isStatusSuccessDollar() {
        viewModel.setSum("1");
        viewModel.setPaymentPeriod("1");
        viewModel.setInterestRate("16.1");
        viewModel.setStartMonth("4");
        viewModel.setTypePayment(TypePayment.Differentiated);
        viewModel.setCurrency(Currency.Dollar);
        viewModel.calculate();

        assertEquals(Status.SUCCESS, viewModel.getStatus());
    }
    @Test
    public void isBadFormatSum() {
        viewModel.setSum("test");
        viewModel.setPaymentPeriod("4");
        viewModel.setInterestRate("16.1");
        viewModel.setStartMonth("4");

        viewModel.calculate();

        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }
    @Test
    public void isBadFormatPaymentPeriod() {
        viewModel.setSum("1");
        viewModel.setPaymentPeriod("test");
        viewModel.setInterestRate("16.1");
        viewModel.setStartMonth("4");

        viewModel.calculate();

        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }
    @Test
    public void isBadFormatInterestRate() {
        viewModel.setSum("1");
        viewModel.setPaymentPeriod("4");
        viewModel.setInterestRate("test");
        viewModel.setStartMonth("4");

        viewModel.calculate();

        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }
    @Test
    public void isBadFormatStartMonth() {
        viewModel.setSum("1");
        viewModel.setPaymentPeriod("4");
        viewModel.setInterestRate("16.1");
        viewModel.setStartMonth("test");

        viewModel.calculate();

        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void isNullSum() {
        viewModel.setSum("0");
        viewModel.setPaymentPeriod("4");
        viewModel.setInterestRate("16.1");
        viewModel.setStartMonth("3");

        viewModel.calculate();

        assertEquals(Status.IS_NULL, viewModel.getStatus());
    }

    @Test
    public void isNullPaymentPeriod() {
        viewModel.setSum("2000");
        viewModel.setPaymentPeriod("0");
        viewModel.setInterestRate("16.1");
        viewModel.setStartMonth("3");

        viewModel.calculate();

        assertEquals(Status.IS_NULL, viewModel.getStatus());
    }

    @Test
    public void isNullInterestRate() {
        viewModel.setSum("2000");
        viewModel.setPaymentPeriod("2");
        viewModel.setInterestRate("0");
        viewModel.setStartMonth("3");

        viewModel.calculate();

        assertEquals(Status.IS_NULL, viewModel.getStatus());
    }

    @Test
    public void isNullStartMonth() {
        viewModel.setSum("2000");
        viewModel.setPaymentPeriod("2");
        viewModel.setInterestRate("4.3");
        viewModel.setStartMonth("0");

        viewModel.calculate();

        assertEquals(Status.IS_NULL, viewModel.getStatus());
    }

    @Test
    public void canGetAllSumPayment() {
        viewModel.setSum("10000");
        viewModel.setPaymentPeriod("6");
        viewModel.setInterestRate("15");
        viewModel.setStartMonth("11");
        viewModel.setTypePayment(TypePayment.Annuity);
        viewModel.setCurrency(Currency.Dollar);

        viewModel.calculate();
        Double allSum = Double.parseDouble(viewModel.getAllSum());
        assertTrue(allSum > 10430 && allSum < 10450);
    }

    @Test
    public void canGetStartDateOfPayment() {
        viewModel.setSum("10000");
        viewModel.setPaymentPeriod("14");
        viewModel.setInterestRate("15");
        viewModel.setStartMonth("1");
        viewModel.setTypePayment(TypePayment.Annuity);
        viewModel.setCurrency(Currency.Dollar);

        viewModel.calculate();
        assertTrue(viewModel.getStartDateOfPayment().equals("2.2014"));
    }


    @Test
    public void canGetFinishDateOfPayment() {
        viewModel.setSum("10000");
        viewModel.setPaymentPeriod("6");
        viewModel.setInterestRate("15");
        viewModel.setStartMonth("11");
        viewModel.setTypePayment(TypePayment.Annuity);
        viewModel.setCurrency(Currency.Dollar);

        viewModel.calculate();
        assertTrue(viewModel.getFinishDateOfPayment().equals("5.2015"));
    }

    @Test
    public void canGetOverPayment() {
        viewModel.setSum("10000");
        viewModel.setPaymentPeriod("6");
        viewModel.setInterestRate("15");
        viewModel.setStartMonth("11");
        viewModel.setTypePayment(TypePayment.Annuity);
        viewModel.setCurrency(Currency.Dollar);

        viewModel.calculate();
        Double overPayment = Double.parseDouble(viewModel.getOverPayment());
        assertTrue(overPayment > 430 && overPayment < 450);
    }

    @Test
    public void canGetFirstPayment() {
        viewModel.setSum("10000");
        viewModel.setPaymentPeriod("6");
        viewModel.setInterestRate("15");
        viewModel.setStartMonth("11");
        viewModel.setTypePayment(TypePayment.Annuity);
        viewModel.setCurrency(Currency.RUB);

        viewModel.calculate();
        Double firstPayment = Double.parseDouble(viewModel.getFirstPayment());
        assertTrue(firstPayment > 1730 && firstPayment < 1750);
    }

    @Test
    public void canSetAddTypePaymentAnnuity() {
        viewModel.setTypePayment(TypePayment.Annuity);
        assertEquals(TypePayment.Annuity, viewModel.getTypePayment());
    }

    @Test
    public void canSetAddTypePaymentDifferentiated() {
        viewModel.setTypePayment(TypePayment.Differentiated);
        assertEquals(TypePayment.Differentiated, viewModel.getTypePayment());
    }

    @Test
    public void canGetTypePaymentName() {
        String annuity = TypePayment.Annuity.toString();
        assertEquals("Annuity", annuity);
    }

    @Test
    public void canGetCurrencyName() {
        String rub = Currency.RUB.toStringCurrency();
        assertEquals("R", rub);
    }
    @Test
    public void canSetCurrencyDollar() {
        viewModel.setCurrency(Currency.Dollar);
        assertEquals(Currency.Dollar, viewModel.getCurrency());
    }

    @Test
    public void canSetCurrencyRub() {
        viewModel.setCurrency(Currency.RUB);
        assertEquals(Currency.RUB, viewModel.getCurrency());
    }

}
