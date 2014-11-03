package ru.unn.agile.ColorConverter.Model.ColorSpaces;

import ru.unn.agile.ColorConverter.Model.Converters.RgbConverter;

public class Rgb extends ColorSpace {

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

    public double getG() {
        return g;
    }

    public double getR() {
        return r;
    }

    public double getB() {
        return b;
    }

    public void setR(final double r) {
        this.r = r;
    }

    public void setG(final double g) {
        this.g = g;
    }

    public void setB(final double b) {
        this.b = b;
    }

    Rgb() {
        r = 0;
        g = 0;
        b = 0;
    }

    public Rgb(final double r, final double g, final double b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public boolean isEqual(final Rgb comparedColor) {
        boolean isRClose = Utils.isCloseEnough(r, comparedColor.getR());
        boolean isGClose = Utils.isCloseEnough(g, comparedColor.getG());
        boolean isBClose = Utils.isCloseEnough(b, comparedColor.getB());
        return isRClose && isGClose && isBClose;
    }
}
