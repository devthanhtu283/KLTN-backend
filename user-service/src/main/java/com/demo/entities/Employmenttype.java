package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "employmenttype", schema = "jobs")
public class Employmenttype implements Serializable {
    private static final long serialVersionUID = 4191848851817333539L;
    private Integer id;

    private String name;

    private Boolean status = false;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public Employmenttype setId(Integer id) {
        this.id = id;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public Employmenttype setName(String name) {
        this.name = name;
        return this;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

    public Employmenttype setStatus(Boolean status) {
        this.status = status;
        return this;
    }

}