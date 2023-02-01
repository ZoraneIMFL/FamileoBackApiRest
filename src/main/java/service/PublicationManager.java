package service;

import persistable.Publication;

import java.util.Date;

public interface PublicationManager {
    Publication createPublication(String name, String localisation, Date date, int profileId, int accountId);
    Publication findByPrimaryKey(int idPub);
}
