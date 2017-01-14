package ru.agrage.project.Mechanics.Avatar;

import ru.agrage.project.Models.UserProfile;

import javax.validation.constraints.NotNull;

public class GameUser {

    @NotNull
    private final UserProfile userProfile;
    @NotNull

    public GameUser(@NotNull UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @NotNull
    public UserProfile getUserProfile() {
        return userProfile;
    }

    @NotNull
    public int getId() {
        return userProfile.getId();
    }

}
