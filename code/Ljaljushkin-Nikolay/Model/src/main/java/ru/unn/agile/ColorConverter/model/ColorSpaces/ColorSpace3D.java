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

    protected abstract void setThirdChannel(double thirdChannel);

    protected abstract void setSecondChannel(double secondChannel);

    protected abstract void setFirstChannel(double firstChannel);

    public abstract double getFirstChannel();

    public abstract double getSecondChannel();

    public abstract double getThirdChannel();

    public abstract void verifyFirstChannel(double value);

    public abstract void verifySecondChannel(double value);

    public abstract void verifyThirdChannel(double value);

    public abstract boolean isEqual(final ColorSpace3D comparedColor);

    public void setValues(final double firstChannel,
                          final double secondChannel,
                          final double thirdChannel) {
        setFirstChannel(firstChannel);
        setSecondChannel(secondChannel);
        setThirdChannel(thirdChannel);
    }
}
