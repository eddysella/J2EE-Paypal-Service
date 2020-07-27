package YABOY.DAO;

import YABOY.Assignment.Entities.Credentials;
import YABOY.Assignment.Entities.UserGroup;
import javax.ejb.Local;

/**
 * @author 164645
 */
@Local
public interface UserGroupStorageService {
    public UserGroup find(Credentials user);
    public void addGroupForUser(UserGroup sys_user_group);
}
