package Utilities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistanceManager {
    private static EntityManagerFactory emf=null;
    private static EntityManager em=null;

    private PersistanceManager(){

    }

    private static void CreateEntityManagerFactory(){
        if(emf==null)
            emf=Persistence.createEntityManagerFactory("ExamplePU");
    }
    private static void CreateEntityManager(){
        CreateEntityManagerFactory();
        if(em==null)
            em=emf.createEntityManager();
    }

    public static EntityManagerFactory getEmf() {
        CreateEntityManagerFactory();
        return emf;
    }

    public static EntityManager getEm() {
        CreateEntityManager();
        return em;
    }

    public void closeEntityManager(){
        if(em!=null)
            em.close();
    }

    public void closeEntityManagerFactory(){
        if(emf!=null)
            emf.close();
    }

}
