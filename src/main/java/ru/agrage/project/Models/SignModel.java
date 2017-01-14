package ru.agrage.project.Models;

import java.io.Serializable;

/**
 * Created by Дмитрий on 06.10.2016.
 */
public class SignModel implements Serializable {
    private int id;
    private String login;
    private String password;

    public SignModel() {}

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }
}
