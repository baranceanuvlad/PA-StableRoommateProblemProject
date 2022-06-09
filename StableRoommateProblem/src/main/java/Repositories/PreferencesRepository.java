package Repositories;

import Roommate.Preferences;
import Roommate.Roommates;
import Utilities.PersistanceManager;

import java.math.BigInteger;
import java.util.List;

public class PreferencesRepository {
    static public void create(Preferences entity){
        PersistanceManager.getEm().getTransaction().begin();
        PersistanceManager.getEm().persist(entity);
        PersistanceManager.getEm().getTransaction().commit();
    }
    static public void updatePreferences(int id,int poz){
        PersistanceManager.getEm().getTransaction().begin();
        List<Preferences> pref=PersistanceManager.getEm().createNamedQuery("Preferences.GetPreferences").setParameter(1,id).getResultList();
        System.out.println(pref);
        for(Preferences prefer:pref){
            System.out.println(prefer.getPozprefered());
            if(prefer.getPozprefered()>=poz) {
                System.out.println("1");
                prefer.setPozprefered(prefer.getPozprefered() + 1);
                PersistanceManager.getEm().merge(prefer);
            }

        }
        PersistanceManager.getEm().getTransaction().commit();
    }
    static public void deletePreferences(int id){
        PersistanceManager.getEm().getTransaction().begin();
        System.out.println(id);
        List<Preferences> pref=PersistanceManager.getEm().createNamedQuery("Preferences.GetPreferences").setParameter(1,id).getResultList();
        for(Preferences prefer:pref){
            PersistanceManager.getEm().remove(prefer);
        }
        PersistanceManager.getEm().getTransaction().commit();
    }
    static public void deleteSPreferences(int id){
        PersistanceManager.getEm().getTransaction().begin();
        List<Preferences> lista=PersistanceManager.getEm().createNamedQuery("Preferences.DeleteSPreferences")
                .setParameter(1,id)
                .getResultList();
        for (Preferences pref:lista){
            PersistanceManager.getEm().remove(pref);
            if(pref.getIdperson()!=id){
                resetPreferences(pref.getIdperson(), pref.getPozprefered());
            }
        }
        PersistanceManager.getEm().getTransaction().commit();
    }
    static public void resetPreferences(int id,int poz){
        List<Preferences> pref=PersistanceManager.getEm().createNamedQuery("Preferences.GetPreferences").setParameter(1,id).getResultList();
        System.out.println(pref);
        for(Preferences prefer:pref){
            System.out.println(prefer.getPozprefered());
            if(prefer.getPozprefered()>=poz) {
                System.out.println("1");
                prefer.setPozprefered(prefer.getPozprefered() - 1);
                PersistanceManager.getEm().merge(prefer);
            }

        }
    }
}
