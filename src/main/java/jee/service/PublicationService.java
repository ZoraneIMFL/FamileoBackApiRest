package jee.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jee.dao.PublicationDao;
import jee.model.Profile;
import jee.model.Publication;

import java.util.List;

@Stateless
public class PublicationService {

    @Inject
    private PublicationDao dao;

    public Publication createPublication(final Publication newPublication) {
        return dao.create(newPublication);
    }

    public Publication updatePublication(Publication publication) {
        final var oldpub = findPublication(publication.getId());
        if (oldpub == null) {
            return null;
        }
        publication.setId(oldpub.getId());
        return dao.update(publication);
    }

    public Publication findPublication(long id) {
        return dao.read(Publication.class, id);
    }

    public List<Publication> getAllPublication() {
        return dao.findAllPublication();
    }

    public void deletePublication(long id) {
        dao.delete(Publication.class, id);
    }

    public List<Publication> getAllPublicationOfProfile(Profile profile) {
        return dao.findPublicationProfile(profile);
    }

}
