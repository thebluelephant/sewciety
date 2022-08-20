package com.sewciety.backend.entity;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "pattern")
@EntityListeners(AuditingEntityListener.class)
public class Pattern {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "brand")
    private String brand;
    @Column(name = "description")
    private String description;
    @Column(name = "difficulty_level")
    private Integer difficulty_level;
    @Column(name = "image")
    private String image;
    @Column(name = "name")
    private String name;
    @Column(name = "url", nullable = true)
    private String url;
    @Column(name = "verified", nullable = true)
    private Boolean verified;

    public Pattern() {
    }

    public Pattern(String brand, String description, Integer difficulty_level, String name, String image, String url,
            Boolean verified) {
        this.brand = brand;
        this.description = description;
        this.difficulty_level = difficulty_level;
        this.image = image;
        this.name = name;
        this.url = url;
        this.verified = verified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDifficulty() {
        return difficulty_level;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty_level = difficulty;
    }

    public String getimage() {
        return image;
    }

    public void setimage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

}
