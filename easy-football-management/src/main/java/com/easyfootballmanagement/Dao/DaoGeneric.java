package com.easyfootballmanagement.Dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class DaoGeneric <T> implements IDaoGeneric<T> {
    private final Class<T> entityClass;
    private final EntityManagerFactory entityManagerFactory;


    public DaoGeneric(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    @Override
    public List<T> get() {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
            return em.createQuery(jpql, entityClass).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public T getById(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(entityClass, id);
        } finally {
            em.close();
        }
    }

    @Override
    public void put(T model) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(model);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(T model) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            T mergedEntity = em.merge(model);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void remove(long id) {
        T entity = getById(id);
        if (entity != null) {
            EntityManager em = getEntityManager();
            try {
                em.getTransaction().begin();
                em.remove(em.contains(entity) ? entity : em.merge(entity));
                em.getTransaction().commit();
            } finally {
                em.close();
            }
        }
    }
}
