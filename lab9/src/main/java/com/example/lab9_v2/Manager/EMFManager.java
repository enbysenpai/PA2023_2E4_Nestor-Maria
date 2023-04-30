package com.example.lab9_v2.Manager;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMFManager
{
    private static EntityManagerFactory emf;
    private EMFManager(){}

    public static EntityManagerFactory getEntityManagerFactory()
    {
        if(emf==null)
        {
            emf= Persistence.createEntityManagerFactory("default");
        }
        return emf;
    }

    public static void closeEntityManagerFactory()
    {
        if(emf!=null)
        {
            emf.close();
            emf=null;
        }
    }
}
