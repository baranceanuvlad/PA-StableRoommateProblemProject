package Roommate;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
public class Preferences {
    @Basic
    @Id
    @Column(name = "IDPERSON")
    private BigInteger idperson;
    @Basic
    @Id
    @Column(name = "IDPREFERED")
    private BigInteger idprefered;
    @Basic
    @Column(name = "POZPREFERED")
    private BigInteger pozprefered;

    public BigInteger getIdperson() {
        return idperson;
    }

    public void setIdperson(BigInteger idperson) {
        this.idperson = idperson;
    }

    public BigInteger getIdprefered() {
        return idprefered;
    }

    public void setIdprefered(BigInteger idprefered) {
        this.idprefered = idprefered;
    }

    public BigInteger getPozprefered() {
        return pozprefered;
    }

    public void setPozprefered(BigInteger pozprefered) {
        this.pozprefered = pozprefered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Preferences that = (Preferences) o;

        if (idperson != null ? !idperson.equals(that.idperson) : that.idperson != null) return false;
        if (idprefered != null ? !idprefered.equals(that.idprefered) : that.idprefered != null) return false;
        if (pozprefered != null ? !pozprefered.equals(that.pozprefered) : that.pozprefered != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idperson != null ? idperson.hashCode() : 0;
        result = 31 * result + (idprefered != null ? idprefered.hashCode() : 0);
        result = 31 * result + (pozprefered != null ? pozprefered.hashCode() : 0);
        return result;
    }
}
