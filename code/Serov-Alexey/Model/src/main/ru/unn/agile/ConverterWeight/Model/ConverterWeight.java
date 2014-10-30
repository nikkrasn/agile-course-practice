package ru.unn.agile.ConverterWeight.Model;

/**
 * Created by Алексей on 26.10.14.
 */
public class ConverterWeight {

    public double GrammToKilogram(String input){

        if(!isEmpty(input)&&(!isNegativNumber(input))){
            return Double.parseDouble(input)/1000;
        }
        else return 0.0;
    }

    public double GrammToCentner(String input){

        if(!isEmpty(input)&&(!isNegativNumber(input))){
            return Double.parseDouble(input)/100000;
        }
        else return 0.0;
    }

    public double GrammToTon(String input){

        if(!isEmpty(input)&&(!isNegativNumber(input))){
            return Double.parseDouble(input)/1000000;
        }
        else return 0.0;
    }
        //________________________________________________

    public double KilogramToGram(String input){

        if(!isEmpty(input)&&(!isNegativNumber(input))){
            return Double.parseDouble(input)*1000;
        }
        else return 0.0;
    }

    public double KilogramToCentner(String input){

        if(!isEmpty(input)&&(!isNegativNumber(input))){
            return Double.parseDouble(input)/100;
        }
        else return 0.0;
    }

    public double KilogramToTon(String input){

        if(!isEmpty(input)&&(!isNegativNumber(input))){
            return Double.parseDouble(input)/1000;
        }
        else return 0.0;
    }
//---------------------------
    public double CentnerToGram(String input){

        if(!isEmpty(input)&&(!isNegativNumber(input))){
            return Double.parseDouble(input)*100000;
        }
        else return 0.0;
    }

    public double CentnerToKilogram(String input){

        if(!isEmpty(input)&&(!isNegativNumber(input))){
            return Double.parseDouble(input)*100;
        }
        else return 0.0;
    }

    public double CentnerToTon(String input){

        if(!isEmpty(input)&&(!isNegativNumber(input))){
            return Double.parseDouble(input)/10;
        }
        else return 0.0;
    }
//---------

    public double TonToGram(String input){

        if(!isEmpty(input)&&(!isNegativNumber(input))){
            return Double.parseDouble(input)*1000000;
        }
        else return 0.0;
    }

    public double TonToKilogram(String input){

        if(!isEmpty(input)&&(!isNegativNumber(input))){
            return Double.parseDouble(input)*1000;
        }
        else return 0.0;
    }

    public double TonToCentner(String input){

        if(!isEmpty(input)&&(!isNegativNumber(input))){
            return Double.parseDouble(input)*100;
        }
        else return 0.0;
    }

    private boolean isEmpty(String input) {
        if (input == ""){
            return true;
        }
        else
            return false;

    }
    private boolean isNegativNumber(String input) {
        if (Double.parseDouble(input) < 0){
            return true;
        }
        else
            return false;

    }

}
