package ru.unn.agile.Dichotomy.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
//import ru.unn.agile.Dichotomy.Model.Dichotomy.Operation;

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
        assertEquals("", viewModel.stringArrayProperty().get());
        assertEquals("", viewModel.stringElementProperty().get());
    }

    @Test
    public void statusIsWaitingWhenInputArrayEmpty() {
        assertEquals(InputStatus.WAITING.toString(), viewModel.dichotomyStatusProperty().get());
    }

    @Test
    public void statusIsBadFormatWhenInputArrayIncorrect() {
        viewModel.stringArrayProperty().set("a b c");
        assertEquals(InputStatus.BAD_FORMAT_ARRAY.toString(),
                viewModel.dichotomyStatusProperty().get());
    }

    @Test
    public void statusIsApplyWhenInputArrayCorrect() {
        viewModel.stringArrayProperty().set("1 2 3");
        assertEquals(InputStatus.APPLY.toString(), viewModel.dichotomyStatusProperty().get());
    }

    @Test
    public void statusIsUnsortedWhenArrayUnsorted() {
        viewModel.stringArrayProperty().set("3 1 2");
        viewModel.parseString();
        assertEquals(InputStatus.UNSORTED.toString(), viewModel.dichotomyStatusProperty().get());
    }

    @Test
    public void statusIsWaitingElementWhenArraySorted() {
        viewModel.stringArrayProperty().set("1 2 3");
        viewModel.parseString();
        assertEquals(InputStatus.WAITING_ELEMENT.toString(),
                viewModel.dichotomyStatusProperty().get());
    }

    @Test
    public void statusIsBadFormatWhenInputElementIncorrect() {
        viewModel.stringArrayProperty().set("1 2 3");
        viewModel.parseString();
        viewModel.stringElementProperty().set("a");
        assertEquals(InputStatus.BAD_FORMAT_ELEMENT.toString(),
                viewModel.dichotomyStatusProperty().get());
    }

    @Test
    public void statusIsReadyWhenInputElementCorrect() {
        viewModel.stringArrayProperty().set("1 2 3");
        viewModel.parseString();
        viewModel.stringElementProperty().set("1");
        assertEquals(InputStatus.READY.toString(), viewModel.dichotomyStatusProperty().get());
    }

    @Test
    public void statusIsSuccessWhenPressSearch() {
        viewModel.stringArrayProperty().set("1 2 3");
        viewModel.parseString();
        viewModel.stringElementProperty().set("1");
        viewModel.findElement();
        assertEquals(InputStatus.SUCCESS.toString(), viewModel.dichotomyStatusProperty().get());
    }

    @Test
    public void resultIsEmptyBeforePressSearch() {
        assertEquals("", viewModel.dichotomyResultProperty().get());
        viewModel.stringArrayProperty().set("1 2 3");
        assertEquals("", viewModel.dichotomyResultProperty().get());
        viewModel.parseString();
        assertEquals("", viewModel.dichotomyResultProperty().get());
        viewModel.stringElementProperty().set("1");
        assertEquals("", viewModel.dichotomyResultProperty().get());
    }


    @Test
    public void resultIsContainWhenArrayContainElement() {
        viewModel.stringArrayProperty().set("1 2 3");
        viewModel.parseString();
        viewModel.stringElementProperty().set("1");
        viewModel.findElement();
        assertEquals(ResultStatus.CONTAIN.toString(), viewModel.dichotomyResultProperty().get());
    }

    @Test
    public void resultIsNotContainWhenArrayNotContainElement() {
        viewModel.stringArrayProperty().set("1 2 3");
        viewModel.parseString();
        viewModel.stringElementProperty().set("0");
        viewModel.findElement();
        assertEquals(ResultStatus.NOT_CONTAIN.toString(),
                viewModel.dichotomyResultProperty().get());
    }

    @Test
    public void arrayTextFieldIsEnabledInitially() {
        assertFalse(viewModel.inputArrayDisabledProperty().get());
    }

    @Test
    public void applyButtonIsDisabledInitially() {
        assertTrue(viewModel.applyDisabledProperty().get());
    }

    @Test
    public void elementTextFieldIsDisabledInitially() {
        assertTrue(viewModel.inputElementDisabledProperty().get());
    }

    @Test
    public void searchButtonIsDisabledInitially() {
        assertTrue(viewModel.searchDisabledProperty().get());
    }

    @Test
    public void arrayTextFieldIsDisabledAfterApply() {
        viewModel.stringArrayProperty().set("1 2 3");
        viewModel.parseString();
        assertTrue(viewModel.inputArrayDisabledProperty().get());
    }

    @Test
    public void applyButtonIsDisabledAfterEnterIncorrectArray() {
        viewModel.stringArrayProperty().set("a b c");
        assertTrue(viewModel.applyDisabledProperty().get());
    }

    @Test
    public void applyButtonIsEnabledAfterEnterCorrectArray() {
        viewModel.stringArrayProperty().set("1 2 3");
        assertFalse(viewModel.applyDisabledProperty().get());
    }

    @Test
    public void elementTextFieldIsDisabledWhenArrayUnsorted() {
        viewModel.stringArrayProperty().set("3 1 2");
        viewModel.parseString();
        assertTrue(viewModel.inputElementDisabledProperty().get());
    }

    @Test
    public void elementTextFieldIsEnabledWhenArraySorted() {
        viewModel.stringArrayProperty().set("1 2 3");
        viewModel.parseString();
        assertFalse(viewModel.inputElementDisabledProperty().get());
    }

    @Test
    public void searchButtonIsDisabledAfterEnterIncorrectElement() {
        viewModel.stringArrayProperty().set("1 2 3");
        viewModel.parseString();
        viewModel.stringElementProperty().set("a");
        assertTrue(viewModel.searchDisabledProperty().get());
    }

    @Test
    public void searchButtonIsEnabledAfterEnterCorrectElement() {
        viewModel.stringArrayProperty().set("1 2 3");
        viewModel.parseString();
        viewModel.stringElementProperty().set("1");
        assertFalse(viewModel.searchDisabledProperty().get());
    }
}
