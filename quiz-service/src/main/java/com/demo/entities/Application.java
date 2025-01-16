package com.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "application", schema = "jobs")
public class Application implements Serializable {
    private static final long serialVersionUID = 1001390878747989563L;
    private Integer id;

    private Integer jobId;

    private Integer seekerId;

    private Instant appliedAt;

    private Integer status;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public Application setId(Integer id) {
        this.id = id;
        return this;
    }

    @NotNull
    @Column(name = "job_id", nullable = false)
    public Integer getJobId() {
        return jobId;
    }

    public Application setJobId(Integer jobId) {
        this.jobId = jobId;
        return this;
    }

    @NotNull
    @Column(name = "seeker_id", nullable = false)
    public Integer getSeekerId() {
        return seekerId;
    }

    public Application setSeekerId(Integer seekerId) {
        this.seekerId = seekerId;
        return this;
    }

    @NotNull
    @Column(name = "applied_at", nullable = false)
    public Instant getAppliedAt() {
        return appliedAt;
    }

    public Application setAppliedAt(Instant appliedAt) {
        this.appliedAt = appliedAt;
        return this;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Integer getStatus() {
        return status;
    }

    public Application setStatus(Integer status) {
        this.status = status;
        return this;
    }

}