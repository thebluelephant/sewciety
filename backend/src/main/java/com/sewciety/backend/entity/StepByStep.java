package com.sewciety.backend.entity;

import java.util.Date;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "step_by_step")
public class StepByStep {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "pattern_id")
    private Integer patternId;
    @Column(name = "author_id")
    private String authorId;
    @Column(name = "author_username")
    private String authorUsername;
    @Column(name = "date")
    private Date date;

    public StepByStep() {
    }

    public StepByStep(Integer patternId, String authorId, String authorUsername, Date date) {
        this.patternId = patternId;
        this.authorId = authorId;
        this.authorUsername = authorUsername;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
