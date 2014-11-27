package ru.unn.agile.ConverterWeight.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.ConverterWeight.viewmodel.ViewModel.Status;
import ru.unn.agile.ConverterWeight.Model.ConverterWeight.*;
import static org.junit.Assert.*;
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

}
