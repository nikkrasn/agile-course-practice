package ru.unn.agile.ComplexNumber.viewmodel;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel(new MockLogger());
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
        assertEquals(ViewModel.Operation.ADD, viewModel.operationProperty().get());
        assertEquals("", viewModel.resultProperty().get());
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyFields() {
        viewModel.getCalculationFiredEventHandler().handle(new ActionEvent());
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
        assertFalse(viewModel.isCalculationPossibleProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setInputData();
        viewModel.re1Property().set("trash");

        assertFalse(viewModel.isCalculationPossibleProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        viewModel.re1Property().set("1");

        assertFalse(viewModel.isCalculationPossibleProperty().get());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        setInputData();

        assertTrue(viewModel.isCalculationPossibleProperty().get());
    }

    @Test
    public void canSetAddOperation() {
        viewModel.operationProperty().set(ViewModel.Operation.ADD);
        assertEquals(ViewModel.Operation.ADD, viewModel.operationProperty().get());
    }

    @Test
    public void addIsDefaultOperation() {
        assertEquals(ViewModel.Operation.ADD, viewModel.operationProperty().get());
    }

    @Test
    public void operationAddHasCorrectResult() {
        viewModel.re1Property().set("1");
        viewModel.im1Property().set("4");
        viewModel.re2Property().set("-2");
        viewModel.im2Property().set("-2.5");

        viewModel.getCalculationFiredEventHandler().handle(new ActionEvent());

        assertEquals("-1.0 + 1.5i", viewModel.resultProperty().get());
    }

    @Test
    public void canSetSuccessMessage() {
        setInputData();

        viewModel.getCalculationFiredEventHandler().handle(new ActionEvent());

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
        viewModel.operationProperty().set(ViewModel.Operation.MULTIPLY);

        viewModel.getCalculationFiredEventHandler().handle(new ActionEvent());

        assertEquals("-4.0 + 7.0i", viewModel.resultProperty().get());
    }

    @Test
    public void operationAddWithNegativeNumbersHasCorrectResult() {
        viewModel.re1Property().set("1.2");
        viewModel.im1Property().set("2.3");
        viewModel.re2Property().set("-10.4");
        viewModel.im2Property().set("-20.5");
        viewModel.operationProperty().set(ViewModel.Operation.ADD);

        viewModel.getCalculationFiredEventHandler().handle(new ActionEvent());

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
        viewModel.getCalculationFiredEventHandler().handle(new ActionEvent());
        String message = viewModel.getLog().get(0);

        assertTrue(message.matches(".*" + LogMessages.CALCULATE_WAS_PRESSED + ".*"));
    }

    @Test
    public void logContainsInputArgumentsAfterCalculation() {
        setInputData();

        viewModel.getCalculationFiredEventHandler().handle(new ActionEvent());

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + viewModel.re1Property().get()
                + ".*" + viewModel.im1Property().get()
                + ".*" + viewModel.re2Property().get()
                + ".*" + viewModel.im1Property().get() + ".*"));
    }

    @Test
    public void argumentsInfoIssProperlyFormatted() {
        setInputData();

        viewModel.getCalculationFiredEventHandler().handle(new ActionEvent());

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

        viewModel.getCalculationFiredEventHandler().handle(new ActionEvent());

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*Add.*"));
    }

    @Test
    public void canPutSeveralLogMessages() {
        setInputData();

        viewModel.getCalculationFiredEventHandler().handle(new ActionEvent());
        viewModel.getCalculationFiredEventHandler().handle(new ActionEvent());
        viewModel.getCalculationFiredEventHandler().handle(new ActionEvent());

        assertEquals(3, viewModel.getLog().size());
    }

    @Test
    public void canSeeOperationChangeInLog() {
        setInputData();

        viewModel.getOperationChangedListener().changed(mockOperationChanged,
                ViewModel.Operation.ADD, ViewModel.Operation.MULTIPLY);

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + LogMessages.OPERATION_WAS_CHANGED + "Mul.*"));
    }

    @Test
    public void operationIsNotLoggedIfNotChanged() {
        viewModel.getOperationChangedListener().changed(mockOperationChanged,
                ViewModel.Operation.ADD, ViewModel.Operation.MULTIPLY);

        viewModel.getOperationChangedListener().changed(mockOperationChanged,
                ViewModel.Operation.MULTIPLY, ViewModel.Operation.MULTIPLY);

        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void editingFinishIsLogged() {
        viewModel.re1Property().set("1.5");

        viewModel.getFocusChangeListener().changed(mockFocusChanged,
                Boolean.TRUE, Boolean.FALSE);

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + LogMessages.EDITING_FINISHED + ".*"));
    }

    @Test
    public void argumentsAreCorrectlyLoggedOnEditingFinish() {
        setInputData();

        viewModel.getFocusChangeListener().changed(mockFocusChanged,
                Boolean.TRUE, Boolean.FALSE);

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
        viewModel.getCalculationFiredEventHandler().handle(new ActionEvent());

        assertTrue(viewModel.getLog().isEmpty());
    }

    @Test
    public void doNotLogSameParametersTwice() {
        setInputData();

        viewModel.getFocusChangeListener().changed(mockFocusChanged,
                Boolean.TRUE, Boolean.FALSE);
        viewModel.getFocusChangeListener().changed(mockFocusChanged,
                Boolean.TRUE, Boolean.FALSE);

        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void doNotLogSameParametersTwiceWithPartialInput() {
        viewModel.re1Property().set("12");

        viewModel.getFocusChangeListener().changed(mockFocusChanged,
                Boolean.TRUE, Boolean.FALSE);

        assertEquals(1, viewModel.getLog().size());
    }

    private void setInputData() {
        viewModel.re1Property().set("1");
        viewModel.im1Property().set("2");
        viewModel.re2Property().set("3");
        viewModel.im2Property().set("4");
    }

    private final ObservableBooleanValue mockFocusChanged = new ObservableBooleanValue() {
        @Override
        public boolean get() {
            return false;
        }

        @Override
        public void addListener(final ChangeListener<? super Boolean> listener) {

        }

        @Override
        public void removeListener(final ChangeListener<? super Boolean> listener) {

        }

        @Override
        public Boolean getValue() {
            return null;
        }

        @Override
        public void addListener(final InvalidationListener listener) {

        }

        @Override
        public void removeListener(final InvalidationListener listener) {

        }
    };

    private final ObservableValue<? extends ViewModel.Operation> mockOperationChanged
            = new ObservableValue<ViewModel.Operation>() {
        @Override
        public void addListener(final ChangeListener<? super ViewModel.Operation> listener) {

        }

        @Override
        public void removeListener(final ChangeListener<? super ViewModel.Operation> listener) {

        }

        @Override
        public ViewModel.Operation getValue() {
            return null;
        }

        @Override
        public void addListener(final InvalidationListener listener) {

        }

        @Override
        public void removeListener(final InvalidationListener listener) {

        }
    };
}
