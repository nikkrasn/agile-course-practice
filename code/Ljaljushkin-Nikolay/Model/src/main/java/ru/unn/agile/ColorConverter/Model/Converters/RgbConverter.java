package ru.unn.agile.ColorConverter.model.Converters;

import ru.unn.agile.ColorConverter.model.ColorSpaces.ColorSpace3D;
import ru.unn.agile.ColorConverter.model.ColorSpaces.Rgb;

public final class RgbConverter extends BaseConverter {

    @Override
    public void fromRgb(final Rgb srcColor, final ColorSpace3D dstColor) {
        dstColor.setFirstChannel(srcColor.getFirstChannel());
        dstColor.setSecondChannel(srcColor.getSecondChannel());
        dstColor.setThirdChannel(srcColor.getThirdChannel());
    }

    @Override
    public Rgb toRgbColor(final ColorSpace3D srcColor) {
        return (Rgb) srcColor;
    }
}
