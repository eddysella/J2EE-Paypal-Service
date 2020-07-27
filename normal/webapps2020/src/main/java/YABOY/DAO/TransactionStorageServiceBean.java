package YABOY.DAO;

import YABOY.Assignment.Currency;
import YABOY.Assignment.Entities.Credentials;
import YABOY.Assignment.Entities.Funds;
import YABOY.Assignment.Entities.Transactions;
import YABOY.Assignment.Entities.UserGroup;
import YABOY.Assignment.API.APIAccess;
import YABOY.Thirft.ThriftClient;
import java.util.List;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author 164645
 */
@Stateless
@RolesAllowed("User")
public class TransactionStorageServiceBean implements TransactionStorageService {
    
    @PersistenceContext
    EntityManager em;

    @Resource
    SessionContext session;
    
    @EJB
    CredentialsStorageService credentialsStoreService;
    
    @EJB
    FundsStorageService fundsService;
    
    @EJB
    UserGroupStorageService userGroupService;
    
    ThriftClient tClient;
    
    Credentials curUser;
    
    public TransactionStorageServiceBean(){}
    
    private void initCurUser(){
        curUser = credentialsStoreService.getCredentials(session.getCallerPrincipal().getName());
    }
    
    /**
     * Sends fromAmount of money to username
     * @param username
     * @param fromAmount
     * @return 
     */
    @Override
    public synchronized String insertTransaction(String username, double fromAmount) {
        initCurUser();
        if(curUser.getUsername().equals(username)){
            return "invalidUser";
        }
        double toAmount = fromAmount;
        Credentials toUser = credentialsStoreService.getCredentials(username);
        if(toUser == null){
            return "invalidUser";
        }
        UserGroup toUserGroup = userGroupService.find(toUser);
        if(!"User".equals(toUserGroup.getGroupname())){
            return "invalidUser";
        }
        Funds curUserFunds = fundsService.find(curUser.getId());
        if(curUserFunds.getFunds() < fromAmount){
            return "insufficientFunds";
        }
        Funds toUserFunds = fundsService.find(toUser.getId());
        Currency toUserCurrency = toUserFunds.getCurrency();
        Currency curUserCurrency = curUserFunds.getCurrency();
        
        if(curUserCurrency != toUserCurrency){
            APIAccess API = new APIAccess();
            double convR = API.getRate(curUserCurrency.getLabel() , toUserCurrency.getLabel());
            toAmount = fromAmount * convR;
        }
        
        toUserFunds.credit(toAmount);
        curUserFunds.debit(fromAmount);
        
        em.persist(new Transactions(curUser, fromAmount, toUser, toAmount, tClient.getTimestamp()));
        em.flush();
        return "success";
    }

    @Override
    public List<Transactions> getOutboundTransactions() {
        initCurUser();
        TypedQuery<Transactions> transactions = em.createQuery(
            "Select c FROM Transactions c WHERE c.from = :user",Transactions.class);
        return transactions.setParameter("user", curUser).getResultList();
    }

    @Override
    public List<Transactions> getInboundTransactions() {
        initCurUser();
        TypedQuery<Transactions> transactions = em.createQuery(
            "Select c FROM Transactions c WHERE c.to = :user",Transactions.class);
        return transactions.setParameter("user", curUser).getResultList();
    }

    @Override
    @RolesAllowed("Admin")
    public List<Transactions> getAllTransactions() {
        return em.createQuery("SELECT c FROM Transactions c", Transactions.class).getResultList();
    }
    
}
