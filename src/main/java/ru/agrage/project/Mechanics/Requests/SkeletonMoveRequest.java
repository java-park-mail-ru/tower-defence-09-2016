package ru.agrage.project.Mechanics.Requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by dmitry on 1/13/17.
 */
public class SkeletonMoveRequest {

    private Integer skeletonId;
    private Integer targetCellX;

    @JsonCreator
    public SkeletonMoveRequest(@JsonProperty("skeletonId") Integer skeletonId,
                               @JsonProperty("targetCellX") Integer targetCellX) {
        this.skeletonId = skeletonId;
        this.targetCellX = targetCellX;
    }

    public Integer getTargetCellX() {
        return targetCellX;
    }

    public void setTargetCellX(Integer targetCellX) {
        this.targetCellX = targetCellX;
    }

    public Integer getSkeletonId() {
        return skeletonId;
    }

    public void setSkeletonId(Integer skeletonId) {
        this.skeletonId = skeletonId;
    }
}
