package ru.unn.agile.ComplexProcent.Model;

public class ComplexDeposite {
    private double base;
    private double procent;
    private double interestCountInYear;

    public ComplexDeposite(final double depositeBase,final double depositeProcent ,final double depositeinterestCount){
        this.base=depositeBase;
        this.procent=depositeProcent/100;
        this.interestCountInYear=depositeinterestCount;
    }

    public double getCapitolizedBase(int years){
        return this.base*(1+this.procent);
    };
}
