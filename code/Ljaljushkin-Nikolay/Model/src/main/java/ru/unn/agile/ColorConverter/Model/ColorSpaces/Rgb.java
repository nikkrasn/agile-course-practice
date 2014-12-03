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

    public double getR() {
        return r;
    }

    public double getG() {
        return g;
    }

    public double getB() {
        return b;
    }

    public void setR(final double r) {
        verifyFirstChannel(r);
        this.r = r;
    }

    public void setG(final double g) {
        verifySecondChannel(g);
        this.g = g;
    }

    public void setB(final double b) {
        verifyThirdChannel(b);
        this.b = b;
    }

    public Rgb() {
        super();
    }

    public Rgb(final double r, final double g, final double b) {
        super(r, g, b);
    }

    public boolean isEqual(final Rgb comparedColor) {
        boolean isRClose = Utils.isCloseEnough(r, comparedColor.getR());
        boolean isGClose = Utils.isCloseEnough(g, comparedColor.getG());
        boolean isBClose = Utils.isCloseEnough(b, comparedColor.getB());
        return isRClose && isGClose && isBClose;
    }

    @Override
    public void setValues(final double firstChannel,
                          final double secondChannel,
                          final double thirdChannel) {
        setR(firstChannel);
        setG(secondChannel);
        setB(thirdChannel);
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
