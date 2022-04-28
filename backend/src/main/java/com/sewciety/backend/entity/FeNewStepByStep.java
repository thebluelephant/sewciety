package com.sewciety.backend.entity;

public class FeNewStepByStep {

    private Integer patternId;
    private String authorId;
    private String authorUsername;
    private Integer lastStepDone;

    public FeNewStepByStep() {
    }

    public FeNewStepByStep(Integer patternId, String authorId, String authorUsername, Integer lastStepDone) {
        this.patternId = patternId;
        this.authorId = authorId;
        this.lastStepDone = lastStepDone;
        this.authorUsername = authorUsername;
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

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }
}
