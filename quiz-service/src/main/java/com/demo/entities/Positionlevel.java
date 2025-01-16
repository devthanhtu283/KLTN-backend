package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "positionlevel", schema = "jobs")
public class Positionlevel implements Serializable {
    private static final long serialVersionUID = 6854134418550870512L;
    private Integer id;

    private String name;

    private Boolean status = false;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public Positionlevel setId(Integer id) {
        this.id = id;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public Positionlevel setName(String name) {
        this.name = name;
        return this;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

    public Positionlevel setStatus(Boolean status) {
        this.status = status;
        return this;
    }

}