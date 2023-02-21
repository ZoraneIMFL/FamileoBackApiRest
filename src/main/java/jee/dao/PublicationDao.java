package jee.dao;

import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Query;
import jee.model.Profile;
import jee.model.Publication;

import java.util.List;
@NamedQueries(value = {
        @NamedQuery(name = "findPublicationByProfile",query = "select p from Publication p where p.profile=:profile")
})


public class PublicationDao extends DAO<Publication> implements CRUD<Publication>{
    public List<Publication> findAllPublication() {
        return em.createQuery("from Publication ", Publication.class).getResultList();
    }

    public List<Publication> findPublicationProfile(Profile profile){
        Query query = em.createNamedQuery("findPublicationByProfile");
        query.setParameter("profile", profile);
        List<Publication> Publications = query.getResultList();
        return Publications;
    }
}
