package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "interview", schema = "jobs")
public class Interview implements Serializable {
    private static final long serialVersionUID = 3313652690033752270L;
    private Integer id;

    private Integer applicationId;

    private Instant scheduledAt;

    private String interviewLink;

    private Integer status;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public Interview setId(Integer id) {
        this.id = id;
        return this;
    }

    @NotNull
    @Column(name = "application_id", nullable = false)
    public Integer getApplicationId() {
        return applicationId;
    }

    public Interview setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
        return this;
    }

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "scheduled_at", nullable = false)
    public Instant getScheduledAt() {
        return scheduledAt;
    }

    public Interview setScheduledAt(Instant scheduledAt) {
        this.scheduledAt = scheduledAt;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "interview_link", nullable = false)
    public String getInterviewLink() {
        return interviewLink;
    }

    public Interview setInterviewLink(String interviewLink) {
        this.interviewLink = interviewLink;
        return this;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Integer getStatus() {
        return status;
    }

    public Interview setStatus(Integer status) {
        this.status = status;
        return this;
    }

}