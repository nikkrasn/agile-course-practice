package ru.unn.agile.calculateSalary;

import java.time.LocalDate;

public class CalculateSalary {

    public static double CountCash(double salary, LocalDate countingMonth, int workedHourInMonth, LocalDate startOfVacation, int lengthOfVacation){
        if (salary < 0) {return 0;}
        double cashForPay = 0;
        int jobDaysInCurrentMonth = CalculateJobDays.countJobDaysInMonth(countingMonth);
        double payForOneJobHour = salary/(jobDaysInCurrentMonth*8);
        int cashDaysInMonth = CalculateJobDays.countCashDaysInMonth(lengthOfVacation, startOfVacation, countingMonth);
        int amountOfJobHoursInCountingMonth = cashDaysInMonth*8;
        if(workedHourInMonth > amountOfJobHoursInCountingMonth) {
            cashForPay = (amountOfJobHoursInCountingMonth*payForOneJobHour)+((workedHourInMonth-amountOfJobHoursInCountingMonth)*(payForOneJobHour*2));
        }
        else if(workedHourInMonth < amountOfJobHoursInCountingMonth) {
            cashForPay = workedHourInMonth*payForOneJobHour;
        }
        else {cashForPay = amountOfJobHoursInCountingMonth*payForOneJobHour;}
        return cashForPay*0.87;
    }

    public static double CountCashWithoutVacation(double salary, LocalDate countingMonth, int workedHourInMonth){
        if (salary < 0) {return 0;}
        double cashForPay = 0;
        int jobDaysInCurrentMonth = CalculateJobDays.countJobDaysInMonth(countingMonth);
        double payForOneJobHour = salary/(jobDaysInCurrentMonth*8);
        int amountOfJobHoursInCountingMonth = jobDaysInCurrentMonth*8;
        if(workedHourInMonth > amountOfJobHoursInCountingMonth) {
            cashForPay = (amountOfJobHoursInCountingMonth*payForOneJobHour)+((workedHourInMonth-amountOfJobHoursInCountingMonth)*(payForOneJobHour*2));
        }
        else if(workedHourInMonth < amountOfJobHoursInCountingMonth) {
            cashForPay = workedHourInMonth*payForOneJobHour;
        }
        else {cashForPay = amountOfJobHoursInCountingMonth*payForOneJobHour;}
        return cashForPay*0.87;
    }
}
