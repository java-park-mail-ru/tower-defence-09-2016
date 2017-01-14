package ru.agrage.project.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


import javax.persistence.*;

/**
 * Created by dmitry on 1/4/17.
 */
@Entity
@Table(name = "userprofiles", schema = "agrage")
public class UserProfile {
    @GenericGenerator(name = "generator", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "users"))
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private UserModel userModel;

    private int wins;
    private int loses;
    private int draws;
    private int rating;

    public UserProfile(){}

    public UserProfile(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    @Basic
    @Column(name = "loses", nullable = false)
    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    @Basic
    @Column(name = "draws", nullable = false)
    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    @Basic
    @Column(name = "rating", nullable = false)
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserProfile that = (UserProfile) o;

        if (id != that.id) return false;
        if (wins != that.wins) return false;
        if (loses != that.loses) return false;
        if (draws != that.draws) return false;
        if (rating != that.rating) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + wins;
        result = 31 * result + loses;
        result = 31 * result + draws;
        result = 31 * result + rating;
        return result;
    }
}
