package ru.unn.agile.CreditCalculator.ViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.CreditCalculator.ViewModel.ViewModel.Currency;
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
        assertEquals(Currency.RUR, viewModel.getCurrency());
        assertEquals(TypePayment.Annuity, viewModel.getTypePayment());
        assertEquals("", viewModel.getAllSum());
        assertEquals(ViewModel.userInputStatus.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusWaitingInTheBeginning() {
        assertEquals(ViewModel.userInputStatus.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusWaitingWhenCalculateWithEmptyFields() {
        viewModel.calculate();

        assertEquals(ViewModel.userInputStatus.WAITING, viewModel.getStatus());
    }
    @Test
    public void isStatusSuccessAnnuity() {
        viewModel.setSum("1");
        viewModel.setPaymentPeriod("1");
        viewModel.setInterestRate("16.1");
        viewModel.setStartMonth("4");
        viewModel.setTypePayment(TypePayment.Annuity);
        viewModel.setCurrency(Currency.RUR);
        viewModel.calculate();

        assertEquals(ViewModel.userInputStatus.SUCCESS, viewModel.getStatus());
    }
    @Test
    public void isStatusSuccessDifferentiated() {
        viewModel.setSum("1");
        viewModel.setPaymentPeriod("1");
        viewModel.setInterestRate("16.1");
        viewModel.setStartMonth("4");
        viewModel.setTypePayment(TypePayment.Differentiated);
        viewModel.setCurrency(Currency.RUR);
        viewModel.calculate();

        assertEquals(ViewModel.userInputStatus.SUCCESS, viewModel.getStatus());
    }
    @Test
    public void isStatusSuccessDollar() {
        viewModel.setSum("1");
        viewModel.setPaymentPeriod("1");
        viewModel.setInterestRate("16.1");
        viewModel.setStartMonth("4");
        viewModel.setTypePayment(TypePayment.Differentiated);
        viewModel.setCurrency(Currency.USD);
        viewModel.calculate();

        assertEquals(ViewModel.userInputStatus.SUCCESS, viewModel.getStatus());
    }
    @Test
    public void isBadFormatSum() {
        viewModel.setSum("test");
        viewModel.setPaymentPeriod("4");
        viewModel.setInterestRate("16.1");
        viewModel.setStartMonth("4");

        viewModel.calculate();

        assertEquals(ViewModel.userInputStatus.BAD_FORMAT, viewModel.getStatus());
    }
    @Test
    public void isBadFormatPaymentPeriod() {
        viewModel.setSum("1");
        viewModel.setPaymentPeriod("test");
        viewModel.setInterestRate("16.1");
        viewModel.setStartMonth("4");

        viewModel.calculate();

        assertEquals(ViewModel.userInputStatus.BAD_FORMAT, viewModel.getStatus());
    }
    @Test
    public void isBadFormatInterestRate() {
        viewModel.setSum("1");
        viewModel.setPaymentPeriod("4");
        viewModel.setInterestRate("test");
        viewModel.setStartMonth("4");

        viewModel.calculate();

        assertEquals(ViewModel.userInputStatus.BAD_FORMAT, viewModel.getStatus());
    }
    @Test
    public void isBadFormatStartMonth() {
        viewModel.setSum("1");
        viewModel.setPaymentPeriod("4");
        viewModel.setInterestRate("16.1");
        viewModel.setStartMonth("test");

        viewModel.calculate();

        assertEquals(ViewModel.userInputStatus.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void isNullSum() {
        viewModel.setSum("0");
        viewModel.setPaymentPeriod("4");
        viewModel.setInterestRate("16.1");
        viewModel.setStartMonth("3");

        viewModel.calculate();

        assertEquals(ViewModel.userInputStatus.IS_NULL, viewModel.getStatus());
    }

    @Test
    public void isNullPaymentPeriod() {
        viewModel.setSum("2000");
        viewModel.setPaymentPeriod("0");
        viewModel.setInterestRate("16.1");
        viewModel.setStartMonth("3");

        viewModel.calculate();

        assertEquals(ViewModel.userInputStatus.IS_NULL, viewModel.getStatus());
    }

    @Test
    public void isNullInterestRate() {
        viewModel.setSum("2000");
        viewModel.setPaymentPeriod("2");
        viewModel.setInterestRate("0");
        viewModel.setStartMonth("3");

        viewModel.calculate();

        assertEquals(ViewModel.userInputStatus.IS_NULL, viewModel.getStatus());
    }

    @Test
    public void isNullStartMonth() {
        viewModel.setSum("2000");
        viewModel.setPaymentPeriod("2");
        viewModel.setInterestRate("4.3");
        viewModel.setStartMonth("0");

        viewModel.calculate();

        assertEquals(ViewModel.userInputStatus.IS_NULL, viewModel.getStatus());
    }

    @Test
    public void canGetAllSumPayment() {
        setViewModelVariables(viewModel);

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
        viewModel.setCurrency(Currency.USD);

        viewModel.calculate();
        assertTrue(viewModel.getStartDateOfPayment().equals("2.2014"));
    }


    @Test
    public void canGetFinishDateOfPayment() {
        setViewModelVariables(viewModel);
        viewModel.calculate();
        assertTrue(viewModel.getFinishDateOfPayment().equals("5.2015"));
    }

    @Test
    public void canGetOverPayment() {
        setViewModelVariables(viewModel);
        viewModel.calculate();
        Double overPayment = Double.parseDouble(viewModel.getOverPayment());
        assertTrue(overPayment > 430 && overPayment < 450);
    }

    @Test
    public void canGetFirstPayment() {
        setViewModelVariables(viewModel);
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
        String rub = Currency.RUR.toString();
        assertEquals("R", rub);
    }
    @Test
    public void canSetCurrencyDollar() {
        viewModel.setCurrency(Currency.USD);
        assertEquals(Currency.USD, viewModel.getCurrency());
    }

    @Test
    public void canSetCurrencyRub() {
        viewModel.setCurrency(Currency.RUR);
        assertEquals(Currency.RUR, viewModel.getCurrency());
    }

    public void setViewModelVariables(final ViewModel viewModel) {
        viewModel.setSum("10000");
        viewModel.setPaymentPeriod("6");
        viewModel.setInterestRate("15");
        viewModel.setStartMonth("11");
        viewModel.setTypePayment(TypePayment.Annuity);
        viewModel.setCurrency(Currency.USD);
    }

}
