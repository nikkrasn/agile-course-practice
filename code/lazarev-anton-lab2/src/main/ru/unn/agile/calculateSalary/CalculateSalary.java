package ru.unn.agile.calculateSalary;

public class CalculateSalary {

    public static double CountingCash(double salary){
        if (salary < 0){return 0;}
        salary = salary * 0.87;
        return salary;
    }
}
