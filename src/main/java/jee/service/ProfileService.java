package jee.service;


import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jee.dao.ProfileDao;
import jee.model.Profile;

@Stateless
public class ProfileService {

    @Inject
    private ProfileDao dao;

    public Profile createProfile(final Profile newProfile) {
        return dao.create(newProfile);
    }
    public Profile updateProfile(Profile profile) {
        final Profile oldProfile = findProfile(profile.getId());
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
