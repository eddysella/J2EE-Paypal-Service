package YABOY.Assignment.Entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author 164645
 */
@Entity
public class UserGroup implements Serializable {
    
    @Id
    @OneToOne
    @JoinColumn(name="ID")
    private Credentials id;
    private String username;
    private String groupname;
    
    public UserGroup(){}

    public UserGroup(Credentials id, String groupname) {
        this.id = id;
        this.username = id.getUsername();
        this.groupname = groupname;
    }

    public Credentials getCredentials() {
        return id;
    }

    public void setCredentials(Credentials id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.username);
        hash = 97 * hash + Objects.hashCode(this.groupname);
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
        final UserGroup other = (UserGroup) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.groupname, other.groupname)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }
}
