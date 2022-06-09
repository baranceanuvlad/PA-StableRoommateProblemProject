package Roommate;

import Utilities.PersistanceManager;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;
@NamedQueries({
        @NamedQuery(name = "Roommates.getPreferences",
                query = "select e from Roommates e join Preferences p on p.idprefered=e.id where p.idperson=?1 order by p.pozprefered"),
        @NamedQuery(name = "Roommates.getAll",
                query = "select e from Roommates e"),
        @NamedQuery(name="Roommates.findByName", query = "select e from Roommates e where e.firstname=?1 and e.lastname=?2")
})
@Entity
public class Roommates {
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "sequence_roommates")
    @SequenceGenerator(
            name = "sequence_roommates",
            allocationSize = 1
    )
    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Basic
    @Column(name = "LASTNAME")
    private String lastname;
    @Transient
    public List<Roommates> preferences;
    @Transient
    int index;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPreferences(){
        this.preferences=PersistanceManager.getEm().createNamedQuery("Roommates.getPreferences")
                .setParameter(1,this.id)
                .getResultList();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Roommates() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Roommates roommates = (Roommates) o;

        if (id != null ? !id.equals(roommates.id) : roommates.id != null) return false;
        if (firstname != null ? !firstname.equals(roommates.firstname) : roommates.firstname != null) return false;
        if (lastname != null ? !lastname.equals(roommates.lastname) : roommates.lastname != null) return false;

        return true;
    }

    public Roommates(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Roommates{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                //", preferences=" + preferences +
                '}';
    }
}