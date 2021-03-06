package com.sewciety.backend.entity;

public class FeNewStepByStep {

    private Integer patternId;
    private String authorId;
    private String authorUsername;
    private Boolean onProgress;

    public FeNewStepByStep() {
    }

    public FeNewStepByStep(Integer patternId, String authorId, String authorUsername, Boolean onProgress) {
        this.patternId = patternId;
        this.authorId = authorId;
        this.authorUsername = authorUsername;
        this.onProgress = onProgress;
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

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public Boolean getOnProgress() {
        return this.onProgress;
    }

    public void setOnProgress(Boolean onProgress) {
        this.onProgress = onProgress;
    }
}
