package com.sewciety.backend.entity;

public class FeNewStepByStep {

    private Integer patternId;
    private String authorId;
    private Integer lastStepDone;

    public FeNewStepByStep() {
    }

    public FeNewStepByStep(Integer patternId, String authorId, Integer lastStepDone) {
        this.patternId = patternId;
        this.authorId = authorId;
        this.lastStepDone = lastStepDone;
    }

    public Integer getPatternId() {
        return patternId;
    }

    public void setPatternId(Integer patternId) {
        this.patternId = patternId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public Integer getLastStepDone() {
        return lastStepDone;
    }

    public void setLastStepDone(Integer lastStepDone) {
        this.lastStepDone = lastStepDone;
    }
}
