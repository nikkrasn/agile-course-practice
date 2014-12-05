package ru.unn.agile.ColorConverter.model.Converters;

import ru.unn.agile.ColorConverter.model.ColorSpaces.Rgb;

public final class RgbConverter {

    private RgbConverter() {
    }

    public static void fromRgb(final Rgb srcColor, final Rgb dstColor) {
        dstColor.setFirstChannel(srcColor.getFirstChannel());
        dstColor.setSecondChannel(srcColor.getSecondChannel());
        dstColor.setThirdChannel(srcColor.getThirdChannel());
    }

    public static Rgb toRgbColor(final Rgb srcColor) {
        return srcColor;
    }
}
