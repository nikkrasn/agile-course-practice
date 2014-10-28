package ru.unn.agile.DemandElasticity;

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
        DemandRange demands = new DemandRange(400, 200);
        IncomeRange incomes = new IncomeRange(4, 5);
        Coefficient answer = demandElasticityByIncome.calculate(demands, incomes);
        assertEquals(-3, answer.getValue(), delta);
    }

    @Test
    public void canCalculateUndefinedType() {
        DemandRange demands = new DemandRange(768, 768);
        IncomeRange incomes = new IncomeRange(9, 9);
        Coefficient answer = demandElasticityByIncome.calculate(demands, incomes);
        assertEquals(GoodType.Undefined, answer.getType());
    }

    @Test
    public void canCalculateLuxuryType() {
        DemandRange demands = new DemandRange(250, 10);
        IncomeRange incomes = new IncomeRange(9, 8);
        Coefficient answer = demandElasticityByIncome.calculate(demands, incomes);
        assertEquals(GoodType.Luxury, answer.getType());
    }

    @Test
    public void canCalculateFirstNeedType() {
        DemandRange demands = new DemandRange(250, 102);
        IncomeRange incomes = new IncomeRange(9, 2);
        Coefficient answer = demandElasticityByIncome.calculate(demands, incomes);
        assertEquals(GoodType.FirstNeed, answer.getType());
    }

    @Test
    public void canCalculateInferiorType() {
        DemandRange demands = new DemandRange(312, 112);
        IncomeRange incomes = new IncomeRange(3, 7);
        Coefficient answer = demandElasticityByIncome.calculate(demands, incomes);
        assertEquals(GoodType.Inferior, answer.getType());
    }

    @Test
    public void canCalculateNeutralType() {
        DemandRange demands = new DemandRange(101, 101);
        IncomeRange incomes = new IncomeRange(3, 7);
        Coefficient answer = demandElasticityByIncome.calculate(demands, incomes);
        assertEquals(GoodType.Neutral, answer.getType());
    }
}
