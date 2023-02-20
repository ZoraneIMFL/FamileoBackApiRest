package jee.service;

import jee.dao.UserDao;
import jee.model.User;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class UserService {

    @Inject
    private UserDao dao;

    public List<User> getAllUser() {
        return dao.findAllUser();
    }

    public User createUser(final User newUser) {
        return dao.create(newUser);
    }

    public User findUser(final long id) {
        return dao.read(User.class, id);
    }

    public User updateUser(final User user) {
        final User oldUser = findUser(user.getId());
        if (oldUser == null) {
            return null;
        }
        user.setId(oldUser.getId());
        return dao.update(user);
    }

    public void deleteUser(final Long id) {
        dao.delete(User.class, id);
    }
}
