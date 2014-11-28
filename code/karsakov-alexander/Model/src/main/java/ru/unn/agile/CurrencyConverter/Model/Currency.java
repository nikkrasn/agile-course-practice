package ru.unn.agile.CurrencyConverter.Model;

// Store currency in format like in cbr.ru: http://www.cbr.ru/scripts/XML_daily.asp
public final class Currency {
    private final int numCode;
    private final String charCode;
    private final String name;
    private final int nominal;
    private final double value;

    public Currency(final Builder b) {
        if (b.numCode <= 0) {
            throw new IllegalArgumentException(
                    "'numCode' must be specified using Currency.Builder.numCode()");
        }
        if (b.charCode == null) {
            throw new IllegalArgumentException(
                    "'charCode' must be specified using Currency.Builder.charCode()");
        }
        if (b.name == null) {
            throw new IllegalArgumentException(
                    "'name' must be specified using Currency.Builder.name()");
        }
        if (b.nominal <= 0) {
            throw new IllegalArgumentException(
                    "'nominal' must be specified using Currency.Builder.nominal()");
        }
        if (b.value <= 0) {
            throw new IllegalArgumentException(
                    "'value' must be specified using Currency.Builder.value()");
        }

        numCode = b.numCode;
        charCode = b.charCode;
        name = b.name;
        nominal = b.nominal;
        value = b.value;
    }

    public int getNumCode() {
        return numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public String getName() {
        return name;
    }

    public int getNominal() {
        return nominal;
    }

    public double getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        final int oddPrime = 31;
        int result;
        result = numCode;
        result = oddPrime * result + charCode.hashCode();
        result = oddPrime * result + name.hashCode();
        result = oddPrime * result + nominal;
        long temp = Double.doubleToLongBits(value);
        result = oddPrime * result + (int) (temp ^ (temp >>> Integer.SIZE));
        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Currency)) {
            return false;
        }

        Currency currency = (Currency) o;
        return this.hashCode() == o.hashCode();
    }

    @Override
    public String toString() {
        return charCode;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int numCode;
        private String charCode;
        private String name;
        private int nominal;
        private double value = -1;

        public Builder numCode(final int numCode) {
            if (numCode <= 0) {
                throw new IllegalArgumentException("numCode must be positive integer");
            }

            this.numCode = numCode;
            return this;
        }

        public Builder charCode(final String charCode) {
            if (charCode == null) {
                throw new IllegalArgumentException("charCode can't be null");
            }

            this.charCode = charCode;
            return this;
        }

        public Builder name(final String name) {
            if (name == null) {
                throw new IllegalArgumentException("name can't be null");
            }

            this.name = name;
            return this;
        }

        public Builder nominal(final int nominal) {
            if (nominal <= 0) {
                throw new IllegalArgumentException("nominal must be positive integer");
            }

            this.nominal = nominal;
            return this;
        }

        public Builder value(final double value) {
            if (value < 0) {
                throw new IllegalArgumentException("value must be non-negative number");
            }

            this.value = value;
            return this;
        }

        public Currency build() {
            return new Currency(this);
        }
    }
}

