package ru.unn.agile.ComplexProcent.ViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;

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
        setInputData();
        viewModel.calculate();
        assertEquals("1045,00", viewModel.getResult());
    }

    @Test
    public void canCalcCapitalizeInOnePeriodFewYearsVM() {
        setTxtFields("1000", "4,5", "1");
        viewModel.dtPkrStartProperty().set(LocalDate.of(2014, 7 , 10));
        viewModel.dtPkrEndProperty().set(LocalDate.of(2016, 7 , 10));
        viewModel.calculate();
        assertEquals("1092,16", viewModel.getResult());
    }

    @Test
    public void canCalcCapitalizeInFewPeriodForYearVM() {
        setTxtFields("1000", "4.5", "3");
        viewModel.dtPkrStartProperty().set(LocalDate.of(2014, 7, 10));
        viewModel.dtPkrEndProperty().set(LocalDate.of(2015, 7, 10));
        viewModel.calculate();
        assertEquals("1045,94", viewModel.getResult());
    }

    @Test
    public void canCalcCapitalizeInFewPeriodForFewYearsVM() {
        setTxtFields("1000", "4.5", "3");
        viewModel.dtPkrStartProperty().set(LocalDate.of(2014, 7, 10));
        viewModel.dtPkrEndProperty().set(LocalDate.of(2018, 7, 10));
        viewModel.calculate();
        assertEquals("1196,95", viewModel.getResult());
    }

    @Test
    public void canCalcCapitalizeInLessThenYearVM() {
        setTxtFields("1000", "4.5", "3");
        viewModel.dtPkrStartProperty().set(LocalDate.of(2014, 7, 10));
        viewModel.dtPkrEndProperty().set(LocalDate.of(2015, 5, 11));
        viewModel.calculate();
        assertEquals("1038,27", viewModel.getResult());
    }

    @Test
    public void defaultStatusAndButton() {
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
        assertEquals(viewModel.getCalculationDisabled(), true);
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
    public void canReportBadFormatForInterestCount() {
        viewModel.getTxtInterestCountProperty().set("5,4");
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
        assertEquals(viewModel.dtPkrEndProperty().get(), LocalDate.now());
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
    public  void statusWaitingWhenPickerEmpty() {
        setTxtFields("1000", "4" , "1");
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setInputData();

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    private void setTxtFields(final String base, final String percent, final String interestCount) {
        viewModel.getTxtBaseProperty().set(base);
        viewModel.getTxtInterestCountProperty().set(interestCount);
        viewModel.getTxtPercentProperty().set(percent);
}

    private void setInputData() {
        viewModel.getTxtBaseProperty().set("1000");
        viewModel.getTxtPercentProperty().set("4,5");
        viewModel.getTxtInterestCountProperty().set("1");
        viewModel.dtPkrStartProperty().set(LocalDate.of(2014, 7, 10));
        viewModel.dtPkrEndProperty().set(LocalDate.of(2015, 7, 10));
    }
}
