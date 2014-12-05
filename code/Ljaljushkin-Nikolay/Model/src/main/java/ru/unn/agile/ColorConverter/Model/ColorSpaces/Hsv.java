package ru.unn.agile.ColorConverter.model.ColorSpaces;

import ru.unn.agile.ColorConverter.model.Converters.HsvConverter;

public class Hsv extends ColorSpace3D {
    public static final double MAX_H = 360;
    public static final double MAX_S = 1;
    public static final double MAX_V = 1;

    public static final double MIN_H = 0;
    public static final double MIN_S = 0;
    public static final double MIN_V = 0;

    @Override
    public void initialize(final Rgb color) {
        HsvConverter.fromRgb(color, this);
    }

    @Override
    public Rgb toRgb() {
        return HsvConverter.toRgbColor(this);
    }

    private double h;
    private double s;
    private double v;

    @Override
    public double getFirstChannel() {
        return h;
    }

    @Override
    public double getFirstChannelMax() {
        return MAX_H;
    }

    @Override
    public double getFirstChannelMin() {
        return MIN_H;
    }

    @Override
    public double getSecondChannel() {
        return s;
    }

    @Override
    public double getSecondChannelMax() {
        return MAX_S;
    }

    @Override
    public double getSecondChannelMin() {
        return MIN_S;
    }

    @Override
    public double getThirdChannel() {
        return v;
    }

    @Override
    public double getThirdChannelMax() {
        return MAX_V;
    }

    @Override
    public double getThirdChannelMin() {
        return MIN_V;
    }

    @Override
    public void setFirstChannel(final double h) {
        this.h = h;
    }

    @Override
    public void setSecondChannel(final double s) {
        this.s = s;
    }

    @Override
    public void setThirdChannel(final double v) {
        this.v = v;
    }

    public Hsv() {
        super();
    }

    public Hsv(final double h, final double s, final double v) {
        super(h, s, v);
        verifyChannels();
    }
}
