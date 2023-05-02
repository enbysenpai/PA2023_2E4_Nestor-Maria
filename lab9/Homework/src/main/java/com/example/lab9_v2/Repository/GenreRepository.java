package com.example.lab9_v2.Repository;

import com.example.lab9_v2.Model.Artist;
import com.example.lab9_v2.Model.Genre;
import jakarta.persistence.*;

import java.util.List;

public class GenreRepository extends AbstractRepository<Genre,Integer>
{
    public GenreRepository(EntityManagerFactory entityManagerFactory)
    {
        super(entityManagerFactory);
    }

    @Override
    public Genre save(Genre genre)
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
                genre=null;
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
            return genre;
        }
    }

    @Override
    public void deleteById(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Genre genre = entityManager.find(Genre.class, id);

        if (genre != null) {
            entityManager.remove(genre);
            System.out.println("DELETE OPERATION WAS SUCCESSFUL");
        } else {
            System.err.println("!!![THIS ID IS NOT VALID: ID=" + id + "]!!!");
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Genre findById(Integer id)
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

    @Override
    public List<Genre> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT g from Genre g", Genre.class)
                    .getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void update(Genre genre)
    {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        if(findById(genre.getId())!=null)
        {
            EntityTransaction entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.merge(genre);
            entityTransaction.commit();
            entityManager.close();
        }
        else
        {
            System.out.println("THIS GENRE DOESN'T EXIST. TRY TO SAVE IT");
        }
    }

    public List<Genre> findByNamePattern(String namePattern)
    {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        List<Genre>genres=entityManager.createNamedQuery("Genre.findByName",Genre.class)
                .setParameter("namePattern","%"+namePattern+"%")
                .getResultList();
        entityManager.close();
        return genres;
    }

    public Genre findByNameSpecific(String name)
    {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        TypedQuery<Genre>query=entityManager.createQuery(
                "select g from Genre g where g.name=:name",Genre.class
        );
        query.setParameter("name",name);
        Genre genre=query.getSingleResult();
        entityManager.close();
        if(genre!=null)
        {
            return genre;
        }
        else
        {
            System.err.println("!!![THIS NAME DOESN'T EXIST: NAME="+name);
            return null;
        }
    }
}
