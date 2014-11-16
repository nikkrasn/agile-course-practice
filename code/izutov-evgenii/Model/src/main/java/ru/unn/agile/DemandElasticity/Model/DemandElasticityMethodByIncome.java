package ru.unn.agile.DemandElasticity.Model;

public final class DemandElasticityMethodByIncome extends DemandElasticityMethod {
    @Override
    protected void testArgumentsOnType(final IPositiveRange first, final IPositiveRange second) {
        if (!(first instanceof DemandRange)) {
            throw new IllegalArgumentException("first demand parameter must be DemandRange type");
        }

        if (!(second instanceof IncomeRange)) {
            throw new IllegalArgumentException("second income parameter must be IncomeRange type");
        }
    }

    @Override
    protected Coefficient createUndefinedCoefficient() {
        return new CoefficientDescription<>(GoodType.Undefined, Double.NaN);
    }

    @Override
    protected Coefficient createInfiniteCoefficient(final double firstMidpoint) {
        GoodType type;
        if (firstMidpoint > 0d) {
            type = GoodType.Luxury;
        } else {
            type = GoodType.Inferior;
        }
        return new CoefficientDescription<>(type, Double.NaN);
    }

    @Override
    protected Coefficient createFiniteCoefficient(final double coefficientValue) {
        GoodType type;
        if (coefficientValue > 1d) {
            type = GoodType.Luxury;
        } else if (coefficientValue > 0d) {
            type = GoodType.FirstNeed;
        } else if (Math.abs(coefficientValue) < getDelta()) {
            type = GoodType.Neutral;
        } else {
            type = GoodType.Inferior;
        }
        return new CoefficientDescription<>(type, coefficientValue);
    }
}
