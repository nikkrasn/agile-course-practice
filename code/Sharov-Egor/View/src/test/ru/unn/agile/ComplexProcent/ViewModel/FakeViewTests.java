package ru.unn.agile.ComplexProcent.ViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class FakeViewTests {
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
        assertEquals(LocalDate.now(), viewModel.dtPkrStartProperty().get());
        assertEquals("", viewModel.getTxtInterestCountProperty().get());
        assertEquals("", viewModel.getTxtPercentProperty().get());
        assertEquals("", viewModel.getTxtBaseProperty().get());
        assertEquals("", viewModel.resultProperty().get());
    }
}
