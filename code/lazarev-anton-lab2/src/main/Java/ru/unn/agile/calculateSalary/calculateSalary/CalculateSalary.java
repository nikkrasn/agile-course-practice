package ru.unn.agile.calculateSalary.calculateSalary;

import CalculateJobDays;

import java.time.LocalDate;

public class CalculateSalary {

    public static double CountingCash(double salary, LocalDate countingMonth){
        if (salary < 0){return 0;}
        int jobDaysInCurrentMonth = CalculateJobDays.countingJobDaysInMonth(countingMonth);
        double payForOneJobDay = salary/jobDaysInCurrentMonth;
        double payForOneJobHour = payForOneJobDay/8;
        double cashForPay = (jobDaysInCurrentMonth*payForOneJobDay)*0.87;
        return cashForPay;
    }
}
