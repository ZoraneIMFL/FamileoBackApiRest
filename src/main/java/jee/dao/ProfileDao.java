package jee.dao;

import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.TypedQuery;
import jee.model.Account;
import jee.model.Profile;

import java.util.List;

@NamedQueries(value = {
        @NamedQuery(name = "findProfileByAccountId", query = "select p from Profile p where p.acc.id=:accId")
})

public class ProfileDao extends DAO<Profile> implements CRUD<Profile> {

    //mettre potentiellement une méthode afin d'avoir tous les profile du compte associé à l'instance

    public List<Profile> findProfilesByAccountId(Long accountId) {
        TypedQuery<Profile> query = em.createQuery("SELECT p FROM Profile p WHERE p.acc.id = :accountId", Profile.class);
        query.setParameter("accountId", accountId);
        return query.getResultList();
    }

    public List<Profile> findAllProfile() {
        //return em.createQuery("SELECT p FROM Profile p JOIN FETCH p.account", Profile.class).getResultList();
        return em.createQuery("from Profile ", Profile.class).getResultList();
    }

}
