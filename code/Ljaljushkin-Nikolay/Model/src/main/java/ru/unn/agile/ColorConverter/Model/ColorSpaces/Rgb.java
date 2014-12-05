package ru.unn.agile.ColorConverter.model.ColorSpaces;

import ru.unn.agile.ColorConverter.model.Converters.RgbConverter;

public class Rgb extends ColorSpace3D {

    public static final double MAX_R = 255;
    public static final double MAX_G = 255;
    public static final double MAX_B = 255;

    public static final double MIN_R = 0;
    public static final double MIN_G = 0;
    public static final double MIN_B = 0;

    private double r;
    private double g;
    private double b;

    @Override
    public double getFirstChannel() {
        return r;
    }

    @Override
    public double getFirstChannelMax() {
        return MAX_R;
    }

    @Override
    public double getFirstChannelMin() {
        return MIN_R;
    }

    @Override
    public double getSecondChannel() {
        return g;
    }

    @Override
    public double getSecondChannelMax() {
        return MAX_G;
    }

    @Override
    public double getSecondChannelMin() {
        return MIN_G;
    }

    @Override
    public double getThirdChannel() {
        return b;
    }

    @Override
    public double getThirdChannelMax() {
        return MAX_B;
    }

    @Override
    public double getThirdChannelMin() {
        return MIN_B;
    }

    @Override
    public void setFirstChannel(final double r) {
        this.r = r;
    }

    @Override
    public void setSecondChannel(final double g) {
        this.g = g;
    }

    @Override
    public void setThirdChannel(final double b) {
        this.b = b;
    }

    public Rgb() {
        super(new RgbConverter());
    }

    public Rgb(final double r, final double g, final double b) {
        super(new RgbConverter(), r, g, b);
        verifyChannels();
    }
}
