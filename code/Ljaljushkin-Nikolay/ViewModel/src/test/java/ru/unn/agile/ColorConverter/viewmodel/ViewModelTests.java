package ru.unn.agile.ColorConverter.viewmodel;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ViewModelTests {
    private ViewModel viewModel;

    @Test
    public void canSetDefaultValues() {
        viewModel = new ViewModel();
        assertEquals("0", viewModel.dstLabLProperty().get());
        assertEquals("0", viewModel.dstLabAProperty().get());
        assertEquals("0", viewModel.dstLabBProperty().get());

        assertEquals("0", viewModel.dstHsvHProperty().get());
        assertEquals("0", viewModel.dstHsvSProperty().get());
        assertEquals("0", viewModel.dstHsvVProperty().get());

        assertEquals("0", viewModel.dstRgbBProperty().get());
        assertEquals("0", viewModel.dstRgbBProperty().get());
        assertEquals("0", viewModel.dstRgbBProperty().get());

        assertEquals("0", viewModel.srcFirstChannelProperty().get());
        assertEquals("0", viewModel.srcFirstChannelProperty().get());
        assertEquals("0", viewModel.srcFirstChannelProperty().get());
        assertEquals(SourceColor.HSV, viewModel.sourceColorProperty().get());
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }
}
