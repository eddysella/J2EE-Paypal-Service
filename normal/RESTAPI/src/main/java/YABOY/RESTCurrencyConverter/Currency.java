package YABOY.RESTCurrencyConverter;

/**
 * This class encapsulates all info about a currency.
 * To add a new currency add it here, to change the 
 * value change the constructor parameter.
 * Sterling is base currency at value 1, all other currencies
 * are based off of it.
 * @author 164645
 */
public enum Currency {
    GBP("Sterling", 1),EUR("Euro", 1.24),DLR("Dollar", 1.12);
    
    String name;
    
    double value;
    
    Currency(String name, double value){
        this.name=name;
        this.value=value;
    }

    public String getLabel() {
        return name;
    }
    
    public double getValue() {
        return value;
    }
    
    public static Currency stringToCurrency(String label){
        switch(label){
            case "Sterling":
                return Currency.GBP;
            case "Euro":
                return Currency.EUR;
            case "Dollar":
                return Currency.DLR;
            default:
                return null;
        }
    }
}

