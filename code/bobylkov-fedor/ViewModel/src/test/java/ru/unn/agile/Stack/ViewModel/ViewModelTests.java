package ru.unn.agile.Stack.ViewModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewModelTests {
    private final ViewModel viewModel = new ViewModel();

    public void setLogger(final ILogger logger) {
        viewModel.setLogger(logger);
    }

    @Before
    public void setUp() {
        viewModel.setLogger(new FakeLogger());
    }

    @Test
    public void checkDefaultPushValue() {
        assertEquals("Push me!", viewModel.getTextToPush());
    }

    @Test
    public void isTopEmptyStringInitially() {
        assertEquals("", viewModel.getTop());
    }

    @Test
    public void isStackTableEmptyInitially() {
        assertArrayEquals(new String[] {}, viewModel.getStackTable().toArray());
    }

    @Test
    public void isPopButtonDisabledInitially() {
        assertTrue(viewModel.isPopButtonDisabled());
    }

    @Test
    public void canPushDefaultValue() {
        viewModel.push();
        assertArrayEquals(new String[] {"Push me!"},
                viewModel.getStackTable().toArray());
    }

    @Test
    public void isPopButtonEnabledInNotEmptyStack() {
        viewModel.push();
        assertFalse(viewModel.isPopButtonDisabled());
    }

    @Test
    public void isTopCorrectAfterPush() {
        viewModel.push();
        assertEquals("Push me!", viewModel.getTop());
    }

    @Test
    public void canPushSomeValue() {
        viewModel.setTextToPush("something");
        viewModel.push();
        assertArrayEquals(new String[] {"something"},
                viewModel.getStackTable().toArray());
    }

    @Test
    public void canPushManyValues() {
        viewModel.push();
        viewModel.setTextToPush("something");
        viewModel.push();
        assertArrayEquals(new String[] {"Push me!", "something"},
                viewModel.getStackTable().toArray());
    }

    @Test
    public void isTopCorrectAfterManyPushes() {
        viewModel.push();
        viewModel.setTextToPush("something");
        viewModel.push();

        assertEquals("something", viewModel.getTop());
    }

    @Test
    public void canPop() {
        viewModel.push();
        viewModel.pop();
        assertArrayEquals(new String[] {}, viewModel.getStackTable().toArray());
    }

    @Test
    public void canDoManyPops() {
        viewModel.push();
        viewModel.setTextToPush("something");
        viewModel.push();

        viewModel.pop();
        assertArrayEquals(new String[] {"Push me!"}, viewModel.getStackTable().toArray());
        viewModel.pop();
        assertArrayEquals(new String[] {}, viewModel.getStackTable().toArray());
    }

    @Test
    public void isPopButtonDisabledAfterManyPops() {
        viewModel.push();
        viewModel.setTextToPush("something");
        viewModel.push();

        viewModel.pop();
        viewModel.pop();
        assertTrue(viewModel.isPopButtonDisabled());
    }

    @Test
    public void isLogEmptyInitially() {
        assertTrue(viewModel.getLog().isEmpty());
    }

    @Test
    public void doesLogContainTextToPushChangedMessage() {
        viewModel.setTextToPush("new value");
        assertEquals("Text-To-Push changed to: new value", getLogMessageByIndex(0).getMessage());
    }

    @Test
    public void doesLogContainMessagesAfterPush() {
        viewModel.push();
        assertEquals("Pushed: Push me!", getLogMessageByIndex(0).getMessage());
        assertEquals("Top changed to: Push me!", getLogMessageByIndex(1).getMessage());
        assertEquals("Stack size changed to: 1", getLogMessageByIndex(2).getMessage());
    }

    @Test
    public void doesLogContainMessagesAfterPop() {
        viewModel.push();
        viewModel.pop();

        assertEquals("Popped: Push me!", getLogMessageByIndex(3).getMessage());
        assertEquals("Top changed to: ", getLogMessageByIndex(4).getMessage());
        assertEquals("Stack size changed to: 0", getLogMessageByIndex(5).getMessage());
    }

    @Test
    public void doesLogContainMessagesAfterManyPushes() {
        doesLogContainMessagesAfterPush();
        viewModel.push();
        assertEquals("Pushed: Push me!", getLogMessageByIndex(3).getMessage());
        assertEquals("Stack size changed to: 2", getLogMessageByIndex(4).getMessage());
    }

    @Test
    public void doesLogContainMessagesAfterManyPops() {
        viewModel.push();
        viewModel.push();

        viewModel.pop();
        viewModel.pop();

        assertEquals("Popped: Push me!", getLogMessageByIndex(5).getMessage());
        assertEquals("Stack size changed to: 1", getLogMessageByIndex(6).getMessage());

        assertEquals("Popped: Push me!", getLogMessageByIndex(7).getMessage());
        assertEquals("Top changed to: ", getLogMessageByIndex(8).getMessage());
        assertEquals("Stack size changed to: 0", getLogMessageByIndex(9).getMessage());
    }

    private LogMessage getLogMessageByIndex(final Integer index) {
        return viewModel.getLog().get(index);
    }
}
