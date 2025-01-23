package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "matches", schema = "jobs")
public class Match implements Serializable {
    private static final long serialVersionUID = -2679790838351519642L;
    private Integer id;

    private Integer cvId;

    private Integer jobId;

    private String matchedSkill;

    private Instant timeMatches;

    private Boolean status = false;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public Match setId(Integer id) {
        this.id = id;
        return this;
    }

    @NotNull
    @Column(name = "cv_id", nullable = false)
    public Integer getCvId() {
        return cvId;
    }

    public Match setCvId(Integer cvId) {
        this.cvId = cvId;
        return this;
    }

    @NotNull
    @Column(name = "job_id", nullable = false)
    public Integer getJobId() {
        return jobId;
    }

    public Match setJobId(Integer jobId) {
        this.jobId = jobId;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "matched_skill", nullable = false)
    public String getMatchedSkill() {
        return matchedSkill;
    }

    public Match setMatchedSkill(String matchedSkill) {
        this.matchedSkill = matchedSkill;
        return this;
    }

    @NotNull
    @Column(name = "time_matches", nullable = false)
    public Instant getTimeMatches() {
        return timeMatches;
    }

    public Match setTimeMatches(Instant timeMatches) {
        this.timeMatches = timeMatches;
        return this;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

    public Match setStatus(Boolean status) {
        this.status = status;
        return this;
    }

}