package persistable;

import javax.persistence.*;
import java.util.Date;
@Entity(name = "Photo")
@TableGenerator(name = "PhotoGen",table = "SEQ_TABLE",allocationSize = 1000)
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PhotoGen")
    int id;
    String name;
    String localisation;
    Date date;
    //Relation à faire !!
    int profileId;
    // relation à faire !!
    int accountId;
    public Photo(){}
    public Photo(String name,String localisation,Date date,int profileId,int accountId){
        this.name=name;
        this.localisation=localisation;
        this.date=date;
        this.profileId=profileId;
        this.accountId=accountId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
