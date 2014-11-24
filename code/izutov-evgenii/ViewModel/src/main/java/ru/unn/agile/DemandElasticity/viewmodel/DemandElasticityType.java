package ru.unn.agile.DemandElasticity.viewmodel;

import ru.unn.agile.DemandElasticity.Model.*;

public enum DemandElasticityType {
    ByCrossPrice("By cross price") {
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

        @Override
        protected DemandElasticityMethod getMethod() {
            return new DemandElasticityMethodByCrossPrice();
        }
    },
    ByIncome("By income") {
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

        @Override
        protected DemandElasticityMethod getMethod() {
            return new DemandElasticityMethodByIncome();
        }
    },
    ByPrice("By price") {
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

        @Override
        protected DemandElasticityMethod getMethod() {
            return new DemandElasticityMethodByPrice();
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

    public Coefficient calculate(final IPositiveRange firstRange,
                                 final IPositiveRange secondRange) {
        DemandElasticityMethod method = getMethod();
        return method.calculate(firstRange, secondRange);
    }
    public IPositiveRange getFirstRange(final double start, final double finish) {
        return new DemandRange(start, finish);
    }
    public abstract IPositiveRange getSecondRange(final double start, final double finish);
    public abstract String getFirstRangeName();
    public abstract String getSecondRangeName();
    protected abstract DemandElasticityMethod getMethod();
}
