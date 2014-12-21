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
        assertEquals("Text-To-Push changed to: new value", viewModel.getLog().get(0).getMessage());
    }

    @Test
    public void doesLogContainPushedMessage() {
        viewModel.push();
        assertEquals("Pushed: Push me!", viewModel.getLog().get(1).getMessage());
    }

    @Test
    public void doesLogContainTopChangedMessageAfterPush() {
        viewModel.push();
        assertEquals("Top changed to: Push me!", viewModel.getLog().get(0).getMessage());
    }

    @Test
    public void doesLogContainPoppedMessage() {
        viewModel.push();
        viewModel.pop();
        assertEquals("Popped: Push me!", viewModel.getLog().get(3).getMessage());
    }

    @Test
    public void doesLogContainTopChangedMessageAfterPop() {
        viewModel.push();
        viewModel.pop();
        assertEquals("Top changed to: ", viewModel.getLog().get(2).getMessage());
    }
}
