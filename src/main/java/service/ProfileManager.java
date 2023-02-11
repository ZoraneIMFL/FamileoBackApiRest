package service;

import entity.Account;
import entity.Profile;

public interface ProfileManager {

    Profile createProfile (Account acc, String name, String password, int type, byte[] profileImage);

    void updateType(int idProfile, int type);

    Profile findByPrimaryKey(int idProfile);
}
