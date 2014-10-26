package ru.unn.agile.CurrencyConverter.Model;

public class Money {
    public Money(Currency currency, double amount) {
        if (currency == null) {
            throw new IllegalArgumentException("Currency can't be null.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive number.");
        }

        this.currency = currency;
        this.amount = amount;
    }

    

    private Currency currency;
    private double amount;
}