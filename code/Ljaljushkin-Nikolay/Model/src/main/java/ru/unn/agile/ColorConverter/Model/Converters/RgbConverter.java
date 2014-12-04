package ru.unn.agile.ColorConverter.model.Converters;

import ru.unn.agile.ColorConverter.model.ColorSpaces.Rgb;

public final class RgbConverter {

    private RgbConverter() {
    }

    public static void fromRgbToColorSpace(final Rgb srcColor, final Rgb dstColor) {
        dstColor.setR(srcColor.getFirstChannel());
        dstColor.setG(srcColor.getSecondChannel());
        dstColor.setB(srcColor.getThirdChannel());
    }

    public static Rgb toRgbColor(final Rgb srcColor) {
        return srcColor;
    }
}
