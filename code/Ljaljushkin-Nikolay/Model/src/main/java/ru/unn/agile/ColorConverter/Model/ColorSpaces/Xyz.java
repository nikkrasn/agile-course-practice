package ru.unn.agile.ColorConverter.model.ColorSpaces;

import ru.unn.agile.ColorConverter.model.Converters.XyzConverter;

public class Xyz extends ColorSpace3D {

    public static final double MAX_X = 95.05;
    public static final double MAX_Y = 100.0;
    public static final double MAX_Z = 108.9;

    public static final double MIN_X = 0;
    public static final double MIN_Y = 0;
    public static final double MIN_Z = 0;

    private double x;
    private double y;
    private double z;

    @Override
    public double getFirstChannel() {
        return x;
    }

    @Override
    public double getFirstChannelMax() {
        return MAX_X;
    }

    @Override
    public double getFirstChannelMin() {
        return MIN_X;
    }

    @Override
    public double getSecondChannel() {
        return y;
    }

    @Override
    public double getSecondChannelMax() {
        return MAX_Y;
    }

    @Override
    public double getSecondChannelMin() {
        return MIN_Y;
    }

    @Override
    public double getThirdChannel() {
        return z;
    }

    @Override
    public double getThirdChannelMax() {
        return MAX_Z;
    }

    @Override
    public double getThirdChannelMin() {
        return MIN_Z;
    }

    @Override
    public void setFirstChannel(final double x) {
        this.x = x;
    }

    @Override
    public void setSecondChannel(final double y) {
        this.y = y;
    }

    @Override
    public void setThirdChannel(final double z) {
        this.z = z;
    }

    public Xyz() {
        super(new XyzConverter());
    }

    public Xyz(final double x, final double y, final double z) {
        super(new XyzConverter(), x, y, z);
        verifyChannels();
    }
}




