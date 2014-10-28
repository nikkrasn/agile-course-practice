package ru.unn.agile.DemandElasticity.Model;

public final class Coefficient<T extends Enum> {
    private T type;
    private double value;

    public Coefficient(final T type, final double value) {
        if (type == null) {
            throw new IllegalArgumentException("type can not be null");
        }

        this.type = type;
        this.value = value;
    }

    public T getType() {
        return type;
    }

    public double getValue() {
        return value;
    }

    public boolean isUndefined() {
        return Double.isNaN(value);
    }
}
