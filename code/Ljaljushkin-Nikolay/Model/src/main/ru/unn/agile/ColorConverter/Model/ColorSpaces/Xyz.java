package ru.unn.agile.ColorConverter.Model.ColorSpaces;

import ru.unn.agile.ColorConverter.Model.Converters.XyzConverter;

public class Xyz extends ColorSpace {

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

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setX(final double x) {
        this.x = x;
    }

    public void setY(final double y) {
        this.y = y;
    }

    public void setZ(final double z) {
        this.z = z;
    }

    public Xyz() {
        x = 0;
        y = 0;
        z = 0;
    }

    public Xyz(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

}




