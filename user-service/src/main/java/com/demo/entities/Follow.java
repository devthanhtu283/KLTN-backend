package com.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "follow", schema = "jobs")
public class Follow implements Serializable {
    private static final long serialVersionUID = 24185709766460774L;
    private Integer id;

    private Integer employerId;

    private Integer seekerId;

    private Boolean status = false;

    private LocalDate created;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public Follow setId(Integer id) {
        this.id = id;
        return this;
    }

    @NotNull
    @Column(name = "employer_id", nullable = false)
    public Integer getEmployerId() {
        return employerId;
    }

    public Follow setEmployerId(Integer employerId) {
        this.employerId = employerId;
        return this;
    }

    @NotNull
    @Column(name = "seeker_id", nullable = false)
    public Integer getSeekerId() {
        return seekerId;
    }

    public Follow setSeekerId(Integer seekerId) {
        this.seekerId = seekerId;
        return this;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

    public Follow setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    @NotNull
    @Column(name = "created", nullable = false)
    public LocalDate getCreated() {
        return created;
    }

    public Follow setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

}