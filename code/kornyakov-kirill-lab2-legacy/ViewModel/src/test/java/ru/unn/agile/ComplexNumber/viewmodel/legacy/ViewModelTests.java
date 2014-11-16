package ru.unn.agile.ComplexNumber.viewmodel.legacy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.ComplexNumber.viewmodel.legacy.ViewModel.Operation;
import ru.unn.agile.ComplexNumber.viewmodel.legacy.ViewModel.Status;

import static org.junit.Assert.*;

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
        assertEquals("", viewModel.getRe1());
        assertEquals("", viewModel.getIm1());
        assertEquals("", viewModel.getRe2());
        assertEquals("", viewModel.getIm2());
        assertEquals(Operation.ADD, viewModel.getOperation());
        assertEquals("", viewModel.getResult());
        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusWaitingInTheBeginning() {
        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusWaitingWhenCalculateWithEmptyFields() {
        viewModel.calculate();

        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenFieldsAreFill() {
        fillInputFields();

        viewModel.processKeyInTextField(KeyboardKeys.ANY);

        assertEquals(Status.READY, viewModel.getStatus());
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

        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void canCleanStatusIfParseIsOK() {
        viewModel.setRe1("a");
        viewModel.processKeyInTextField(KeyboardKeys.ANY);
        viewModel.setRe1("1.0");
        viewModel.processKeyInTextField(KeyboardKeys.ANY);

        assertEquals(Status.WAITING, viewModel.getStatus());
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
        String addName = Operation.ADD.toString();
        assertEquals("Add", addName);
    }

    @Test
    public void canGetNumberOfOperations() {
        int nOperations = Operation.values().length;
        assertEquals(2, nOperations);
    }

    @Test
    public void canGetListOfOperations() {
        ViewModel.Operation[] operations = ViewModel.Operation.values();
        ViewModel.Operation[] currentOperations = new ViewModel.Operation[]{
                Operation.ADD,
                Operation.MULTIPLY};

        assertArrayEquals(currentOperations, operations);
    }

    @Test
    public void canCompareOperationsByName() {
        assertEquals(Operation.ADD, Operation.ADD);
        assertNotEquals(Operation.ADD, Operation.MULTIPLY);
    }

    @Test
    public void isCalculateButtonEnabledWithCorrectInput() {
        fillInputFields();

        viewModel.processKeyInTextField(KeyboardKeys.ANY);

        assertEquals(true, viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void canSetAddOperation() {
        viewModel.setOperation(Operation.ADD);
        assertEquals(Operation.ADD, viewModel.getOperation());
    }

    @Test
    public void canSetMulOperation() {
        viewModel.setOperation(Operation.MULTIPLY);
        assertEquals(Operation.MULTIPLY, viewModel.getOperation());
    }

    @Test
    public void isDefaultOperationAdd() {
        assertEquals(Operation.ADD, viewModel.getOperation());
    }

    @Test
    public void canPerformCalcAction() {
        viewModel.setRe1("1");
        viewModel.setIm1("2");
        viewModel.setRe2("-10");
        viewModel.setIm2("-20");
        viewModel.setOperation(Operation.ADD);

        viewModel.calculate();

        assertEquals("-9.0 - 18.0i", viewModel.getResult());
    }

    @Test
    public void canSetSuccessMessage() {
        fillInputFields();

        viewModel.calculate();

        assertEquals(Status.SUCCESS, viewModel.getStatus());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.setRe1("a");

        viewModel.calculate();

        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenKeyIsNotEnter() {
        fillInputFields();

        viewModel.processKeyInTextField(KeyboardKeys.ANY);

        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void isStatusSuccessWhenKeyIsEnter() {
        fillInputFields();

        viewModel.processKeyInTextField(KeyboardKeys.ENTER);

        assertEquals(Status.SUCCESS, viewModel.getStatus());
    }

    @Test
    public void canMultiplyNumbers() {
        viewModel.setRe1("1");
        viewModel.setIm1("0");
        viewModel.setRe2("2");
        viewModel.setIm2("0");
        viewModel.setOperation(Operation.MULTIPLY);

        viewModel.calculate();

        assertEquals("2.0 + 0.0i", viewModel.getResult());
    }

    @Test
    public void canPerformAddWithArbitraryNumbers() {
        viewModel.setRe1("1.2");
        viewModel.setIm1("2.3");
        viewModel.setRe2("-10.4");
        viewModel.setIm2("-20.5");
        viewModel.setOperation(Operation.ADD);

        viewModel.calculate();

        assertEquals("-9.2 - 18.2i", viewModel.getResult());
    }
}
