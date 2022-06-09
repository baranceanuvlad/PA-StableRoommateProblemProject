package Repositories;

import Roommate.Preferences;
import Roommate.Roommates;
import Utilities.PersistanceManager;

public class PreferencesRepository {
    static public void create(Preferences entity){
        PersistanceManager.getEm().getTransaction().begin();
        PersistanceManager.getEm().persist(entity);
        PersistanceManager.getEm().getTransaction().commit();
    }
    static public void updatePreferences(int id,int poz){
        PersistanceManager.getEm().createNamedQuery("UpdatePreferences")
                .setParameter(1,id)
                .setParameter(2,poz);
    }
    static public void deletePreferences(int id){
        PersistanceManager.getEm().createNamedQuery("DeletePreferences")
                .setParameter(1,id);
    }
    static public void deleteSPreferences(int id){
        PersistanceManager.getEm().createNamedQuery("DeleteSPreferences")
                .setParameter(1,id);
    }
}