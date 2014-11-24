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

        @Override
        public String getFirstRangeName() {
            return "Demand range of first good";
        }

        @Override
        public String getSecondRangeName() {
            return "Price range of second good";
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

        @Override
        public String getFirstRangeName() {
            return "Demand range";
        }

        @Override
        public String getSecondRangeName() {
            return "Income range";
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

        @Override
        public String getFirstRangeName() {
            return "Demand range";
        }

        @Override
        public String getSecondRangeName() {
            return "Price range";
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
    public abstract String getFirstRangeName();
    public abstract String getSecondRangeName();
}
