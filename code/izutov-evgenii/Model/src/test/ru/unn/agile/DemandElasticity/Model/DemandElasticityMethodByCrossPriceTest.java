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
        DemandRange demands = new DemandRange(400d, 200d);
        PriceRange prices = new PriceRange(4d, 5d);
        double expectedValue = ((200 - 400d) / (400d + 200d)) / ((5d - 4d) / (5d + 4d));

        Coefficient answer = demandElasticityByCrossPrice.calculate(demands, prices);

        assertEquals(expectedValue, answer.getValue(), delta);
    }

    @Test
    public void canCalculateUndefinedType() {
        DemandRange demands = new DemandRange(710d, 710d);
        PriceRange prices = new PriceRange(5d, 5d);

        Coefficient answer = demandElasticityByCrossPrice.calculate(demands, prices);

        assertEquals(GoodsPairType.Undefined, answer.getType());
    }

    @Test
    public void canCalculateSubstituteType() {
        DemandRange demands = new DemandRange(130d, 501d);
        PriceRange prices = new PriceRange(4d, 9d);

        Coefficient answer = demandElasticityByCrossPrice.calculate(demands, prices);

        assertEquals(GoodsPairType.Substitute, answer.getType());
    }

    @Test
    public void canCalculateComplementaryType() {
        DemandRange demands = new DemandRange(100d, 500d);
        PriceRange prices = new PriceRange(10d, 3d);

        Coefficient answer = demandElasticityByCrossPrice.calculate(demands, prices);

        assertEquals(GoodsPairType.Complementary, answer.getType());
    }

    @Test
    public void canCalculateIndependentType() {
        DemandRange demands = new DemandRange(570d, 570d);
        PriceRange prices = new PriceRange(11d, 17d);

        Coefficient answer = demandElasticityByCrossPrice.calculate(demands, prices);

        assertEquals(GoodsPairType.Independent, answer.getType());
    }
}
