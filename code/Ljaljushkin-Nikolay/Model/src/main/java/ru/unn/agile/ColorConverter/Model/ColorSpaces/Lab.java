package ru.unn.agile.ColorConverter.model.ColorSpaces;

import ru.unn.agile.ColorConverter.model.Converters.LabConverter;

public class Lab extends ColorSpace3D {
    public static final double MAX_L = 100;
    public static final double MAX_A = 128;
    public static final double MAX_B = 128;

    public static final double MIN_L = 0;
    public static final double MIN_A = -128;
    public static final double MIN_B = -128;

    @Override
    public void initialize(final Rgb color) {
        LabConverter.fromRgbToColorSpace(color, this);
    }

    @Override
    public Rgb toRgb() {
        return LabConverter.toRgbColor(this);
    }

    private double l;
    private double a;
    private double b;

    @Override
    public double getFirstChannel() {
        return l;
    }

    @Override
    public double getSecondChannel() {
        return a;
    }

    @Override
    public double getThirdChannel() {
        return b;
    }

    @Override
    public void setFirstChannel(final double l) {
        this.l = l;
    }

    @Override
    public void setSecondChannel(final double a) {
        this.a = a;
    }

    @Override
    public void setThirdChannel(final double b) {
        this.b = b;
    }

    public Lab() {
        super();
    }

    public Lab(final double l, final double a, final double b) {
        super(l, a, b);
        verifyChannels();
    }

    @Override
    public boolean isEqual(final ColorSpace3D comparedColor) {
        boolean isLClose = Utils.isCloseEnough(l, ((Lab) comparedColor).getFirstChannel());
        boolean isAClose = Utils.isCloseEnough(a, ((Lab) comparedColor).getSecondChannel());
        boolean isBClose = Utils.isCloseEnough(b, ((Lab) comparedColor).getThirdChannel());
        return isLClose && isAClose && isBClose;
    }

    @Override
    public void setChannels(final double firstChannel,
                            final double secondChannel,
                            final double thirdChannel) {
        setFirstChannel(firstChannel);
        setSecondChannel(secondChannel);
        setThirdChannel(thirdChannel);
    }

    @Override
    public void verifyFirstChannel(final double l) {
        if (l > MAX_L || l < MIN_L) {
            throw new IllegalArgumentException("L channel(" + l + ") of LAB should be "
                    + MIN_L + " to " + MAX_L);
        }
    }

    @Override
    public void verifySecondChannel(final double a) {
        if (a > MAX_A || a < MIN_A) {
            throw new IllegalArgumentException("A channel(" + a + ") of LAB should be "
                    + MIN_A + " to " + MAX_A);
        }
    }

    @Override
    public void verifyThirdChannel(final double b) {
        if (b > MAX_B || b < MIN_B) {
            throw new IllegalArgumentException("B channel(" + b + ") of LAB should be "
                    + MIN_B + " to " + MAX_B);
        }
    }
}
