package Persistable;

import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Account")
@TableGenerator(name="AccountGen", table = "SEQ_TABLE", allocationSize = 1000)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "AccountGen")
    private int id;

    private String name;

    private String email;

    private String password;

    private int status;

    public Account(){

    }
    public Account(String name, String email, String password, int status){
        this.name = name;
        this.email = email;
        this.password = password;
        this.status = status;

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






}
