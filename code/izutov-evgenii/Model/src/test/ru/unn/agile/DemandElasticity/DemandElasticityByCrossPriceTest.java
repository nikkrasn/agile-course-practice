package ru.unn.agile.DemandElasticity;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DemandElasticityByCrossPriceTest {
    private final double delta = 0.000001;
    private DemandElasticityByCrossPrice demandElasticityByCrossPrice;

    @Before
    public void init() {
        demandElasticityByCrossPrice = new DemandElasticityByCrossPrice();
    }

    @Test
    public void canCreateDemandElasticityByCrossPriceWithDefaultValues() {
        DemandElasticityByCrossPrice demand = new DemandElasticityByCrossPrice();
        assertNotNull(demand);
    }

    @Test(expected = NullPointerException.class)
    public void calculateThrowsExceptionOnNullInput() {
        demandElasticityByCrossPrice.calculate(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void calculateThrowsExceptionOnNullDemandRange() {
        demandElasticityByCrossPrice.calculate(null, new PriceRange());
    }

    @Test(expected = NullPointerException.class)
    public void calculateThrowsExceptionOnNullPriceRange() {
        demandElasticityByCrossPrice.calculate(new DemandRange(), null);
    }

    @Test
    public void canCalculate() {
        DemandRange demands = new DemandRange(400, 200);
        PriceRange prices = new PriceRange(4, 5);
        double answer = demandElasticityByCrossPrice.calculate(demands, prices);
        assertEquals(-3, answer, delta);
    }
}
