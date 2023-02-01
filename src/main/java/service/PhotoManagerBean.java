package service;

import persistable.Photo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
@Stateless
public class PhotoManagerBean implements PhotoManager{
    @PersistenceContext
    EntityManager em;
    @Override
    public Photo createPhoto(String name, String localisation, Date date, int profileId, int accountId) {
        Photo photo = new Photo(name,localisation,date,profileId,accountId);
        em.persist(photo);
        return photo;
    }

    @Override
    public Photo findByPrimaryKey(int idPhoto) {
        return em.find(Photo.class,idPhoto);
    }
}
