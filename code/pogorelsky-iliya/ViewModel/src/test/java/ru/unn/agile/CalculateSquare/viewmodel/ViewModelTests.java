package ru.unn.agile.CalculateSquare.viewmodel;

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
            viewModel = new ViewModel(new FictiousLog());
        }
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.txtParam1Property().get());
        assertEquals("", viewModel.txtParam1Property().get());
        assertEquals("Cone", viewModel.operationProperty().get());
        assertEquals("", viewModel.resultSquareProperty().get());
        assertEquals(MessageStatus.WAITING.toString(), viewModel.statusMessageProperty().get());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyFields() {
        viewModel.calculate();

        assertEquals(MessageStatus.WAITING.toString(), viewModel.statusMessageProperty().get());
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        setInputData();

        assertEquals(MessageStatus.READY.toString(), viewModel.statusMessageProperty().get());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.txtParam1Property().set("b");

        assertEquals(MessageStatus.BAD_FORMAT.toString(), viewModel.statusMessageProperty().get());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        viewModel.txtParam1Property().set("1");

        assertEquals(MessageStatus.WAITING.toString(), viewModel.statusMessageProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setInputData();
        viewModel.txtParam1Property().set("trash");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        viewModel.txtParam1Property().set("1");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        setInputData();

        assertFalse(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void canSetCylinderFigure() {
        viewModel.operationProperty().set("Cylinder");

        assertEquals("Cylinder", viewModel.operationProperty().get());
    }

    @Test
    public void addIsDefaultFigure() {
        assertEquals("Cone", viewModel.operationProperty().get());
    }

    @Test
    public void figureConeHasCorrectResult() {
        viewModel.txtParam1Property().set("4");
        viewModel.txtParam2Property().set("3");

        viewModel.calculate();

        assertEquals(Double.toString(36 * Math.PI), viewModel.resultSquareProperty().get());
    }

    @Test
    public void figureCylinderHasCorrectResult() {
        viewModel.operationProperty().set("Cylinder");

        viewModel.txtParam1Property().set("1");
        viewModel.txtParam2Property().set("1");

        viewModel.calculate();

        assertEquals(Double.toString(4 * Math.PI), viewModel.resultSquareProperty().get());
    }

    @Test
    public void figureSphereSectorHasCorrectResult() {
        viewModel.operationProperty().set("SphereSector");
        viewModel.txtParam1Property().set("4");
        viewModel.txtParam2Property().set("3");

        viewModel.calculate();

        assertEquals(Double.toString(4 * Math.PI), viewModel.resultSquareProperty().get());
    }

    @Test
    public void figureSphereSegmentHasCorrectResult() {
        viewModel.operationProperty().set("SphereSegment");
        viewModel.txtParam1Property().set("1");
        viewModel.txtParam2Property().set("1");

        viewModel.calculate();

        assertEquals(Double.toString(2 * Math.PI), viewModel.resultSquareProperty().get());
    }


    @Test
    public void canSetSuccessMessage() {
        setInputData();

        viewModel.calculate();

        assertEquals(MessageStatus.SUCCESS.toString(), viewModel.statusMessageProperty().get());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.txtParam1Property().set("qwerty");

        assertEquals(MessageStatus.BAD_FORMAT.toString(), viewModel.statusMessageProperty().get());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setInputData();

        assertEquals(MessageStatus.READY.toString(), viewModel.statusMessageProperty().get());
    }

    private void setInputData() {
        viewModel.txtParam1Property().set("1");
        viewModel.txtParam2Property().set("2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void viewModelConstructorThrowsExceptionIfLoggerNull() {
        new ViewModel(null);
    }

    @Test
    public void logIsEmptyAtTheStart() {
        List<String> log = viewModel.getLog();

        assertTrue(log.isEmpty());
    }

    @Test
    public void logContainsTrueMessageAfterCalculation() {
        setInputData();
        viewModel.calculate();
        String message = viewModel.getLog().get(0);

        assertTrue(message.matches(".*" + LogInfo.PRESSED_CALCULATE + ".*"));
    }

    @Test
    public void logContainsInputParamenersAfterCalculation() {
        setInputData();

        viewModel.calculate();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + viewModel.txtParam1Property().get()
                + ".*" + viewModel.txtParam2Property().get() + ".*"));
    }

    @Test
    public void argumentsInfoDoneFormatted() {
        setInputData();

        viewModel.calculate();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*Arguments"
                + ": r = " + viewModel.txtParam1Property().get()
                + "; h = " + viewModel.txtParam2Property().get() + ".*"));
    }

    @Test
    public void operationTypeIsDisplayedInTheLog() {
        setInputData();

        viewModel.calculate();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*Cone.*"));
    }

    @Test
    public void canDisplayFewLogMessages() {
        setInputData();

        viewModel.calculate();
        viewModel.calculate();
        viewModel.calculate();

        assertEquals(3, viewModel.getLog().size());
    }

    @Test
    public void canDisplayOperationChangeInLog() {
        setInputData();

        viewModel.onOperationChanged("Cone", "Cube");

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + LogInfo.CHANGED_OPERATION + "Cube.*"));
    }

    @Test
    public void operationIsNotDispalayAtLogIfNotChanged() {
        viewModel.onOperationChanged("Cone", "Cube");

        viewModel.onOperationChanged("Cube", "Cube");

        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void calculateIsNotCalledIfButtonIsDisabled() {
        viewModel.calculate();

        assertTrue(viewModel.getLog().isEmpty());
    }

    @Test
    public void doNotDisplayAtLogEqualParamTwiceWithPartialInput() {
        viewModel.txtParam1Property().set("1");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);
        viewModel.txtParam1Property().set("1");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);

        assertEquals(1, viewModel.getLog().size());
    }
}


