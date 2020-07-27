package YABOY.Assignment.JSF;

import YABOY.Assignment.EJB.AdminService;
import YABOY.Assignment.Entities.Transactions;
import YABOY.DTO.User;
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
@RolesAllowed("Admin")
public class AdminBean implements Serializable{
    
    @EJB
    AdminService adminService;
    
    public AdminBean(){}
    
    public String getUsername(){
        return adminService.getUsername();
    }
    
    public List<User> getUsers(){
        return adminService.getUsers();
    }

    public List<Transactions> getTransactions(){
        return adminService.getTransactions();
    }
}
