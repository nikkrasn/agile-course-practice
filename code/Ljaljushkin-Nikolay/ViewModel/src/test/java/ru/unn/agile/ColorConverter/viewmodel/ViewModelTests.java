package ru.unn.agile.ColorConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.ColorConverter.model.ColorSpaces.ColorSpace3D;
import ru.unn.agile.ColorConverter.model.TestUtilities.*;

import java.util.List;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    public void setExternalViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUp() {
        viewModel = new ViewModel(new MockLogger());
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
        assertEquals(AppStatus.WAITING.toString(), viewModel.getAppStatus());
    }

    @Test
    public void isAppStatusWaitingWhenConvertWithEmptyFields() {
        viewModel.convert();
        assertEquals(AppStatus.WAITING.toString(), viewModel.getAppStatus());
    }

    @Test
    public void isAppStatusReadyWhenFieldsAreFillCorrectly() {
        fillInputFieldsCorrectly();
        assertEquals(AppStatus.READY.toString(), viewModel.getAppStatus());
    }

    @Test
    public void isAppStatusWaitingWhenNotAllFieldsAreFill() {
        fillNotAllFields();
        assertEquals(AppStatus.WAITING.toString(), viewModel.getAppStatus());
    }

    @Test
    public void canReportBadFormat() {
        fillFieldInBadFormat();
        assertEquals(AppStatus.BAD_FORMAT.toString(), viewModel.getAppStatus());
    }

    @Test
    public void canReportBadFormatIfThereIsNumberWithComma() {
        fillFieldFloatingPointNumberWithComma();
        assertEquals(AppStatus.BAD_FORMAT.toString(), viewModel.getAppStatus());
    }

    @Test
    public void isAppStatusReadyIfThereIsNumberWithDot() {
        fillFieldFloatingPointNumberWithDot();
        assertEquals(AppStatus.READY.toString(), viewModel.getAppStatus());
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
        assertEquals(AppStatus.OUT_OF_RANGE.toString(), viewModel.getAppStatus());
    }

    @Test
    public void canReportOutOfRangeForHsv() {
        viewModel.setSrcColor(Color.HSV);
        setValueOutsideAcceptableRangesForHsv();
        assertEquals(AppStatus.OUT_OF_RANGE.toString(), viewModel.getAppStatus());
    }

    @Test
    public void canReportOutOfRangeForLab() {
        viewModel.setSrcColor(Color.LAB);
        setValueOutsideAcceptableRangesForLab();
        assertEquals(AppStatus.OUT_OF_RANGE.toString(), viewModel.getAppStatus());
    }

    @Test
    public void canRecheckAppStatusAfterChangingSrcColor() {
        setValuesOutsideAcceptableRangesForRgbAndOkForHsv();
        viewModel.setSrcColor(Color.HSV);
        assertEquals(AppStatus.READY.toString(), viewModel.getAppStatus());
    }

    @Test
    public void convertButtonIsDisabledInitially() {
        assertTrue(viewModel.getConversionDisabled());
    }

    @Test
    public void convertButtonIsDisabledWhenFormatIsBad() {
        fillFieldInBadFormat();
        assertTrue(viewModel.getConversionDisabled());
    }

    @Test
    public void convertButtonIsDisabledWithIncompleteInput() {
        fillNotAllFields();
        assertTrue(viewModel.getConversionDisabled());
    }

    @Test
    public void convertButtonIsDisabledWithOutOfRangeInput() {
        setValueOutsideAcceptableRangesForRgb();
        assertTrue(viewModel.getConversionDisabled());
    }

    @Test
    public void convertButtonIsEnabledWithCorrectInput() {
        fillInputFieldsCorrectly();
        assertFalse(viewModel.getConversionDisabled());
    }

    @Test
    public void canResetButtonDisabilityAfterChangingSrcColor() {
        viewModel.setSrcColor(Color.HSV);
        setValuesOutsideAcceptableRangesForRgbAndOkForHsv();
        viewModel.setSrcColor(Color.RGB);
        assertTrue(viewModel.getConversionDisabled());
    }

    @Test
    public void conversionWhiteColorForDefaultColorSpacesHasCorrectResult() {
        setWhiteRgbSrcColor();
        viewModel.convert();
        ColorSpace3D dstColor = viewModel.getDstColorValue();
        assertTrue(dstColor.equals(KnownColors.WHITE_LAB));
    }

    @Test
    public void conversionDarkRedColorForDefaultColorSpacesHasCorrectResult() {
        setDarkRedRgbSrcColor();
        viewModel.convert();
        ColorSpace3D dstColor = viewModel.getDstColorValue();
        assertTrue(dstColor.equals(KnownColors.DARK_RED_LAB));
    }

    @Test
    public void conversionWhiteColorForRgbAndHsvHasCorrectResult() {
        setWhiteRgbSrcColor();
        viewModel.setDstColor(Color.HSV);
        viewModel.convert();
        ColorSpace3D dstColor = viewModel.getDstColorValue();
        assertTrue(dstColor.equals(KnownColors.WHITE_HSV));
    }

    @Test
    public void conversionDarkRedColorForRgbAndHsvHasCorrectResult() {
        setDarkRedRgbSrcColor();
        viewModel.setDstColor(Color.HSV);
        viewModel.convert();
        ColorSpace3D dstColor = viewModel.getDstColorValue();
        assertTrue(dstColor.equals(KnownColors.DARK_RED_HSV));
    }

    @Test
    public void conversionWhiteColorForLabAndHsvHasCorrectResult() {
        setWhiteLabSrcColor();
        viewModel.setSrcColor(Color.LAB);
        viewModel.setDstColor(Color.HSV);
        viewModel.convert();
        ColorSpace3D dstColor = viewModel.getDstColorValue();
        assertTrue(dstColor.equals(KnownColors.WHITE_HSV));
    }

    @Test
    public void conversionDarkRedColorForLabAndHsvHasCorrectResult() {
        setDarkRedLabSrcColor();
        viewModel.setSrcColor(Color.LAB);
        viewModel.setDstColor(Color.HSV);
        viewModel.convert();
        ColorSpace3D dstColor = viewModel.getDstColorValue();
        assertTrue(dstColor.equals(KnownColors.DARK_RED_HSV));
    }

    @Test
    public void canCreateViewModelWithNullLogger() {
        ViewModel viewModelWithNullLogger = new ViewModel(null);
        List<String> log = viewModelWithNullLogger.getLog();
        assertTrue(log.isEmpty());
    }

    @Test
    public void isLogEmptyAfterCreatingViewModelWithMockLogger() {
        List<String> log = viewModel.getLog();
        assertTrue(log.isEmpty());
    }

    @Test
    public void logContainsProperMessageAfterConversion() {
        fillInputFieldsCorrectly();
        viewModel.convert();
        String message = getLogMessageByNumber(0);
        assertTrue(message.matches(".*" + LogEvents.CONVERT_WAS_PRESSED + ".*"));
    }

    @Test
    public void logContainsProperMessageAfterChangingSrcColor() {
        viewModel.setSrcColor(Color.HSV);
        String message = getLogMessageByNumber(0);
        assertTrue(message.matches(".*" + LogEvents.SRC_COLOR_WAS_CHANGED + "from RGB to HSV.*"));
    }

    @Test
    public void logContainsProperMessageAfterChangingDstColor() {
        viewModel.setDstColor(Color.HSV);
        String message = getLogMessageByNumber(0);
        assertTrue(message.matches(".*" + LogEvents.DST_COLOR_WAS_CHANGED + "from LAB to HSV.*"));
    }

    @Test
    public void logContainsColorChannelsAfterConversion() {
        fillInputFieldsCorrectly();
        viewModel.convert();

        String message = getLogMessageByNumber(0);
        assertTrue(message.matches(".*Src.*" + viewModel.getSrcColor().toString()
                + ".*Dst.*" + viewModel.getDstColor().toString() + ".*"));
    }

    @Test
    public void logContainsColorValuesAfterConversion() {
        fillInputFieldsCorrectly();
        viewModel.convert();

        String message = getLogMessageByNumber(0);
        assertTrue(message.matches(".*"
                + viewModel.getFirstChannelSrcColorString() + ".*"
                + viewModel.getSecondChannelSrcColorString() + ".*"
                + viewModel.getThirdChannelSrcColorString() + ".*"
                + viewModel.getFirstChannelDstColorString() + ".*"
                + viewModel.getSecondChannelDstColorString() + ".*"
                + viewModel.getThirdChannelDstColorString() + ".*"));
    }

    @Test
    public void canPutSeveralLogMessages() {
        fillInputFieldsCorrectly();

        viewModel.convert();
        viewModel.setDstColor(Color.HSV);
        viewModel.setSrcColor(Color.HSV);
        viewModel.convert();

        assertEquals(4, getLogSize());
    }

    @Test
    public void srcColorIsNotLoggedIfNotChanged() {
        viewModel.setSrcColor(Color.RGB);
        viewModel.setSrcColor(Color.HSV);
        assertEquals(1, getLogSize());
    }

    @Test
    public void dstColorIsNotLoggedIfNotChanged() {
        viewModel.setDstColor(Color.LAB);
        viewModel.setDstColor(Color.HSV);
        assertEquals(1, getLogSize());
    }

    @Test
    public void areValuesOfSourceColorCorrectlyLoggedOn() {
        fillInputFieldsCorrectly();
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);

        String message = getLogMessageByNumber(0);
        assertTrue(message.matches(".*" + LogEvents.EDITING_SRC_COLOR_FINISHED + "\\[ "
                + viewModel.getFirstChannelSrcColorString() + " , "
                + viewModel.getSecondChannelSrcColorString() + " , "
                + viewModel.getThirdChannelSrcColorString() + " \\]"));
    }

    @Test
    public void isConversionNotRunWhenConversionButtonIsDisabled() {
        viewModel.convert();
        List<String> log = viewModel.getLog();
        assertTrue(log.isEmpty());
    }

    @Test
    public void doNotLogSameParametersTwiceWithPartialInput() {
        viewModel.setFirstChannelSrcColorString("255");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);
        viewModel.setFirstChannelSrcColorString("255");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);
        assertEquals(1, getLogSize());
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
        viewModel.setSecondChannelSrcColorString("0");
        viewModel.setThirdChannelSrcColorString("0");
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

    private String getLogMessageByNumber(final Integer number) {
        if (getLogSize() == 0) {
            return "";
        }
        return viewModel.getLog().get(number);
    }

    private int getLogSize() {
        return viewModel.getLog().size();
    }
}
