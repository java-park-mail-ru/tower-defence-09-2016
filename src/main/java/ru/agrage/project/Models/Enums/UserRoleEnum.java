package ru.agrage.project.Models.Enums;

/**
 * Created by dmitry on 1/8/17.
 */
public enum UserRoleEnum {

    USER_ROLE_USER ("user"),
    USER_ROLE_ADMIN ("admin");

    String userRole;

    private UserRoleEnum (String userRole) {
        this.userRole = userRole;
    }

    public String getUserRole(){
        return userRole;
    }
}
