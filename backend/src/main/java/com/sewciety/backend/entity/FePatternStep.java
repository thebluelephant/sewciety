package com.sewciety.backend.entity;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;


public class FePatternStep {
    
    @Column(name = "sequence")
    private Integer sequence;
    @Column(name = "sbs_id")
    private Integer sbsId;
    @Column(name = "explanations")
    private String explanations;
    @Column(name = "image")
    private MultipartFile image;

    public FePatternStep() {
    }

    public FePatternStep(Integer sequence, Integer sbsId, String explanations, MultipartFile image) {
        this.sequence = sequence;
        this.sbsId = sbsId;
        this.explanations = explanations;
        this.image = image;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getSbsId() {
        return sbsId;
    }

    public void setSbsId(Integer sbsId) {
        this.sbsId = sbsId;
    }

    public String getExplanations() {
        return explanations;
    }

    public void setExplanations(String explanations) {
        this.explanations = explanations;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
