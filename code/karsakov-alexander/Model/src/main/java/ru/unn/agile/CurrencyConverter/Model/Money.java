package ru.unn.agile.CurrencyConverter.Model;

public class Money {
    private Currency currency;
    private double amount;

    public Money(final Currency currency, final double amount) {
        setCurrency(currency);
        setAmount(amount);
    }

    public final Currency getCurrency() {
        return currency;
    }

    private void setCurrency(final Currency newCurrency) {
        if (newCurrency == null) {
            throw new IllegalArgumentException("Currency can't be null.");
        }

        currency = newCurrency;
    }

    public final double getAmount() {
        return amount;
    }

    public void setAmount(final double newAmount) {
        if (newAmount < 0) {
            throw new IllegalArgumentException("Amount must be non negative number.");
        }

        amount = newAmount;
    }

    public boolean isInCurrency(final Currency currency) {
        return this.currency.equals(currency);
    }

    public Money convertToCurrency(final Currency newCurrency) {
        if (newCurrency == null) {
            throw new IllegalArgumentException("New currency can't be null.");
        }

        if (currency.equals(newCurrency)) {
            return new Money(currency, amount);
        } else {
            double newAmount = amount * (newCurrency.getNominal() / newCurrency.getValue())
                               * (currency.getValue() / currency.getNominal());
            return new Money(newCurrency, newAmount);
        }
    }
}
