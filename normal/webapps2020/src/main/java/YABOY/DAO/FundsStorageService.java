package YABOY.DAO;

import YABOY.Assignment.Entities.Funds;
import javax.ejb.Local;

/**
 * @author 164645
 */
@Local
public interface FundsStorageService {
    public Funds find(Long id);
    public String getFunds(Long id);
    public void initFundsForUser(Funds funds);
}
