/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YABOY.Assignment.EJB;

import YABOY.Assignment.Entities.PaymentRequests;
import YABOY.Assignment.Entities.Transactions;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 164645
 */
@Local
public interface UserService {
    
    public String fetchUserFunds();
    public String fetchUserCurrency();
    public String fetchUsername();
    public void acceptRequest(Long transID);
    public List<PaymentRequests> getOutboundRequests();
    public List<PaymentRequests> getInboundRequests();
    public List<Transactions> getOutboundTransactions();
    public List<Transactions> getInboundTransactions();
    public void declineRequest(Long transID);
    public String confirmPayment(String username, String amount);
    public String confirmPaymentRequest(String username, String amount);
}
