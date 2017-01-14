package ru.agrage.project.Models;

/**
 * Created by dmitry on 1/7/17.
 */
public class RatingModel {

    private int id;

    private String username;

    private int rating;

    public RatingModel() {
    }

    public RatingModel(int id, String username, int rating) {
        this.id = id;
        this.username = username;
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
