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
        HsvConverter.fromRgbToColorSpace(color, this);
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
    public double getSecondChannel() {
        return s;
    }

    @Override
    public double getThirdChannel() {
        return v;
    }

    @Override
    public void setFirstChannel(final double h) {
        verifyFirstChannel(h);
        this.h = h;
    }

    @Override
    public void setSecondChannel(final double s) {
        verifySecondChannel(s);
        this.s = s;
    }

    @Override
    public void setThirdChannel(final double v) {
        verifyThirdChannel(v);
        this.v = v;
    }

    public Hsv() {
        super();
    }

    public Hsv(final double h, final double s, final double v) {
        super(h, s, v);
    }

    @Override
    public boolean isEqual(final ColorSpace3D comparedColor) {
        boolean isHClose = Utils.isCloseEnough(h, ((Hsv) comparedColor).getFirstChannel());
        boolean isSClose = Utils.isCloseEnough(s, ((Hsv) comparedColor).getSecondChannel());
        boolean isVClose = Utils.isCloseEnough(v, ((Hsv) comparedColor).getThirdChannel());
        return isHClose && isSClose && isVClose;
    }

    @Override
    public void setValues(final double firstChannel,
                          final double secondChannel,
                          final double thirdChannel) {
        setFirstChannel(firstChannel);
        setSecondChannel(secondChannel);
        setThirdChannel(thirdChannel);
    }

    @Override
    public void verifyFirstChannel(final double h) {
        if (h > MAX_H || h < MIN_H) {
            throw new IllegalArgumentException("H channel(" + h + ") of HSV should be "
                    + MIN_H + " to " + MAX_H);
        }
    }

    @Override
    public void verifySecondChannel(final double s) {
        if (s > MAX_S || s < MIN_S) {
            throw new IllegalArgumentException("S channel(" + s + ") of HSV should be "
                    + MIN_S + " to " + MAX_S);
        }
    }

    @Override
    public void verifyThirdChannel(final double v) {
        if (v > MAX_V || v < MIN_V) {
            throw new IllegalArgumentException("V channel(" + v + ") of HSV should be "
                    + MIN_V + " to " + MAX_V);
        }
    }
}
