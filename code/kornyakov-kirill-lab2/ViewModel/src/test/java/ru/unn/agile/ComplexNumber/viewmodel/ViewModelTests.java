package ru.unn.agile.ComplexNumber.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ViewModelTests {
    private ViewModel viewModel;

    public void setViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUp() {
        //FakeLogger logger = new FakeLogger();
        //viewModel = new ViewModel(logger);
        viewModel = new ViewModel(new ArrayLogger());
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
        //assertEquals(ViewModel.Operation.ADD, viewModel.getOperation());
        //assertEquals("", viewModel.getResult());
        //assertEquals(Status.WAITING, viewModel.getStatus());
    }

    private class ArrayLogger implements ILogger {
        private final List<String> log = new ArrayList<>();
        @Override
        public void log(final String s) {
            log.add(s);
        }
        @Override
        public final List<String> getLog() {
            return log;
        }
    }
}
