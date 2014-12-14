package ru.unn.agile.ComplexNumber.model_lab3_legacy;

public class ComplexNumber {
    private final double re;
    private final double im;

    public ComplexNumber(final double real, final double imaginary) {
        this.re = real;
        this.im = imaginary;
    }

    public ComplexNumber(final String re, final String im) {
        this.re = Double.parseDouble(re);
        this.im = Double.parseDouble(im);
    }

    public ComplexNumber() {
        this.re = 0;
        this.im = 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;

        final int shift = 32;

        temp = Double.doubleToLongBits(re);
        result = (int) (temp ^ (temp >>> shift));
        temp = Double.doubleToLongBits(im);
        result = (shift - 1) * result + (int) (temp ^ (temp >>> shift));
        return result;
    }

    public boolean equals(final Object object) {
        ComplexNumber number;
        number = (ComplexNumber) object;
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

    public double getReal() {
        return re;
    }

    public double getImaginary() {
        return im;
    }

    public String toString() {
        return Formatter.getFormatted(this);
    }
}
