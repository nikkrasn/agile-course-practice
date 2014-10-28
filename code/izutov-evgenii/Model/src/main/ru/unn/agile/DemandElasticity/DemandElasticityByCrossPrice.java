package ru.unn.agile.DemandElasticity;

public final class DemandElasticityByCrossPrice extends DemandElasticity {
    public double calculate(final DemandRange demandsFirstGoods, final PriceRange pricesSecondGoods) {
        return super.calculate(demandsFirstGoods, pricesSecondGoods);
    }
}
