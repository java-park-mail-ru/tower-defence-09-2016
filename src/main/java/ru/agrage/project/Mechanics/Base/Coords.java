package ru.agrage.project.Mechanics.Base;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by dmitry on 1/13/17.
 */
public class Coords {

    public Coords(@JsonProperty("x") double x) {
        this.x = x;
    }

    public final double x;

    @NotNull
    public Coords add(@NotNull Coords addition) {
        return new Coords(x + addition.x);
    }
}
