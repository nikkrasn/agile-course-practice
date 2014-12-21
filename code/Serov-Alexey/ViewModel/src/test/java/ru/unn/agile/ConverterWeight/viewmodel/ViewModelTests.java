package ru.unn.agile.ConverterWeight.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.ConverterWeight.viewmodel.ViewModel.Status;
import ru.unn.agile.ConverterWeight.Model.ConverterWeight.*;
import java.util.List;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static ru.unn.agile.ConverterWeight.viewmodel.RegexMatcher.matchesPattern;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        FakeLogger logger = new FakeLogger();
        viewModel = new ViewModel(logger);
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.getValue());
        assertEquals("", viewModel.getResult());
        assertEquals(Status.WAITING, viewModel.getStatus());
        assertEquals(UnitWeight.GRAMM, viewModel.getValueUnit());
        assertEquals(UnitWeight.GRAMM, viewModel.getResultUnit());
        assertEquals(false, viewModel.isConvertButton());
    }

    @Test
    public void isStatusWaitingInTheBeginning() {
        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void setValueReturnThisNumber() {
        viewModel.setValue("1");
        assertEquals("1", viewModel.getValue());
    }

    @Test
    public void setResultReturnThisNumber() {
        viewModel.setResult("1");
        assertEquals("1", viewModel.getResult());
    }

    @Test
    public void setValueUnitGramm() {
        viewModel.setValueUnit(UnitWeight.GRAMM);
        assertEquals(UnitWeight.GRAMM, viewModel.getValueUnit());
    }

    @Test
    public void setValueUnitKilogramm() {
        viewModel.setValueUnit(UnitWeight.KILOGRAMM);
        assertEquals(UnitWeight.KILOGRAMM, viewModel.getValueUnit());
    }

    @Test
    public void setValueUnitCentner() {
        viewModel.setValueUnit(UnitWeight.CENTNER);
        assertEquals(UnitWeight.CENTNER, viewModel.getValueUnit());
    }

    @Test
    public void setValueUnitTon() {
        viewModel.setValueUnit(UnitWeight.TON);
        assertEquals(UnitWeight.TON, viewModel.getValueUnit());
    }

    @Test
    public void setResultUnitGramm() {
        viewModel.setResultUnit(UnitWeight.GRAMM);
        assertEquals(UnitWeight.GRAMM, viewModel.getResultUnit());
    }

    @Test
    public void setResultUnitKilogramm() {
        viewModel.setResultUnit(UnitWeight.KILOGRAMM);
        assertEquals(UnitWeight.KILOGRAMM, viewModel.getResultUnit());
    }

    @Test
    public void setResultUnitCentner() {
        viewModel.setResultUnit(UnitWeight.CENTNER);
        assertEquals(UnitWeight.CENTNER, viewModel.getResultUnit());
    }

    @Test
    public void setResultUnitTon() {
        viewModel.setResultUnit(UnitWeight.TON);
        assertEquals(UnitWeight.TON, viewModel.getResultUnit());
    }

    @Test
    public void getGrammFromValueUnit() {
        String grammName = UnitWeight.GRAMM.toString();
        assertEquals("GRAMM", grammName);
    }

    @Test
    public void getKilogrammFromValueUnit() {
        String kilogrammName = UnitWeight.KILOGRAMM.toString();
        assertEquals("KILOGRAMM", kilogrammName);
    }

    @Test
    public void getCentnerFromValueUnit() {
        String centnerName = UnitWeight.CENTNER.toString();
        assertEquals("CENTNER", centnerName);
    }

    @Test
    public void getTonFromValueUnit() {
        String tonName = UnitWeight.TON.toString();
        assertEquals("TON", tonName);
    }

    @Test
    public void canCompareNameUntilWeight() {
        assertEquals(UnitWeight.GRAMM, UnitWeight.GRAMM);
        assertEquals(UnitWeight.KILOGRAMM, UnitWeight.KILOGRAMM);
        assertEquals(UnitWeight.CENTNER, UnitWeight.CENTNER);
        assertEquals(UnitWeight.TON, UnitWeight.TON);
        assertNotEquals(UnitWeight.CENTNER, UnitWeight.GRAMM);
    }

    @Test
    public void canGetListOfUntilWeight() {
        UnitWeight[] unitWeights = UnitWeight.values();
        UnitWeight[] currentUntilWeight = new UnitWeight[]{
                UnitWeight.GRAMM,
                UnitWeight.KILOGRAMM,
                UnitWeight.CENTNER,
                UnitWeight.TON};
        assertArrayEquals(currentUntilWeight, unitWeights);
    }

    @Test
    public void canSetUntilWeight() {
        viewModel.setValueUnit(UnitWeight.CENTNER);
        viewModel.setResultUnit(UnitWeight.TON);
        assertEquals(UnitWeight.CENTNER, viewModel.getValueUnit());
        assertEquals(UnitWeight.TON, viewModel.getResultUnit());
    }

    @Test
    public void isReturnTrueWhenSetRightValue() {
        viewModel.setValue("1");
        assertEquals(true, viewModel.isRightValue());
    }

    @Test
    public void isReturnFalseWhenSetNotRightValue() {
        viewModel.setValue("a");
        assertEquals(false, viewModel.isRightValue());
    }

    @Test
    public void isStatusReadyWhenSetRightValue() {
        viewModel.setValue("1");
        viewModel.prepareForConvert();
        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void isStatusBadFormatWhenSetInputNotPositiveValue() {
        viewModel.setValue("-1");
        viewModel.prepareForConvert();
        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void isStatusLargeWhenSetInputBigValue() {
        double tmp = Double.MAX_VALUE;
        viewModel.setValue(Double.toString(tmp));
        viewModel.setValueUnit(UnitWeight.KILOGRAMM);
        viewModel.setResultUnit(UnitWeight.GRAMM);
        viewModel.convert();
        assertEquals(Status.LARGE, viewModel.getStatus());
    }

    @Test
    public void isStatusBadFormatWhenSetInputNotRightValue() {
        viewModel.setValue("a");
        viewModel.prepareForConvert();
        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void canGrammConvertGramm() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.GRAMM);
        viewModel.setResultUnit(UnitWeight.GRAMM);
        viewModel.convert();
        assertEquals("1.0", viewModel.getResult());
    }

    @Test
    public void canGrammConvertKilogram() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.GRAMM);
        viewModel.setResultUnit(UnitWeight.KILOGRAMM);
        viewModel.convert();
        assertEquals("0.001", viewModel.getResult());
    }

    @Test
    public void canGrammConvertCentner() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.GRAMM);
        viewModel.setResultUnit(UnitWeight.CENTNER);
        viewModel.convert();
        assertEquals("1.0E-5", viewModel.getResult());
    }

    @Test
    public void canGrammConvertTon() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.GRAMM);
        viewModel.setResultUnit(UnitWeight.TON);
        viewModel.convert();
        assertEquals("1.0E-6", viewModel.getResult());
    }

    @Test
    public void canKilogrammConvertGramm() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.KILOGRAMM);
        viewModel.setResultUnit(UnitWeight.GRAMM);
        viewModel.convert();
        assertEquals("1000.0", viewModel.getResult());
    }

    @Test
    public void canKilogramConvertKilogramm() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.KILOGRAMM);
        viewModel.setResultUnit(UnitWeight.KILOGRAMM);
        viewModel.convert();
        assertEquals("1.0", viewModel.getResult());
    }

    @Test
    public void canKilogrammConvertCentner() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.KILOGRAMM);
        viewModel.setResultUnit(UnitWeight.CENTNER);
        viewModel.convert();
        assertEquals("0.01", viewModel.getResult());
    }

    @Test
    public void canKilogrammConvertTon() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.KILOGRAMM);
        viewModel.setResultUnit(UnitWeight.TON);
        viewModel.convert();
        assertEquals("0.001", viewModel.getResult());
    }

    @Test
    public void canCentnerConvertGramm() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.CENTNER);
        viewModel.setResultUnit(UnitWeight.GRAMM);
        viewModel.convert();
        assertEquals("100000.0", viewModel.getResult());
    }

    @Test
    public void canCentnerConvertKilogramm() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.CENTNER);
        viewModel.setResultUnit(UnitWeight.KILOGRAMM);
        viewModel.convert();
        assertEquals("100.0", viewModel.getResult());
    }

    @Test
    public void canCentnerConvertCentner() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.CENTNER);
        viewModel.setResultUnit(UnitWeight.CENTNER);
        viewModel.convert();
        assertEquals("1.0", viewModel.getResult());
    }

    @Test
    public void canCentnerConvertTon() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.CENTNER);
        viewModel.setResultUnit(UnitWeight.TON);
        viewModel.convert();
        assertEquals("0.1", viewModel.getResult());
    }

    @Test
    public void canTonConvertGramm() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.TON);
        viewModel.setResultUnit(UnitWeight.GRAMM);
        viewModel.convert();
        assertEquals("1000000.0", viewModel.getResult());
    }

    @Test
    public void canTonConvertKilogramm() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.TON);
        viewModel.setResultUnit(UnitWeight.KILOGRAMM);
        viewModel.convert();
        assertEquals("1000.0", viewModel.getResult());
    }

    @Test
    public void canTonConvertCentner() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.TON);
        viewModel.setResultUnit(UnitWeight.CENTNER);
        viewModel.convert();
        assertEquals("10.0", viewModel.getResult());
    }
    @Test
    public void canTonConvertTon() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.TON);
        viewModel.setResultUnit(UnitWeight.TON);
        viewModel.convert();
        assertEquals("1.0", viewModel.getResult());
    }

    @Test
    public void isStatusSuccessWhenConverted() {
        viewModel.setValue("1");
        viewModel.convert();
        assertEquals(Status.SUCCESS, viewModel.getStatus());
    }

    @Test
    public void isRightStatusWhenInputAllOptions() {
        viewModel.setValue("-1");
        viewModel.convert();
        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());

        viewModel.setValue("1");
        viewModel.convert();
        assertEquals(Status.SUCCESS, viewModel.getStatus());

        viewModel.setValue("12e308");
        viewModel.setResultUnit(UnitWeight.KILOGRAMM);
        viewModel.setResultUnit(UnitWeight.GRAMM);
        viewModel.convert();
        assertEquals(Status.LARGE,  viewModel.getStatus());
    }

    @Test
    public void canCreateViewModelWithLogger() {
        FakeLogger logger = new FakeLogger();
        ViewModel viewModelLogged = new ViewModel(logger);
        assertNotNull(viewModelLogged);
    }

    @Test
    public void createViewModelWithNullReturnException() {
        try {
            new ViewModel(null);
            fail("is not exception");
        } catch (IllegalArgumentException ex) {
            assertEquals("Logger is don't null", ex.getMessage());
        } catch (Exception ex) {
            fail("Invalid exception type");
        }
    }

    @Test
    public void isEmptyLogInTheBeginning() {
        List<String> log = viewModel.getLog();
        assertEquals(0, log.size());
    }

    @Test
    public void isConvertWritesLog() {
        viewModel.convert();

        List<String> log = viewModel.getLog();
        assertNotEquals(0, log.size());
    }

    @Test
    public void isPressingButtonWritesInLog() {
        viewModel.convert();
        String message = viewModel.getLog().get(0);
        assertThat(message,
                matchesPattern(".*" + ViewModel.Messages.PRESSED_TO_CONVERT + ".*"));
    }

    @Test
    public void isWritesInputValueInLog() {
        viewModel.setValue("1");
        viewModel.convert();
        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*" + viewModel.getValue() + ".*"));
    }

    @Test
    public void isWritesValueUnitGrammInLog() {
        viewModel.setValueUnit(UnitWeight.GRAMM);
        viewModel.convert();
        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*GRAMM.*"));
    }

    @Test
    public void isWritesValueUnitKilogramInLog() {
        viewModel.setValueUnit(UnitWeight.KILOGRAMM);
        viewModel.convert();
        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*KILOGRAM.*"));
    }

    @Test
    public void isWritesValueUnitCentnerInLog() {
        viewModel.setValueUnit(UnitWeight.CENTNER);
        viewModel.convert();
        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*CENTNER.*"));
    }

    @Test
    public void isWritesValueUnitTonInLog() {
        viewModel.setValueUnit(UnitWeight.TON);
        viewModel.convert();
        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*TON.*"));
    }

    @Test
    public void isWritesResultUnitGrammInLog() {
        viewModel.setValueUnit(UnitWeight.GRAMM);
        viewModel.convert();
        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*GRAMM.*"));
    }

    @Test
    public void isWritesResultUnitKilogramInLog() {
        viewModel.setValueUnit(UnitWeight.KILOGRAMM);
        viewModel.convert();
        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*KILOGRAM.*"));
    }

    @Test
    public void isWritesResultUnitCentnerInLog() {
        viewModel.setValueUnit(UnitWeight.CENTNER);
        viewModel.convert();
        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*CENTNER.*"));
    }

    @Test
    public void isWritesResultUnitTonInLog() {
        viewModel.setValueUnit(UnitWeight.TON);
        viewModel.convert();
        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*TON.*"));
    }

    @Test
    public void isWritesInfoAboutArgumentsInLog() {
        viewModel.setValue("1");
        viewModel.convert();
        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(ViewModel.Messages.PRESSED_TO_CONVERT
                + "Value = " + viewModel.getValue() + "."
                + " Value unit: " + viewModel.getValueUnit()
                + " convert to: "+ viewModel.getValueUnit() + "."));
    }

    @Test
    public void canWtitesSeveralMessagesInLog() {
        viewModel.setValue("1");
        viewModel.convert();
        viewModel.convert();
        assertEquals(2, viewModel.getLog().size());
    }

    @Test
    public void isNotWritesInLogWhenNotChangedValueUnit() {
        viewModel.setValueUnit(UnitWeight.GRAMM);
        viewModel.setValueUnit(UnitWeight.GRAMM);
        assertEquals(0, viewModel.getLog().size());
    }

    @Test
    public void isWritesInLogWhenChangedValueUnit() {
        viewModel.setValueUnit(UnitWeight.KILOGRAMM);
        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void isNotWritesInLogWhenNotChangedResultUnit() {
        viewModel.setResultUnit(UnitWeight.GRAMM);
        viewModel.setResultUnit(UnitWeight.GRAMM);
        assertEquals(0, viewModel.getLog().size());
    }

    @Test
    public void isWritesInLogWhenNotChangedResultUnit() {
        viewModel.setResultUnit(UnitWeight.KILOGRAMM);
        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void isWritesEditingParamsInLog() {
        viewModel.setValue("1");
        viewModel.editingParams();
        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*" + ViewModel.Messages.EDITING_FINISHED + ".*"));
    }

    @Test
    public void isNotWritesInLogWhenInputParametersTwice() {
        viewModel.setValue("1");
        viewModel.setValue("1");
        viewModel.editingParams();
        viewModel.editingParams();

        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*" + ViewModel.Messages.EDITING_FINISHED + ".*"));
        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void areArgumentsCorrectlyLoggedOnEditingFinish() {
        viewModel.setValue("1");
        viewModel.editingParams();

        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*" + ViewModel.Messages.EDITING_FINISHED
                                                + "Input argument are: "
                                                + viewModel.getValue()));
    }
}
