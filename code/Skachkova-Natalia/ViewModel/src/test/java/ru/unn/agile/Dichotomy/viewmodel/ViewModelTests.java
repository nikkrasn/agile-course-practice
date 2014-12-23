package ru.unn.agile.Dichotomy.viewmodel;

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
        viewModel = new ViewModel(new FakeLogger());
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
        setCorrectArrayAndFindElement();
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
        setCorrectArrayAndFindElement();
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

    @Test(expected = IllegalArgumentException.class)
    public void viewModelConstructorWithNullLog() {
        new ViewModel(null);
    }

    @Test
    public void logIsEmptyInitially() {
        List<String> log = viewModel.getLog();
        assertTrue(log.isEmpty());
    }

    @Test
    public void logContainsProperMessageAfterApply() {
        viewModel.stringArrayProperty().set("1 2 3");
        viewModel.parseString();
        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + MessagesInLog.APPLY_WAS_PRESSED
                + viewModel.stringArrayProperty().get() + ".*"));
    }

    @Test
    public void logContainsProperMessageAfterSearch() {
        setCorrectArrayAndFindElement();
        String message = viewModel.getLog().get(1);
        assertTrue(message.matches(".*" + MessagesInLog.SEARCH_WAS_PRESSED
                + viewModel.stringElementProperty().get() + MessagesInLog.IN_ARRAY
                + viewModel.stringArrayProperty().get() + ".*"));
    }

    @Test
     public void logContainsProperMessageAfterSearchWhenArrayContainElement() {
        setCorrectArrayAndFindElement();
        String message = viewModel.getLog().get(1);
        assertTrue(message.matches(".*" + ResultStatus.CONTAIN + ".*"));
    }

    @Test
    public void logContainsProperMessageAfterSearchWhenArrayNotContainElement() {
        viewModel.stringArrayProperty().set("1 2 3");
        viewModel.parseString();
        viewModel.stringElementProperty().set("0");
        viewModel.findElement();
        String message = viewModel.getLog().get(1);
        assertTrue(message.matches(".*" + ResultStatus.NOT_CONTAIN + ".*"));
    }

    @Test
    public void logContainsProperMessageAfterNewArray() {
        viewModel.stringArrayProperty().set("1 2 3");
        viewModel.parseString();
        viewModel.stringElementProperty().set("0");
        viewModel.findElement();
        viewModel.enterNewArray();
        String message = viewModel.getLog().get(2);
        assertTrue(message.matches(".*" + MessagesInLog.NEW_ARRAY_WAS_PRESSED + ".*"));
    }

    @Test
    public void canPutSeveralLogMessages() {
        viewModel.stringArrayProperty().set("1 2 3");
        viewModel.parseString();
        viewModel.stringElementProperty().set("0");
        viewModel.findElement();
        viewModel.findElement();
        viewModel.findElement();
        assertEquals(4, viewModel.getLog().size());
    }

    @Test
    public void applyIsNotLoggedIfArrayIsEntered() {
        viewModel.stringArrayProperty().set("1 2 3");
        viewModel.parseString();
        viewModel.parseString();
        viewModel.parseString();
        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void arrayAreCorrectlyLogged() {
        viewModel.stringArrayProperty().set("1 2 3");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);
        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + MessagesInLog.ARRAY_EDITING_FINISHED + ".*"));
    }

    @Test
    public void elementAreCorrectlyLogged() {
        viewModel.stringElementProperty().set("1");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);
        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + MessagesInLog.ELEMENT_EDITING_FINISHED + ".*"));
    }

    private void setCorrectArrayAndFindElement() {
        viewModel.stringArrayProperty().set("1 2 3");
        viewModel.parseString();
        viewModel.stringElementProperty().set("1");
        viewModel.findElement();
    }
}
