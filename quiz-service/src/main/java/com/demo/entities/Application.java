package com.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    @NotNull
    @Column(name = "job_id", nullable = false)
    public Integer getJobId() {
        return jobId;
    }

    @NotNull
    @Column(name = "seeker_id", nullable = false)
    public Integer getSeekerId() {
        return seekerId;
    }

    @NotNull
    @Column(name = "applied_at", nullable = false)
    public Instant getAppliedAt() {
        return appliedAt;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Integer getStatus() {
        return status;
    }

}