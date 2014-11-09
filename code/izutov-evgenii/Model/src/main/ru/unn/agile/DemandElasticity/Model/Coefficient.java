package ru.unn.agile.DemandElasticity.Model;

public class Coefficient {
    private final double value;

    public Coefficient(final double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public boolean isValueUndefined() {
        return Double.isNaN(value);
    }
}
