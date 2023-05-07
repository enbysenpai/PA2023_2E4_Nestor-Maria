package com.example.lab9_v2.repository;

import com.example.lab9_v2.model.Artist;
import jakarta.persistence.*;

import java.util.List;

public class ArtistRepository extends AbstractRepository<Artist, Integer> {
    public ArtistRepository(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }


    @Override
    public Artist save(Artist artist) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Artist existingArtist = entityManager.createQuery("SELECT a FROM Artist a WHERE a.name= :name", Artist.class)
                    .setParameter("name", artist.getName())
                    .getSingleResult();

            if (existingArtist != null) {
                System.err.println("!!![THIS ARTIST EXISTS: ID=" + existingArtist.getId() + "]!!!");
                artist = null;
            }
        } catch (NoResultException e) {
            int maxID = entityManager.createQuery("SELECT MAX(a.id) from Artist a", int.class)
                    .getSingleResult();

            artist.setId(maxID + 1);

            entityManager.persist(artist);
            entityManager.getTransaction().commit();
            System.out.println("NEW ARTIST ADDED");
            entityManager.close();
        } finally {
            return artist;
        }
    }

    @Override
    public void deleteById(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Artist artist = entityManager.find(Artist.class, id);

        if (artist != null) {
            entityManager.remove(artist);
            System.out.println("DELETE OPERATION WAS SUCCESSFUL");
        } else {
            System.err.println("!!![THIS ID IS NOT VALID: ID=" + id + "]!!!");
        }

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public Artist findById(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Artist artist = entityManager.find(Artist.class, id);
        entityManager.close();
        if (artist != null)
            return artist;
        else {
            System.err.println("!!![THIS ID IS NOT VALID: ID=" + id + "]!!!");
            return null;
        }
    }

    @Override
    public List<Artist> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT a from Artist a", Artist.class)
                    .getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void update(Artist artist)
    {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        if(findById(artist.getId())!=null)
        {
            EntityTransaction entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.merge(artist);
            entityTransaction.commit();
            entityManager.close();
        }
        else
        {
            System.out.println("THIS ARTIST DOESN'T EXIST. TRY TO SAVE IT");
        }
    }

    public List<Artist> findByNamePattern(String namePattern) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Artist> artists = entityManager.createNamedQuery("Artist.findByName", Artist.class)
                .setParameter("namePattern", "%" + namePattern + "%")
                .getResultList();
        entityManager.close();
        return artists;
    }

    public Artist findByNameSpecific(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Artist> query=entityManager.createQuery(
                "select a from Artist  a where a.name=:name",Artist.class
        );
        query.setParameter("name",name);
        Artist artist=query.getSingleResult();
        entityManager.close();
        if (artist != null)
            return artist;
        else {
            System.err.println("!!![THIS NAME DOESN'T EXIST: NAME=" + name + "]!!!");
            return null;
        }
    }
}