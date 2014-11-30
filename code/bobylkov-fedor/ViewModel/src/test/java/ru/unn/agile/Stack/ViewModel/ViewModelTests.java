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
    public void checkDefaultValues() {
        assertEquals("", viewModel.topProperty().get());
        assertEquals("Push me!", viewModel.pushTextProperty().get());
        assertTrue(viewModel.isEmptyProperty().get());
        assertArrayEquals(new String[] {}, viewModel.stackTableProperty().get().toArray());
    }

    @Test
    public void canPushDefaultValue() {
        viewModel.push();
        assertArrayEquals(new String[] {"Push me!"}, viewModel.stackTableProperty().get().toArray());
    }

    @Test
    public void canPush() {
        viewModel.pushTextProperty().set("something");
        viewModel.push();
        assertArrayEquals(new String[] {"something"}, viewModel.stackTableProperty().get().toArray());
    }

    @Test
    public void canPushManyValues() {
        viewModel.push();
        viewModel.pushTextProperty().set("something");
        viewModel.push();
        assertArrayEquals(new String[] {"Push me!", "something"}, viewModel.stackTableProperty().get().toArray());
    }
}
