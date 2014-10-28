package ru.unn.agile.DemandElasticity;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DemandElasticityByPriceTest {
    private final double delta = 0.000001;
    private DemandElasticityByPrice demandElasticityByPrice;

    @Before
    public void init() {
        demandElasticityByPrice = new DemandElasticityByPrice();
    }

    @Test
    public void canCreateDemandElasticityByPriceWithDefaultValues() {
        DemandElasticityByPrice demand = new DemandElasticityByPrice();
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
    public void canCalculate() {
        DemandRange demands = new DemandRange(400, 200);
        PriceRange prices = new PriceRange(4, 5);
        double answer = demandElasticityByPrice.calculate(demands, prices);
        assertEquals(-3, answer, delta);
    }
}
