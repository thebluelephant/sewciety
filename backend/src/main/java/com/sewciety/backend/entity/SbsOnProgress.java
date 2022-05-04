package com.sewciety.backend.entity;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "sbs_onprogress")
@EntityListeners(AuditingEntityListener.class)
public class SbsOnProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "pattern_id")
    private Integer patternId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "progress")
    private Boolean progress;
    @Column(name = "sbs_id")
    private Integer sbsId;

    public SbsOnProgress() {
    }

    public SbsOnProgress(Integer patternId, String userId, Boolean progress, Integer sbsId) {
        this.patternId = patternId;
        this.userId = userId;
        this.progress = progress;
        this.sbsId = sbsId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatternId() {
        return patternId;
    }

    public void setPatternId(Integer patternId) {
        this.patternId = patternId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public Boolean getProgress() {
        return progress;
    }

    public void setProgress(Boolean progress) {
        this.progress = progress;
    }

    public Integer getSbsId() {
        return sbsId;
    }

    public void setSbsId(Integer sbsId) {
        this.sbsId = sbsId;
    }

}
