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

    public double getL() {
        return l;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public void setL(final double l) {
        verifyFirstChannel(l);
        this.l = l;
    }

    public void setA(final double a) {
        verifySecondChannel(a);
        this.a = a;
    }

    public void setB(final double b) {
        verifyThirdChannel(b);
        this.b = b;
    }

    public Lab() {
        super();
    }

    public Lab(final double l, final double a, final double b) {
        super(l, a, b);
    }

    public boolean isEqual(final Lab comparedColor) {
        boolean isLClose = Utils.isCloseEnough(l, comparedColor.getL());
        boolean isAClose = Utils.isCloseEnough(a, comparedColor.getA());
        boolean isBClose = Utils.isCloseEnough(b, comparedColor.getB());
        return isLClose && isAClose && isBClose;
    }

    @Override
    public void setValues(final double firstChannel,
                          final double secondChannel,
                          final double thirdChannel) {
        setL(firstChannel);
        setA(secondChannel);
        setB(thirdChannel);
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
