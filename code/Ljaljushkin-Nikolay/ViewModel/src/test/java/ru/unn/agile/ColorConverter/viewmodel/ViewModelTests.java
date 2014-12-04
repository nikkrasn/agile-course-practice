package ru.unn.agile.ColorConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.ColorConverter.model.ColorSpaces.ColorSpace3D;
import ru.unn.agile.ColorConverter.model.TestUtilities.KnownColors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        assertEquals("", viewModel.getFirstChannelSrcColorString());
        assertEquals("", viewModel.getSecondChannelSrcColorString());
        assertEquals("", viewModel.getThirdChannelSrcColorString());

        assertEquals("", viewModel.getFirstChannelDstColorString());
        assertEquals("", viewModel.getSecondChannelDstColorString());
        assertEquals("", viewModel.getThirdChannelDstColorString());

        assertEquals(Color.RGB, viewModel.getSrcColor());
        assertEquals(Color.LAB, viewModel.getDstColor());
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void isStatusWaitingWhenConvertWithEmptyFields() {
        viewModel.convert();
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenFieldsAreFillCorrectly() {
        fillInputFieldsCorrectly();
        assertEquals(Status.READY.toString(), viewModel.getStatus());
    }

    @Test
    public void isStatusWaitingWhenNotAllFieldsAreFill() {
        fillNotAllFields();
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void canReportBadFormat() {
        fillFieldInBadFormat();
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void canReportBadFormatIfThereIsNumberWithComma() {
        fillFieldFloatingPointNumberWithComma();
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void isStatusReadyIfThereIsNumberWithDot() {
        fillFieldFloatingPointNumberWithDot();
        assertEquals(Status.READY.toString(), viewModel.getStatus());
    }
    @Test
    public void canChooseRgbAsSrcColor() {
        viewModel.setSrcColor(Color.RGB);
        assertEquals(Color.RGB, viewModel.getSrcColor());
    }

    @Test
    public void canChooseHsvAsSrcColor() {
        viewModel.setSrcColor(Color.HSV);
        assertEquals(Color.HSV, viewModel.getSrcColor());
    }

    @Test
    public void canChooseLabAsSrcColor() {
        viewModel.setSrcColor(Color.LAB);
        assertEquals(Color.LAB, viewModel.getSrcColor());
    }

    @Test
    public void canChooseRgbAsDstColor() {
        viewModel.setDstColor(Color.RGB);
        assertEquals(Color.RGB, viewModel.getDstColor());
    }

    @Test
    public void canChooseHsvAsDstColor() {
        viewModel.setDstColor(Color.HSV);
        assertEquals(Color.HSV, viewModel.getDstColor());
    }

    @Test
    public void canChooseLabAsDstColor() {
        viewModel.setDstColor(Color.LAB);
        assertEquals(Color.LAB, viewModel.getDstColor());
    }

    @Test
    public void canReportOutOfRangeForRgb() {
        setValueOutsideAcceptableRangesForRgb();
        assertEquals(Status.OUT_OF_RANGE.toString(), viewModel.getStatus());
    }

    @Test
    public void canReportOutOfRangeForHsv() {
        viewModel.setSrcColor(Color.HSV);
        setValueOutsideAcceptableRangesForHsv();
        assertEquals(Status.OUT_OF_RANGE.toString(), viewModel.getStatus());
    }

    @Test
    public void canReportOutOfRangeForLab() {
        viewModel.setSrcColor(Color.LAB);
        setValueOutsideAcceptableRangesForLab();
        assertEquals(Status.OUT_OF_RANGE.toString(), viewModel.getStatus());
    }

    @Test
    public void canRecheckValuesAfterChangingSrcColor() {
        setValuesOutsideAcceptableRangesForRgbAndOkForHsv();
        viewModel.setSrcColor(Color.HSV);
        assertEquals(Status.READY.toString(), viewModel.getStatus());
    }

    @Test
    public void convertButtonIsDisabledInitially() {
        assertTrue(viewModel.isCalculationDisabled());
    }

    @Test
    public void convertButtonIsDisabledWhenFormatIsBad() {
        fillFieldInBadFormat();
        assertTrue(viewModel.isCalculationDisabled());
    }

    @Test
    public void convertButtonIsDisabledWithIncompleteInput() {
        fillNotAllFields();
        assertTrue(viewModel.isCalculationDisabled());
    }

    @Test
    public void convertButtonIsDisabledWithOutOfRangeInput() {
        setValueOutsideAcceptableRangesForRgb();
        assertTrue(viewModel.isCalculationDisabled());
    }

    @Test
    public void convertButtonIsEnabledWithCorrectInput() {
        fillInputFieldsCorrectly();
        assertFalse(viewModel.isCalculationDisabled());
    }

    @Test
    public void convertingWhiteColorForDefaultColorSpacesHasCorrectResult() {
        setWhiteRgbSrcColor();
        viewModel.convert();
        ColorSpace3D dstColor = viewModel.getDstColorValue();
        assertTrue(dstColor.isEqual(KnownColors.WHITE_LAB));
    }

    @Test
    public void convertingDarkRedColorForDefaultColorSpacesHasCorrectResult() {
        setDarkRedRgbSrcColor();
        viewModel.convert();
        ColorSpace3D dstColor = viewModel.getDstColorValue();
        assertTrue(dstColor.isEqual(KnownColors.DARK_RED_LAB));
    }

    @Test
    public void convertingWhiteColorForRgbAndHsvHasCorrectResult() {
        setWhiteRgbSrcColor();
        viewModel.setDstColor(Color.HSV);
        viewModel.convert();
        ColorSpace3D dstColor = viewModel.getDstColorValue();
        assertTrue(dstColor.isEqual(KnownColors.WHITE_HSV));
    }

    @Test
    public void convertingDarkRedColorForRgbAndHsvHasCorrectResult() {
        setDarkRedRgbSrcColor();
        viewModel.setDstColor(Color.HSV);
        viewModel.convert();
        ColorSpace3D dstColor = viewModel.getDstColorValue();
        assertTrue(dstColor.isEqual(KnownColors.DARK_RED_HSV));
    }

    @Test
    public void convertingWhiteColorForLabAndHsvHasCorrectResult() {
        setWhiteLabSrcColor();
        viewModel.setSrcColor(Color.LAB);
        viewModel.setDstColor(Color.HSV);
        viewModel.convert();
        ColorSpace3D dstColor = viewModel.getDstColorValue();
        assertTrue(dstColor.isEqual(KnownColors.WHITE_HSV));
    }

    @Test
    public void convertingDarkRedColorForLabAndHsvHasCorrectResult() {
        setDarkRedLabSrcColor();
        viewModel.setSrcColor(Color.LAB);
        viewModel.setDstColor(Color.HSV);
        viewModel.convert();
        ColorSpace3D dstColor = viewModel.getDstColorValue();
        assertTrue(dstColor.isEqual(KnownColors.DARK_RED_HSV));
    }

    private void fillInputFieldsCorrectly() {
        viewModel.setFirstChannelSrcColorString("0");
        viewModel.setSecondChannelSrcColorString("0");
        viewModel.setThirdChannelSrcColorString("0");
    }

    private void setValueOutsideAcceptableRangesForRgb() {
        fillInputFieldsCorrectly();
        viewModel.setFirstChannelSrcColorString("-1");
    }

    private void setValuesOutsideAcceptableRangesForRgbAndOkForHsv() {
        fillInputFieldsCorrectly();
        viewModel.setFirstChannelSrcColorString("360");
    }

    private void setValueOutsideAcceptableRangesForLab() {
        fillInputFieldsCorrectly();
        viewModel.setSecondChannelSrcColorString("-129");
    }

    private void setValueOutsideAcceptableRangesForHsv() {
        fillInputFieldsCorrectly();
        viewModel.setThirdChannelSrcColorString("2");
    }

    private void fillNotAllFields() {
        viewModel.setFirstChannelSrcColorString("0");
    }

    private void fillFieldInBadFormat() {
        viewModel.setFirstChannelSrcColorString("0trash$");
    }

    private void fillFieldFloatingPointNumberWithComma() {
        fillInputFieldsCorrectly();
        viewModel.setFirstChannelSrcColorString("0,01");
    }

    private void fillFieldFloatingPointNumberWithDot() {
        fillInputFieldsCorrectly();
        viewModel.setFirstChannelSrcColorString("0.01");
    }

    private void setWhiteRgbSrcColor() {
        viewModel.setFirstChannelSrcColorString("255");
        viewModel.setSecondChannelSrcColorString("255");
        viewModel.setThirdChannelSrcColorString("255");
    }

    private void setWhiteLabSrcColor() {
        viewModel.setFirstChannelSrcColorString("100");
        viewModel.setSecondChannelSrcColorString("0.01");
        viewModel.setThirdChannelSrcColorString("-0.01");
    }

    private void setDarkRedRgbSrcColor() {
        viewModel.setFirstChannelSrcColorString("139");
        viewModel.setSecondChannelSrcColorString("0");
        viewModel.setThirdChannelSrcColorString("0");
    }

    private void setDarkRedLabSrcColor() {
        viewModel.setFirstChannelSrcColorString("28.0847");
        viewModel.setSecondChannelSrcColorString("51.0104");
        viewModel.setThirdChannelSrcColorString("41.2945");
    }
}
