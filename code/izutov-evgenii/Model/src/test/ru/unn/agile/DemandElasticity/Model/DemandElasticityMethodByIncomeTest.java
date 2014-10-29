package ru.unn.agile.DemandElasticity.Model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DemandElasticityMethodByIncomeTest {
    private final double delta = 0.000001;
    private DemandElasticityMethodByIncome demandElasticityByIncome;

    @Before
    public void init() {
        demandElasticityByIncome = new DemandElasticityMethodByIncome();
    }

    @Test
    public void canCreateDemandElasticityByIncomeWithDefaultValues() {
        DemandElasticityMethodByIncome demand = new DemandElasticityMethodByIncome();

        assertNotNull(demand);
    }

    @Test(expected = NullPointerException.class)
    public void calculateThrowsExceptionOnNullInput() {
        demandElasticityByIncome.calculate(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void calculateThrowsExceptionOnNullDemandRange() {
        demandElasticityByIncome.calculate(null, new IncomeRange());
    }

    @Test(expected = NullPointerException.class)
    public void calculateThrowsExceptionOnNullIncomeRange() {
        demandElasticityByIncome.calculate(new DemandRange(), null);
    }

    @Test
    public void canCalculateValue() {
        DemandRange demands = new DemandRange(210d, 37d);
        IncomeRange incomes = new IncomeRange(7d, 8d);
        double expectedValue = ((37d - 210d) / (210d + 37d)) / ((8d - 7d) / (7d + 8d));

        Coefficient answer = demandElasticityByIncome.calculate(demands, incomes);

        assertEquals(expectedValue, answer.getValue(), delta);
    }

    @Test
    public void canCalculateUndefinedType() {
        DemandRange demands = new DemandRange(768d, 768d);
        IncomeRange incomes = new IncomeRange(9d, 9d);

        Coefficient answer = demandElasticityByIncome.calculate(demands, incomes);

        assertEquals(GoodType.Undefined, answer.getType());
    }

    @Test
    public void canCalculateLuxuryType() {
        DemandRange demands = new DemandRange(250d, 10d);
        IncomeRange incomes = new IncomeRange(9d, 8d);

        Coefficient answer = demandElasticityByIncome.calculate(demands, incomes);

        assertEquals(GoodType.Luxury, answer.getType());
    }

    @Test
    public void canCalculateFirstNeedType() {
        DemandRange demands = new DemandRange(250d, 102d);
        IncomeRange incomes = new IncomeRange(9d, 2d);

        Coefficient answer = demandElasticityByIncome.calculate(demands, incomes);

        assertEquals(GoodType.FirstNeed, answer.getType());
    }

    @Test
    public void canCalculateInferiorType() {
        DemandRange demands = new DemandRange(312d, 112d);
        IncomeRange incomes = new IncomeRange(3d, 7d);

        Coefficient answer = demandElasticityByIncome.calculate(demands, incomes);

        assertEquals(GoodType.Inferior, answer.getType());
    }

    @Test
    public void canCalculateNeutralType() {
        DemandRange demands = new DemandRange(101d, 101d);
        IncomeRange incomes = new IncomeRange(3d, 7d);

        Coefficient answer = demandElasticityByIncome.calculate(demands, incomes);

        assertEquals(GoodType.Neutral, answer.getType());
    }
}
