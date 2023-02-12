package dao;

import javax.ejb.Lock;
import javax.ejb.LockType;

import javax.persistence.*;
import java.util.List;

/**
 * Simply maps the entitymanager.
 * It simplifies refactoring (unitName change) and wraps some logic (limited queries).
 */
@Lock(LockType.READ)
public abstract class DAO<E> implements CRUD<E> {
    @PersistenceContext(unitName = "service.AccountManagerTests")
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

    public List<E> find(final Class<E> clazz, final String query, final int min, final int max) {
        return queryRange(em.createQuery(query, clazz), min, max).getResultList();
    }

    public List<E> namedFind(final Class<E> clazz, final String query, final int min, final int max) {
        return queryRange(em.createNamedQuery(query, clazz), min, max).getResultList();
    }

    private static <E> TypedQuery<E> queryRange(final TypedQuery<E> query, final int min, final int max) {
        if (max >= 0) {
            query.setMaxResults(max);
        }
        if (min >= 0) {
            query.setFirstResult(min);
        }
        return query;
    }
}
