package ru.unn.agile.ComplexNumber.core;

public class ComplexNumber {
    private double re;
    private double im;

    public ComplexNumber(final double real, final double imaginary) {
        this.re = real;
        this.im = imaginary;
    }

    @Override
    public int hashCode() {
        final int shift = 32;

        long temp = Double.doubleToLongBits(re);
        int result = (int) (temp ^ (temp >>> shift));
        temp = Double.doubleToLongBits(im);
        result = (shift - 1) * result + (int) (temp ^ (temp >>> shift));
        return result;
    }

    @Override
    public boolean equals(final Object object) {
        ComplexNumber number = (ComplexNumber) object;
        return number.getReal() == getReal()
                && number.getImaginary() == getImaginary();
    }

    public ComplexNumber add(final ComplexNumber other) {
        return new ComplexNumber(other.getReal() + getReal(),
                other.getImaginary() + getImaginary());
    }

    public ComplexNumber multiply(final ComplexNumber other) {
        return new ComplexNumber(
                other.getReal() * getReal() - other.getImaginary() * getImaginary(),
                other.getReal() * getImaginary() + other.getImaginary() * getReal());
    }

    public void setReal(final double real) {
        this.re = real;
    }

    public double getReal() {
        return re;
    }

    public void setImaginary(final double imaginary) {
        this.im = imaginary;
    }

    public double getImaginary() {
        return im;
    }

    public String toString() {
        return Formatter.getFormatted(this);
    }
}
