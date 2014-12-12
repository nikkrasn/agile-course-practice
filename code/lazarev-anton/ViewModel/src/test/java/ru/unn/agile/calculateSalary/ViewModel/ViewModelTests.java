package ru.unn.agile.calculateSalary.ViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.calculateSalary.ViewModel.ViewModel.Status;
import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUpEmptyExample() {
        viewModel = new ViewModel();
    }

    @After
    public void deleteExample() {
        viewModel = null;
    }

    @Test
    public void checkDefaultEmptyParameters() {
        assertEquals("", viewModel.getSalary());
        assertEquals("", viewModel.getWorkedHours());
        assertEquals("", viewModel.getCountMonth());
        assertEquals("", viewModel.getCountYear());
        assertEquals("", viewModel.getVacationLength());
        assertEquals("", viewModel.getStartVacationDay());
        assertEquals("", viewModel.getVacationMonth());
        assertEquals("", viewModel.getVacationYear());
        assertEquals("", viewModel.getResult());
    }

    @Test
    public void checkStatusInBegin() {
        assertEquals(Status.COUNT_WAITING, viewModel.getStatus());
    }

    @Test
    public void checkSetters() {
        viewModel.setSalary("10000");
        viewModel.setWorkedHours("145");
        viewModel.setCountMonth("5");
        viewModel.setCountYear("2000");
        viewModel.setVacationLength("25");
        viewModel.setStartVacationDay("15");
        viewModel.setVacationMonth("3");
        viewModel.setVacationYear("2008");
        assertEquals("10000", viewModel.getSalary());
        assertEquals("145", viewModel.getWorkedHours());
        assertEquals("5", viewModel.getCountMonth());
        assertEquals("2000", viewModel.getCountYear());
        assertEquals("25", viewModel.getVacationLength());
        assertEquals("15", viewModel.getStartVacationDay());
        assertEquals("3", viewModel.getVacationMonth());
        assertEquals("2008", viewModel.getVacationYear());
    }

    @Test
    public void checkStatusWhenInputEmpty() {
        viewModel.calculate();
        assertEquals(Status.COUNT_WAITING, viewModel.getStatus());
    }

    @Test
    public void checkStatusWhenOneOfCountFieldEmpty() {
        viewModel.setSalary("10000");
        viewModel.setWorkedHours("150");
        viewModel.setCountMonth("5");
        viewModel.setCountYear("");
        viewModel.calculate();
        assertEquals(Status.COUNT_WAITING, viewModel.getStatus());
        assertFalse(viewModel.getCalculateButtonEnable());
        assertEquals("", viewModel.getResult());
    }

    @Test
    public void checkStatusWhenOneOfVacationFieldEmpty() {
        viewModel.setSalary("10000");
        viewModel.setWorkedHours("150");
        viewModel.setCountMonth("5");
        viewModel.setCountYear("2010");
        viewModel.setVacationLength("10");
        viewModel.setStartVacationDay("15");
        viewModel.setVacationMonth("3");
        viewModel.calculate();
        assertEquals(Status.VACATION_WAITING, viewModel.getStatus());
        assertFalse(viewModel.getCalculateButtonEnable());
        assertEquals("", viewModel.getResult());
    }

    @Test
     public void checkStatusWhenCountInputWithChar() {
        viewModel.setSalary("10000");
        viewModel.setWorkedHours("a");
        viewModel.setCountMonth("5");
        viewModel.setCountYear("2000");
        viewModel.calculate();
        assertEquals(Status.BAD_COUNT_FORMAT, viewModel.getStatus());
        assertFalse(viewModel.getCalculateButtonEnable());
        assertEquals("", viewModel.getResult());
    }

    @Test
    public void checkStatusWhenCountInputWithIncorrectMonth() {
        viewModel.setSalary("10000");
        viewModel.setWorkedHours("200");
        viewModel.setCountMonth("50");
        viewModel.setCountYear("2000");
        viewModel.calculate();
        assertEquals(Status.BAD_MONTH_FORMAT, viewModel.getStatus());
        assertFalse(viewModel.getCalculateButtonEnable());
        assertEquals("", viewModel.getResult());
    }

    @Test
    public void checkStatusWhenCountInputWithIncorrectYear() {
        viewModel.setSalary("10000");
        viewModel.setWorkedHours("200");
        viewModel.setCountMonth("5");
        viewModel.setCountYear("19191");
        viewModel.calculate();
        assertEquals(Status.BAD_YEAR_FORMAT, viewModel.getStatus());
        assertFalse(viewModel.getCalculateButtonEnable());
        assertEquals("", viewModel.getResult());
    }

    @Test
    public void checkStatusWhenVacationInputWithIncorrectYear() {
        viewModel.setSalary("10000");
        viewModel.setWorkedHours("200");
        viewModel.setCountMonth("5");
        viewModel.setCountYear("2005");
        viewModel.setVacationLength("10");
        viewModel.setStartVacationDay("15");
        viewModel.setVacationMonth("3");
        viewModel.setVacationYear("2614");
        viewModel.calculate();
        assertEquals(Status.BAD_YEAR_FORMAT, viewModel.getStatus());
        assertFalse(viewModel.getCalculateButtonEnable());
        assertEquals("", viewModel.getResult());
    }

    @Test
         public void checkStatusWhenVacationInputWithIncorrectDay() {
        viewModel.setSalary("10000");
        viewModel.setWorkedHours("200");
        viewModel.setCountMonth("5");
        viewModel.setCountYear("2005");
        viewModel.setVacationLength("10");
        viewModel.setStartVacationDay("98");
        viewModel.setVacationMonth("3");
        viewModel.setVacationYear("2014");
        viewModel.calculate();
        assertEquals(Status.BAD_DAY_FORMAT, viewModel.getStatus());
        assertFalse(viewModel.getCalculateButtonEnable());
        assertEquals("", viewModel.getResult());
    }

    @Test
    public void checkStatusWhenVacationInputWithIncorrectMonth() {
        viewModel.setSalary("10000");
        viewModel.setWorkedHours("200");
        viewModel.setCountMonth("5");
        viewModel.setCountYear("2005");
        viewModel.setVacationLength("10");
        viewModel.setStartVacationDay("28");
        viewModel.setVacationMonth("25");
        viewModel.setVacationYear("2014");
        viewModel.calculate();
        assertEquals(Status.BAD_MONTH_FORMAT, viewModel.getStatus());
        assertFalse(viewModel.getCalculateButtonEnable());
        assertEquals("", viewModel.getResult());
    }

    @Test
    public void checkStatusWhenCorrectVacationInput() {
        viewModel.setSalary("10000");
        viewModel.setWorkedHours("150");
        viewModel.setCountMonth("5");
        viewModel.setCountYear("2000");
        viewModel.setVacationLength("10");
        viewModel.setStartVacationDay("15");
        viewModel.setVacationMonth("3");
        viewModel.setVacationYear("2014");
        viewModel.calculate();
        assertEquals(Status.CASH, viewModel.getStatus());
        assertTrue(viewModel.getCalculateButtonEnable());
    }

    @Test
    public void checkStatusWhenIncorrectVacationInput() {
        viewModel.setSalary("10000");
        viewModel.setWorkedHours("150");
        viewModel.setCountMonth("5");
        viewModel.setCountYear("2000");
        viewModel.setVacationLength("q");
        viewModel.setStartVacationDay("15");
        viewModel.setVacationMonth("3");
        viewModel.setVacationYear("2014");
        viewModel.calculate();
        assertEquals("", viewModel.getResult());
        assertEquals(Status.BAD_VACATION_FORMAT, viewModel.getStatus());
        assertFalse(viewModel.getCalculateButtonEnable());
    }

    @Test
    public void checkResultWithNormalParameters() {
        viewModel.setSalary("10000");
        viewModel.setWorkedHours("184");
        viewModel.setCountMonth("10");
        viewModel.setCountYear("2014");
        viewModel.calculate();
        assertEquals("8700.0", viewModel.getResult());
        assertEquals(Status.CASH, viewModel.getStatus());
        assertTrue(viewModel.getCalculateButtonEnable());
    }

    @Test
    public void checkResultWithOvertime() {
        viewModel.setSalary("10000");
        viewModel.setWorkedHours("200");
        viewModel.setCountMonth("10");
        viewModel.setCountYear("2014");
        viewModel.calculate();
        assertEquals("10213.04", viewModel.getResult());
        assertEquals(Status.CASH, viewModel.getStatus());
        assertTrue(viewModel.getCalculateButtonEnable());
    }

    @Test
    public void checkResultWithLessTime() {
        viewModel.setSalary("10000");
        viewModel.setWorkedHours("154");
        viewModel.setCountMonth("10");
        viewModel.setCountYear("2014");
        viewModel.calculate();
        assertEquals("7281.52", viewModel.getResult());
        assertEquals(Status.CASH, viewModel.getStatus());
        assertTrue(viewModel.getCalculateButtonEnable());
    }

    @Test
    public void checkResultWithVacationIncludeInCountMonth() {
        viewModel.setSalary("10000");
        viewModel.setWorkedHours("104");
        viewModel.setCountMonth("10");
        viewModel.setCountYear("2014");
        viewModel.setVacationLength("14");
        viewModel.setStartVacationDay("6");
        viewModel.setVacationMonth("10");
        viewModel.setVacationYear("2014");
        viewModel.calculate();
        assertEquals("4917.39", viewModel.getResult());
        assertEquals(Status.CASH, viewModel.getStatus());
        assertTrue(viewModel.getCalculateButtonEnable());
    }

    @Test
    public void checkResultWithVacationStartInCountMonthAndEndInAnother() {
        viewModel.setSalary("10000");
        viewModel.setWorkedHours("144");
        viewModel.setCountMonth("10");
        viewModel.setCountYear("2014");
        viewModel.setVacationLength("14");
        viewModel.setStartVacationDay("27");
        viewModel.setVacationMonth("10");
        viewModel.setVacationYear("2014");
        viewModel.calculate();
        assertEquals("6808.7", viewModel.getResult());
        assertEquals(Status.CASH, viewModel.getStatus());
        assertTrue(viewModel.getCalculateButtonEnable());
    }

    @Test (expected = ArithmeticException.class)
    public void checkResultWithNegativeWorkedHours() {
        viewModel.setSalary("10000");
        viewModel.setWorkedHours("-144");
        viewModel.setCountMonth("10");
        viewModel.setCountYear("2014");
        viewModel.calculate();
    }

    @Test (expected = ArithmeticException.class)
    public void checkResultWithNegativeVacationLength() {
        viewModel.setSalary("10000");
        viewModel.setWorkedHours("144");
        viewModel.setCountMonth("10");
        viewModel.setCountYear("2014");
        viewModel.setVacationLength("-14");
        viewModel.setStartVacationDay("27");
        viewModel.setVacationMonth("10");
        viewModel.setVacationYear("2014");
        viewModel.calculate();
    }

    @Test (expected = ArithmeticException.class)
    public void checkResultWithNegativeSalary() {
        viewModel.setSalary("-10000");
        viewModel.setWorkedHours("144");
        viewModel.setCountMonth("10");
        viewModel.setCountYear("2014");
        viewModel.calculate();
    }

    @Test
    public void checkStatusAndButtonWhenIncorrectDate() {
        viewModel.setSalary("10000");
        viewModel.setWorkedHours("144");
        viewModel.setCountMonth("35");
        viewModel.setCountYear("2014");
        viewModel.calculate();
        assertEquals("", viewModel.getResult());
        assertEquals(Status.BAD_MONTH_FORMAT, viewModel.getStatus());
        assertFalse(viewModel.getCalculateButtonEnable());
    }

    @Test
    public void checkStatusAndButtonWhenIncorrectVacationDate() {
        viewModel.setSalary("10000");
        viewModel.setWorkedHours("144");
        viewModel.setCountMonth("5");
        viewModel.setCountYear("2014");
        viewModel.setVacationLength("14");
        viewModel.setStartVacationDay("27");
        viewModel.setVacationMonth("10");
        viewModel.setVacationYear("2500");
        viewModel.calculate();
        assertEquals("", viewModel.getResult());
        assertEquals(Status.BAD_YEAR_FORMAT, viewModel.getStatus());
        assertFalse(viewModel.getCalculateButtonEnable());
    }
}
