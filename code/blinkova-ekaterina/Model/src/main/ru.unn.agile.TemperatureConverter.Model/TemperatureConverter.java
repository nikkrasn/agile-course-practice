package ru.unn.agile.TemperatureConverter.Model;




public class TemperatureConverter {

    public double CelsiusToFahrenheit(double input){
        return (FirstConstForCelsiusToFahrenheit()*input+SecondConstForCelsiusToFahrenheit());
    }

    private double FirstConstForCelsiusToFahrenheit() {
        return (9.0/5.0);
    }
    private double SecondConstForCelsiusToFahrenheit() {
        return 32.0;
    }

    public double CelsiusToKelvin(double input) {
        return (input+ConstForCelsiusToKelvin());
    }

    private double ConstForCelsiusToKelvin() {
        return 273.0;
    }

    public double CelsiusToNewton(double input) {
        return (ConstForCelsiusToNewton()*input);
    }

    private double ConstForCelsiusToNewton() {
        return (33.0/100.0);
    }

}
