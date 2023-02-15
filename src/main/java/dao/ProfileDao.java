package dao;

import entity.Account;
import entity.Profile;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import java.util.List;
@NamedQueries(value = {
        @NamedQuery(name = "findProfileByAcconutId",query = "select p from Profile p where p.account=:account")
})
public class ProfileDao extends DAO<Profile> implements CRUD<Profile>{

    //mettre potentiellement une méthode afin d'avoir tous les profile du compte associé à l'instance


    // Mettre peut-etre l'id du account pour la recherche c'est mieux !!
    public List<Profile> findProfileAccount(Account account){
        Query query = em.createNamedQuery("findProfileByAcconutId");
        query.setParameter("account", account);
        List<Profile> profiles = query.getResultList();
        return profiles;
    }

    public List<Profile> findAllProfile() {
        return em.createQuery("from Profile ", Profile.class).getResultList();
    }

}
