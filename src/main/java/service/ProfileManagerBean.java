package service;

import entity.Account;
import entity.Profile;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProfileManagerBean  implements ProfileManager{
    @PersistenceContext
    EntityManager em;

    private Profile p = null;




    @Override
    public Profile createProfile(Account acc, String name, String password, int type, byte[] profileImage) {
        p = new Profile(acc, name, password, type, profileImage);
        em.persist(p);
        return p;
    }

    @Override
    public void updateType(int idProfile, int type) {
        Profile managedProfile = em.find(Profile.class, idProfile);
        managedProfile.setType(type);
    }

    @Override
    public Profile findByPrimaryKey(int idProfile) {
        return em.find(Profile.class, idProfile);
    }
}
