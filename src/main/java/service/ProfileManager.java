package service;

import persistable.Profile;

public interface ProfileManager {
    Profile createProfile(int accountId,String name,String password,int type,byte[] profileImage);
    Profile findByPrimaryKey(int profileId);
}
