package service;

import persistable.Profile;
import validator.PasswordValidator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ProfileManageBean implements  ProfileManager{
    @PersistenceContext
    EntityManager em;
    @Override
    public Profile createProfile(int accountId, String name, String password, int type, byte[] profileImage) {
        if(password != null){
            if(PasswordValidator.isValid(password) ){
                Profile profile=new Profile(accountId,name,password,type,profileImage);
                em.persist(profile);
                return profile;
            }
        }else{
            Profile profile= new Profile();
            profile.setAccountId(accountId);
            profile.setName(name);
            profile.setType(type);
            profile.setProfileImage(profileImage);
            em.persist(profile);
            return profile;
        }
        return  null;
    }

    @Override
    public Profile findByPrimaryKey(int profileId) {
        return em.find(Profile.class,profileId);
    }
}
