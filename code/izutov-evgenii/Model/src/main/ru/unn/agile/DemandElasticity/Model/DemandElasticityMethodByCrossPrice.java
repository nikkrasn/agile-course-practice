package ru.unn.agile.DemandElasticity.Model;

public final class DemandElasticityMethodByCrossPrice extends DemandElasticityMethod {
    @Override
    protected void testArgumentsOnType(final IPositiveRange first, final IPositiveRange second) {
        if (!(first instanceof DemandRange)) {
            throw new IllegalArgumentException("first demand parameter must be DemandRange type");
        }

        if (!(second instanceof PriceRange)) {
            throw new IllegalArgumentException(
                    "second cross price parameter must be PriceRange type");
        }
    }

    @Override
    protected Coefficient createUndefinedCoefficient() {
        return new CoefficientDescription<>(GoodsPairType.Undefined, Double.NaN);
    }

    @Override
    protected Coefficient createInfiniteCoefficient(final double firstMidpoint) {
        GoodsPairType type;
        if (firstMidpoint > 0d) {
            type = GoodsPairType.Substitute;
        } else {
            type = GoodsPairType.Complementary;
        }
        return new CoefficientDescription<>(type, Double.NaN);
    }

    @Override
    protected Coefficient createFiniteCoefficient(final double coefficientValue) {
        GoodsPairType type;
        if (coefficientValue > 0d) {
            type = GoodsPairType.Substitute;
        } else if (Math.abs(coefficientValue) < getDelta()) {
            type = GoodsPairType.Independent;
        } else {
            type = GoodsPairType.Complementary;
        }
        return new CoefficientDescription<>(type, coefficientValue);
    }
}
