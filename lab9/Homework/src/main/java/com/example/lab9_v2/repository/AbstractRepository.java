package com.example.lab9_v2.repository;

import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public abstract class AbstractRepository<T,ID>
{
    protected final EntityManagerFactory entityManagerFactory;

    public AbstractRepository(EntityManagerFactory entityManagerFactory)
    {
        this.entityManagerFactory=entityManagerFactory;
    }

    public abstract T save(T entity);

    public abstract void deleteById(ID id);

    public abstract T findById(ID id);
    public abstract List<T> findAll();
    public abstract void update(T entity);
}
