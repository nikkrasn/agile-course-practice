package ru.unn.agile.ColorConverter.viewmodel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ViewModelTests {
    private ViewModel viewModel;

    @Test
    public void canSetDefaultValues() {
        viewModel = new ViewModel();

        assertEquals("", viewModel.firstChannelSrcColorProperty().get());
        assertEquals("", viewModel.secondChannelSrcColorProperty().get());
        assertEquals("", viewModel.thirdChannelSrcColorProperty().get());

        assertEquals("", viewModel.firstChannelDstColorProperty().get());
        assertEquals("", viewModel.secondChannelDstColorProperty().get());
        assertEquals("", viewModel.thirdChannelDstColorProperty().get());

        assertEquals(Color.RGB, viewModel.srcColorProperty().get());
        assertEquals(Color.RGB, viewModel.dstColorProperty().get());
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }
}
