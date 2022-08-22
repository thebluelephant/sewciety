package com.sewciety.backend.entity;

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
    @Column(name = "sbs_id")
    private Integer sbsId;
    @Column(name = "title")
    private String title;
    @Column(name = "explanations")
    private String explanations;
    @Column(name = "image")
    private String image;

    public PatternStep() {
    }

    public PatternStep(Integer sbsId, String explanations, String title, String image) {
        this.sbsId = sbsId;
        this.explanations = explanations;
        this.title = title;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
