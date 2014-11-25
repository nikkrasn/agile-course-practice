package ru.unn.agile.ComplexProcent.ViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.ComplexProcent.Model.ComplexDeposit;

import java.time.LocalDate;
import java.util.GregorianCalendar;

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
        assertEquals("", viewModel.getTxtBaseProperty().get());
        assertEquals("", viewModel.getTxtInterestCountProperty().get());
        assertEquals("", viewModel.getTxtPercentProperty().get());
        assertEquals(LocalDate.now(), viewModel.dtPkrStartProperty().get());
        assertEquals(null, viewModel.dtPkrEndProperty().get());
        assertEquals("", viewModel.resultProperty().get());
    }

    @Test
    public void canCalculateModel() {
        setInputData();
        viewModel.calculate();
        assertNotNull(viewModel.getResult());
    }

    @Test
    public void canCalcCapitalizeInOnePeriodVM() {
        viewModel.getTxtBaseProperty().set("1000");
        viewModel.getTxtPercentProperty().set("4.5");
        viewModel.getTxtInterestCountProperty().set("1");
        viewModel.dtPkrStartProperty().set(LocalDate.of(2014, 7, 10));
        viewModel.dtPkrEndProperty().set(LocalDate.of(2015, 7, 10));
        viewModel.calculate();
        assertEquals("1045,00",viewModel.getResult());
    }

    @Test
    public void canCalcCapitalizeInOnePeriodFewYearsVM() {
        viewModel.getTxtBaseProperty().set("1000");
        viewModel.getTxtPercentProperty().set("4.5");
        viewModel.getTxtInterestCountProperty().set("1");
        viewModel.dtPkrStartProperty().set(LocalDate.of(2014, 7 , 10));
        viewModel.dtPkrEndProperty().set(LocalDate.of(2016, 7 , 10));
        viewModel.calculate();
        assertEquals("1092,16",viewModel.getResult());
    }

    @Test
    public void canCalcCapitalizeInFewPeriodForYearVM() {
        viewModel.getTxtBaseProperty().set("1000");
        viewModel.getTxtPercentProperty().set("4.5");
        viewModel.getTxtInterestCountProperty().set("3");
        viewModel.dtPkrStartProperty().set(LocalDate.of(2014, 7, 10));
        viewModel.dtPkrEndProperty().set(LocalDate.of(2015, 7, 10));
        viewModel.calculate();
        assertEquals("1045,94",viewModel.getResult());
    }

    @Test
    public void canCalcCapitalizeInFewPeriodForFewYearsVM() {
        viewModel.getTxtBaseProperty().set("1000");
        viewModel.getTxtPercentProperty().set("4.5");
        viewModel.getTxtInterestCountProperty().set("3");
        viewModel.dtPkrStartProperty().set(LocalDate.of(2014, 7, 10));
        viewModel.dtPkrEndProperty().set(LocalDate.of(2018, 7, 10));
        viewModel.calculate();
        assertEquals("1196,95",viewModel.getResult());
    }

    @Test
    public void canCalcCapitalizeInLessThenYearVM() {
        viewModel.getTxtBaseProperty().set("1000");
        viewModel.getTxtPercentProperty().set("4.5");
        viewModel.getTxtInterestCountProperty().set("3");
        viewModel.dtPkrStartProperty().set(LocalDate.of(2014, 7, 10));
        viewModel.dtPkrEndProperty().set(LocalDate.of(2015, 5, 11));
        viewModel.calculate();
        assertEquals("1038,27",viewModel.getResult());
    }

    @Test
    public void defaultStatusAndButton() {
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
        assertEquals(viewModel.getCalculationDisabled(),true);
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        setInputData();
        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }
    @Test
    public void canReportBadFormat() {
        viewModel.getTxtBaseProperty().set("sadcacasa");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        viewModel.getTxtBaseProperty().set("1");
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setInputData();
        viewModel.getTxtBaseProperty().set("spam");
        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        viewModel.getTxtBaseProperty().set("1000");
        viewModel.getTxtInterestCountProperty().set("1");
        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        setInputData();
        assertFalse(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWithErasing() {
        setInputData();
        viewModel.getTxtBaseProperty().set("");
        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void canChangeDatePickers() {
        viewModel.dtPkrStartProperty().set(LocalDate.of(2016, 10, 9));
        viewModel.dtPkrEndProperty().set(LocalDate.of(2014, 1, 1));
        viewModel.dtPkrEndProperty().set(LocalDate.now());
        assertEquals(viewModel.dtPkrEndProperty().get(),LocalDate.now());
        assertEquals(viewModel.dtPkrStartProperty().asString().get(), "2016-10-09");
    }

    @Test
    public void canSetSuccessMessage() {
        setInputData();

        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.getTxtPercentProperty().set("www.google.ru");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setInputData();

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    private void setInputData() {
        viewModel.getTxtBaseProperty().set("1000");
        viewModel.getTxtPercentProperty().set("4.5");
        viewModel.getTxtInterestCountProperty().set("1");
        viewModel.dtPkrStartProperty().set(LocalDate.of(2014, 7, 10));
        viewModel.dtPkrEndProperty().set(LocalDate.of(2015, 7, 10));
    }
}
