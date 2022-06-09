package Roommate;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@NamedQueries({
        @NamedQuery(name = "Preferences.UpdatePreferences",
                query = "update Preferences p set p.pozprefered=p.pozprefered+1 where p.idperson=?1 and p.pozprefered>?2"),
        @NamedQuery(name = "Preferences.DeletePreferences",
                query = "delete from Preferences p where p.idperson=?1"),
        @NamedQuery(name = "Preferences.GetPreferences",
                query = "select p from Preferences p where p.idperson=?1"),
        @NamedQuery(name = "Preferences.DeleteSPreferences", query = "select p from Preferences p where p.idperson=?1 or p.idprefered=?1")
})
public class Preferences {
    @Basic
    @Id
    @Column(name = "IDPERSON")
    private Integer idperson;
    @Basic
    @Id
    @Column(name = "IDPREFERED")
    private BigInteger idprefered;
    @Basic
    @Column(name = "POZPREFERED")
    private Integer pozprefered;

    public Preferences(Integer i, Integer id, int indexOf) {
        this.idperson= i;
        this.idprefered= BigInteger.valueOf(id);
        this.pozprefered= indexOf;
    }

    public Integer getIdperson() {
        return idperson;
    }

    public void setIdperson(Integer idperson) {
        this.idperson = idperson;
    }

    public BigInteger getIdprefered() {
        return idprefered;
    }

    public void setIdprefered(BigInteger idprefered) {
        this.idprefered = idprefered;
    }

    public Integer getPozprefered() {
        return pozprefered;
    }

    public void setPozprefered(Integer pozprefered) {
        this.pozprefered = pozprefered;
    }

    public Preferences() {
    }

    public Preferences(Integer idperson, BigInteger idprefered, Integer pozprefered) {
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
