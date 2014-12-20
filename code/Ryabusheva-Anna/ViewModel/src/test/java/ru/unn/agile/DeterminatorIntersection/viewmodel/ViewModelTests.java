package ru.unn.agile.DeterminatorIntersection.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    public void setExternalViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUp() {
        if (viewModel == null) {
            viewModel = new ViewModel(new FakeLogger());
        }
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

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

    @Test
    public void constructorThrowsExceptionWithNullLogger() {
        try {
            new ViewModel(null);
            fail("Exception wasn't thrown");
        } catch (IllegalArgumentException ex) {
            assertEquals("Logger parameter can't be null", ex.getMessage());
        } catch (Exception ex) {
            fail("Invalid exception type");
        }
    }

    @Test
    public void logIsEmptyInTheBeginning() {
        List<String> log = viewModel.getLog();

        assertTrue(log.isEmpty());
    }

    @Test
    public void logContainsProperMessageAfterDeterminate() {
        setAllFieldsCorrectValues();

        viewModel.determinate();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + LoggerMessages.DETERMINATE_WAS_PRESSED + ".*"));
    }

    @Test
    public void logContainsInputArgumentsAfterCalculation() {
        setAllFieldsCorrectValues();

        viewModel.determinate();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + viewModel.planeAProperty().get()
                + ".*" + viewModel.planeBProperty().get()
                + ".*" + viewModel.planeCProperty().get()
                + ".*" + viewModel.planeDProperty().get()
                + ".*" + viewModel.getLinePXProperty().get()
                + ".*" + viewModel.getLinePYProperty().get()
                + ".*" + viewModel.getLinePZProperty().get()
                + ".*" + viewModel.getLineVXProperty().get()
                + ".*" + viewModel.getLineVYProperty().get()
                + ".*" + viewModel.getLineVZProperty().get() + ".*"));
    }

    @Test
    public void argumentsInfoIssProperlyFormatted() {
        setAllFieldsCorrectValues();

        viewModel.determinate();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*Arguments"
                + ": PlaneA = " + viewModel.planeAProperty().get()
                + "; PlaneB = " + viewModel.planeBProperty().get()
                + "; PlaneC = " + viewModel.planeCProperty().get()
                + "; PlaneD = " + viewModel.planeDProperty().get()
                + "; LinePointX = " + viewModel.getLinePXProperty().get()
                + "; LinePointY = " + viewModel.getLinePYProperty().get()
                + "; LinePointZ = " + viewModel.getLinePZProperty().get()
                + "; LineVectorX = " + viewModel.getLineVXProperty().get()
                + "; LineVectorY = " + viewModel.getLineVYProperty().get()
                + "; LineVectorZ = " + viewModel.getLineVZProperty().get() + ".*"));
    }

    @Test
    public void canPutSeveralLogMessages() {
        setAllFieldsCorrectValues();

        viewModel.determinate();
        viewModel.determinate();
        viewModel.determinate();

        assertEquals(3, viewModel.getLog().size());
    }

    @Test
    public void argumentsAreCorrectlyLogged() {
        setAllFieldsCorrectValues();

        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + LoggerMessages.EDITING_FINISHED
                + "Input arguments are: \\["
                + viewModel.planeAProperty().get() + "; "
                + viewModel.planeBProperty().get() + "; "
                + viewModel.planeCProperty().get() + "; "
                + viewModel.planeDProperty().get() + "; "
                + viewModel.getLinePXProperty().get() + "; "
                + viewModel.getLinePYProperty().get() + "; "
                + viewModel.getLinePZProperty().get() + "; "
                + viewModel.getLineVXProperty().get() + "; "
                + viewModel.getLineVYProperty().get() + "; "
                + viewModel.getLineVZProperty().get() + "\\]"));
    }

    @Test
    public void calculateIsNotCalledWhenButtonIsDisabled() {
        viewModel.determinate();

        assertTrue(viewModel.getLog().isEmpty());
    }

    @Test
    public void doNotLogSameParametersTwiceWithPartialInput() {
        viewModel.planeAProperty().set("4");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);
        viewModel.planeAProperty().set("4");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);

        assertEquals(1, viewModel.getLog().size());
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
