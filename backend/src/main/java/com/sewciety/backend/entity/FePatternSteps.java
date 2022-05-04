package com.sewciety.backend.entity;

import java.util.List;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

public class FePatternSteps {

    List<PatternStep> steps;
    List<SbsImage> images;

    public FePatternSteps() {
    }

    public FePatternSteps(List<PatternStep> steps, List<SbsImage> images) {
        this.images = images;
        this.steps = steps;
    }

    public List<SbsImage> getImages() {
        return images;
    }

    public void setImages(List<SbsImage> images) {
        this.images = images;
    }

    public List<PatternStep> getSteps() {
        return this.steps;
    }

    public void setSteps(List<PatternStep> steps) {
        this.steps = steps;
    }

}
