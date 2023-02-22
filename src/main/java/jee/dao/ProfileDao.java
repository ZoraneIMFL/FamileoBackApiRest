package jee.dao;





import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Query;
import jee.model.Account;
import jee.model.Profile;

import java.util.List;
@NamedQueries(value = {
        @NamedQuery(name = "findProfileByAcconutId",query = "select p from Profile p where p.account=:account")
})

public class ProfileDao extends DAO<Profile> implements CRUD<Profile>{

    //mettre potentiellement une méthode afin d'avoir tous les profile du compte associé à l'instance


    public List<Profile> findProfileAccount(Account account){
        var query = em.createNamedQuery("findProfileByAcconutId");
        query.setParameter("account", account);
        return query.getResultList();
    }

    public List<Profile> findAllProfile() {
        return em.createQuery("from Profile ", Profile.class).getResultList();
    }

}
