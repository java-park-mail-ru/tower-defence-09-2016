package ru.agrage.project.Models;

/**
 * Created by dmitry on 1/5/17.
 */
public class ParamModel {
    private int id;

    private String username;

    public ParamModel(){}

    public ParamModel(int id) {
        this.id = id;
    }
    public ParamModel(String username) {
        this.username = username;
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

}
