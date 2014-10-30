package ru.unn.agile.ComplexProcent.Model;


import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ComplexDepositeTest {

    private final double delta = 0.001;

    @Test
    public void canCreateDeposite(){
        ComplexDeposite deposite=new ComplexDeposite(1000,4,1);
        assertNotNull(deposite);
    }
    @Test
    public void canSetCapitalizedBaseInOnePeriod(){
        ComplexDeposite deposite=new ComplexDeposite(1000,4.5,1);
        assertEquals(1045,deposite.getCapitolizedBase(1),delta);
    }

    @Test
    public void canSetCapitalizedBaseInOnePeriodForFewYears(){
        ComplexDeposite deposite=new ComplexDeposite(1000,4.5,1);
        assertEquals(1092.025,deposite.getCapitolizedBase(2),delta);
    }

    @Test
    public void canSetCapitalizedBaseInFewPeriodForYear(){
        ComplexDeposite deposite=new ComplexDeposite(1000,4.5,3);
        assertEquals(1045.67838,deposite.getCapitolizedBase(1),delta);
    }

    @Test
    public void canSetCapitalizedBaseInFewPeriodForFewYears(){
        ComplexDeposite deposite=new ComplexDeposite(1000,4.5,3);
        assertEquals(1195.61817,deposite.getCapitolizedBase(4),delta);
    }

}



