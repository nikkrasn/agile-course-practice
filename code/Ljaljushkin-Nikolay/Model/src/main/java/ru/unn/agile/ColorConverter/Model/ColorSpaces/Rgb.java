package ru.unn.agile.ColorConverter.model.ColorSpaces;

import ru.unn.agile.ColorConverter.model.Converters.RgbConverter;

public class Rgb extends ColorSpace3D {

    public static final double MAX_R = 255;
    public static final double MAX_G = 255;
    public static final double MAX_B = 255;

    public static final double MIN_R = 0;
    public static final double MIN_G = 0;
    public static final double MIN_B = 0;

    @Override
    public void initialize(final Rgb color) {
        RgbConverter.fromRgbToColorSpace(color, this);
    }

    @Override
    public Rgb toRgb() {
        return RgbConverter.toRgbColor(this);
    }

    private double r;
    private double g;
    private double b;

    @Override
    public double getFirstChannel() {
        return r;
    }

    @Override
    public double getSecondChannel() {
        return g;
    }

    @Override
    public double getThirdChannel() {
        return b;
    }

    @Override
    public void setFirstChannel(final double r) {
        verifyFirstChannel(r);
        this.r = r;
    }

    @Override
    public void setSecondChannel(final double g) {
        verifySecondChannel(g);
        this.g = g;
    }

    @Override
    public void setThirdChannel(final double b) {
        verifyThirdChannel(b);
        this.b = b;
    }

    public Rgb() {
        super();
    }

    public Rgb(final double r, final double g, final double b) {
        super(r, g, b);
    }

    @Override
    public boolean isEqual(final ColorSpace3D comparedColor) {
        boolean isRClose = Utils.isCloseEnough(r, ((Rgb) comparedColor).getFirstChannel());
        boolean isGClose = Utils.isCloseEnough(g, ((Rgb) comparedColor).getSecondChannel());
        boolean isBClose = Utils.isCloseEnough(b, ((Rgb) comparedColor).getThirdChannel());
        return isRClose && isGClose && isBClose;
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
    public void verifyFirstChannel(final double r) {
        if (r > MAX_R || r < MIN_R) {
            throw new IllegalArgumentException("R channel(" + r + ") of RGB should be "
                    + MIN_R + " to " + MAX_R);
        }
    }

    @Override
    public void verifySecondChannel(final double g) {
        if (g > MAX_G || g < MIN_G) {
            throw new IllegalArgumentException("G channel(" + g + ") of RGB should be "
                    + MIN_G + " to " + MAX_G);
        }
    }

    @Override
    public void verifyThirdChannel(final double b) {
        if (b > MAX_B || b < MIN_B) {
            throw new IllegalArgumentException("B channel(" + b + ") of RGB should be "
                    + MIN_B + " to " + MAX_B);
        }
    }
}
