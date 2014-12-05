package ru.unn.agile.ColorConverter.model.ColorSpaces;

import ru.unn.agile.ColorConverter.model.Converters.LabConverter;

public class Lab extends ColorSpace3D {
    public static final double MAX_L = 100;
    public static final double MAX_A = 128;
    public static final double MAX_B = 128;

    public static final double MIN_L = 0;
    public static final double MIN_A = -128;
    public static final double MIN_B = -128;

    private double l;
    private double a;
    private double b;

    @Override
    public double getFirstChannel() {
        return l;
    }

    @Override
    public double getFirstChannelMax() {
        return MAX_L;
    }

    @Override
    public double getFirstChannelMin() {
        return MIN_L;
    }

    @Override
    public double getSecondChannel() {
        return a;
    }

    @Override
    public double getSecondChannelMax() {
        return MAX_A;
    }

    @Override
    public double getSecondChannelMin() {
        return MIN_A;
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
        super(new LabConverter());
    }

    public Lab(final double l, final double a, final double b) {
        super(new LabConverter(), l, a, b);
        verifyChannels();
    }
}
