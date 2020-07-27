package YABOY.Assignment.EJB;

import YABOY.Assignment.Entities.Credentials;
import YABOY.Assignment.Entities.UserGroup;
import YABOY.DAO.CredentialsStorageService;
import YABOY.DAO.UserGroupStorageService;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;

/**
 * @author 164645
 */
@Stateless
@RolesAllowed("Admin")
public class AdminRegisterService {
    
    @EJB
    CredentialsStorageService credentialsStoreService;
    
    @EJB
    UserGroupStorageService userGroupService;

    public AdminRegisterService(){}

    public String registerAdmin(String username, String userpassword) {
        if(credentialsStoreService.exists(username)){
            return "usernameTaken";
        }
        try{
            Credentials sys_user;
            UserGroup sys_user_group;
            
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String passwd = userpassword;
            md.update(passwd.getBytes("UTF-8"));
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
            }   String paswdToStoreInDB = sb.toString();
            
            sys_user = new Credentials(username, paswdToStoreInDB);
            sys_user_group = new UserGroup(sys_user, "Admin");
            credentialsStoreService.addCredentialsForUser(sys_user);
            userGroupService.addGroupForUser(sys_user_group);
            return "success";
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(AdminRegisterService.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
    }
}
