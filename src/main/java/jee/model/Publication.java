package jee.model;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name = "Publication")
@TableGenerator(name = "PublicationGen",table = "SEQ_TABLE",allocationSize = 1000)
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "PublicationGen")
    int id;



    private String description;

    private double latitude;

    private double longitude;

    @Temporal(TemporalType.TIMESTAMP)
    private Date publishDate;


    @ManyToOne(fetch = FetchType.EAGER)
    private Profile profile;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account account;


    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "publication_photo",
            joinColumns = @JoinColumn(name = "publication_id"),
            inverseJoinColumns = @JoinColumn(name = "photo_id")
    )
    private List<Photo> photos;


    public Publication(){}
    public Publication(String description, Date date, double latitude,double longitude, Profile profile , Account acc ){
        this.description=description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.profile = profile;
        this.account = acc;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }







}