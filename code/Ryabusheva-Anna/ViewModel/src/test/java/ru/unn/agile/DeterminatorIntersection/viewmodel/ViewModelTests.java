package ru.unn.agile.DeterminatorIntersection.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() { viewModel = null; }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.planeAProperty().get());
        assertEquals("", viewModel.planeBProperty().get());
        assertEquals("", viewModel.planeCProperty().get());
        assertEquals("", viewModel.planeDProperty().get());
        assertEquals("", viewModel.getLinePXProperty().get());
        assertEquals("", viewModel.getLinePYProperty().get());
        assertEquals("", viewModel.getLinePZProperty().get());
        assertEquals("", viewModel.getLineVXProperty().get());
        assertEquals("", viewModel.getLineVYProperty().get());
        assertEquals("", viewModel.getLineVZProperty().get());
        assertEquals("", viewModel.resultProperty().get());
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
        assertTrue(viewModel.determinateDisabledProperty().get());
    }

    @Test
    public void statusIsWaitingWhenDeterminateWithEmptyFields() {
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        viewModel.planeBProperty().set("7");
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.planeAProperty().set("zzzz");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenFieldsAreFull() {
        setAllFieldsCorrectValues();
        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void determinateButtonIsDisabledInitially() {
        assertTrue(viewModel.determinateDisabledProperty().get());
    }

    @Test
    public void determinateButtonIsDisabledWhenFormatIsBad() {
        setAllFieldsCorrectValues();
        viewModel.getLinePXProperty().set("wrong");
        assertTrue(viewModel.determinateDisabledProperty().get());
    }

    @Test
    public void determinateButtonIsDisabledWithIncompleteInput() {
        viewModel.getLineVYProperty().set("0");
        assertTrue(viewModel.determinateDisabledProperty().get());
    }

    @Test
    public void determinateButtonIsEnabledWithCorrectInput() {
        setAllFieldsCorrectValues();
        assertFalse(viewModel.determinateDisabledProperty().get());
    }

    @Test
    public void correctResultAfterDeterminationWithIntersection() {
        setFieldsValuesWithIntersection();
        viewModel.determinate();
        assertEquals("Intersection determinate", viewModel.resultProperty().get());
    }

    @Test
    public void correctResultAfterDeterminationWithNoIntersection() {
        setFieldsValuesWithNoIntersection();
        viewModel.determinate();
        assertEquals("No intersection determinate", viewModel.resultProperty().get());
    }

    @Test
    public void correctResultAfterDeterminationWithNotEnoughCorrectData() {
        viewModel.planeBProperty().set("2");
        viewModel.determinate();
        assertEquals("", viewModel.resultProperty().get());
    }
    @Test
    public void correctStatusAfterDeterminationWithNotEnoughCorrectData() {
        viewModel.planeBProperty().set("2");
        viewModel.determinate();
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void correctResultAfterDeterminationWithBadFormat() {
        setAllFieldsCorrectValues();
        viewModel.planeBProperty().set("wrong");
        viewModel.determinate();
        assertEquals("", viewModel.resultProperty().get());
    }

    @Test
    public void correctStatusAfterDeterminationWithBadFormat() {
        setAllFieldsCorrectValues();
        viewModel.planeBProperty().set("wrong");
        viewModel.determinate();
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    private void setAllFieldsCorrectValues() {
        viewModel.planeAProperty().set("2");
        viewModel.planeBProperty().set("3");
        viewModel.planeCProperty().set("6");
        viewModel.planeDProperty().set("4");
        viewModel.getLinePXProperty().set("7");
        viewModel.getLinePYProperty().set("5");
        viewModel.getLinePZProperty().set("8");
        viewModel.getLineVXProperty().set("6");
        viewModel.getLineVYProperty().set("9");
        viewModel.getLineVZProperty().set("7");
    }

    private void setFieldsValuesWithIntersection() {
        viewModel.planeAProperty().set("1");
        viewModel.planeBProperty().set("1");
        viewModel.planeCProperty().set("1");
        viewModel.planeDProperty().set("1");
        viewModel.getLinePXProperty().set("0");
        viewModel.getLinePYProperty().set("0");
        viewModel.getLinePZProperty().set("0");
        viewModel.getLineVXProperty().set("1");
        viewModel.getLineVYProperty().set("1");
        viewModel.getLineVZProperty().set("1");
    }

    private void setFieldsValuesWithNoIntersection() {
        viewModel.planeAProperty().set("1");
        viewModel.planeBProperty().set("1");
        viewModel.planeCProperty().set("0");
        viewModel.planeDProperty().set("1");
        viewModel.getLinePXProperty().set("0");
        viewModel.getLinePYProperty().set("0");
        viewModel.getLinePZProperty().set("0");
        viewModel.getLineVXProperty().set("-1");
        viewModel.getLineVYProperty().set("1");
        viewModel.getLineVZProperty().set("0");
    }
}
