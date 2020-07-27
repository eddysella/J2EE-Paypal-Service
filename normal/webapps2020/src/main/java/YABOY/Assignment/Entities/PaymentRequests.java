package YABOY.Assignment.Entities;

import java.io.Serializable;
import java.util.Objects;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * @author 164645
 */
@Entity
public class PaymentRequests implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long id;

    @ManyToOne(cascade = ALL)
    private Credentials from;
    
    @OneToOne
    private Credentials to;
    
    private double fromAmount;
    
    private double toAmount;
    
    private boolean accepted;
    
    private boolean declined;
    
    public PaymentRequests(){}

    public PaymentRequests(Credentials from, double fromAmount, Credentials to, double toAmount) {
        this.from = from;
        this.to = to;
        this.fromAmount = fromAmount;
        this.toAmount = toAmount;
        this.accepted = false;
        this.declined = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Credentials getFrom() {
        return from;
    }

    public void setFrom(Credentials from) {
        this.from = from;
    }

    public Credentials getTo() {
        return to;
    }

    public void setTo(Credentials to) {
        this.to = to;
    }

    public double getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(double fromAmount) {
        this.fromAmount = fromAmount;
    }

    public double getToAmount() {
        return toAmount;
    }

    public void setToAmount(double toAmount) {
        this.toAmount = toAmount;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean isDeclined() {
        return declined;
    }

    public void setDeclined(boolean declined) {
        this.declined = declined;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.from);
        hash = 59 * hash + Objects.hashCode(this.to);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.fromAmount) ^ (Double.doubleToLongBits(this.fromAmount) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.toAmount) ^ (Double.doubleToLongBits(this.toAmount) >>> 32));
        hash = 59 * hash + (this.accepted ? 1 : 0);
        hash = 59 * hash + (this.declined ? 1 : 0);
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
        final PaymentRequests other = (PaymentRequests) obj;
        if (Double.doubleToLongBits(this.fromAmount) != Double.doubleToLongBits(other.fromAmount)) {
            return false;
        }
        if (Double.doubleToLongBits(this.toAmount) != Double.doubleToLongBits(other.toAmount)) {
            return false;
        }
        if (this.accepted != other.accepted) {
            return false;
        }
        if (this.declined != other.declined) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.from, other.from)) {
            return false;
        }
        return Objects.equals(this.to, other.to);
    }

}