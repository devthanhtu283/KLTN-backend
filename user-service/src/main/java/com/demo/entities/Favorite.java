package com.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "favorite", schema = "jobs")
public class Favorite implements Serializable {
    private static final long serialVersionUID = 2232521212022821982L;
    private Integer id;

    private Integer seekerId;

    private Integer jobId;

    private Boolean status = false;

    private LocalDate created;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public Favorite setId(Integer id) {
        this.id = id;
        return this;
    }

    @NotNull
    @Column(name = "seeker_id", nullable = false)
    public Integer getSeekerId() {
        return seekerId;
    }

    public Favorite setSeekerId(Integer seekerId) {
        this.seekerId = seekerId;
        return this;
    }

    @NotNull
    @Column(name = "job_id", nullable = false)
    public Integer getJobId() {
        return jobId;
    }

    public Favorite setJobId(Integer jobId) {
        this.jobId = jobId;
        return this;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

    public Favorite setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    @NotNull
    @Column(name = "created", nullable = false)
    public LocalDate getCreated() {
        return created;
    }

    public Favorite setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

}