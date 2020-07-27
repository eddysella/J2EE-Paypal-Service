package YABOY.DAO;

import YABOY.Assignment.Entities.Funds;
import java.text.DecimalFormat;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 164645
 */
@Stateless
public class FundsStorageServiceBean implements FundsStorageService {
    
    @PersistenceContext
    EntityManager em;

    public FundsStorageServiceBean(){}

    @Override
    @RolesAllowed({"User", "Admin"})
    public Funds find(Long id) {
        return em.find(Funds.class, id);
    }

    @Override
    @RolesAllowed({"User", "Admin"})
    public String getFunds(Long id) {
        DecimalFormat df = new DecimalFormat("#.##");
        Double funds = find(id).getFunds();
        return df.format(funds);
    }

    @Override
    public void initFundsForUser(Funds funds) {
        em.persist(funds);
    }
    
}
