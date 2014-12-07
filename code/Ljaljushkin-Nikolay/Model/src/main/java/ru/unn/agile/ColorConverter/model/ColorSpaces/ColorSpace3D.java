package ru.unn.agile.ColorConverter.model.ColorSpaces;

import ru.unn.agile.ColorConverter.model.Converters.BaseConverter;

public abstract class ColorSpace3D extends ColorSpace {

    private final BaseConverter converter;

    ColorSpace3D(final BaseConverter converter) {
        this.converter = converter;
        setChannels(0, 0, 0);
    }

    ColorSpace3D(final BaseConverter converter,
                 final double firstChannel,
                 final double secondChannel,
                 final double thirdChannel) {
        this.converter = converter;
        setChannels(firstChannel, secondChannel, thirdChannel);
    }

    @Override
    public void initialize(final Rgb color) {
        converter.fromRgb(color, this);
    }

    @Override
    public Rgb toRgb() {
        return converter.toRgbColor(this);
    }

    public abstract void setFirstChannel(final double firstChannel);

    public abstract void setSecondChannel(final double secondChannel);

    public abstract void setThirdChannel(final double thirdChannel);

    public void verifyFirstChannel(final double value) {
        if (Utils.isValueInRange(value, getFirstChannelMin(), getFirstChannelMax())) {
            throw new IllegalArgumentException("First channel(" + value + ") should be "
                    + getFirstChannelMin() + " to " + getFirstChannelMax());
        }
    }

    public void verifySecondChannel(final double value) {
        if (Utils.isValueInRange(value, getSecondChannelMin(), getSecondChannelMax())) {
            throw new IllegalArgumentException("Second channel(" + value + ") should be "
                    + getSecondChannelMin() + " to " + getSecondChannelMax());
        }
    }

    public void verifyThirdChannel(final double value) {
        if (Utils.isValueInRange(value, getThirdChannelMin(), getThirdChannelMax())) {
            throw new IllegalArgumentException("Third channel(" + value + ") should be "
                    + getThirdChannelMin() + " to " + getThirdChannelMax());
        }
    }

    public abstract double getFirstChannel();

    public abstract double getFirstChannelMax();

    public abstract double getFirstChannelMin();

    public abstract double getSecondChannel();

    public abstract double getSecondChannelMax();

    public abstract double getSecondChannelMin();

    public abstract double getThirdChannel();

    public abstract double getThirdChannelMax();

    public abstract double getThirdChannelMin();

    public Boolean equals(final ColorSpace3D comparedColor) {
        boolean isFirstChannelsClose = Utils.isCloseEnough(
                getFirstChannel(),
                comparedColor.getFirstChannel());
        boolean isSecondChannelsClose = Utils.isCloseEnough(
                getSecondChannel(),
                comparedColor.getSecondChannel());
        boolean isThirdChannelsClose = Utils.isCloseEnough(
                getThirdChannel(),
                comparedColor.getThirdChannel());
        return isFirstChannelsClose && isSecondChannelsClose && isThirdChannelsClose;
    }

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

    public BaseConverter getConverter() {
        return converter;
    }
}
