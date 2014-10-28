package ru.unn.agile.DemandElasticity;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DemandElasticityByIncomeTest {
    private final double delta = 0.000001;
    private DemandElasticityByIncome demandElasticityByIncome;

    @Before
    public void init() {
        demandElasticityByIncome = new DemandElasticityByIncome();
    }

    @Test
    public void canCreateDemandElasticityByIncomeWithDefaultValues() {
        DemandElasticityByIncome demand = new DemandElasticityByIncome();
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
    public void canCalculate() {
        DemandRange demands = new DemandRange(400, 200);
        IncomeRange incomes = new IncomeRange(4, 5);
        double answer = demandElasticityByIncome.calculate(demands, incomes);
        assertEquals(-3, answer, delta);
    }
}
