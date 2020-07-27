package YABOY.Assignment.EJB;

import YABOY.DAO.TransactionStorageService;
import YABOY.Assignment.Entities.Credentials;
import YABOY.Assignment.Entities.Transactions;
import YABOY.DTO.User;
import YABOY.DAO.CredentialsStorageService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

/**
 * @author 164645
 */
@Stateless
@RolesAllowed("Admin")
public class AdminServiceBean implements AdminService{
    
    @Resource
    SessionContext session;
    
    @EJB
    CredentialsStorageService credentialsStoreService;
    
    @EJB
    TransactionStorageService transStoreService;
    
    User curUser;
    
    Long transID;
    
    public AdminServiceBean(){}
    
    private void initCurUser(){
        curUser = credentialsStoreService.getUser(session.getCallerPrincipal().getName(), "Admin");
    }
    
    @Override
    public String getUsername(){
        initCurUser();
        return curUser.getUsername();
    }

    @Override
    public List<User> getUsers() {
        List<Credentials> credentials = credentialsStoreService.getAllUsers();
        List<User> output = new ArrayList();
        credentials.forEach((user) -> {
            output.add(new User(user));
        });
        return output;
    }

    @Override
    public List<Transactions> getTransactions() {
        return transStoreService.getAllTransactions();
    }
}
