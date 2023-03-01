package jee.dto;

import jakarta.persistence.*;
import jee.model.Account;
import jee.model.Photo;
import jee.model.Profile;
import jee.model.Publication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LitePublication {
    long id;

    private String description;

    private double latitude;

    private double longitude;

    private Date publishDate;


    private long profileId;

    private long accountId;

    private List<LitePhoto> photos;

    public LitePublication (Publication publication){
        this.id = publication.getId();
        this.description = publication.getDescription();
        this.latitude = publication.getLatitude();
        this.longitude = publication.getLongitude();
        this.publishDate = publication.getPublishDate();
        if(publication.getProfile() != null)
            this.profileId = publication.getProfile().getId();
        if(publication.getAccount() != null)
            this.accountId = publication.getAccount().getId();
        this.photos = new ArrayList<LitePhoto>();
        for(var i = 0; i < publication.getPhotos().size(); i++) {
            this.photos.add(new LitePhoto(publication.getPhotos().get(i)));
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public List<LitePhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<LitePhoto> photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "LitePublication{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", publishDate=" + publishDate +
                ", profileId=" + profileId +
                ", accountId=" + accountId +
                '}';
    }
}
