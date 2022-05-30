package Repositories;

import Roommate.Roommates;
import Utilities.PersistanceManager;

import java.util.List;

public class RoommateRepository {
    public void create(Roommates entity){
        PersistanceManager.getEm().getTransaction().begin();
        PersistanceManager.getEm().persist(entity);
        PersistanceManager.getEm().getTransaction().commit();
    }
    static public List<Roommates> findAll(){
       return PersistanceManager.getEm().createNamedQuery("Roommates.getAll")
                .getResultList();
    }
}
