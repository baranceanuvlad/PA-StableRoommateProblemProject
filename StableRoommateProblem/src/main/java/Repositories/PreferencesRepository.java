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
}
