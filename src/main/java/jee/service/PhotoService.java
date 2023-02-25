package jee.service;

import jakarta.inject.Inject;
import jee.dao.AccountDao;
import jee.dao.PhotoDao;
import jee.model.Account;
import jee.model.Photo;
import jee.model.Profile;
import jee.validator.EmailValidator;
import jee.validator.PasswordValidator;

import java.util.List;

public class PhotoService {

    @Inject
    private PhotoDao dao;


    public List<Photo> getAllPhoto() {
        return dao.findAllPhoto();
    }
    public Photo createAccount(final Photo newPhoto) {
        return dao.create(newPhoto);

    }

    public Photo findPhoto(final long id) {
        return dao.read(Photo.class, id);
    }

    public Photo updateAccount(final Photo photo) {
        final Photo oldPhoto = findPhoto(photo.getId());
        if (oldPhoto == null) {
            return null;
        }
        photo.setId(oldPhoto.getId());
        //LOGGER.log(Level.INFO, "Photo Updated");
        return dao.update(photo);
    }

    public void deleteAccount(final Long id) {
        //LOGGER.log(Level.INFO, "Account Deleted");
        dao.delete(Photo.class, id);
    }



}
