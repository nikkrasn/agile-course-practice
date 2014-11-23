package ru.unn.agile.CurrencyConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.CurrencyConverter.Model.CurrencyIndexes;

import static org.junit.Assert.*;

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
        assertEquals("", viewModel.inputValueProperty().get());
        assertEquals(CurrencyIndexes.RUB, viewModel.fromCurrencyProperty().get());
        assertEquals(CurrencyIndexes.USD, viewModel.toCurrencyProperty().get());
        assertEquals("", viewModel.resultProperty().get());
        assertEquals("", viewModel.resultCurrencyProperty().get());
        assertEquals(ViewModelStatus.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyFields() {
        viewModel.convert();

        assertEquals(ViewModelStatus.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void convertButtonIsDisabledInitially() {
        assertTrue(viewModel.getCalculationDisabled());
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        setInputData();

        assertEquals(ViewModelStatus.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void convertButtonIsDisabledWhenInputIsEmpty() {
        viewModel.inputValueProperty().set("");

        assertTrue(viewModel.getCalculationDisabled());
    }

    @Test
    public void convertButtonIsDisabledWhenInputBecomeEmpty() {
        setInputData();
        viewModel.inputValueProperty().set("");

        assertTrue(viewModel.getCalculationDisabled());
    }

    @Test
    public void convertButtonIsDisabledWhenFormatIsBad() {
        setInputData();
        viewModel.inputValueProperty().set("abba");

        assertTrue(viewModel.getCalculationDisabled());
    }

    @Test
    public void convertButtonIsEnabledWhenCorrectInput() {
        setInputData();

        assertFalse(viewModel.getCalculationDisabled());
    }

    private void setInputData() {
        viewModel.inputValueProperty().set("10");
    }
}
