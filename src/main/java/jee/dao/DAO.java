package jee.dao;

import jakarta.ejb.Lock;
import jakarta.ejb.LockType;

import jakarta.persistence.*;
import java.util.List;

/**
 * Simply maps the entitymanager.
 * It simplifies refactoring (unitName change) and wraps some logic (limited queries).
 */
@Lock(LockType.READ)
public abstract class DAO<E> implements CRUD<E> {
    @PersistenceContext(unitName = "projet")
    protected EntityManager em;

    @Override
    public E create(final E e) {
        em.persist(e);
        return e;
    }

    @Override
    public E update(final E e) {
        return em.merge(e);
    }

    @Override
    public void delete(final Class<E> clazz, final long id) {
        delete(em.find(clazz, id));
    }

    @Override
    public void delete(final E entity) {
        em.remove(entity);
    }

    @Override
    public E read(final Class<E> clazz, final long id) {
        return em.find(clazz, id);
    }
}
