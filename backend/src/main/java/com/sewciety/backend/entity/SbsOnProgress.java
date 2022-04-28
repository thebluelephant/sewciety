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
    @Column(name = "last_step_done")
    private Integer lastStepDone;
    @Column(name = "progress")
    private Boolean progress;

    public SbsOnProgress() {
    }

    public SbsOnProgress(Integer patternId, String userId, Integer lastStepDone, Boolean progress) {
        this.patternId = patternId;
        this.userId = userId;
        this.lastStepDone = lastStepDone;
        this.progress = progress;
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

    public Integer getLastStepDone() {
        return lastStepDone;
    }

    public void setLastStepDone(Integer lastStepDone) {
        this.lastStepDone = lastStepDone;
    }

    public Boolean getProgress() {
        return progress;
    }

    public void setProgress(Boolean progress) {
        this.progress = progress;
    }

}
