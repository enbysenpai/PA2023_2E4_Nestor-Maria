package com.example.lab9_v2.Repository;

import com.example.lab9_v2.Model.Album;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;

import java.util.List;

public class AlbumRepository
{
    private final EntityManagerFactory entityManagerFactory;

    public AlbumRepository(EntityManagerFactory entityManagerFactory)
    {
        this.entityManagerFactory=entityManagerFactory;
    }

    public void create(Album album)
    {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        try
        {
            entityManager.getTransaction().begin();

            Album existingAlbum=entityManager.createQuery("SELECT a FROM Album a WHERE a.title=:title",Album.class)
                    .setParameter("title",album.getTitle())
                    .getSingleResult();

            if(existingAlbum!=null)
            {
                System.err.println("!!![THIS ALBUM EXISTS: ID="+existingAlbum.getId()+"]!!!");
            }
        }
        catch (NoResultException e)
        {
            int maxId=entityManager.createQuery("SELECT MAX(a.id) FROM Album a",int.class)
                    .getSingleResult();

            album.setId(maxId+1);

            entityManager.persist(album);
            entityManager.getTransaction().commit();
            System.out.println("NEW ALBUM ADDED");
        }
        finally
        {
            entityManager.close();
        }
    }

    public Album findById(int id)
    {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        Album album=entityManager.find(Album.class,id);
        entityManager.close();
        if(album!=null)
            return album;
        else
        {
            System.err.println("!!![THIS ID IS NOT VALID: ID="+id+"]!!!");
            return null;
        }
    }

    public List<Album> findByName(String titlePattern)
    {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        List<Album> albums=entityManager.createNamedQuery("Album.findByName",Album.class)
                .setParameter("titlePattern","%"+titlePattern+"%")
                .getResultList();
        entityManager.close();
        return albums;
    }
}
