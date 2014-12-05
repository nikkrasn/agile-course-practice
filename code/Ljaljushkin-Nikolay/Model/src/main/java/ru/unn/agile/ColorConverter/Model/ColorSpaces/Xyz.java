package ru.unn.agile.ColorConverter.model.ColorSpaces;

import ru.unn.agile.ColorConverter.model.Converters.XyzConverter;

public class Xyz extends ColorSpace3D {

    public static final double MAX_X = 95.05;
    public static final double MAX_Y = 100.0;
    public static final double MAX_Z = 108.9;

    public static final double MIN_X = 0;
    public static final double MIN_Y = 0;
    public static final double MIN_Z = 0;

    @Override
    public void initialize(final Rgb color) {
        XyzConverter.fromRgbToColorSpace(color, this);
    }

    @Override
    public Rgb toRgb() {
        return XyzConverter.toRgbColor(this);
    }

    private double x;
    private double y;
    private double z;

    @Override
    public double getFirstChannel() {
        return x;
    }

    @Override
    public double getSecondChannel() {
        return y;
    }

    @Override
    public double getThirdChannel() {
        return z;
    }

    @Override
    public void setFirstChannel(final double x) {
        verifyFirstChannel(x);
        this.x = x;
    }

    @Override
    public void setSecondChannel(final double y) {
        verifySecondChannel(y);
        this.y = y;
    }

    @Override
    public void setThirdChannel(final double z) {
        verifyThirdChannel(z);
        this.z = z;
    }

    public Xyz() {
        super();
    }

    public Xyz(final double x, final double y, final double z) {
        super(x, y, z);
    }

    @Override
    public boolean isEqual(final ColorSpace3D comparedColor) {
        boolean isXClose = Utils.isCloseEnough(x, ((Xyz) comparedColor).getFirstChannel());
        boolean isYClose = Utils.isCloseEnough(y, ((Xyz) comparedColor).getSecondChannel());
        boolean isZClose = Utils.isCloseEnough(z, ((Xyz) comparedColor).getThirdChannel());
        return isXClose && isYClose && isZClose;
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
    public void verifyFirstChannel(final double x) {
        if ((x > MAX_X || x < MIN_X)
                && !Utils.isCloseEnough(x, MAX_X) && !Utils.isCloseEnough(x, MIN_X)) {
            throw new IllegalArgumentException("X channel(" + x + ") of XYZ should be "
                    + MIN_X + " to " + MAX_X);
        }
    }

    @Override
    public void verifySecondChannel(final double y) {
        if ((y > MAX_Y || y < MIN_Y)
                && !Utils.isCloseEnough(y, MAX_Y) && !Utils.isCloseEnough(y, MIN_Y)) {
            throw new IllegalArgumentException("Y channel(" + y + ") of XYZ should be "
                    + MIN_Y + " to " + MAX_Y);
        }
    }

    @Override
    public void verifyThirdChannel(final double z) {
        if ((z > MAX_Z || z < MIN_Z)
                && !Utils.isCloseEnough(z, MAX_Z) && !Utils.isCloseEnough(z, MIN_Z)) {
            throw new IllegalArgumentException("Z channel(" + z + ") of XYZ should be "
                    + MIN_Z + " to " + MAX_Z);
        }
    }
}




