package jee.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.persistence.*;

@Entity(name = "Photo")
@TableGenerator(name = "PhotoGen",table = "SEQ_TABLE",allocationSize = 1000)
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "AccountGen")
    private long id;
    private String name;


    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private double latitude;

    private double longitude;

    @ManyToMany(mappedBy = "photos")
    private List<Publication> publications;

    public Photo(){}
    public Photo(String name,Date date, double latitude, double longitude ){
        this.name=name;
        this.date=date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.publications = new ArrayList<>();
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

    public List<Publication> getPublications() {
        return this.publications;
    }

    public void addPublication(Publication p) {
        this.publications.add(p);
    }

    public void removePublication(Publication p) {
        this.publications.remove(p);
    }

}
