package ru.unn.agile.DemandElasticity;

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
        DemandRange demands = new DemandRange(400, 200);
        PriceRange prices = new PriceRange(4, 5);
        Coefficient answer = demandElasticityByPrice.calculate(demands, prices);
        assertEquals(-3, answer.getValue(), delta);
    }

    @Test
    public void canCalculateUndefinedType() {
        DemandRange demands = new DemandRange(50, 50);
        PriceRange prices = new PriceRange(24, 24);
        Coefficient answer = demandElasticityByPrice.calculate(demands, prices);
        assertEquals(DemandType.Undefined, answer.getType());
    }

    @Test
    public void canCalculateGiffenGoodType() {
        DemandRange demands = new DemandRange(460, 510);
        PriceRange prices = new PriceRange(2, 10);
        Coefficient answer = demandElasticityByPrice.calculate(demands, prices);
        assertEquals(DemandType.GiffenGood, answer.getType());
    }

    @Test
    public void canCalculatePerfectlyInelasticType() {
        DemandRange demands = new DemandRange(312, 312);
        PriceRange prices = new PriceRange(2, 17);
        Coefficient answer = demandElasticityByPrice.calculate(demands, prices);
        assertEquals(DemandType.PerfectlyInelastic, answer.getType());
    }

    @Test
    public void canCalculateInelasticType() {
        DemandRange demands = new DemandRange(100, 57);
        PriceRange prices = new PriceRange(5, 9);
        Coefficient answer = demandElasticityByPrice.calculate(demands, prices);
        assertEquals(DemandType.Inelastic, answer.getType());
    }

    @Test
    public void canCalculateElasticityType() {
        DemandRange demands = new DemandRange(300, 12);
        PriceRange prices = new PriceRange(7, 8);
        Coefficient answer = demandElasticityByPrice.calculate(demands, prices);
        assertEquals(DemandType.Elasticity, answer.getType());
    }

    @Test
    public void canCalculatePerfectlyElasticityType() {
        DemandRange demands = new DemandRange(500, 300);
        PriceRange prices = new PriceRange(5, 5);
        Coefficient answer = demandElasticityByPrice.calculate(demands, prices);
        assertEquals(DemandType.PerfectlyElasticity, answer.getType());
    }
}
