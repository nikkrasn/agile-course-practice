package ru.unn.agile.DemandElasticity.Model;

public final class DemandElasticityMethodByIncome extends DemandElasticityMethod
        <DemandRange, IncomeRange, GoodType> {
    @Override
    protected Coefficient<GoodType> createUndefinedCoefficient() {
        return new Coefficient<>(GoodType.Undefined, Double.NaN);
    }

    @Override
    protected Coefficient<GoodType> createInfiniteCoefficient(final double firstMidpoint) {
        GoodType type;
        if (firstMidpoint > 0) {
            type = GoodType.Luxury;
        } else {
            type = GoodType.Inferior;
        }
        return new Coefficient<>(type, Double.NaN);
    }

    @Override
    protected Coefficient<GoodType> createFiniteCoefficient(final double coefficientValue) {
        GoodType type;
        if (coefficientValue > 1) {
            type = GoodType.Luxury;
        } else if (coefficientValue > 0) {
            type = GoodType.FirstNeed;
        } else if (Math.abs(coefficientValue) < getDelta()) {
            type = GoodType.Neutral;
        } else {
            type = GoodType.Inferior;
        }
        return new Coefficient<>(type, coefficientValue);
    }
}
