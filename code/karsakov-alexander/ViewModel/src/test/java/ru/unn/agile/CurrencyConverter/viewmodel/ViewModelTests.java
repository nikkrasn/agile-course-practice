package ru.unn.agile.CurrencyConverter.viewmodel;

import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.CurrencyConverter.Model.Currency;
import ru.unn.agile.CurrencyConverter.Model.CurrencyIndexes;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotEquals;

public class ViewModelTests {
    private ViewModel viewModel;
    private ObservableList<Currency> currencyList;
    private final double delta = 0.0001;

    @Before
    public void setUp() {
        viewModel = new ViewModel(new MockLogger());
        currencyList = viewModel.fromCurrencyListProperty().get();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.inputValueProperty().get());
        assertEquals(currencyList.get(CurrencyIndexes.RUB.getIndex()),
                     viewModel.fromCurrencyProperty().get());
        assertEquals(currencyList.get(CurrencyIndexes.USD.getIndex()),
                     viewModel.toCurrencyProperty().get());
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
        assertTrue(viewModel.getConvertButtonDisabled());
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        setInputData();

        assertEquals(ViewModelStatus.READY.toString(), viewModel.getStatus());
    }

    @Test
    public void convertButtonIsDisabledWhenInputIsEmpty() {
        viewModel.inputValueProperty().set("");

        assertTrue(viewModel.getConvertButtonDisabled());
    }

    @Test
    public void convertButtonIsDisabledWhenInputBecomeEmpty() {
        setInputData();
        viewModel.inputValueProperty().set("");

        assertTrue(viewModel.getConvertButtonDisabled());
    }

    @Test
    public void convertButtonIsDisabledWhenFormatIsBad() {
        setInputData();
        viewModel.inputValueProperty().set("abba");

        assertTrue(viewModel.getConvertButtonDisabled());
    }

    @Test
    public void convertButtonIsEnabledWhenCorrectInput() {
        setInputData();

        assertFalse(viewModel.getConvertButtonDisabled());
    }

    @Test
    public void canSetAnotherConvertMode() {
        setInputData();
        viewModel.fromCurrencyProperty().set(currencyList.get(CurrencyIndexes.EUR.getIndex()));
        viewModel.toCurrencyProperty().set(currencyList.get(CurrencyIndexes.RUB.getIndex()));

        assertEquals(currencyList.get(CurrencyIndexes.EUR.getIndex()),
                     viewModel.fromCurrencyProperty().get());
    }

    @Test
    public void canConvertCorrectly() {
        setInputData();
        double expectResult = 0.23918;

        viewModel.convert();

        assertEquals(String.format("%.5f", expectResult), viewModel.resultProperty().get());
    }

    @Test
    public void correctCurrencyNameAfterConvert() {
        setInputData();

        viewModel.convert();

        assertEquals(viewModel.toCurrencyProperty().get().getCharCode(),
                     viewModel.resultCurrencyProperty().get());
    }

    @Test
    public void canSetSuccessMessage() {
        setInputData();

        viewModel.convert();

        assertEquals(ViewModelStatus.SUCCESS.toString(), viewModel.getStatus());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.inputValueProperty().set("incorrect");

        assertEquals(ViewModelStatus.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void canSetReadyStatusOnProperInput() {
        setInputData();

        assertEquals(ViewModelStatus.READY.toString(), viewModel.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsExceptionWithNullLogger() {
        new ViewModel(null);
    }

    @Test
    public void logIsEmptyAfterConstruction() {
        String log = viewModel.getLog();

        assertTrue(log.isEmpty());
    }

    @Test
    public void logContainsEventMessageWhenCorrectInputValue() {
        setInputData();
        String logMessages = viewModel.getLog();

        assertNotEquals(logMessages.indexOf("Event: New input value: 10"), -1);
    }

    @Test
    public void logContainsErrorMessageWhenIncorrectInputValue() {
        viewModel.inputValueProperty().set("incorrect");
        String logMessages = viewModel.getLog();

        assertNotEquals(logMessages.indexOf("Error: Incorrect input value"), -1);
    }

    @Test
    public void logContainsEventMessageAfterConvertOperation() {
        setInputData();
        viewModel.convert();
        String logMessages = viewModel.getLog();

        assertNotEquals(logMessages.indexOf("Converting is done. Result: "), -1);
    }

    @Test
    public void logContainsEventMessageWhenConversionModeIsChanged() {
        viewModel.onCurrencyConvertModeChanged(currencyList.get(CurrencyIndexes.RUB.getIndex()),
                                               currencyList.get(CurrencyIndexes.EUR.getIndex()));
        String logMessages = viewModel.getLog();

        assertNotEquals(logMessages.indexOf("Currency conversion mode is changed."), -1);
    }

    @Test
    public void logNotContainEventMessageWhenCurrencyNotChanged() {
        viewModel.onCurrencyConvertModeChanged(currencyList.get(CurrencyIndexes.RUB.getIndex()),
                                               currencyList.get(CurrencyIndexes.RUB.getIndex()));
        String logMessages = viewModel.getLog();

        assertEquals(logMessages.indexOf("Currency conversion mode is changed."), -1);
    }

    @Test
    public void canPutSeveralLogMessages() {
        setInputData();
        viewModel.convert();
        viewModel.convert();
        viewModel.convert();

        assertTrue(viewModel.getLog().split("\n").length > 3);
    }

    private void setInputData() {
        viewModel.inputValueProperty().set("10");
    }
}
