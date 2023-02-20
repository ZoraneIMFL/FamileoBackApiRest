package jee.dao;

import jee.model.User;

import java.util.List;

public class UserDao extends DAO<User> implements CRUD<User> {

    public List<User> findAllUser() {
        return em.createQuery("from User", User.class).getResultList();
    }
}
