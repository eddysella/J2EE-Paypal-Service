package YABOY.Assignment.JSF;

import YABOY.Assignment.EJB.UserService;
import java.io.Serializable;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 * @author 164645
 */
@Named
@RequestScoped
@RolesAllowed("User")
public class PaymentRequestBean implements Serializable{
    
    @EJB
    UserService userService;
    
    String username;
    String amount;
    String error;

    public PaymentRequestBean(){}
    
    public String confirm(){
        String outcome = userService.confirmPaymentRequest(username, amount);
        if("success".equals(outcome)){
            return outcome;
        }else{
            setError(outcome);
            return "error";
        }
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
