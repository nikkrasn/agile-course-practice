package ru.unn.agile.DemandElasticity.Model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DemandElasticityMethodByCrossPriceTest {
    private final double delta = 0.000001;
    private DemandElasticityMethodByCrossPrice demandElasticityByCrossPrice;

    @Before
    public void init() {
        demandElasticityByCrossPrice = new DemandElasticityMethodByCrossPrice();
    }

    @Test
    public void canCreateDemandElasticityByCrossPriceWithDefaultValues() {
        DemandElasticityMethodByCrossPrice demand = new DemandElasticityMethodByCrossPrice();
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
    public void canCalculateValue() {
        DemandRange demands = new DemandRange(400, 200);
        PriceRange prices = new PriceRange(4, 5);
        Coefficient answer = demandElasticityByCrossPrice.calculate(demands, prices);
        assertEquals(-3, answer.getValue(), delta);
    }

    @Test
    public void canCalculateUndefinedType() {
        DemandRange demands = new DemandRange(710, 710);
        PriceRange prices = new PriceRange(5, 5);
        Coefficient answer = demandElasticityByCrossPrice.calculate(demands, prices);
        assertEquals(GoodsPairType.Undefined, answer.getType());
    }

    @Test
    public void canCalculateSubstituteType() {
        DemandRange demands = new DemandRange(130, 501);
        PriceRange prices = new PriceRange(4, 9);
        Coefficient answer = demandElasticityByCrossPrice.calculate(demands, prices);
        assertEquals(GoodsPairType.Substitute, answer.getType());
    }

    @Test
    public void canCalculateComplementaryType() {
        DemandRange demands = new DemandRange(100, 500);
        PriceRange prices = new PriceRange(10, 3);
        Coefficient answer = demandElasticityByCrossPrice.calculate(demands, prices);
        assertEquals(GoodsPairType.Complementary, answer.getType());
    }

    @Test
    public void canCalculateIndependentType() {
        DemandRange demands = new DemandRange(570, 570);
        PriceRange prices = new PriceRange(11, 17);
        Coefficient answer = demandElasticityByCrossPrice.calculate(demands, prices);
        assertEquals(GoodsPairType.Independent, answer.getType());
    }
}
