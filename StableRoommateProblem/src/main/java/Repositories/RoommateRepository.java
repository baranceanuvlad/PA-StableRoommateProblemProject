package Repositories;

import Roommate.Roommates;
import Utilities.PersistanceManager;

import java.util.List;

public class RoommateRepository {
    static public void create(Roommates entity){
        PersistanceManager.getEm().getTransaction().begin();
        PersistanceManager.getEm().persist(entity);
        PersistanceManager.getEm().getTransaction().commit();
    }
    static public List<Roommates> findAll(){
        return PersistanceManager.getEm().createNamedQuery("Roommates.getAll")
                .getResultList();
    }
    static public Roommates findByFirstNameLastName(String firstName,String lastName){
        return (Roommates) PersistanceManager.getEm().createNamedQuery("Roommates.findByName")
                .setParameter(1,firstName)
                .setParameter(2,lastName)
                .getSingleResult();
    }
    static public List<Roommates> findPreferences(int id){

        return PersistanceManager.getEm().createNamedQuery("Roommates.getPreferences")
                .setParameter(1,id)
                .getResultList();
    }
}
