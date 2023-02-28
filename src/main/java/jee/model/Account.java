package jee.model;

import jakarta.persistence.*;
import jee.validator.EmailValidator;
import jee.validator.PasswordValidator;
import security.PasswordEncryption;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an account that can be created on the application.
 */
@Entity(name = "Account")
//@TableGenerator(name="AccountGen", table = "SEQ_TABLE", allocationSize = 1000)
//@Table(name="Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "AccountGen")

    private long id = 0L;

    @OneToMany(mappedBy = "acc",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Profile> profiles;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Publication> publications;

    private String name;

    private String email;

    private String password;

    private byte[] salt;

    private int status;

    public Account(){

    }
    public Account(String name, String email, String password, int status){
        this.name = name;
        this.email = email;
        this.status = status;
        this.profiles = new ArrayList<>();
        this.changePassword(password);
        this.setEmail(email);
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
        if (EmailValidator.isValid(email)) {
            this.email = email;
        } else
            this.email = null;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String password) {
        if(PasswordValidator.isValid(password)) {
            this.salt = PasswordEncryption.generateSalt();
            this.password = PasswordEncryption.encryptPassword(password, this.salt);
        } else
            this.password = null;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void addProfile(Profile p) {
        this.profiles.add(p);
        p.setAcc(this);
    }

    public void removeProfile(Profile p) {
        this.profiles.remove(p);
        p.setAcc(null);
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                '}';
    }

    public boolean isValidAccount() {
        return email != null && password != null;
    }
}
