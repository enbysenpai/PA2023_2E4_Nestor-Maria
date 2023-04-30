package com.example.lab9_v2.Repository;

import com.example.lab9_v2.Model.Artist;
import com.sun.source.tree.TryTree;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ArtistRepository
{
    private final EntityManagerFactory entityManagerFactory;

    public ArtistRepository(EntityManagerFactory entityManagerFactory)
    {
        this.entityManagerFactory=entityManagerFactory;
    }

    public void create(Artist artist)
    {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Artist existingArtist = entityManager.createQuery("SELECT a FROM Artist a WHERE a.name= :name", Artist.class)
                    .setParameter("name", artist.getName())
                    .getSingleResult();

            if (existingArtist != null) {
                System.err.println("!!![THIS ARTIST EXISTS: ID=" + existingArtist.getId()+"]!!!");
            }
        }
        catch (NoResultException e)
        {
            int maxID=entityManager.createQuery("SELECT MAX(a.id) from Artist a",int.class)
                    .getSingleResult();

            artist.setId(maxID+1);

            entityManager.persist(artist);
            entityManager.getTransaction().commit();
            System.out.println("NEW ARTIST ADDED");
        }
        finally {
            entityManager.close();
        }

    }

    public Artist findById(int id)
    {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        Artist artist=entityManager.find(Artist.class,id);
        entityManager.close();
        if(artist!=null)
            return artist;
        else
        {
            System.err.println("!!![THIS ID IS NOT VALID: ID="+id+"]!!!");
            return null;
        }
    }

    public List<Artist> findByName(String namePattern)
    {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        List<Artist>artists=entityManager.createNamedQuery("Artist.findByName", Artist.class)
                .setParameter("namePattern","%"+namePattern+"%")
                .getResultList();
        entityManager.close();
        return artists;
    }
}
