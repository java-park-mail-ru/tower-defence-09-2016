package ru.agrage.project.Models;

import static javax.persistence.GenerationType.IDENTITY;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.Email;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import ru.agrage.project.Models.Enums.UserRoleEnum;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;



@Entity
@Table(name="users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "userModel", cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private UserProfile userProfile;

    @Basic
    @Column(name = "username", unique=true, nullable = false, length = 150)
    @NotNull
    @Size(min=3, max=20)
    private String username;

    @Basic
    @Column(name = "password", nullable = false, length = 128)
    @NotNull
    private String password;

    @Basic
    @Column(name = "email", unique=true, nullable = false, length = 30)
    @NotNull
    @Email
    private String email;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "role", nullable = false, length = 10)
    @NotNull
    private String role = UserRoleEnum.USER_ROLE_USER.getUserRole();

    @Basic
    @Column(name = "last_login", nullable = true)
    private Date lastLogin;

    @Basic
    @Column(name = "data_joined", nullable = false)
    private Date dataJoined;

    public UserModel () {}

    public UserModel (int id) {
        this.id = id;
    }

    public UserModel(String username, String password, String email, Date lastLogin, Date dataJoined) {
        this.username = username;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt(10));
        this.email = email;
        this.lastLogin = lastLogin;
        this.dataJoined = dataJoined;
    }

    public UserModel(String username, String password, String email, Date lastLogin, Date dataJoined, UserProfile userProfile) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.lastLogin = lastLogin;
        this.dataJoined = dataJoined;
        this.userProfile = userProfile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getDataJoined() {
        return dataJoined;
    }

    public void setDataJoined(Date dataJoined) {
        this.dataJoined = dataJoined;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserModel that = (UserModel) o;

        if (id != that.id) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (lastLogin != null ? !lastLogin.equals(that.lastLogin) : that.lastLogin != null) return false;
        if (dataJoined != null ? !dataJoined.equals(that.dataJoined) : that.dataJoined != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (lastLogin != null ? lastLogin.hashCode() : 0);
        result = 31 * result + (dataJoined != null ? dataJoined.hashCode() : 0);
        return result;
    }

    public UserProfile getUserProfile() {
        return this.userProfile;
    }

    public void setProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @Override
    public String toString(){
        return "Id="+this.id+", name="+this.username+", email="+this.email;
    }
}
