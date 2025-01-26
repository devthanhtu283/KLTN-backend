package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Accessors(chain = true)
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

    @NotNull
    @Column(name = "application_id", nullable = false)
    public Integer getApplicationId() {
        return applicationId;
    }

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "scheduled_at", nullable = false)
    public Instant getScheduledAt() {
        return scheduledAt;
    }

    @NotNull
    @Lob
    @Column(name = "interview_link", nullable = false)
    public String getInterviewLink() {
        return interviewLink;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Integer getStatus() {
        return status;
    }

}