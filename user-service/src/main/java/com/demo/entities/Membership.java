package com.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "membership", schema = "jobs")
public class Membership implements Serializable {
    private static final long serialVersionUID = 3716896064358012161L;
    private Integer id;

    private String name;

    private Double price;

    private String description;

    private String duration;

    private Boolean status = false;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public Membership setId(Integer id) {
        this.id = id;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public Membership setName(String name) {
        this.name = name;
        return this;
    }

    @NotNull
    @Column(name = "price", nullable = false)
    public Double getPrice() {
        return price;
    }

    public Membership setPrice(Double price) {
        this.price = price;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public Membership setDescription(String description) {
        this.description = description;
        return this;
    }

    @NotNull
    @Lob
    @Column(name = "duration", nullable = false)
    public String getDuration() {
        return duration;
    }

    public Membership setDuration(String duration) {
        this.duration = duration;
        return this;
    }

    @NotNull
    @Column(name = "status", nullable = false)
    public Boolean getStatus() {
        return status;
    }

    public Membership setStatus(Boolean status) {
        this.status = status;
        return this;
    }

}