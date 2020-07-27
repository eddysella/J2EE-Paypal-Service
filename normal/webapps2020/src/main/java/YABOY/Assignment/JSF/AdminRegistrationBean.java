package YABOY.Assignment.JSF;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import YABOY.Assignment.Currency;
import YABOY.Assignment.EJB.AdminRegisterService;
import javax.annotation.security.RolesAllowed;

/**
 * @author 164645
 */
@Named
@RequestScoped
@RolesAllowed("Admin")
public class AdminRegistrationBean implements Serializable{

    @EJB
    AdminRegisterService registerService;
    
    String username;
    String password;
    String error = "";

    public AdminRegistrationBean(){}
    
    public String register() {
        String outcome = registerService.registerAdmin(username, password);
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

    public AdminRegisterService getRegisterService() {
        return registerService;
    }

    public void setRegisterService(AdminRegisterService registerService) {
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
