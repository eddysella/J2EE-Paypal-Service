package YABOY.DAO;

import YABOY.Assignment.Entities.Credentials;
import YABOY.DTO.User;
import java.util.List;
import javax.ejb.Local;

/**
 * @author 164645
 */
@Local
public interface CredentialsStorageService {
    public List<Credentials> getAllUsers();
    public boolean exists(String username);
    public Credentials getCredentials(String username);
    public User getUser(String name, String type);
    public void addCredentialsForUser(Credentials sys_user);
}
