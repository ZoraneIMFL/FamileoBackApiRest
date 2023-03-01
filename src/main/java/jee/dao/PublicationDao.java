package jee.dao;

import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.TypedQuery;
import jee.model.Profile;
import jee.model.Publication;

import java.util.List;
@NamedQueries(value = {
        @NamedQuery(name = "findPublicationByProfile",query = "SELECT p FROM Publication p WHERE p.profile.id = :profileId")
})

public class PublicationDao extends DAO<Publication> implements CRUD<Publication>{
    public List<Publication> findAllPublication() {
        return em.createQuery("from Publication ", Publication.class).getResultList();
    }
    public List<Publication> findPublicationProfile(Long profileId){
        //var query = em.createNamedQuery("findPublicationByProfile");
        var query = em.createQuery("SELECT p FROM Publication p WHERE p.profile.id = :profileId", Publication.class);
        query.setParameter("profileId", profileId);
        return query.getResultList();
    }

}
