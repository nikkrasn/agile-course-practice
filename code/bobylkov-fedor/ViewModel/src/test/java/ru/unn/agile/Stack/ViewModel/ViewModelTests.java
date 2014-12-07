package ru.unn.agile.Stack.ViewModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
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
}
