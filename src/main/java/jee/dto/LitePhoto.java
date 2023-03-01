package jee.dto;

import jee.model.Photo;

import java.util.Date;

public class LitePhoto {
    private long id;
    private String name;

    private Date date;

    private double latitude;

    private double longitude;

    public LitePhoto(Photo photo) {
        this.id = photo.getId();
        this.name = photo.getName();
        this.date = photo.getDate();
        this.latitude = photo.getLatitude();
        this.longitude = photo.getLongitude();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    @Override
    public String toString() {
        return "LitePhoto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
