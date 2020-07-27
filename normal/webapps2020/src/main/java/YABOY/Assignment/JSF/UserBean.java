package YABOY.Assignment.JSF;

import YABOY.Assignment.EJB.UserService;
import YABOY.Assignment.Entities.PaymentRequests;
import YABOY.Assignment.Entities.Transactions;
import java.io.Serializable;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * @author 164645
 */
@Named
@RequestScoped
@RolesAllowed("User")
public class UserBean implements Serializable{
    
    Long transID;
    
    @EJB
    UserService userService;
    
    public UserBean(){}

    public Long getTransID() {
        return transID;
    }

    public void setTransID(Long transID) {
        this.transID = transID;
    }
    
    @RolesAllowed({"User", "Admin"})
    public String getUsername() {
        return userService.fetchUsername();
    }

    @RolesAllowed({"User", "Admin"})
    public String getCurrency() {
        return userService.fetchUserCurrency();
    }

    @RolesAllowed({"User", "Admin"})
    public String getFunds() {
        return userService.fetchUserFunds();
    }
    
    public void acceptRequest(){
        userService.acceptRequest(transID);
    }
    
    public void declineRequest(){
        userService.declineRequest(transID);
    }
    
    public List<Transactions> getInboundTransactions(){
        return userService.getInboundTransactions();
    }
    
    public List<Transactions> getOutboundTransactions(){
        return userService.getOutboundTransactions();
    }
    
    public List<PaymentRequests> getInboundRequests(){
        return userService.getInboundRequests();
    }
    
    public List<PaymentRequests> getOutboundRequests(){
        return userService.getOutboundRequests();
    }
}
