package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "cv", schema = "jobs")
public class Cv implements Serializable {
    private static final long serialVersionUID = -1793156804197389166L;
    private Integer id;

    private Integer seekerId;

    private String name;

    private String skills;

    private String experience;

    private String type;

    private String education;

    private String certification;

    private Boolean status = false;

    private String offerSalary;

    private String jobDeadline;

    private String linkedIn;

    private String linkGit;

    private Instant uploadAt;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    @NotNull
    @Column(name = "seeker_id", nullable = false)
    public Integer getSeekerId() {
        return seekerId;
    }

    @NotNull
    @Lob
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    @Lob
    @Column(name = "skills")
    public String getSkills() {
        return skills;
    }

    @Lob
    @Column(name = "experience")
    public String getExperience() {
        return experience;
    }

    @NotNull
    @Lob
    @Column(name = "type", nullable = false)
    public String getType() {
        return type;
    }

    @Lob
    @Column(name = "education")
    public String getEducation() {
        return education;
    }

    @Lob
    @Column(name = "certification")
    public String getCertification() {
        return certification;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

    @Lob
    @Column(name = "offer_salary")
    public String getOfferSalary() {
        return offerSalary;
    }

    @Lob
    @Column(name = "job_deadline")
    public String getJobDeadline() {
        return jobDeadline;
    }

    @Lob
    @Column(name = "linked_in")
    public String getLinkedIn() {
        return linkedIn;
    }

    @Lob
    @Column(name = "link_git")
    public String getLinkGit() {
        return linkGit;
    }

    @NotNull
    @Column(name = "upload_at", nullable = false)
    public Instant getUploadAt() {
        return uploadAt;
    }

}