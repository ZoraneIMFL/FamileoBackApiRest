package service;

import dao.AccountDao;
import dao.ProfileDao;
import entity.Account;
import entity.Profile;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProfileManagerBean  implements ProfileManager{


    @Inject
    private ProfileDao dao;

    @Override
    public Profile createProfile(final Profile newProfile) {
        return dao.create(newProfile);
    }

    @Override
    public Profile updateProfile(Profile profile) {
        final Profile oldProfile = findProfile(profile.getId());
        if (oldProfile == null) {
            return null;
        }
        profile.setId(oldProfile.getId());
        return dao.update(profile);
    }

    @Override
    public Profile findProfile(long id) {
        return dao.read(Profile.class, id);
    }

    @Override
    public List<Profile> getAllProfile() {
        return dao.findAllProfile();
    }

    @Override
    public List<Profile> getProfileByAccount(Account account) {
        return dao.findProfileAccount(account);
    }

    @Override
    public void deleteProfile(Long id) {
        dao.delete(Profile.class, id);
    }


}
