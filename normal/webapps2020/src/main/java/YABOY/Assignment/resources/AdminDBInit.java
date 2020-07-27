package YABOY.Assignment.resources;

import YABOY.Assignment.EJB.AdminRegisterService;
import YABOY.Assignment.Entities.Credentials;
import YABOY.Assignment.Entities.UserGroup;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Adds an admin to the database if it doesn't already exist.
 * @author 164645
 */
@Startup
@Singleton
public class AdminDBInit {
    
    @PersistenceContext
    EntityManager em;
    
    @EJB
    AdminRegisterService regService;
    
    @PostConstruct
    public void initialize() {
        
        TypedQuery<Credentials> curUserQuery = em.createQuery("SELECT c FROM Credentials c WHERE c.username = :username", Credentials.class);
        if(curUserQuery.setParameter("username", "admin1").getResultList().isEmpty()){   
            try{
                Credentials sys_user;
                UserGroup sys_user_group;

                MessageDigest md = MessageDigest.getInstance("SHA-256");
                String passwd = "admin1";
                md.update(passwd.getBytes("UTF-8"));
                byte[] digest = md.digest();
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < digest.length; i++) {
                    sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
                }   String paswdToStoreInDB = sb.toString();
                // apart from the default constructor which is required by JPA
                // you need to also implement a constructor that will make the following code succeed
                sys_user = new Credentials("admin1", paswdToStoreInDB);
                sys_user_group = new UserGroup(sys_user, "Admin");
                em.persist(sys_user);
                em.persist(sys_user_group);
            }catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
                Logger.getLogger(AdminRegisterService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }   
}
