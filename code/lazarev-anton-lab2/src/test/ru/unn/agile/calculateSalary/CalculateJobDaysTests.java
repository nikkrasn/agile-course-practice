package ru.unn.agile.calculateSalary;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalculateJobDaysTests {

    @Test
    public void checkDayOfWeekTest(){
        assertEquals(6, CalculateJobDays.checkDayOfWeek(22, 11, 2014));
    }

    @Test
    public void checkHolidayTest(){
        assertEquals(true, CalculateJobDays.isHoliday(3, 2014, 8));
    }

    @Test
    public void checkJobDayOnHolidayTest(){
        assertEquals(false, CalculateJobDays.isHoliday(4, 2014, 10));
    }

    @Test
    public void checkJobDaysInMonthTest(){
        assertEquals(23, CalculateJobDays.countingJobDaysInMonth(10, 2014));
    }

    @Test
    public void checkJobDaysInMonthWithOneHolidayTest(){
        assertEquals(19, CalculateJobDays.countingJobDaysInMonth(11, 2014));
    }

    @Test
    public void checkJobDaysInDifficultMonthJanuaryTest(){
        assertEquals(17, CalculateJobDays.countingJobDaysInMonth(1, 2014));
    }
}
