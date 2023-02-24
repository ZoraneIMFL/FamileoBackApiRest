package jee.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jee.dao.ProfileDao;
import jee.model.Account;
import jee.model.Profile;
import org.hibernate.Hibernate;

import java.util.List;

@Stateless
public class ProfileService {

    @Inject
    private ProfileDao dao;

    public List<Profile> getAllProfile() {
        List<Profile> profiles = dao.findAllProfile();
        for (Profile profile : profiles) {
            Hibernate.initialize(profile.getAcc());
        }
        return profiles;





        //return dao.findAllProfile();
    }

    public List<Profile> getAllProfileOfAccount(Account account) {return dao.findProfileAccount(account);}

    public Profile createProfile(final Profile newProfile) {
        return dao.create(newProfile);
    }
    public Profile updateProfile(Profile profile) {
        final var oldProfile = findProfile(profile.getId());
        if (oldProfile == null) {
            return null;
        }
        profile.setId(oldProfile.getId());
        return dao.update(profile);
    }

    public Profile findProfile(long id) {
        return dao.read(Profile.class, id);
    }

    public void deleteProfile(Long id) {
        dao.delete(Profile.class, id);
    }

}
