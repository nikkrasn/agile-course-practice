package ru.unn.agile.ColorConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        assertEquals("", viewModel.getFirstChannelSrcColor());
        assertEquals("", viewModel.getSecondChannelSrcColor());
        assertEquals("", viewModel.getThirdChannelSrcColor());

        assertEquals("", viewModel.getFirstChannelDstColor());
        assertEquals("", viewModel.getSecondChannelDstColor());
        assertEquals("", viewModel.getThirdChannelDstColor());

        assertEquals(Color.RGB.toString(), viewModel.getSrcColor());
        assertEquals(Color.LAB.toString(), viewModel.getDstColor());
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void isStatusWaitingWhenConvertWithEmptyFields() {
        viewModel.convert();
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenFieldsAreFill() {
        fillInputFields();
        assertEquals(Status.READY.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsWaitingWhenNotAllFieldsAreFill() {
        fillNotAllFields();
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void canReportBadFormat() {
        fillFieldInBadFormat();
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

//    @Test
//    public void canReportOutOfRangeForRgb() {
//        fillFieldOutsideAcceptableRangesForRgb();
//        assertEquals(Status.OUT_OF_RANGE.toString(), viewModel.getStatus());
//    }

    private void fillFieldOutsideAcceptableRangesForRgb() {
        viewModel.setFirstChannelSrcColor("-1");
        viewModel.setSecondChannelSrcColor("0");
        viewModel.setThirdChannelSrcColor("256");
    }

    private void fillInputFields() {
        viewModel.setFirstChannelSrcColor("0");
        viewModel.setSecondChannelSrcColor("0");
        viewModel.setThirdChannelSrcColor("0");
        viewModel.setFirstChannelDstColor("0");
        viewModel.setSecondChannelDstColor("0");
        viewModel.setThirdChannelDstColor("0");
    }

    private void fillNotAllFields() {
        viewModel.setFirstChannelSrcColor("0");
    }

    private void fillFieldInBadFormat() {
        viewModel.setFirstChannelSrcColor("0trash$");
    }
}
