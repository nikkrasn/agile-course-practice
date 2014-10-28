package ru.unn.agile.DemandElasticity;

public interface IDemandElasticityMethod<TFirst extends IPositiveRange, TSecond extends IPositiveRange, TOutput extends Enum> {
    Coefficient<TOutput> calculate(TFirst firstRange, TSecond secondRange);
}
