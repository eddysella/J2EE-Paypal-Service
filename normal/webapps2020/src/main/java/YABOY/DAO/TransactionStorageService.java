package YABOY.DAO;

import YABOY.Assignment.Entities.Transactions;
import java.util.List;
import javax.ejb.Local;

/**
 * @author 164645
 */
@Local
public interface TransactionStorageService {
    public String insertTransaction(String username, double amount);
    public List<Transactions> getOutboundTransactions();
    public List<Transactions> getInboundTransactions();
    public List<Transactions> getAllTransactions();
}
