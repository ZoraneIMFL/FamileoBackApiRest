package service;


import dao.PublicationDao;
import entity.Profile;
import entity.Publication;

import javax.inject.Inject;
import java.util.List;

public class PublicationManagerBean implements PublicationManager{
    @Inject
    private PublicationDao dao;
    @Override
    public Publication createPublication(final Publication newPublication) {
        return dao.create(newPublication);
    }

    @Override
    public Publication updatePublication(Publication publication) {
        final Publication oldpub = findPublication(publication.getId());
        if(oldpub == null){
            return null;
        }
        publication.setId(oldpub.getId());
        return  dao.update(publication);
    }

    @Override
    public Publication findPublication(long id) {
        return dao.read(Publication.class,id);
    }

    @Override
    public List<Publication> getAllPublication() {
        return dao.findAllPublication();
    }

    @Override
    public void deletePublication(long id) {
        dao.delete(Publication.class,id);
    }

    @Override
    public List<Publication> getPublicationProfile(Profile profile) {
        return dao.findPublicationProfile(profile);
    }
}
