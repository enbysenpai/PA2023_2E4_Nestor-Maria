package com.example.lab9_v2.repository;

import com.example.lab9_v2.model.Album;
import jakarta.persistence.*;

import java.util.List;

public class AlbumRepository extends AbstractRepository<Album,Integer>
{

    public AlbumRepository(EntityManagerFactory entityManagerFactory)
    {
        super(entityManagerFactory);
    }

    @Override
    public Album save(Album album)
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
                album=null;
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
            entityManager.close();
        }
        finally
        {
            return album;
        }
    }

    @Override
    public void deleteById(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Album album = entityManager.find(Album.class, id);

        if (album != null) {
            entityManager.remove(album);
            System.out.println("DELETE OPERATION WAS SUCCESSFUL");
        } else {
            System.err.println("!!![THIS ID IS NOT VALID: ID=" + id + "]!!!");
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Album findById(Integer id)
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

    @Override
    public List<Album> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT a from Album a", Album.class)
                    .getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void update(Album album)
    {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        if(findById(album.getId())!=null)
        {
            EntityTransaction entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.merge(album);
            entityTransaction.commit();
            entityManager.close();
        }
        else
        {
            System.out.println("THIS ALBUM DOESN'T EXIST. TRY TO SAVE IT");
        }
    }

    public List<Album> findByNamePattern(String titlePattern) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Album> albums = entityManager.createNamedQuery("Album.findByName", Album.class)
                .setParameter("titlePattern", "%" + titlePattern + "%")
                .getResultList();
        entityManager.close();
        return albums;
    }

    public Album findByNameSpecific(String title) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Album> query=entityManager.createQuery(
                "select a from Album a where a.title=:title",Album.class
        );
        query.setParameter("title",title);
        Album album=query.getSingleResult();
        entityManager.close();
        if (album != null)
            return album;
        else {
            System.err.println("!!![THIS NAME DOESN'T EXIST: NAME=" + title + "]!!!");
            return null;
        }
    }

    public List<Album> findByReleaseYear(int yearPattern)
    {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        List<Album> albums=entityManager.createNamedQuery("Album.findByReleaseYear",Album.class)
                .setParameter("releaseYear",yearPattern)
                .getResultList();
        entityManager.close();
        return albums;
    }

    public List<Album> findByArtistId(int artistIdPattern)
    {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        List<Album> albums=entityManager.createNamedQuery("Album.findByArtistId",Album.class)
                .setParameter("artistId",artistIdPattern)
                .getResultList();
        entityManager.close();
        return albums;
    }
}