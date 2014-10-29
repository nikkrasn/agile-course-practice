package ru.unn.agile.DemandElasticity.Model;

public final class DemandElasticityMethodByCrossPrice extends DemandElasticityMethod
        <DemandRange, PriceRange, GoodsPairType> {

    @Override
    protected Coefficient<GoodsPairType> createUndefinedCoefficient() {
        return new Coefficient<>(GoodsPairType.Undefined, Double.NaN);
    }

    @Override
    protected Coefficient<GoodsPairType> createInfiniteCoefficient(final double firstMidpoint) {
        GoodsPairType type;
        if (firstMidpoint > 0d) {
            type = GoodsPairType.Substitute;
        } else {
            type = GoodsPairType.Complementary;
        }
        return new Coefficient<>(type, Double.NaN);
    }

    @Override
    protected Coefficient<GoodsPairType> createFiniteCoefficient(final double coefficientValue) {
        GoodsPairType type;
        if (coefficientValue > 0d) {
            type = GoodsPairType.Substitute;
        } else if (Math.abs(coefficientValue) < getDelta()) {
            type = GoodsPairType.Independent;
        } else {
            type = GoodsPairType.Complementary;
        }
        return new Coefficient<>(type, coefficientValue);
    }
}
