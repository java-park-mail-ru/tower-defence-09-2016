package ru.agrage.project.Models;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Дмитрий on 06.10.2016.
 */

@Entity
@Table(name="users")
public class UserModel {
    @Id
    @PrimaryKeyJoinColumn
    @Column (name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min=3, max=11)
    @Column(name = "name", unique=true, nullable = false)
    private String login;

    @NotNull @Digits(integer=10, fraction = 2)
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Email
    @Column(name = "email", unique=true, nullable = false)
    private String email;

    public UserModel () {}

    public UserModel(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        return "id="+this.id+", name="+this.login+", email="+this.email;
    }
}
