package ru.unn.agile.CurrencyConverter.viewmodel;

import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.CurrencyConverter.Model.Currency;
import ru.unn.agile.CurrencyConverter.Model.CurrencyIndexes;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;
    private ObservableList<Currency> currencyList;
    private final double delta = 0.0001;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
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

    private void setInputData() {
        viewModel.inputValueProperty().set("10");
    }
}
