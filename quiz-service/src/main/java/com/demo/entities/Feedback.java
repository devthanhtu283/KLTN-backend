package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;

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

    public Feedback setId(Integer id) {
        this.id = id;
        return this;
    }

    @NotNull
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public Feedback setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "content", nullable = false)
    public String getContent() {
        return content;
    }

    public Feedback setContent(String content) {
        this.content = content;
        return this;
    }

    @NotNull
    @Column(name = "created_at", nullable = false)
    public Instant getCreatedAt() {
        return createdAt;
    }

    public Feedback setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

    public Feedback setStatus(Boolean status) {
        this.status = status;
        return this;
    }

}