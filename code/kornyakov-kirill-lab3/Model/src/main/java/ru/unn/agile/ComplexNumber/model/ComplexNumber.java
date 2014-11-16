package ru.unn.agile.ComplexNumber.model;

public class ComplexNumber {
    private final double re;
    private final double im;

    public ComplexNumber() {
        re = 0;
        im = 0;
    }

    public ComplexNumber(final double re, final double im) {
        this.re = re;
        this.im = im;
    }

    public ComplexNumber(final String re, final String im) {
        this.re = Double.parseDouble(re);
        this.im = Double.parseDouble(im);
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

    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ComplexNumber)) {
            return false;
        }
        return hashCode() == object.hashCode();
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

    public enum Operation {
        ADD("Add") {
            public ComplexNumber apply(final ComplexNumber l, final ComplexNumber r) {
                return l.add(r);
            }
        },
        MULTIPLY("Mul") {
            public ComplexNumber apply(final ComplexNumber l, final ComplexNumber r) {
                return l.multiply(r);
            }
        };

        private final String name;
        Operation(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        public abstract ComplexNumber apply(final ComplexNumber l, final ComplexNumber r);
    }
}
