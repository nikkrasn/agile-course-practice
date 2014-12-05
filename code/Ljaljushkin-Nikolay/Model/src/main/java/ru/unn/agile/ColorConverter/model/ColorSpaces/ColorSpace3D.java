package ru.unn.agile.ColorConverter.model.ColorSpaces;

public abstract class ColorSpace3D extends ColorSpace {
    ColorSpace3D() {
        setChannels(0, 0, 0);
    }

    ColorSpace3D(final double firstChannel,
                 final double secondChannel,
                 final double thirdChannel) {
        setChannels(firstChannel, secondChannel, thirdChannel);
    }

    protected abstract void setThirdChannel(double thirdChannel);

    protected abstract void setSecondChannel(double secondChannel);

    protected abstract void setFirstChannel(double firstChannel);

    protected abstract void verifyFirstChannel(double value);

    protected abstract void verifySecondChannel(double value);

    protected abstract void verifyThirdChannel(double value);

    public abstract double getFirstChannel();

    public abstract double getSecondChannel();

    public abstract double getThirdChannel();

    public abstract boolean isEqual(final ColorSpace3D comparedColor);

    public void setChannels(final double firstChannel,
                            final double secondChannel,
                            final double thirdChannel) {
        setFirstChannel(firstChannel);
        setSecondChannel(secondChannel);
        setThirdChannel(thirdChannel);
    }

    public void verifyChannels() {
        verifyFirstChannel(getFirstChannel());
        verifySecondChannel(getSecondChannel());
        verifyThirdChannel(getThirdChannel());
    }
}
