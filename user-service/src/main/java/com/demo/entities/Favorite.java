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
import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain = true)
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

    @NotNull
    @Column(name = "seeker_id", nullable = false)
    public Integer getSeekerId() {
        return seekerId;
    }

    @NotNull
    @Column(name = "job_id", nullable = false)
    public Integer getJobId() {
        return jobId;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

    @NotNull
    @Column(name = "created", nullable = false)
    public LocalDate getCreated() {
        return created;
    }

}