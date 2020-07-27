package YABOY.DAO;

import YABOY.Assignment.Entities.PaymentRequests;
import java.util.List;
import javax.ejb.Local;

/**
 * @author 164645
 */
@Local
public interface PaymentRequestStorageService {
    public String insertRequest(String username, double amount);
    public List<PaymentRequests> getOutboundRequests();
    public List<PaymentRequests> getInboundRequests();
    public void acceptRequest(Long transID);
    public void declineRequest(Long transID);
}
