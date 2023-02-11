package entity;


import javax.persistence.*;

@Entity(name = "Profile")
@TableGenerator(name="ProfileGen", table = "SEQ_TABLE", allocationSize = 1000)

public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "AccountGen")
    private  int id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account acc;
    private String name;
    private String password;
    private int type;
    private byte[] profileImage;

    public Profile(){}
    public Profile(Account acc ,String name,String password,int type,byte[] profileImage){
        this.acc = acc;
        this.name=name;
        this.password=password;
        this.type=type;
        this.profileImage=profileImage;
    }





    public Account getAcc() {
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
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
