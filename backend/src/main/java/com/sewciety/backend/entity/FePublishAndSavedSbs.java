package com.sewciety.backend.entity;

import java.util.List;

// Entity return on the FE Step by steps page for a specific pattern.
// Avoid calling 2 end points by grouping the published and user saved step by step
public class FePublishAndSavedSbs {
    public List<StepByStep> published;
    public List<StepByStep> saved;

    public FePublishAndSavedSbs() {
    }

    public FePublishAndSavedSbs(List<StepByStep> published, List<StepByStep> saved) {
        this.published = published;
        this.saved = saved;
    }

    public List<StepByStep> getPublished() {
        return this.published;
    }

    public void setPublished(List<StepByStep> published) {
        this.published = published;
    }

    public List<StepByStep> getSaved() {
        return this.saved;
    }

    public void setSaved(List<StepByStep> saved) {
        this.saved = saved;
    }
}
