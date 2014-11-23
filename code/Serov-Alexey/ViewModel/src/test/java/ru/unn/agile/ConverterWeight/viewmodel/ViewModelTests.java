package ru.unn.agile.ConverterWeight.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import ru.unn.agile.ConverterWeight.viewmodel.ViewModel.Status;
import ru.unn.agile.ConverterWeight.viewmodel.ViewModel.UnitWeight;

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
    public void setValueUnitKilogram() {
        viewModel.setValueUnit(UnitWeight.KILOGRAM);
        assertEquals(UnitWeight.KILOGRAM, viewModel.getValueUnit());
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
    public void setResultUnitKilogram() {
        viewModel.setResultUnit(UnitWeight.KILOGRAM);
        assertEquals(UnitWeight.KILOGRAM, viewModel.getResultUnit());
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
    assertEquals("Gramm", grammName);
    }

    @Test
    public void inputRightValueReturnTrue() {
        viewModel.setValue("1");
        assertEquals(true, viewModel.isRightValue());
    }

    @Test
    public void inputNotRightValueReturnFalse() {
        viewModel.setValue("a");
        assertEquals(false, viewModel.isRightValue());
    }

    @Test
    public void inputRightValueReturnReady() {
        viewModel.setValue("1");
        viewModel.prepareForConvert();
        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void inputNotRightValueReturnBadFormat() {
        viewModel.setValue("a");
        viewModel.prepareForConvert();
        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void grammCovertGramm() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.GRAMM);
        viewModel.setResultUnit(UnitWeight.GRAMM);
        viewModel.convert();
        assertEquals("1", viewModel.getResult());
    }

    @Test
    public void grammCovertKilogram() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.GRAMM);
        viewModel.setResultUnit(UnitWeight.KILOGRAM);
        viewModel.convert();
        assertEquals("0.001", viewModel.getResult());
    }

    @Test
    public void grammCovertCentner() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.GRAMM);
        viewModel.setResultUnit(UnitWeight.CENTNER);
        viewModel.convert();
        assertEquals("1.0E-5", viewModel.getResult());
    }

    @Test
    public void grammCovertTon() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.GRAMM);
        viewModel.setResultUnit(UnitWeight.TON);
        viewModel.convert();
        assertEquals("1.0E-6", viewModel.getResult());
    }

    @Test
    public void kilogramCovertGramm() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.KILOGRAM);
        viewModel.setResultUnit(UnitWeight.GRAMM);
        viewModel.convert();
        assertEquals("1000.0", viewModel.getResult());
    }

    @Test
    public void kilogramCovertKilogram() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.KILOGRAM);
        viewModel.setResultUnit(UnitWeight.KILOGRAM);
        viewModel.convert();
        assertEquals("1", viewModel.getResult());
    }

    @Test
    public void kilogramCovertCentner() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.KILOGRAM);
        viewModel.setResultUnit(UnitWeight.CENTNER);
        viewModel.convert();
        assertEquals("0.01", viewModel.getResult());
    }

    @Test
    public void kilogramCovertTon() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.KILOGRAM);
        viewModel.setResultUnit(UnitWeight.TON);
        viewModel.convert();
        assertEquals("0.001", viewModel.getResult());
    }

    @Test
    public void centnerCovertGramm() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.CENTNER);
        viewModel.setResultUnit(UnitWeight.GRAMM);
        viewModel.convert();
        assertEquals("100000.0", viewModel.getResult());
    }

    @Test
    public void centnerCovertKilogram() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.CENTNER);
        viewModel.setResultUnit(UnitWeight.KILOGRAM);
        viewModel.convert();
        assertEquals("100.0", viewModel.getResult());
    }

    @Test
    public void centnerCovertCentner() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.CENTNER);
        viewModel.setResultUnit(UnitWeight.CENTNER);
        viewModel.convert();
        assertEquals("1", viewModel.getResult());
    }

    @Test
    public void centnerCovertTon() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.CENTNER);
        viewModel.setResultUnit(UnitWeight.TON);
        viewModel.convert();
        assertEquals("0.1", viewModel.getResult());
    }

    @Test
    public void tonCovertGramm() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.TON);
        viewModel.setResultUnit(UnitWeight.GRAMM);
        viewModel.convert();
        assertEquals("1000000.0", viewModel.getResult());
    }

    @Test
    public void tonCovertKilogram() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.TON);
        viewModel.setResultUnit(UnitWeight.KILOGRAM);
        viewModel.convert();
        assertEquals("1000.0", viewModel.getResult());
    }

    @Test
    public void tonCovertCentner() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.TON);
        viewModel.setResultUnit(UnitWeight.CENTNER);
        viewModel.convert();
        assertEquals("10.0", viewModel.getResult());
    }
    @Test
    public void tonCovertTon() {
        viewModel.setValue("1");
        viewModel.setValueUnit(UnitWeight.TON);
        viewModel.setResultUnit(UnitWeight.TON);
        viewModel.convert();
        assertEquals("1", viewModel.getResult());
    }

    @Test
    public void inputRightValueReturnSeccess() {
        viewModel.setValue("1");
        viewModel.convert();
        assertEquals(Status.SECCESS, viewModel.getStatus());
    }
}
