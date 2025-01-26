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

    @NotNull
    @Column(name = "cv_id", nullable = false)
    public Integer getCvId() {
        return cvId;
    }

    @NotNull
    @Column(name = "job_id", nullable = false)
    public Integer getJobId() {
        return jobId;
    }

    @NotNull
    @Lob
    @Column(name = "matched_skill", nullable = false)
    public String getMatchedSkill() {
        return matchedSkill;
    }

    @NotNull
    @Column(name = "time_matches", nullable = false)
    public Instant getTimeMatches() {
        return timeMatches;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

}