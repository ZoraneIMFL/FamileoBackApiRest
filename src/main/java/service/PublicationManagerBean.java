package service;

import persistable.Publication;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Stateless
public class PublicationManagerBean implements PublicationManager {
    @PersistenceContext
    EntityManager em;
    @Override
    public Publication createPublication(String name, String localisation, Date date, int profileId, int accountId) {
        Publication publication = new Publication(name,localisation,date,profileId,accountId);
        em.persist(publication);
        return publication;
    }

    @Override
    public Publication findByPrimaryKey(int idPub) {
        return em.find(Publication.class,idPub);
    }
}
