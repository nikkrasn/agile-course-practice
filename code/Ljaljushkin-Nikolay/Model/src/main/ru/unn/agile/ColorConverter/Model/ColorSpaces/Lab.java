package ru.unn.agile.ColorConverter.Model.ColorSpaces;

import ru.unn.agile.ColorConverter.Model.Converters.LabConverter;

public class Lab extends ColorSpace {

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
        this.l = l;
    }

    public void setA(final double a) {
        this.a = a;
    }

    public void setB(final double b) {
        this.b = b;
    }

    Lab() {
        l = 0;
        a = 0;
        b = 0;
    }

    public Lab(final double l, final double a, final double b) {
        this.l = l;
        this.a = a;
        this.b = b;
    }

}
