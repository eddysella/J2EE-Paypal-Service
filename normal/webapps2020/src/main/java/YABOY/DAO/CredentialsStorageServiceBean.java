package YABOY.DAO;

import YABOY.Assignment.Entities.Credentials;
import YABOY.Assignment.Entities.Funds;
import YABOY.DTO.User;
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
public class CredentialsStorageServiceBean implements CredentialsStorageService {
    
    @EJB
    FundsStorageService fundsService;
    
    @PersistenceContext
    EntityManager em;

    @Resource
    SessionContext session;
    
    TypedQuery<Credentials> curUserQuery;
    
    public CredentialsStorageServiceBean(){}
    
    @Override
    @RolesAllowed("Admin")
    public List<Credentials> getAllUsers() {
        return em.createQuery("SELECT c FROM Credentials c", Credentials.class).getResultList();
    }

    @Override
    public boolean exists(String username) {
        return getCredentials(username) != null;
    }

    @Override
    public Credentials getCredentials(String username) {
        curUserQuery = em.createQuery("SELECT c FROM Credentials c WHERE c.username = :username", Credentials.class);
        Credentials user = null;
        try{
             user = curUserQuery.setParameter("username", username).getSingleResult();
        }catch(Exception e){}
        return user;
    }

    @Override
    public User getUser(String username, String type){
        Credentials credentials = getCredentials(username);
        switch(type){
            case "Admin":
                return new User(credentials);
            case "User":
                Funds funds = fundsService.find(credentials.getId());
                return new User(credentials, funds);       
            default:
                return null;
        }
    }

    @Override
    public void addCredentialsForUser(Credentials sys_user) {
        em.persist(sys_user);
    }

}
