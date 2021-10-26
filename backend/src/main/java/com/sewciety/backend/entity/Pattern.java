package com.sewciety.backend.entity;

import javax.persistence.*;

import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;
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
    @Column(name = "image_url")
    private String image_url;
    @Column(name = "name")
    private String name;
    @Column(name = "url")
    private String url;

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

    public String getImageUrl() {
        return image_url;
    }

    public void setImageUrl(String imageUrl) {
        this.image_url = imageUrl;
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

}
