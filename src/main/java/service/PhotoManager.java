package service;

import persistable.Photo;

import java.util.Date;

public interface PhotoManager {
    Photo createPhoto(String name, String localisation, Date date, int profileId, int accountId);
    Photo findByPrimaryKey(int idPhoto);
}
