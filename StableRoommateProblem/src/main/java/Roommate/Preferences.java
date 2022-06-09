package Roommate;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@NamedQueries(
        @NamedQuery(name="UpdatePreferences",
            query = "update Preferences p set p.pozprefered=p.pozprefered+1 where p.idperson=?1 and p.pozprefered>?2")
)
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

    public Preferences(int i, Integer id, int indexOf) {
        this.idperson= BigInteger.valueOf(i);
        this.idprefered= BigInteger.valueOf(id);
        this.pozprefered= BigInteger.valueOf(indexOf);
    }

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

    public Preferences() {
    }

    public Preferences(BigInteger idperson, BigInteger idprefered, BigInteger pozprefered) {
        this.idperson = idperson;
        this.idprefered = idprefered;
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

    @Override
    public String toString() {
        return "Preferences{" +
                "idperson=" + idperson +
                ", idprefered=" + idprefered +
                ", pozprefered=" + pozprefered +
                '}';
    }
}
