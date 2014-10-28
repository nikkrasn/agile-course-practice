package ru.unn.agile.CurrencyConverter.Model;

// Store currency in format like in cbr.ru: http://www.cbr.ru/scripts/XML_daily.asp
public class Currency {
    private final int numCode;
    private final String charCode;
    private final String name;
    private final int nominal;
    private final double value;

    public Currency(final int numCode, final String charCode, final String name,
                    final int nominal, final double value) {
        if (charCode == null) {
            throw new IllegalArgumentException("charCode can't be null.");
        }

        if (name == null) {
            throw new IllegalArgumentException("Name can't be null.");
        }

        if (nominal <= 0) {
            throw new IllegalArgumentException("Nominal must be positive integer.");
        }

        if (value <= 0) {
            throw new IllegalArgumentException("Value must be positive number.");
        }

        this.numCode = numCode;
        this.charCode = charCode;
        this.name = name;
        this.nominal = nominal;
        this.value = value;
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
        int result;
        long temp;
        result = numCode;
        result = 31 * result + charCode.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + nominal;
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Currency currency = (Currency) o;
        return numCode == currency.numCode;
    }
}

