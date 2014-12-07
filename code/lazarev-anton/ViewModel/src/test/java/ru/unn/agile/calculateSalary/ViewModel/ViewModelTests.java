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
        assertEquals(Status.WAITING, viewModel.getStatus());
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
        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void checkStatusWhenOneOfFieldEmpty() {
        viewModel.setSalary("10000");
        viewModel.setWorkedHours("150");
        viewModel.setCountMonth("5");
        viewModel.setCountYear("");
        viewModel.calculate();
        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
         public void checkStatusWhenIncorrectInput() {
        viewModel.setSalary("10000");
        viewModel.setWorkedHours("a");
        viewModel.setCountMonth("5");
        viewModel.setCountYear("2000");
        viewModel.calculate();
        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
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
    }
}
