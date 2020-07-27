package YABOY.Assignment.EJB;

import YABOY.Assignment.Entities.Credentials;
import YABOY.Assignment.Entities.Funds;
import YABOY.Assignment.Entities.UserGroup;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import YABOY.Assignment.Currency;
import YABOY.Assignment.API.APIAccess;
import YABOY.DAO.CredentialsStorageService;
import YABOY.DAO.FundsStorageService;
import YABOY.DAO.UserGroupStorageService;
import javax.ejb.EJB;

/**
 * @author 164645
 */
@Stateless
public class UserRegisterService {

    @EJB
    CredentialsStorageService credentialsStoreService;

    @EJB
    FundsStorageService fundsService;

    @EJB
    UserGroupStorageService userGroupService;

    public UserRegisterService() {
    }

    public String registerUser(String username, String userpassword, Currency currency) {
        if (credentialsStoreService.exists(username)) {
            return "usernameTaken";
        }
        try {
            Credentials sys_user;
            Funds funds;
            UserGroup sys_user_group;
            double amount = 1000;

            if (currency != Currency.GBP) {
                APIAccess API = new APIAccess();
                double convR = API.getRate("Sterling", currency.getLabel());
                amount = amount * convR;
            }

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String passwd = userpassword;
            md.update(passwd.getBytes("UTF-8"));
            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < digest.length; i++) {
                sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
            }
            String paswdToStoreInDB = sb.toString();

            sys_user = new Credentials(username, paswdToStoreInDB);
            funds = new Funds(sys_user, amount, currency);
            sys_user_group = new UserGroup(sys_user, "User");
            credentialsStoreService.addCredentialsForUser(sys_user);
            fundsService.initFundsForUser(funds);
            userGroupService.addGroupForUser(sys_user_group);
            return "success";
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(UserRegisterService.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
    }
}
