package com.example.lab9_v2.Repository;

import com.example.lab9_v2.Model.Genre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;

import java.util.List;

public class GenreRepository
{
    private final EntityManagerFactory entityManagerFactory;

    public GenreRepository(EntityManagerFactory entityManagerFactory)
    {
        this.entityManagerFactory=entityManagerFactory;
    }

    public void create(Genre genre)
    {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        try
        {
            entityManager.getTransaction().begin();

            Genre existingGenre=entityManager.createQuery("SELECT g FROM Genre g WHERE g.name=:name",Genre.class)
                    .setParameter("name",genre.getName())
                    .getSingleResult();

            if(existingGenre != null)
            {
                System.err.println("!!![THIS GENRE EXISTS: ID="+existingGenre.getId()+"]!!!");
            }
        }
        catch (NoResultException e)
        {
            int maxID=entityManager.createQuery("SELECT MAX(g.id) FROM Genre g",int.class)
                    .getSingleResult();

            genre.setId(maxID+1);

            entityManager.persist(genre);
            entityManager.getTransaction().commit();
            System.out.println("NEW GENRE ADDED");
        }
        finally
        {
            entityManager.close();
        }
    }

    public Genre findById(int id)
    {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        Genre genre=entityManager.find(Genre.class,id);
        entityManager.close();
        if(genre!=null)
            return genre;
        else
        {
            System.err.println("!!![THIS ID IS NOT VALID: ID="+id+"]!!!");
            return null;
        }
    }

    public List<Genre> findByName(String namePattern)
    {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        List<Genre>genres=entityManager.createNamedQuery("Genre.findByName",Genre.class)
                .setParameter("namePattern","%"+namePattern+"%")
                .getResultList();
        entityManager.close();
        return genres;
    }
}
