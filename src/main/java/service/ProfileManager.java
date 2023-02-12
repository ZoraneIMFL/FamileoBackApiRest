package service;

import entity.Account;
import entity.Profile;

import java.util.List;

public interface ProfileManager {

    Profile createProfile(final Profile newProfile);

    Profile updateProfile(final Profile profile);

    Profile findProfile(final long id);


    void deleteProfile(final Long id);
}
