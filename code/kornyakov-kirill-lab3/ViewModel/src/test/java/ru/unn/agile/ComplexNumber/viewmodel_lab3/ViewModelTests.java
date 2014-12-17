package ru.unn.agile.ComplexNumber.viewmodel_lab3;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.ComplexNumber.model_lab3.ComplexNumber.Operation;

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
        assertEquals("", viewModel.re1Property().get());
        assertEquals("", viewModel.im1Property().get());
        assertEquals("", viewModel.re2Property().get());
        assertEquals("", viewModel.im2Property().get());
        assertEquals(Operation.ADD, viewModel.operationProperty().get());
        assertEquals("", viewModel.resultProperty().get());
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyFields() {
        viewModel.calculate();
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        setInputData();

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.re1Property().set("a");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        viewModel.re1Property().set("1");

        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setInputData();
        viewModel.re1Property().set("trash");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        viewModel.re1Property().set("1");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        setInputData();

        assertFalse(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void canSetAddOperation() {
        viewModel.operationProperty().set(Operation.ADD);
        assertEquals(Operation.ADD, viewModel.operationProperty().get());
    }

    @Test
    public void addIsDefaultOperation() {
        assertEquals(Operation.ADD, viewModel.operationProperty().get());
    }

    @Test
    public void operationAddHasCorrectResult() {
        viewModel.re1Property().set("1");
        viewModel.im1Property().set("4");
        viewModel.re2Property().set("-2");
        viewModel.im2Property().set("-2.5");

        viewModel.calculate();

        assertEquals("-1.0 + 1.5i", viewModel.resultProperty().get());
    }

    @Test
    public void canSetSuccessMessage() {
        setInputData();

        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.re1Property().set("#selfie");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setInputData();

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void operationMulHasCorrectResult() {
        viewModel.re1Property().set("2");
        viewModel.im1Property().set("3");
        viewModel.re2Property().set("1");
        viewModel.im2Property().set("2");
        viewModel.operationProperty().set(Operation.MULTIPLY);

        viewModel.calculate();

        assertEquals("-4.0 + 7.0i", viewModel.resultProperty().get());
    }

    @Test
    public void operationAddWithNegativeNumbersHasCorrectResult() {
        viewModel.re1Property().set("1.2");
        viewModel.im1Property().set("2.3");
        viewModel.re2Property().set("-10.4");
        viewModel.im2Property().set("-20.5");
        viewModel.operationProperty().set(Operation.ADD);

        viewModel.calculate();

        assertEquals("-9.2 - 18.2i", viewModel.resultProperty().get());
    }

    @Test
    public void viewModelConstructorThrowsExceptionWithNullLogger() {
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
    public void logContainsProperMessageAfterCalculation() {
        setInputData();
        viewModel.calculate();
        String message = viewModel.getLog().get(0);

        assertTrue(message.matches(".*" + LogMessages.CALCULATE_WAS_PRESSED + ".*"));
    }

    @Test
    public void logContainsInputArgumentsAfterCalculation() {
        setInputData();

        viewModel.calculate();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + viewModel.re1Property().get()
                + ".*" + viewModel.im1Property().get()
                + ".*" + viewModel.re2Property().get()
                + ".*" + viewModel.im1Property().get() + ".*"));
    }

    @Test
    public void argumentsInfoIssProperlyFormatted() {
        setInputData();

        viewModel.calculate();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*Arguments"
                + ": Re1 = " + viewModel.re1Property().get()
                + "; Im1 = " + viewModel.im1Property().get()
                + "; Re2 = " + viewModel.re2Property().get()
                + "; Im2 = " + viewModel.im2Property().get() + ".*"));
    }

    @Test
    public void operationTypeIsMentionedInTheLog() {
        setInputData();

        viewModel.calculate();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*Add.*"));
    }

    @Test
    public void canPutSeveralLogMessages() {
        setInputData();

        viewModel.calculate();
        viewModel.calculate();
        viewModel.calculate();

        assertEquals(3, viewModel.getLog().size());
    }

    @Test
    public void canSeeOperationChangeInLog() {
        setInputData();

        viewModel.onOperationChanged(Operation.ADD, Operation.MULTIPLY);

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + LogMessages.OPERATION_WAS_CHANGED + "Mul.*"));
    }

    @Test
    public void operationIsNotLoggedIfNotChanged() {
        viewModel.onOperationChanged(Operation.ADD, Operation.MULTIPLY);

        viewModel.onOperationChanged(Operation.MULTIPLY, Operation.MULTIPLY);

        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void argumentsAreCorrectlyLogged() {
        setInputData();

        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + LogMessages.EDITING_FINISHED
                + "Input arguments are: \\["
                + viewModel.re1Property().get() + "; "
                + viewModel.im1Property().get() + "; "
                + viewModel.re2Property().get() + "; "
                + viewModel.im2Property().get() + "\\]"));
    }

    @Test
    public void calculateIsNotCalledWhenButtonIsDisabled() {
        viewModel.calculate();

        assertTrue(viewModel.getLog().isEmpty());
    }

    @Test
    public void doNotLogSameParametersTwiceWithPartialInput() {
        viewModel.re1Property().set("12");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);
        viewModel.re1Property().set("12");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);

        assertEquals(1, viewModel.getLog().size());
    }

    private void setInputData() {
        viewModel.re1Property().set("1");
        viewModel.im1Property().set("2");
        viewModel.re2Property().set("3");
        viewModel.im2Property().set("4");
    }
}
