package ru.unn.agile.DemandElasticity.viewmodel;

import ru.unn.agile.DemandElasticity.Model.*;

public enum DemandElasticityType {
    ByCrossPrice("By cross price") {
        @Override
        public Coefficient calculate(final IPositiveRange firstRange,
                                     final IPositiveRange secondRange) {
            DemandElasticityMethod method = new DemandElasticityMethodByCrossPrice();
            return method.calculate(firstRange, secondRange);
        }

        @Override
        public IPositiveRange getFirstRange(final double start, final double finish) {
            return new DemandRange(start, finish);
        }

        @Override
        public IPositiveRange getSecondRange(final double start, final double finish) {
            return new PriceRange(start, finish);
        }
    },
    ByIncome("By income") {
        @Override
        public Coefficient calculate(final IPositiveRange firstRange,
                                     final IPositiveRange secondRange) {
            DemandElasticityMethod method = new DemandElasticityMethodByIncome();
            return method.calculate(firstRange, secondRange);
        }

        @Override
        public IPositiveRange getFirstRange(final double start, final double finish) {
            return new DemandRange(start, finish);
        }

        @Override
        public IPositiveRange getSecondRange(final double start, final double finish) {
            return new IncomeRange(start, finish);
        }
    },
    ByPrice("By price") {
        @Override
        public Coefficient calculate(final IPositiveRange firstRange,
                                     final IPositiveRange secondRange) {
            DemandElasticityMethod method = new DemandElasticityMethodByPrice();
            return method.calculate(firstRange, secondRange);
        }

        @Override
        public IPositiveRange getFirstRange(final double start, final double finish) {
            return new DemandRange(start, finish);
        }

        @Override
        public IPositiveRange getSecondRange(final double start, final double finish) {
            return new PriceRange(start, finish);
        }
    };

    private final String name;
    DemandElasticityType(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public abstract Coefficient calculate(final IPositiveRange firstRange,
                                          final IPositiveRange secondRange);
    public abstract IPositiveRange getFirstRange(final double start, final double finish);
    public abstract IPositiveRange getSecondRange(final double start, final double finish);
}
