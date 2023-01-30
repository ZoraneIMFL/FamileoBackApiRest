package persistable;

import javax.persistence.*;

@Entity(name = "Profile")
@TableGenerator(name="ProfileGen", table = "SEQ_TABLE", allocationSize = 1000)

public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "AccountGen")
    private  int id;
    //@ManyToOne
    private int accountId;
    private String name;
    private String password;
    private int type;
    private byte[] profileImage;

    public Profile(){}
    public Profile(int accountId,String name,String password,int type,byte[] profileImage){
        this.accountId=accountId;
        this.name=name;
        this.password=password;
        this.type=type;
        this.profileImage=profileImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }
}
