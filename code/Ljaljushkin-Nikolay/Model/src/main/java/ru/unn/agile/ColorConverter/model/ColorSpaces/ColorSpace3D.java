package ru.unn.agile.ColorConverter.model.ColorSpaces;

public abstract class ColorSpace3D extends ColorSpace {
    ColorSpace3D() {
        setValues(0, 0, 0);
    }

    ColorSpace3D(final double firstChannel,
                 final double secondChannel,
                 final double thirdChannel) {
        setValues(firstChannel, secondChannel, thirdChannel);
    }

    public abstract void setValues(final double firstChannel,
                                   final double secondChannel,
                                   final double thirdChannel);

    public abstract double getFirstChannel();

    public abstract double getSecondChannel();

    public abstract double getThirdChannel();

    public abstract void verifyFirstChannel(double value);

    public abstract void verifySecondChannel(double value);

    public abstract void verifyThirdChannel(double value);

    public abstract boolean isEqual(final ColorSpace3D comparedColor);
}
