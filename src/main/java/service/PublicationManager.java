package service;

import entity.Profile;
import entity.Publication;

import java.util.List;

public interface PublicationManager {
    Publication createPublication(final Publication newPublication);
    Publication updatePublication(final  Publication publication);
    Publication findPublication(final long id);
    List<Publication> getAllPublication();
    void deletePublication(final long id);
    List<Publication> getPublicationProfile(Profile profile);
}
