package ru.agrage.project.Mechanics.Requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by dmitry on 1/13/17.
 */
public class TowerHealthRequest {

    private Integer towerId;
    private Integer health;

    @JsonCreator
    public TowerHealthRequest(@JsonProperty("towerId") Integer towerId, @JsonProperty("health") Integer health) {
        this.towerId = towerId;
        this.health = health;
    }

    public Integer getTowerId() {
        return towerId;
    }

    public void setTowerId(Integer towerId) {
        this.towerId = towerId;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }
}
