package ru.unn.agile.DemandElasticity.Model;

public final class DemandElasticityMethodByPrice extends DemandElasticityMethod
        <DemandRange, PriceRange, DemandType> {
    @Override
    protected Coefficient<DemandType> createUndefinedCoefficient() {
        return new Coefficient<>(DemandType.Undefined, Double.NaN);
    }

    @Override
    protected Coefficient<DemandType> createInfiniteCoefficient(final double firstMidpoint) {
        DemandType type;
        if (firstMidpoint > 0d) {
            type = DemandType.GiffenGood;
        } else {
            type = DemandType.PerfectlyElasticity;
        }
        return new Coefficient<>(type, Double.NaN);
    }

    @Override
    protected Coefficient<DemandType> createFiniteCoefficient(final double coefficientValue) {
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

        return new Coefficient<>(type, coefficientValue);
    }
}
