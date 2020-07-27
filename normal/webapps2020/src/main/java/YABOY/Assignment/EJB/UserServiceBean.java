package YABOY.Assignment.EJB;

import YABOY.Assignment.Entities.PaymentRequests;
import YABOY.Assignment.Entities.Transactions;
import YABOY.DAO.PaymentRequestStorageService;
import YABOY.DAO.TransactionStorageService;
import YABOY.DTO.User;
import YABOY.DAO.CredentialsStorageService;
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
@RolesAllowed("User")
public class UserServiceBean implements UserService{

    @Resource
    SessionContext session;
    
    @EJB
    CredentialsStorageService credentialsStoreService;
            
    @EJB
    TransactionStorageService transStoreService;
    
    @EJB
    PaymentRequestStorageService payReqStoreService;
    
    User curUser;
    
    Long transID;
    
    public UserServiceBean(){}
     
    private void initCurUser(){
        curUser = credentialsStoreService.getUser(session.getCallerPrincipal().getName(), "User");
    }
    
    @Override
    public synchronized String fetchUserFunds(){
        initCurUser();
        return curUser.getFunds();
    }
    
    @Override
    public synchronized String fetchUserCurrency(){
        initCurUser();
        return curUser.getCurrency();
    }

    @Override
    public synchronized String fetchUsername(){
        initCurUser();
        return curUser.getUsername();
    }

    @Override
    public void acceptRequest(Long transID) {
        payReqStoreService.acceptRequest(transID);
    }
    
    @Override
    public void declineRequest(Long transID) {
        payReqStoreService.declineRequest(transID);
    }

    @Override
    public List<PaymentRequests> getOutboundRequests() {
        return payReqStoreService.getOutboundRequests();
    }

    @Override
    public List<PaymentRequests> getInboundRequests() {
        return payReqStoreService.getInboundRequests();
    }

    @Override
    public List<Transactions> getOutboundTransactions() {
        return transStoreService.getOutboundTransactions();
    }

    @Override
    public List<Transactions> getInboundTransactions() {
        return transStoreService.getInboundTransactions();
    }
    
    @Override
    public String confirmPayment(String username, String amount){
        String outcome = transStoreService.insertTransaction(username, Double.parseDouble(amount));
        if(!"success".equals(outcome)){
            switch(outcome){
                case "insufficientFunds":
                    return "Insufficient Funds";
                case "invalidUser":
                    return "Username Invalid";
            }
            return "error";
        }else{
            return outcome;
        }
    }

    @Override
    public String confirmPaymentRequest(String username, String amount) {
        String outcome = payReqStoreService.insertRequest(username, Double.parseDouble(amount));
        if(!"success".equals(outcome)){
            switch(outcome){
                case "invalidUser":
                    return "Username Invalid";
            }
            return "error";
        }else{
            return outcome;
        }
    }
}
