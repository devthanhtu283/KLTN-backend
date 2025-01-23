package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "image", schema = "jobs")
public class Image implements Serializable {
    private static final long serialVersionUID = -4238223986211622091L;
    private Integer id;

    private String name;

    private Integer userId;

    private Integer userType;

    private Boolean status = false;

    private LocalDate created;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public Image setId(Integer id) {
        this.id = id;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public Image setName(String name) {
        this.name = name;
        return this;
    }

    @NotNull
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public Image setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    @NotNull
    @Column(name = "user_type", nullable = false)
    public Integer getUserType() {
        return userType;
    }

    public Image setUserType(Integer userType) {
        this.userType = userType;
        return this;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

    public Image setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    @NotNull
    @Column(name = "created", nullable = false)
    public LocalDate getCreated() {
        return created;
    }

    public Image setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

}