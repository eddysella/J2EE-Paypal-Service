package YABOY.Assignment.JSF;

import YABOY.Assignment.EJB.UserRegisterService;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import YABOY.Assignment.Currency;

/**
 * @author 164645
 */
@Named
@RequestScoped
public class RegistrationBean implements Serializable{

    @EJB
    UserRegisterService registerService;
    
    String username;
    String password;
    Currency currency;
    String error = "";

    public RegistrationBean(){}

    //call the injected EJB
    public String register() {
        String outcome = registerService.registerUser(username, password, currency);
        if("usernameTaken".equals(outcome)){
            setError("This username is taken");
            return "error";
        }else{
            return outcome;
        }
    }
    
    public Currency[] getCurrencies(){
        return Currency.values();
    }

    public UserRegisterService getRegisterService() {
        return registerService;
    }

    public void setRegisterService(UserRegisterService registerService) {
        this.registerService = registerService;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
