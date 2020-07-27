package YABOY.Assignment;

/**
 * @author 164645
 */
public enum Currency {
    GBP("Sterling"),EUR("Euro"),DLR("Dollar");
    
    String name;
    
    Currency(String name){
        this.name=name;
    }

    public String getLabel() {
        return name;
    }
    
    public static Currency getCurrency(String label){
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

