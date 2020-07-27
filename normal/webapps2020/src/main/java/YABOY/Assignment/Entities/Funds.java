package YABOY.Assignment.Entities;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import YABOY.Assignment.Currency;
import java.io.Serializable;

/**
 * @author 164645
 */
@Entity
public class Funds implements Serializable {
    
    @Id
    @OneToOne(optional=true)
    @JoinColumn(name="ID")
    private Credentials id;
    
    @NotNull
    private double funds;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private Currency currency;
    
    public Funds(){}

    public Funds(Credentials id, double funds, Currency currency) {
        this.id = id;
        this.funds = funds;
        this.currency = currency;
    }

    public Credentials getId() {
        return id;
    }

    public void setId(Credentials id) {
        this.id = id;
    }

    public double getFunds() {
        return funds;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.id);
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.funds) ^ (Double.doubleToLongBits(this.funds) >>> 32));
        hash = 43 * hash + Objects.hashCode(this.currency);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Funds other = (Funds) obj;
        if (Double.doubleToLongBits(this.funds) != Double.doubleToLongBits(other.funds)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return this.currency == other.currency;
    }
    
    public void credit(double amount){
        this.funds += amount;
    }
    
    public void debit(double amount){
        this.funds -= amount;
    }
}
