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
        assertEquals("Push me!", viewModel.pushTextProperty().get());
    }

    @Test
    public void isTopEmptyStringInitially() {
        assertEquals("", viewModel.topProperty().get());
    }

    @Test
    public void isStackTableEmptyInitially() {
        assertArrayEquals(new String[] {}, viewModel.stackTableProperty().get().toArray());
    }

    @Test
    public void isPopButtonDisabledInitially() {
        assertTrue(viewModel.isPopButtonDisabled());
    }

    @Test
    public void canPushDefaultValue() {
        viewModel.push();
        assertArrayEquals(new String[] {"Push me!"},
                viewModel.stackTableProperty().get().toArray());
    }

    @Test
    public void isPopButtonEnabledInNotEmptyStack() {
        viewModel.push();
        assertFalse(viewModel.isPopButtonDisabled());
    }

    @Test
    public void isTopCorrectAfterPush() {
        viewModel.push();
        assertEquals("Push me!", viewModel.topProperty().get());
    }

    @Test
    public void canPushSomeValue() {
        viewModel.pushTextProperty().set("something");
        viewModel.push();
        assertArrayEquals(new String[] {"something"},
                viewModel.stackTableProperty().get().toArray());
    }

    @Test
    public void canPushManyValues() {
        viewModel.push();
        viewModel.pushTextProperty().set("something");
        viewModel.push();
        assertArrayEquals(new String[] {"Push me!", "something"},
                viewModel.stackTableProperty().get().toArray());
    }
}
