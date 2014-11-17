package ru.unn.agile.calculateSalary;

import java.time.LocalDate;

public class CalculateSalary {

    public static double CountingCash(double salary, LocalDate countingMonth, int workedHourInMonth, LocalDate startOfVacation, int lengthOfVacation){
        if (salary < 0){return 0;}
        double cashForPay = 0;
        int jobDaysInCurrentMonth = CalculateJobDays.countingJobDaysInMonth(countingMonth);
        double payForOneJobHour = salary/(jobDaysInCurrentMonth*8);
        int cashDaysInMonth = CalculateJobDays.countingCashDaysInMonth(lengthOfVacation, startOfVacation, countingMonth);
        int amountOfJobHoursInCountingMonth = cashDaysInMonth*8;
        if(workedHourInMonth > amountOfJobHoursInCountingMonth){
            cashForPay = (amountOfJobHoursInCountingMonth*payForOneJobHour)+((workedHourInMonth-amountOfJobHoursInCountingMonth)*(payForOneJobHour*2));
        }else{cashForPay = amountOfJobHoursInCountingMonth*payForOneJobHour;}
        return cashForPay*0.87;
    }
}
