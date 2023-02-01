package persistable;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Publication")
@TableGenerator(name = "PublicationGen",table = "SEQ_TABLE",allocationSize = 1000)
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "PublicationGen")
    int id;
    String name;
    Date date;
    String localisation;
    // sound quel type ( hexa ou chemin vers un mp3 externe ?)
    int profileId;
    int accountId;
    public Publication(){}
    public Publication(String name,String localisation,Date date,int profileId,int accountId){
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
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
