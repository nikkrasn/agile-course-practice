package ru.unn.agile.CreditCalculator.ViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.CreditCalculator.ViewModel.ViewModel.Currency;
import ru.unn.agile.CreditCalculator.ViewModel.ViewModel.Status;
import ru.unn.agile.CreditCalculator.ViewModel.ViewModel.TypePayment;

import static org.junit.Assert.assertEquals;

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
        assertEquals("", viewModel.getResult());
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
