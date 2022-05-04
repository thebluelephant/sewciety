package com.sewciety.backend.entity;
import javax.persistence.*;

@Entity
@Table(name = "sbs_images")
public class SbsImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "image")
    private byte[] image;
    @Column(name = "step_id")
    private Integer stepId;
    @Column(name = "sbs_id")
    private Integer sbsId;

    public SbsImage() {
    }

    public SbsImage(byte[] image, Integer stepId, Integer sbsId) {
        this.image = image;
        this.stepId = stepId;
        this.sbsId = sbsId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Integer getStepId() {
        return stepId;
    }

    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }

    public Integer getSbsId() {
        return sbsId;
    }

    public void setSbsId(Integer sbsId) {
        this.sbsId = sbsId;
    }
}
