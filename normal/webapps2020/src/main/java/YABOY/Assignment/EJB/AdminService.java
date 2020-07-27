package YABOY.Assignment.EJB;

import YABOY.Assignment.Entities.Transactions;
import YABOY.DTO.User;
import java.util.List;
import javax.ejb.Local;

/**
 * @author 164645
 */
@Local
public interface AdminService {
    public String getUsername();
    public List<User> getUsers();
    public List<Transactions> getTransactions();
}
