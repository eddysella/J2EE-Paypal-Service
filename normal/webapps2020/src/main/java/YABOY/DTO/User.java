package YABOY.DTO;

import YABOY.Assignment.Entities.Credentials;
import YABOY.Assignment.Entities.Funds;

/**
 * @author 164645
 */
public class User {
    
    private Long id;
    private String username;
    private String currency;
    private String funds;
    
    public User(Credentials credentials){
        this.username = credentials.getUsername();
    }

    public User(Credentials credentials, Funds funds) {
        this.id = credentials.getId();
        this.username = credentials.getUsername();
        this.currency = funds.getCurrency().getLabel();
        this.funds = Double.toString(funds.getFunds());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getFunds() {
        return funds;
    }

    public void setFunds(String funds) {
        this.funds = funds;
    }
}
