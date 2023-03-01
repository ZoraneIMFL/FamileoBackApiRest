package jee.dao;

import jee.model.Photo;

import java.util.List;

public class PhotoDao extends DAO<Photo> implements CRUD<Photo> {
    public List<Photo> findAllPhoto() {
        return em.createQuery("from Photo", Photo.class).getResultList();
    }
}
