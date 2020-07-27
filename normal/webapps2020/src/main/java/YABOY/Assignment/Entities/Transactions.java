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
public class Transactions implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trans_id")
    private Long id;

    @ManyToOne(cascade = ALL)
    private Credentials from;
    
    @OneToOne
    private Credentials to;
    
    private double fromAmount;
    
    private double toAmount;
    
    private long timestamp;
    
    public Transactions(){}

    public Transactions(Credentials from, double fromAmount, Credentials to, double toAmount, long timestamp) {
        this.from = from;
        this.to = to;
        this.fromAmount = fromAmount;
        this.toAmount = toAmount;
        this.timestamp = timestamp;
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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.from);
        hash = 37 * hash + Objects.hashCode(this.to);
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.fromAmount) ^ (Double.doubleToLongBits(this.fromAmount) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.toAmount) ^ (Double.doubleToLongBits(this.toAmount) >>> 32));
        hash = 37 * hash + (int) (this.timestamp ^ (this.timestamp >>> 32));
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
        final Transactions other = (Transactions) obj;
        if (Double.doubleToLongBits(this.fromAmount) != Double.doubleToLongBits(other.fromAmount)) {
            return false;
        }
        if (Double.doubleToLongBits(this.toAmount) != Double.doubleToLongBits(other.toAmount)) {
            return false;
        }
        if (this.timestamp != other.timestamp) {
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