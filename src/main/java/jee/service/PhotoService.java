package jee.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jee.dao.PhotoDao;
import jee.model.Photo;

import java.util.List;

@Stateless
public class PhotoService {

    @Inject
    private PhotoDao dao;


    public List<Photo> getAllPhoto() {
        return dao.findAllPhoto();
    }

    public Photo createPhoto(final Photo newPhoto) {
        return dao.create(newPhoto);
    }

    public Photo findPhoto(final long id) {
        return dao.read(Photo.class, id);
    }

    public Photo updatePhoto(final Photo photo) {
        final var oldPhoto = findPhoto(photo.getId());
        if (oldPhoto == null) {
            return null;
        }
        photo.setId(oldPhoto.getId());
        return dao.update(photo);
    }

    public void deletePhoto(final Long id) {
        dao.delete(Photo.class, id);
    }


}
