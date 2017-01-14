package ru.agrage.project.Mechanics.Requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by dmitry on 1/13/17.
 */
public class TrollMoveRequest {

    private Integer trollId;
    private Integer targetCellX;

    @JsonCreator
    public TrollMoveRequest(@JsonProperty("trollId") Integer trollId,
                     @JsonProperty("targetCellX") Integer targetCellX) {
        this.trollId = trollId;
        this.targetCellX = targetCellX;
    }

    public Integer getTrollId() {
        return trollId;
    }

    public void setTrollId(Integer trollId) {
        this.trollId = trollId;
    }

    public Integer getTargetCellX() {
        return targetCellX;
    }

    public void setTargetCellX(Integer targetCellX) {
        this.targetCellX = targetCellX;
    }
}
