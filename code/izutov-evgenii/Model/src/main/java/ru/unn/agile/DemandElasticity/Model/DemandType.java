package ru.unn.agile.DemandElasticity.Model;

public enum DemandType {
    Undefined("Demand is undefined"),
    GiffenGood("Giffen good"),
    PerfectlyInelastic("Perfectly inelastic demand"),
    Inelastic("Inelastic demand"),
    Elasticity("Elastic demand"),
    PerfectlyElasticity("Perfectly elastic demand");

    private final String name;
    private DemandType(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
