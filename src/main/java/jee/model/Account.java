package jee.model;

import jakarta.persistence.*;

import java.util.List;

/**
 * This class represents an account that can be created on the application.
 */
@Entity(name = "Account")
//@TableGenerator(name="AccountGen", table = "SEQ_TABLE", allocationSize = 1000)
@Table(name="Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "AccountGen")

    private long id = 0L;

    /*@OneToMany(targetEntity = Profile.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "acc_fk", referencedColumnName = "id")
    private List<Profile> profiles;

     */


    private String name;

    private String email;

    private String password;

    private int status;

    //private byte[] salt;

    public Account(){

    }
    public Account(String name, String email, String password, int status){
        this.name = name;
        this.email = email;
        this.password = password;
        this.status = status;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /*public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

     */

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                '}';
    }
}