package ru.unn.agile.CurrencyConverter.Model;

public class Money {
    private Currency currency;
    private double amount;

    public Money(final Currency currency, final double amount) {
        if (currency == null) {
            throw new IllegalArgumentException("Currency can't be null.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive number.");
        }

        this.currency = currency;
        this.amount = amount;
    }
}
