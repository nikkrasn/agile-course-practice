package ru.unn.agile.ColorConverter.Model.ColorSpaces;

import ru.unn.agile.ColorConverter.Model.Converters.HsvConverter;

public class Hsv extends ColorSpace {

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

    public double getH() {
        return h;
    }

    public double getS() {
        return s;
    }

    public double getV() {
        return v;
    }

    public void setH(final double h) {
        this.h = h;
    }

    public void setS(final double s) {
        this.s = s;
    }

    public void setV(final double v) {
        this.v = v;
    }

    public Hsv() {
        h = 0;
        s = 0;
        v = 0;
    }

    public Hsv(final double h, final double s, final double v) {
        this.h = h;
        this.s = s;
        this.v = v;
    }

    public boolean isEqual(final Hsv comparedColor) {
        boolean isHClose = Utils.isCloseEnough(h, comparedColor.getH());
        boolean isSClose = Utils.isCloseEnough(s, comparedColor.getS());
        boolean isVClose = Utils.isCloseEnough(v, comparedColor.getV());
        return isHClose && isSClose && isVClose;
    }
}
