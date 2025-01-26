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
@Table(name = "feedback", schema = "jobs")
public class Feedback implements Serializable {
    private static final long serialVersionUID = -8885001312115168140L;
    private Integer id;

    private Integer userId;

    private String content;

    private Instant createdAt;

    private Boolean status = false;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    @NotNull
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    @NotNull
    @Lob
    @Column(name = "content", nullable = false)
    public String getContent() {
        return content;
    }

    @NotNull
    @Column(name = "created_at", nullable = false)
    public Instant getCreatedAt() {
        return createdAt;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

}