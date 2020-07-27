package YABOY.DAO;

import YABOY.Assignment.Currency;
import YABOY.Assignment.Entities.Credentials;
import YABOY.Assignment.Entities.Funds;
import YABOY.Assignment.Entities.PaymentRequests;
import YABOY.Assignment.Entities.UserGroup;
import YABOY.Assignment.API.APIAccess;
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
public class PaymentRequestStorageServiceBean implements PaymentRequestStorageService {
    
    @PersistenceContext
    EntityManager em;

    @Resource
    SessionContext session;
    
    @EJB
    CredentialsStorageService credentialsStoreService;
    
    @EJB
    TransactionStorageService transStoreService;
    
    @EJB
    FundsStorageService fundsService;
    
    @EJB
    UserGroupStorageService userGroupService;
    
    Credentials curUser;
    
    public PaymentRequestStorageServiceBean(){}
    
    private void initCurUser(){
        curUser = credentialsStoreService.getCredentials(session.getCallerPrincipal().getName());
    }

    @Override
    public synchronized String insertRequest(String username, double fromAmount) {
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
        Funds toUserFunds = fundsService.find(toUser.getId());
        Currency toUserCurrency = toUserFunds.getCurrency();
        Currency curUserCurrency = curUserFunds.getCurrency();
        
        if(curUserCurrency != toUserCurrency){
            APIAccess API = new APIAccess();
            double convR = API.getRate(curUserCurrency.getLabel() , toUserCurrency.getLabel());
            toAmount = fromAmount * convR;
        }
        
        em.persist(new PaymentRequests(curUser, fromAmount, toUser, toAmount));
        em.flush();
        return "success";
    }

    @Override
    public List<PaymentRequests> getOutboundRequests() {
        initCurUser();
        TypedQuery<PaymentRequests> paymentReq = em.createQuery(
            "Select c FROM PaymentRequests c WHERE c.from = :user",
            PaymentRequests.class);
        return paymentReq.setParameter("user", curUser).getResultList();
    }

    @Override
    public List<PaymentRequests> getInboundRequests() {
        initCurUser();
        TypedQuery<PaymentRequests> paymentReq = em.createQuery(
            "Select c FROM PaymentRequests c WHERE c.to = :user",
            PaymentRequests.class);
        return paymentReq.setParameter("user", curUser).getResultList();
    }

    @Override
    public synchronized void acceptRequest(Long transID) {
        initCurUser();
        PaymentRequests request = em.find(PaymentRequests.class, transID);
        if("success".equals(transStoreService.insertTransaction(request.getFrom().getUsername(), request.getToAmount()))){
            request.setAccepted(true);
            em.flush();
        }
    }

    @Override
    public void declineRequest(Long transID) {
        initCurUser();
        PaymentRequests request = em.find(PaymentRequests.class, transID);
        request.setDeclined(true);
        em.flush();
    }
}
