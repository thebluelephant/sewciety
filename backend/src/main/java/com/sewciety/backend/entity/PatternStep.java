package com.sewciety.backend.entity;

import java.sql.Blob;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "pattern_step")
@EntityListeners(AuditingEntityListener.class)
public class PatternStep {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "sequence") // ordre de la step
    private Integer sequence;
    @Column(name = "sbs_id")
    private Integer sbsId;
    @Column(name = "title")
    private String title;
    @Column(name = "explanations")
    private String explanations;
    @Column(name = "image")
    private Blob image;

    public PatternStep() {
    }

    public PatternStep(Integer sequence, Integer sbsId, String explanations, Blob image, String title) {
        this.sequence = sequence;
        this.sbsId = sbsId;
        this.explanations = explanations;
        this.image = image;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }
}
