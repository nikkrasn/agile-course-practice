package ru.unn.agile.ComplexNumber.viewmodel_lab3_legacy;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static ru.unn.agile.ComplexNumber.viewmodel_lab3_legacy.RegexMatcher.matchesPattern;

public class ViewModelTests {
    private ViewModel viewModel;

    public void setViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

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
        assertEquals("", viewModel.getRe1());
        assertEquals("", viewModel.getIm1());
        assertEquals("", viewModel.getRe2());
        assertEquals("", viewModel.getIm2());
        Assert.assertEquals(ViewModel.Operation.ADD, viewModel.getOperation());
        assertEquals("", viewModel.getResult());
        Assert.assertEquals(ViewModel.Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusWaitingInTheBeginning() {
        Assert.assertEquals(ViewModel.Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusWaitingWhenCalculateWithEmptyFields() {
        viewModel.calculate();

        Assert.assertEquals(ViewModel.Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenFieldsAreFill() {
        fillInputFields();

        viewModel.processKeyInTextField(KeyboardKeys.ANY);

        Assert.assertEquals(ViewModel.Status.READY, viewModel.getStatus());
    }

    private void fillInputFields() {
        viewModel.setRe1("1");
        viewModel.setIm1("1");
        viewModel.setRe2("3");
        viewModel.setIm2("3");
    }

    @Test
    public void canReportBadFormat() {
        viewModel.setRe1("a");
        viewModel.processKeyInTextField(KeyboardKeys.ANY);

        Assert.assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void canCleanStatusIfParseIsOK() {
        viewModel.setRe1("a");
        viewModel.processKeyInTextField(KeyboardKeys.ANY);
        viewModel.setRe1("1.0");
        viewModel.processKeyInTextField(KeyboardKeys.ANY);

        Assert.assertEquals(ViewModel.Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isCalculateButtonDisabledInitially() {
        assertEquals(false, viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void isCalculateButtonDisabledWhenFormatIsBad() {
        fillInputFields();
        viewModel.processKeyInTextField(KeyboardKeys.ANY);
        assertEquals(true, viewModel.isCalculateButtonEnabled());

        viewModel.setRe1("trash");
        viewModel.processKeyInTextField(KeyboardKeys.ANY);

        assertEquals(false, viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void isCalculateButtonDisabledWithIncompleteInput() {
        viewModel.setRe1("1");
        viewModel.setIm1("1");

        viewModel.processKeyInTextField(KeyboardKeys.ANY);

        assertEquals(false, viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void canGetOperationName() {
        String addName = ViewModel.Operation.ADD.toString();
        assertEquals("Add", addName);
    }

    @Test
    public void canGetNumberOfOperations() {
        int nOperations = ViewModel.Operation.values().length;
        assertEquals(2, nOperations);
    }

    @Test
    public void canGetListOfOperations() {
        ViewModel.Operation[] operations = ViewModel.Operation.values();
        ViewModel.Operation[] currentOperations = new ViewModel.Operation[]{
                ViewModel.Operation.ADD,
                ViewModel.Operation.MULTIPLY};

        assertArrayEquals(currentOperations, operations);
    }

    @Test
    public void canCompareOperationsByName() {
        Assert.assertEquals(ViewModel.Operation.ADD, ViewModel.Operation.ADD);
        Assert.assertNotEquals(ViewModel.Operation.ADD, ViewModel.Operation.MULTIPLY);
    }

    @Test
    public void isCalculateButtonEnabledWithCorrectInput() {
        fillInputFields();

        viewModel.processKeyInTextField(KeyboardKeys.ANY);

        assertEquals(true, viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void canSetAddOperation() {
        viewModel.setOperation(ViewModel.Operation.ADD);
        Assert.assertEquals(ViewModel.Operation.ADD, viewModel.getOperation());
    }

    @Test
    public void canSetMulOperation() {
        viewModel.setOperation(ViewModel.Operation.MULTIPLY);
        Assert.assertEquals(ViewModel.Operation.MULTIPLY, viewModel.getOperation());
    }

    @Test
    public void isDefaultOperationAdd() {
        Assert.assertEquals(ViewModel.Operation.ADD, viewModel.getOperation());
    }

    @Test
    public void canPerformCalcAction() {
        viewModel.setRe1("1");
        viewModel.setIm1("2");
        viewModel.setRe2("-10");
        viewModel.setIm2("-20");
        viewModel.setOperation(ViewModel.Operation.ADD);

        viewModel.calculate();

        assertEquals("-9.0 - 18.0i", viewModel.getResult());
    }

    @Test
    public void canSetSuccessMessage() {
        fillInputFields();

        viewModel.calculate();

        Assert.assertEquals(ViewModel.Status.SUCCESS, viewModel.getStatus());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.setRe1("a");

        viewModel.calculate();

        Assert.assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenKeyIsNotEnter() {
        fillInputFields();

        viewModel.processKeyInTextField(KeyboardKeys.ANY);

        Assert.assertEquals(ViewModel.Status.READY, viewModel.getStatus());
    }

    @Test
    public void isStatusSuccessWhenKeyIsEnter() {
        fillInputFields();

        viewModel.processKeyInTextField(KeyboardKeys.ENTER);

        Assert.assertEquals(ViewModel.Status.SUCCESS, viewModel.getStatus());
    }

    @Test
    public void canMultiplyNumbers() {
        viewModel.setRe1("1");
        viewModel.setIm1("0");
        viewModel.setRe2("2");
        viewModel.setIm2("0");
        viewModel.setOperation(ViewModel.Operation.MULTIPLY);

        viewModel.calculate();

        assertEquals("2.0 + 0.0i", viewModel.getResult());
    }

    @Test
    public void canPerformAddWithArbitraryNumbers() {
        viewModel.setRe1("1.2");
        viewModel.setIm1("2.3");
        viewModel.setRe2("-10.4");
        viewModel.setIm2("-20.5");
        viewModel.setOperation(ViewModel.Operation.ADD);

        viewModel.calculate();

        assertEquals("-9.2 - 18.2i", viewModel.getResult());
    }

    @Test
    public void canCreateViewModelWithLogger() {
        FakeLogger logger = new FakeLogger();
        ViewModel viewModelLogged = new ViewModel(logger);

        assertNotNull(viewModelLogged);
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
    public void isLogEmptyInTheBeginning() {
        List<String> log = viewModel.getLog();

        assertEquals(0, log.size());
    }

    @Test
    public void isCalculatePuttingSomething() {
        viewModel.calculate();

        List<String> log = viewModel.getLog();
        assertNotEquals(0, log.size());
    }

    @Test
    public void isLogContainsProperMessage() {
        viewModel.calculate();
        String message = viewModel.getLog().get(0);

        assertThat(message,
            matchesPattern(".*" + ViewModel.LogMessages.CALCULATE_WAS_PRESSED + ".*"));
    }

    @Test
    public void isLogContainsInputArguments() {
        fillInputFields();

        viewModel.calculate();

        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*" + viewModel.getRe1()
                + ".*" + viewModel.getIm1()
                + ".*" + viewModel.getRe2()
                + ".*" + viewModel.getIm2() + ".*"
        ));
    }

    @Test
    public void isProperlyFormattingInfoAboutArguments() {
        fillInputFields();

        viewModel.calculate();
        String message = viewModel.getLog().get(0);

        assertThat(message, matchesPattern(".*Arguments"
                + ": Re1 = " + viewModel.getRe1()
                + "; Im1 = " + viewModel.getIm1()
                + "; Re2 = " + viewModel.getRe2()
                + "; Im2 = " + viewModel.getIm2() + ".*"
        ));
    }

    @Test
    public void isOperationMentionedInTheLog() {
        viewModel.setOperation(ViewModel.Operation.ADD);

        viewModel.calculate();

        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*Add.*"));
    }

    @Test
    public void isMulOperationMentionedInTheLog() {
        viewModel.setOperation(ViewModel.Operation.MULTIPLY);

        viewModel.calculate();

        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*Mul.*"));
    }

    @Test
    public void canPutSeveralLogMessages() {
        fillInputFields();

        viewModel.calculate();
        viewModel.calculate();
        viewModel.calculate();

        assertEquals(3, viewModel.getLog().size());
    }

    @Test
    public void canSeeOperationChangeInLog() {
        viewModel.setOperation(ViewModel.Operation.MULTIPLY);

        String message = viewModel.getLog().get(0);
        assertThat(message,
            matchesPattern(".*" + ViewModel.LogMessages.OPERATION_WAS_CHANGED + "Mul.*"));
    }

    @Test
    public void isOperationNotLoggedWhenNotChanged() {
        viewModel.setOperation(ViewModel.Operation.MULTIPLY);
        viewModel.setOperation(ViewModel.Operation.MULTIPLY);

        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void isEditingFinishLogged() {
        viewModel.setRe1("10");

        viewModel.focusLost();

        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*" + ViewModel.LogMessages.EDITING_FINISHED + ".*"));
    }

    @Test
    public void areArgumentsCorrectlyLoggedOnEditingFinish() {
        fillInputFields();
        viewModel.focusLost();

        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*" + ViewModel.LogMessages.EDITING_FINISHED
                + "Input arguments are: \\["
                + viewModel.getRe1() + "; "
                + viewModel.getIm1() + "; "
                + viewModel.getRe2() + "; "
                + viewModel.getIm2() + "\\]"));
    }

    @Test
    public void isLogInputsCalledOnEnter() {
        fillInputFields();

        viewModel.processKeyInTextField(KeyboardKeys.ENTER);

        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*" + ViewModel.LogMessages.EDITING_FINISHED + ".*"));
    }

    @Test
    public void isCalculateNotCalledWhenButtonIsDisabled() {
        viewModel.processKeyInTextField(KeyboardKeys.ENTER);

        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*" + ViewModel.LogMessages.EDITING_FINISHED + ".*"));
        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void doNotLogSameParametersTwice() {
        fillInputFields();
        fillInputFields();

        viewModel.focusLost();
        viewModel.focusLost();

        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*" + ViewModel.LogMessages.EDITING_FINISHED + ".*"));
        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void doNotLogSameParametersTwiceWithPartialInput() {
        viewModel.setRe1("12");
        viewModel.setRe1("12");
        viewModel.setRe1("12");

        viewModel.focusLost();
        viewModel.focusLost();
        viewModel.focusLost();

        assertEquals(1, viewModel.getLog().size());
    }
}
