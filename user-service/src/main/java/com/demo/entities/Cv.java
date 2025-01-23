package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;

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

    public Cv setId(Integer id) {
        this.id = id;
        return this;
    }

    @NotNull
    @Column(name = "seeker_id", nullable = false)
    public Integer getSeekerId() {
        return seekerId;
    }

    public Cv setSeekerId(Integer seekerId) {
        this.seekerId = seekerId;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public Cv setName(String name) {
        this.name = name;
        return this;
    }

    @Lob
    @Column(name = "skills")
    public String getSkills() {
        return skills;
    }

    public Cv setSkills(String skills) {
        this.skills = skills;
        return this;
    }

    @Lob
    @Column(name = "experience")
    public String getExperience() {
        return experience;
    }

    public Cv setExperience(String experience) {
        this.experience = experience;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "type", nullable = false)
    public String getType() {
        return type;
    }

    public Cv setType(String type) {
        this.type = type;
        return this;
    }

    @Lob
    @Column(name = "education")
    public String getEducation() {
        return education;
    }

    public Cv setEducation(String education) {
        this.education = education;
        return this;
    }

    @Lob
    @Column(name = "certification")
    public String getCertification() {
        return certification;
    }

    public Cv setCertification(String certification) {
        this.certification = certification;
        return this;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

    public Cv setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    @Lob
    @Column(name = "offer_salary")
    public String getOfferSalary() {
        return offerSalary;
    }

    public Cv setOfferSalary(String offerSalary) {
        this.offerSalary = offerSalary;
        return this;
    }

    @Lob
    @Column(name = "job_deadline")
    public String getJobDeadline() {
        return jobDeadline;
    }

    public Cv setJobDeadline(String jobDeadline) {
        this.jobDeadline = jobDeadline;
        return this;
    }

    @Lob
    @Column(name = "linked_in")
    public String getLinkedIn() {
        return linkedIn;
    }

    public Cv setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
        return this;
    }

    @Lob
    @Column(name = "link_git")
    public String getLinkGit() {
        return linkGit;
    }

    public Cv setLinkGit(String linkGit) {
        this.linkGit = linkGit;
        return this;
    }

    @NotNull
    @Column(name = "upload_at", nullable = false)
    public Instant getUploadAt() {
        return uploadAt;
    }

    public Cv setUploadAt(Instant uploadAt) {
        this.uploadAt = uploadAt;
        return this;
    }

}