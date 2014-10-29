package ru.unn.agile.DemandElasticity.Model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DemandElasticityMethodByPriceTest {
    private final double delta = 0.000001;
    private DemandElasticityMethodByPrice demandElasticityByPrice;

    @Before
    public void init() {
        demandElasticityByPrice = new DemandElasticityMethodByPrice();
    }

    @Test
    public void canCreateDemandElasticityByPriceWithDefaultValues() {
        DemandElasticityMethodByPrice demand = new DemandElasticityMethodByPrice();

        assertNotNull(demand);
    }

    @Test(expected = NullPointerException.class)
    public void calculateThrowsExceptionOnNullInput() {
        demandElasticityByPrice.calculate(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void calculateThrowsExceptionOnNullDemandRange() {
        demandElasticityByPrice.calculate(null, new PriceRange());
    }

    @Test(expected = NullPointerException.class)
    public void calculateThrowsExceptionOnNullPriceRange() {
        demandElasticityByPrice.calculate(new DemandRange(), null);
    }

    @Test
    public void canCalculateValue() {
        DemandRange demands = new DemandRange(507d, 132d);
        PriceRange prices = new PriceRange(1d, 4d);
        double expectedValue = ((132d - 507d) / (507d + 132d)) / ((4d - 1d) / (1d + 4d));

        Coefficient answer = demandElasticityByPrice.calculate(demands, prices);

        assertEquals(expectedValue, answer.getValue(), delta);
    }

    @Test
    public void canCalculateUndefinedType() {
        DemandRange demands = new DemandRange(50d, 50d);
        PriceRange prices = new PriceRange(24d, 24d);

        Coefficient answer = demandElasticityByPrice.calculate(demands, prices);

        assertEquals(DemandType.Undefined, answer.getType());
    }

    @Test
    public void canCalculateGiffenGoodType() {
        DemandRange demands = new DemandRange(460d, 510d);
        PriceRange prices = new PriceRange(2d, 10d);

        Coefficient answer = demandElasticityByPrice.calculate(demands, prices);

        assertEquals(DemandType.GiffenGood, answer.getType());
    }

    @Test
    public void canCalculatePerfectlyInelasticType() {
        DemandRange demands = new DemandRange(312d, 312d);
        PriceRange prices = new PriceRange(2d, 17d);

        Coefficient answer = demandElasticityByPrice.calculate(demands, prices);

        assertEquals(DemandType.PerfectlyInelastic, answer.getType());
    }

    @Test
    public void canCalculateInelasticType() {
        DemandRange demands = new DemandRange(100d, 57d);
        PriceRange prices = new PriceRange(5d, 9d);

        Coefficient answer = demandElasticityByPrice.calculate(demands, prices);

        assertEquals(DemandType.Inelastic, answer.getType());
    }

    @Test
    public void canCalculateElasticityType() {
        DemandRange demands = new DemandRange(300d, 12d);
        PriceRange prices = new PriceRange(7d, 8d);

        Coefficient answer = demandElasticityByPrice.calculate(demands, prices);

        assertEquals(DemandType.Elasticity, answer.getType());
    }

    @Test
    public void canCalculatePerfectlyElasticityType() {
        DemandRange demands = new DemandRange(500d, 300d);
        PriceRange prices = new PriceRange(5d, 5d);

        Coefficient answer = demandElasticityByPrice.calculate(demands, prices);

        assertEquals(DemandType.PerfectlyElasticity, answer.getType());
    }
}
