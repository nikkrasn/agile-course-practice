package ru.unn.agile.DemandElasticity.Model;

public interface IDemandElasticityMethod {
    Coefficient calculate(IPositiveRange firstRange, IPositiveRange secondRange);
}
