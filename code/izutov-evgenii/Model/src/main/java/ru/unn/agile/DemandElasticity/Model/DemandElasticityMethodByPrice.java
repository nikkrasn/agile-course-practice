package ru.unn.agile.DemandElasticity.Model;

public final class DemandElasticityMethodByPrice extends DemandElasticityMethod {
    @Override
    protected void testArgumentsOnType(final IPositiveRange first, final IPositiveRange second) {
        if (!(first instanceof DemandRange)) {
            throw new IllegalArgumentException("first demand parameter must be DemandRange type");
        }

        if (!(second instanceof PriceRange)) {
            throw new IllegalArgumentException("second price parameter must be PriceRange type f");
        }
    }

    @Override
    protected Coefficient createUndefinedCoefficient() {
        return new CoefficientDescription<>(DemandType.Undefined, Double.NaN);
    }

    @Override
    protected Coefficient createInfiniteCoefficient(final double firstMidpoint) {
        DemandType type;
        if (firstMidpoint > 0d) {
            type = DemandType.GiffenGood;
        } else {
            type = DemandType.PerfectlyElasticity;
        }
        return new CoefficientDescription<>(type, Double.NaN);
    }

    @Override
    protected Coefficient createFiniteCoefficient(final double coefficientValue) {
        DemandType type;
        if (coefficientValue > 0d) {
            type = DemandType.GiffenGood;
        } else if (Math.abs(coefficientValue) < getDelta()) {
            type = DemandType.PerfectlyInelastic;
        } else if (coefficientValue > -1d) {
            type = DemandType.Inelastic;
        } else {
            type = DemandType.Elasticity;
        }

        return new CoefficientDescription<>(type, coefficientValue);
    }
}
