package ru.unn.agile.DemandElasticity.Model;

public class CoefficientDescription<T extends Enum> extends Coefficient {
    private final T type;

    public CoefficientDescription(final T type, final double value) {
        super(value);

        if (type == null) {
            throw new IllegalArgumentException("type can not be null");
        }

        this.type = type;
    }

    public T getType() {
        return type;
    }
}
